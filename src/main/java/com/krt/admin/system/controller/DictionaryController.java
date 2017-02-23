package com.krt.admin.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.krt.admin.system.entity.Dictionary;
import com.krt.admin.system.service.DictionaryService;
import com.krt.common.annotation.LogAop;
import com.krt.common.base.BaseController;
import com.krt.common.bean.DataTable;
import com.krt.common.bean.ReturnBean;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.krt.common.util.Common;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 字典管理控制层
 * @date 2016年7月25日
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Controller
public class DictionaryController extends BaseController {

    @Resource
    private DictionaryService dictionaryService;

    /**
     * 字典管理 页
     *
     * @param pid     父id
     * @param request
     * @return
     */
    @RequiresPermissions("dictionary:list")
    @RequestMapping("admin/system/dictionary/dictionary_listUI")
    public String dictionary_listUI(String pid, HttpServletRequest request) {
        String fid = "-1";
        if (!"0".equals(pid) && !"".equals(pid)) {
            Map m = dictionaryService.selectById(Integer.valueOf(pid));
            fid = m.get("pid") + "";
        }
        request.setAttribute("fid", fid);
        return "admin/system/dictionary/dictionary_listUI";
    }

    /**
     * 字典管理
     *
     * @param start
     * @param length
     * @param draw
     * @param request
     * @return
     */
    @RequiresPermissions("dictionary:list")
    @RequestMapping("admin/system/dictionary/dictionary_list")
    @ResponseBody
    public DataTable dictionary_list(Integer start, Integer length, Integer draw, HttpServletRequest request) {
        String type = Common.toUTF(request.getParameter("type"));
        String pid = Common.toUTF(request.getParameter("pid"));
        String code = Common.toUTF(request.getParameter("code"));
        String name = Common.toUTF(request.getParameter("name"));
        Map para = new HashMap();
        para.put("code", code);
        para.put("name", name);
        para.put("type", type);
        para.put("pid", pid);
        DataTable dt = dictionaryService.selectListPara(start, length, draw, para);
        return dt;
    }

    /**
     * 添加字典
     *
     * @param dictionary 字典实体
     * @return
     */
    @LogAop(description = "添加字典")
    @RequiresPermissions("dictionary:insert")
    @RequestMapping("admin/system/dictionary/dictionary_insert")
    @ResponseBody
    public ReturnBean dictionary_insert(Dictionary dictionary) {
        ReturnBean rb;
        try {
            // 检测字典代码是否重复
            Integer count = dictionaryService.checkCode(dictionary.getType(), dictionary.getCode(), dictionary.getId());
            if (count == 0) {
                dictionaryService.insert(dictionary);
                rb = ReturnBean.getSuccessReturnBean();
            } else {
                rb = ReturnBean.getCustomReturnBean("error_code");
            }
        } catch (Exception e) {
            rb = ReturnBean.getErrorReturnBean();
            logger.error("添加字典失败", e);
        }
        return rb;
    }

    /**
     * 修改字典
     *
     * @param dictionary 字典实体
     * @return
     */
    @LogAop(description = "修改字典")
    @RequiresPermissions("dictionary:update")
    @RequestMapping("admin/system/dictionary/dictionary_update")
    @ResponseBody
    public ReturnBean dictionary_update(Dictionary dictionary) {
        ReturnBean rb;
        try {
            // 检测字典代码是否重复
            Integer count = dictionaryService.checkCode(dictionary.getType(), dictionary.getCode(), dictionary.getId());
            if (count == 0) {
                dictionaryService.update(dictionary);
                rb = ReturnBean.getSuccessReturnBean();
            } else {
                rb = ReturnBean.getCustomReturnBean("error_code");
            }
        } catch (Exception e) {
            rb = ReturnBean.getErrorReturnBean();
            logger.error("修改字典失败", e);
        }
        return rb;
    }

    /**
     * 删除字典
     *
     * @param id
     * @return
     */
    @LogAop(description = "删除字典")
    @RequiresPermissions("dictionary:delete")
    @RequestMapping("admin/system/dictionary/dictionary_delete")
    @ResponseBody
    public ReturnBean dictionary_delete(Integer id) {
        ReturnBean rb;
        try {
            dictionaryService.delete(id);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            rb = ReturnBean.getErrorReturnBean();
            logger.error("删除字典失败", e);
        }
        return rb;
    }
}
