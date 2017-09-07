package com.krt.admin.system.controller;

import com.krt.core.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 异常页控制层
 * @date 2016年7月22日
 */
@Controller
public class ErrorController extends BaseController {

    /**
     * 没有权限
     *
     * @return
     */
    @RequestMapping("error/noPermission")
    public String error_noPermission() {
        return "error/noPermission";
    }

    /**
     * 500异常
     *
     * @return
     */
    @RequestMapping("error/500")
    public String error_500() {
        return "error/500";
    }

    /**
     * 500异常
     *
     * @return
     */
    @RequestMapping("error/404")
    public String error_404() {
        return "error/404";
    }
}
