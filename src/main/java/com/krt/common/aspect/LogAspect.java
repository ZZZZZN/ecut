package com.krt.common.aspect;

import com.krt.admin.system.entity.Log;
import com.krt.admin.system.service.LogService;
import com.krt.common.annotation.LogAop;
import com.krt.common.bean.ReturnBean;
import com.krt.common.util.ShiroUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 切面日志
 * @date 2016年7月21日
 */
@SuppressWarnings("rawtypes")
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private LogService logService;
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间
    private long userTimeMillis = 0; // 耗时

    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.krt.common.annotation.LogAop)")
    public void logAspect() {

    }

    @Before("logAspect()")
    public void doBefore(JoinPoint joinPoint) {
        startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
    }

    @After("logAspect()")
    public void doAfter(JoinPoint joinPoint) {
        endTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
        userTimeMillis = endTimeMillis - startTimeMillis;
        if (userTimeMillis <= 1000) {
            //执行时间小于1秒
            logger.debug("执行" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()" + "耗时============>" + userTimeMillis + "毫秒");
        } else if (userTimeMillis <= 3000) {
            //执行时间小于3秒
            logger.info("执行" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()" + "耗时============>" + userTimeMillis + "毫秒");
        } else {
            //执行时间大于3秒
            logger.warn("执行" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()" + "耗时============>" + userTimeMillis + "毫秒");
        }
    }

    @AfterReturning(pointcut = "logAspect()", returning = "returnValue")
    public void log(JoinPoint joinPoint, Object returnValue) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 读取session中的用户
        Map currentUser = (Map) ShiroUtil.getCurrentUser();
        // 请求的IP
        String ip = request.getRemoteAddr();
        try {
            // 不是登录日志
            Log log = getAopLog(joinPoint);
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.setRequestIp(ip);
            log.setParams(null);
            if (currentUser != null) {
                log.setUsername(currentUser.get("username") + "");
            }
            if (log.getType().equals("0")) {
                // 登录日志 参数不保存密码
                log.setParams(joinPoint.getArgs()[0].toString());
                ReturnBean rb = (ReturnBean) returnValue;
                if (rb.state.equals("success")) {
                    // 登录成功记录日志
                    // 保存数据库
                    logService.insert(log);
                }
            } else {
                // 操作日志
                log.setParams(Arrays.toString(joinPoint.getArgs()));
                // 保存数据库
                logService.insert(log);
            }
        } catch (Exception e) {
            // 记录本地异常日志
            logger.error("aop保存日志异常", e);
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static Log getAopLog(JoinPoint joinPoint) throws Exception {
        Log log = new Log();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    String description = method.getAnnotation(LogAop.class).description();
                    String type = method.getAnnotation(LogAop.class).type();
                    log.setDescription(description);
                    log.setType(type);
                    break;
                }
            }
        }
        return log;
    }
}