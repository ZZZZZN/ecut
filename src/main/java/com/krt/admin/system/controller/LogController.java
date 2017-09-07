package com.krt.admin.system.controller;

import com.krt.admin.system.service.LogService;
import com.krt.core.annotation.LogAop;
import com.krt.core.base.BaseController;
import com.krt.core.bean.DataTable;
import com.krt.core.bean.ReturnBean;
import com.krt.core.util.Common;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 日志管理控制层
 * @date 2016年7月21日
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Controller
public class LogController extends BaseController {

    @Resource
    private LogService logService;

    /**
     * 日志管理页
     *
     * @return
     */
    @RequiresPermissions("log:list")
    @RequestMapping("admin/system/log/log_listUI")
    public String log_listUI() {
        return "admin/system/log/log_listUI";
    }

    /**
     * 日志管理
     *
     * @param start
     * @param length
     * @param draw
     * @param request
     * @return
     */
    @RequiresPermissions("log:list")
    @RequestMapping("admin/system/log/log_list")
    @ResponseBody
    public DataTable log_list(Integer start, Integer length, Integer draw, HttpServletRequest request) {
        String username = Common.toUTF(request.getParameter("username"));
        String description = Common.toUTF(request.getParameter("description"));
        String type = Common.toUTF(request.getParameter("type"));
        Map para = new HashMap();
        para.put("username", username);
        para.put("description", description);
        para.put("type", type);
        DataTable dt = logService.selectListPara(start, length, draw, para);
        return dt;
    }

    /**
     * 清空日志
     *
     * @return
     */
    @LogAop(description = "清空日志")
    @RequiresPermissions("log:deleteAll")
    @RequestMapping("admin/system/log/log_deleteAll")
    @ResponseBody
    public ReturnBean log_deleteAll() {
        ReturnBean rb;
        try {
            logService.deleteAll();
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            rb = ReturnBean.getErrorReturnBean();
            logger.error("清空日志失败", e);
        }
        return rb;
    }

}
