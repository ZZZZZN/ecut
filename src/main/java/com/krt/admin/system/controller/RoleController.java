package com.krt.admin.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.krt.admin.system.entity.Role;
import com.krt.admin.system.service.RoleService;
import com.krt.core.annotation.LogAop;
import com.krt.core.base.BaseController;
import com.krt.core.bean.DataTable;
import com.krt.core.bean.ReturnBean;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Description: 角色管理控制层
 * @date 2016年7月16日
 */
@SuppressWarnings({"rawtypes"})
@Controller
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;

    /**
     * 角色管理页
     *
     * @return
     */
    @RequiresPermissions("role:list")
    @RequestMapping("admin/system/role/role_listUI")
    public String role_listUI() {
        return "admin/system/role/role_listUI";
    }

    /**
     * 角色管理
     *
     * @param start
     * @param length
     * @param draw
     * @param request
     * @return
     */
    @RequiresPermissions("role:list")
    @RequestMapping("admin/system/role/role_list")
    @ResponseBody
    public DataTable role_list(Integer start, Integer length, Integer draw, HttpServletRequest request) {
        Map para = new HashMap();
        DataTable dt = roleService.selectListPara(start, length, draw, para);
        return dt;
    }

    /**
     * 添加角色页
     *
     * @return
     */
    @RequiresPermissions("role:insert")
    @RequestMapping("admin/system/role/role_insertUI")
    public String role_insertUI() {
        return "admin/system/role/role_insertUI";
    }

    /**
     * 添加角色
     *
     * @param role 角色
     * @return
     */
    @LogAop(description = "添加角色")
    @RequiresPermissions("role:insert")
    @RequestMapping("admin/system/role/role_insert")
    @ResponseBody
    public ReturnBean role_insert(Role role) {
        ReturnBean rb;
        try {
            roleService.insert(role);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("添加角色失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 修改角色信息
     *
     * @param id      角色id
     * @param request
     * @return
     */
    @RequiresPermissions("role:update")
    @RequestMapping("admin/system/role/role_updateUI")
    public String role_updateUI(Integer id, HttpServletRequest request) {
        Map roleMap = roleService.selectById(id);
        request.setAttribute("role", roleMap);
        return "admin/system/role/role_updateUI";
    }

    /**
     * 修改角色
     *
     * @param role 角色
     * @return
     */
    @LogAop(description = "修改角色")
    @RequiresPermissions("role:update")
    @RequestMapping("admin/system/role/role_update")
    @ResponseBody
    public ReturnBean role_update(Role role) {
        ReturnBean rb;
        try {
            roleService.update(role);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("修改角色失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return
     */
    @LogAop(description = "删除角色")
    @RequiresPermissions("role:delete")
    @RequestMapping("admin/system/role/role_delete")
    @ResponseBody
    public ReturnBean role_delete(Integer id, String roleCode) {
        ReturnBean rb;
        try {
            roleService.delete(id, roleCode);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("删除角色失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 查看角色信息
     *
     * @param id      角色id
     * @param request
     * @return
     */
    @RequiresPermissions("role:see")
    @RequestMapping("admin/system/role/role_seeUI")
    public String role_seeUI(Integer id, HttpServletRequest request) {
        Map roleMap = roleService.selectById(id);
        request.setAttribute("role", roleMap);
        return "admin/system/role/role_seeUI";
    }

    /**
     * 检测角色名
     *
     * @param roleName 角色名
     * @param id       角色id
     * @return
     */
    @RequestMapping("admin/system/role/checkRoleName")
    @ResponseBody
    public Boolean checkRoleName(String roleName, Integer id) {
        Integer count = roleService.checkRoleName(roleName, id);
        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检测角色编码
     *
     * @param roleCode 角色编码
     * @param id       角色id
     * @return
     */
    @RequestMapping("admin/system/role/checkRoleCode")
    @ResponseBody
    public Boolean checkRoleCode(String roleCode, Integer id) {
        Integer count = roleService.checkRoleCode(roleCode, id);
        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 权限设置树形页
     *
     * @param roleCode 角色编码
     * @return
     */
    @RequiresPermissions("role:res")
    @RequestMapping("admin/system/role/role_resTreeUI")
    public String role_resTreeUI(String roleCode, HttpServletRequest request) {
        JSONArray resTree = roleService.selectRoleResTree(roleCode);
        request.setAttribute("resTree", resTree);
        return "admin/system/role/role_resTreeUI";
    }

    /**
     * 权限设置
     *
     * @param roleCode 角色编码
     * @param res_ids  资源ids
     * @return
     */
    @LogAop(description = "权限设置")
    @RequiresPermissions("role:res")
    @RequestMapping("admin/system/role/role_res")
    @ResponseBody
    public ReturnBean role_res(String roleCode, String res_ids) {
        ReturnBean rb;
        try {
            roleService.updateRoleRes(roleCode, res_ids);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("权限设置失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }
}
