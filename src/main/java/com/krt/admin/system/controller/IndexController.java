package com.krt.admin.system.controller;

import com.krt.admin.system.service.RoleService;
import com.krt.core.base.BaseController;
import com.krt.core.util.ShiroUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 殷帅
 * @version 1.0
 * @Description:后台首页控制层
 * @date 2016年7月16日
 */
@SuppressWarnings("rawtypes")
@Controller
public class IndexController extends BaseController {

    @Resource
    private RoleService roleService;

    /**
     * 后台管理页
     *
     * @return
     */
    @RequestMapping("admin/index")
    public String index() {
        return "admin/index";
    }

    /**
     * 用户登录成功 获取URL菜单
     *
     * @return
     */
    @RequestMapping("admin/selectRoleRes")
    @ResponseBody
    public List selectRoleRes() {
        Map currentUser = ShiroUtil.getCurrentUser();
        String roleCode = currentUser.get("roleCode") + "";
        List resList = roleService.selectRoleUrlRes(roleCode, 0);
        return resList;
    }
}
