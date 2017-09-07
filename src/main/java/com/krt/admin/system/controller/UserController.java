package com.krt.admin.system.controller;

import com.krt.admin.system.entity.User;
import com.krt.admin.system.service.RoleService;
import com.krt.admin.system.service.UserService;
import com.krt.core.annotation.LogAop;
import com.krt.core.base.BaseController;
import com.krt.core.bean.DataTable;
import com.krt.core.bean.ReturnBean;
import com.krt.core.config.Constant;
import com.krt.core.util.AESvbjavajs;
import com.krt.core.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 用户管理控制层
 * @date 2016年7月16日
 */
@Controller
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;


    /**
     * 用户管理页
     *
     * @return
     */
    @RequiresPermissions("user:list")
    @RequestMapping("admin/system/user/user_listUI")
    public String user_listUI() {
        return "admin/system/user/user_listUI";
    }

    /**
     * 用户管理
     *
     * @param start   起始数
     * @param length  每页显示数量
     * @param draw    客户端请求次数
     * @param request
     * @return
     */
    @RequiresPermissions("user:list")
    @RequestMapping("admin/system/user/user_list")
    @ResponseBody
    public DataTable user_list(Integer start, Integer length, Integer draw, HttpServletRequest request) {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String roleName = request.getParameter("roleName");
        Map para = new HashMap();
        para.put("username", username);
        para.put("name", name);
        para.put("roleName", roleName);
        DataTable dt = userService.selectListPara(start, length, draw, para);
        return dt;
    }

    /**
     * 新增用户页
     *
     * @return
     */
    @RequiresPermissions("user:insert")
    @RequestMapping("admin/system/user/user_insertUI")
    public String user_insertUI(HttpServletRequest request) {
        List roleList = roleService.selectAll();
        request.setAttribute("roleList", roleList);
        return "admin/system/user/user_insertUI";
    }

    /**
     * 添加
     *
     * @param user 用户
     * @return
     */
    @LogAop(description = "添加用户")
    @RequiresPermissions("user:insert")
    @RequestMapping("admin/system/user/user_insert")
    @ResponseBody
    public ReturnBean user_insert(User user) {
        ReturnBean rb;
        try {
            user.setStatus("0");
            String password = user.getPassword();
            // 密码加密
            password = AESvbjavajs.getAESEncrypt(password, Constant.PASS_KEY);
            user.setPassword(password);
            userService.insert(user);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("添加用户失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 修改用户信息
     *
     * @param id      用户id
     * @param request
     * @return
     */
    @RequiresPermissions("user:update")
    @RequestMapping("admin/system/user/user_updateUI")
    public String user_updateUI(Integer id, HttpServletRequest request) {
        List roleList = roleService.selectAll();
        request.setAttribute("roleList", roleList);
        Map userMap = userService.selectById(id);
        request.setAttribute("user", userMap);
        return "admin/system/user/user_updateUI";
    }

    /**
     * 修改用户
     *
     * @param user 用户
     * @return
     */
    @LogAop(description = "修改用户")
    @RequiresPermissions("user:update")
    @RequestMapping("admin/system/user/user_update")
    @ResponseBody
    public ReturnBean user_update(User user) {
        ReturnBean rb;
        try {
            String password = user.getPassword();
            if (password == null || "".equals(password)) {// 保持原密码不变
                Map userMap = userService.selectById(user.getId());
                password = userMap.get("password") + "";
            } else {
                // 密码加密
                password = AESvbjavajs.getAESEncrypt(password, Constant.PASS_KEY);
            }
            user.setPassword(password);
            userService.update(user);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("修改用户失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return
     */
    @LogAop(description = "删除用户")
    @RequiresPermissions("user:delete")
    @RequestMapping("admin/system/user/user_delete")
    @ResponseBody
    public ReturnBean user_delete(Integer id) {
        ReturnBean rb;
        try {
            userService.delete(id);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("删除用户失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 禁用||启用用户
     *
     * @param id 用户id
     * @return
     */
    @LogAop(description = "禁用||启用用户")
    @RequiresPermissions("user:cancel")
    @RequestMapping("admin/system/user/user_cancel")
    @ResponseBody
    public ReturnBean user_cancel(Integer id, String status) {
        ReturnBean rb;
        try {
            User user = new User();
            user.setId(id);
            user.setStatus(status);
            userService.update(user);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("删除用户失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 查看用户信息
     *
     * @param id      用户id
     * @param request
     * @return
     */
    @RequiresPermissions("user:see")
    @RequestMapping("admin/system/user/user_seeUI")
    public String user_seeUI(Integer id, HttpServletRequest request) {
        List roleList = roleService.selectAll();
        request.setAttribute("roleList", roleList);
        Map userMap = userService.selectById(id);
        request.setAttribute("user", userMap);
        return "admin/system/user/user_seeUI";
    }

    /**
     * 检测用户名
     *
     * @param username 用户名
     * @param id       用户id
     * @return
     */
    @RequestMapping("admin/system/user/checkUsername")
    @ResponseBody
    public Boolean checkUsername(String username, Integer id) {
        Integer count = userService.checkUsername(username, id);
        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 修改密码页
     *
     * @return
     */
    @RequestMapping("admin/system/user/user_updatePswUI")
    public String user_updatePswUI() {
        return "admin/system/user/user_updatePswUI";
    }

    /**
     * 修改密码
     *
     * @param request
     * @return
     */
    @LogAop(description = "修改密码")
    @RequestMapping("admin/system/user/user_updatePsw")
    @ResponseBody
    public ReturnBean user_updatePsw(HttpServletRequest request) {
        ReturnBean rb;
        try {
            String password = request.getParameter("password");
            Map currentUser = ShiroUtil.getCurrentUser();
            Integer id = Integer.valueOf(currentUser.get("id") + "");
            User user = new User();
            user.setId(id);
            // 密码加密
            password = AESvbjavajs.getAESEncrypt(password, Constant.PASS_KEY);
            user.setPassword(password);
            userService.update(user);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("修改密码失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 检测用户密码
     *
     * @param oldPassword
     * @return
     */
    @RequestMapping("admin/system/user/checkPsw")
    @ResponseBody
    public boolean checkPsw(String oldPassword) {
        try {
            Map currentUser = ShiroUtil.getCurrentUser();
            String passwordRe = currentUser.get("password") + "";
            // 密码加密
            oldPassword = AESvbjavajs.getAESEncrypt(oldPassword, Constant.PASS_KEY);
            if (passwordRe.equals(oldPassword)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("检测密码异常", e);
            return false;
        }
    }
}
