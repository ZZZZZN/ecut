package com.krt.admin.system.controller;

import com.krt.admin.system.service.LoginService;
import com.krt.core.annotation.LogAop;
import com.krt.core.base.BaseController;
import com.krt.core.bean.ReturnBean;
import com.krt.core.util.ShiroUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

/**
 * @version 1.0
 * @Description: 系统登录控制层
 * @date 2016年7月15日
 */
@Controller
public class LoginController extends BaseController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private LoginService loginService;

    /**
     * 登陆页
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "admin/loginUI")
    public String loginUI(HttpServletRequest request, HttpServletResponse response) {
        // 判断session是否登录成功
        Map user = ShiroUtil.getCurrentUser();
        if (user != null) {
            return "redirect:index";
        } else {
            boolean isAjax = false;
            Enumeration<String> values = request.getHeaders("X-Requested-With");
            while (values.hasMoreElements()) {
                String value = values.nextElement();
                if ("XMLHttpRequest".equalsIgnoreCase(value)) {
                    isAjax = true;
                    break;
                }
            }
            if (isAjax) {
                response.setHeader("Session-Status", "timeout");
            }
            return "admin/loginUI";
        }
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @LogAop(description = "后台登录", type = "0")
    @RequestMapping(value = "admin/login")
    @ResponseBody
    public ReturnBean login(String username, String password, String code,
                            HttpServletRequest request) {
        ReturnBean rb;
        try {
            // 判断验证码
            HttpSession session = request.getSession();
            String sessionCode = (String) session.getAttribute("imgCode");
            if (sessionCode != null && sessionCode.equalsIgnoreCase(code)) {
                // 密码AES加密
                //password = AESvbjavajs.getAESEncrypt(password, Constant.PASS_KEY);
                // shiro的登录
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                Subject currentUser = SecurityUtils.getSubject();
                currentUser.login(token); // 失败 会抛出异常
                rb = ReturnBean.getSuccessReturnBean();
                // 清除验证码
                session.removeAttribute("imgCode");
            } else {
                rb = ReturnBean.getCustomReturnBean("code_error");
            }
        } catch (Exception e) {
            logger.debug("登录失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 退出
     */
    @RequestMapping(value = "admin/logout")
    public String logout() {
        ShiroUtil.logOut();
        return "admin/loginUI";
    }

}
