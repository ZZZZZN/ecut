package com.krt.admin.system.controller;

import com.krt.admin.system.entity.Res;
import com.krt.admin.system.service.ResService;
import com.krt.admin.system.util.ResUtil;
import com.krt.core.annotation.LogAop;
import com.krt.core.base.BaseController;
import com.krt.core.bean.ReturnBean;
import net.sf.json.JSONArray;
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
 * @version 1.0
 * @Description: 资源管理控制层
 * @date 2016年7月19日
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Controller
public class ResController extends BaseController {

    @Resource
    private ResService resService;
    @Resource
    private ResUtil resUtil;

    /**
     * 资源管理
     *
     * @param request
     * @return
     */
    @RequiresPermissions("res:list")
    @RequestMapping("admin/system/res/res_listUI")
    public String res_listUI(HttpServletRequest request) {
        return "admin/system/res/res_listUI";
    }

    /**
     * 资源管理
     *
     * @return
     */
    @RequiresPermissions("res:list")
    @RequestMapping("admin/system/res/res_list")
    @ResponseBody
    public List res_list(Integer pid) {
        Map para = new HashMap();
        para.put("pid", pid);
        List list = resService.selectListByPid(para);
        return list;
    }

    /**
     * 资源树
     *
     * @return
     */
    @RequestMapping("admin/system/res/res_tree")
    @ResponseBody
    public JSONArray res_tree() {
        List rlist = resService.selectAllTree();
        JSONArray resTree = JSONArray.fromObject(rlist);
        return resTree;
    }


    /**
     * 获取子集
     *
     * @param pid
     * @return
     */
    @RequestMapping("admin/system/res/res_child")
    @ResponseBody
    public List res_child(String pid) {
        Map para = new HashMap();
        para.put("pid", pid);
        List list = resService.selectListByPid(para);
        return list;
    }

    /**
     * 添加资源页
     *
     * @return
     */
    @RequiresPermissions("res:insert")
    @RequestMapping("admin/system/res/res_insertUI")
    public String res_insertUI(HttpServletRequest request, Integer id) {
        if (id != null) {
            Map resMap = resService.selectById(id);
            request.setAttribute("res", resMap);
        }
        return "admin/system/res/res_insertUI";
    }

    /**
     * 资源ICON页
     *
     * @return
     */
    @RequestMapping("admin/system/res/res_iconUI")
    public String res_iconUI(String value) {
        return "admin/system/res/res_iconUI";
    }

    /**
     * 获取资源树
     *
     * @return
     */
    @RequestMapping("admin/system/res/res_treeUI")
    public String res_treeUI(HttpServletRequest request) {
        List list = resService.selectAllTree();
        JSONArray resTree = JSONArray.fromObject(list);
        request.setAttribute("resTree", resTree);
        return "admin/system/res/res_treeUI";
    }

    /**
     * 添加资源
     *
     * @param res 资源实体
     * @return
     */
    @LogAop(description = "添加资源")
    @RequiresPermissions("res:insert")
    @RequestMapping("admin/system/res/res_insert")
    @ResponseBody
    public ReturnBean res_insert(Res res) {
        ReturnBean rb;
        try {
            if (res.getPid() == null) {
                res.setPid(0);
            }
            resService.insert(res);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("添加资源失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 修改资源
     *
     * @param request
     * @param id      资源id
     * @return
     */
    @RequiresPermissions("res:update")
    @RequestMapping("admin/system/res/res_updateUI")
    public String res_updateUI(HttpServletRequest request, Integer id) {
        Map resMap = resService.selectById(id);
        request.setAttribute("res", resMap);
        return "admin/system/res/res_updateUI";
    }

    /**
     * 修改资源
     *
     * @param res 资源实体
     * @return
     */
    @LogAop(description = "修改资源")
    @RequiresPermissions("res:update")
    @RequestMapping("admin/system/res/res_update")
    @ResponseBody
    public ReturnBean res_update(Res res) {
        ReturnBean rb;
        try {
            if (res.getPid() == null) {
                res.setPid(0);
            }
            resService.update(res);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("修改资源失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 删除资源
     *
     * @param id 资源id
     * @return
     */
    @LogAop(description = "删除资源")
    @RequiresPermissions("res:delete")
    @RequestMapping("admin/system/res/res_delete")
    @ResponseBody
    public ReturnBean res_delete(Integer id) {
        ReturnBean rb;
        try {
            resService.delete(id);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("删除资源失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 资源信息页
     *
     * @param request
     * @param id      资源id
     * @return
     */
    @RequiresPermissions("res:see")
    @RequestMapping("admin/system/res/res_seeUI")
    public String res_seeUI(HttpServletRequest request, Integer id) {
        Map resMap = resService.selectById(id);
        request.setAttribute("res", resMap);
        return "admin/system/res/res_seeUI";
    }
}
