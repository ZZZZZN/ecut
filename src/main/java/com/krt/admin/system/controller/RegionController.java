package com.krt.admin.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.krt.admin.system.entity.Region;
import com.krt.admin.system.service.DictionaryService;
import com.krt.admin.system.service.RegionService;
import com.krt.core.annotation.LogAop;
import com.krt.core.base.BaseController;
import com.krt.core.bean.ReturnBean;
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
 * @Description：区域控制层
 * @author：殷帅
 * @date：2016-07-26
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Controller
public class RegionController extends BaseController {

    @Resource
    private RegionService regionService;
    @Resource
    private DictionaryService dictionaryService;


    /**
     * 区域管理
     *
     * @param request
     * @return
     */
    @RequiresPermissions("region:list")
    @RequestMapping("admin/system/region/region_list")
    public String region_list(HttpServletRequest request) {
        Map para = new HashMap();
        para.put("pid", "0");
        List list = regionService.selectListByPid(para);
        request.setAttribute("list", list);
        request.setAttribute("para", para);
        return "admin/system/region/region_list";
    }

    /**
     * 获取子集
     *
     * @param pid
     * @return
     */
    @RequestMapping("admin/system/region/region_child")
    @ResponseBody
    public List region_child(String pid) {
        Map para = new HashMap();
        para.put("pid", pid);
        List list = regionService.selectListByPid(para);
        return list;
    }

    /**
     * 新增区域页
     *
     * @return
     */
    @RequiresPermissions("region:insert")
    @RequestMapping("admin/system/region/region_insertUI")
    public String region_insertUI(Integer id, HttpServletRequest request) {
        List typeList = dictionaryService.selectDicByType("region_type");
        request.setAttribute("typeList", typeList);
        if (id != null) {
            Map regionMap = regionService.selectById(id);
            request.setAttribute("region", regionMap);
        }
        return "admin/system/region/region_insertUI";
    }

    /**
     * 添加区域
     *
     * @param region
     * @return
     */
    @LogAop(description = "添加区域")
    @RequiresPermissions("region:insert")
    @RequestMapping("admin/system/region/region_insert")
    @ResponseBody
    public ReturnBean region_insert(Region region) {
        ReturnBean rb;
        try {
            if (region.getPid() == null) {
                region.setPid(0);
            }
            regionService.insert(region);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("添加区域失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 修改区域页
     *
     * @param id      区域 id
     * @param request
     * @return
     */
    @RequiresPermissions("region:update")
    @RequestMapping("admin/system/region/region_updateUI")
    public String region_updateUI(Integer id, HttpServletRequest request) {
        Map regionMap = regionService.selectById(id);
        request.setAttribute("region", regionMap);
        List typeList = dictionaryService.selectDicByType("region_type");
        request.setAttribute("typeList", typeList);
        return "admin/system/region/region_updateUI";
    }

    /**
     * 修改区域
     *
     * @param region
     * @return
     */
    @LogAop(description = "修改区域")
    @RequiresPermissions("region:update")
    @RequestMapping("admin/system/region/region_update")
    @ResponseBody
    public ReturnBean region_update(Region region) {
        ReturnBean rb;
        try {
            if (region.getPid() == null) {
                region.setPid(0);
            }
            regionService.update(region);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("修改区域失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 删除区域
     *
     * @param id 区域 id
     * @return
     */
    @LogAop(description = "删除区域")
    @RequiresPermissions("region:delete")
    @RequestMapping("admin/system/region/region_delete")
    @ResponseBody
    public ReturnBean region_delete(Integer id) {
        ReturnBean rb;
        try {
            regionService.delete(id);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            logger.error("删除区域失败", e);
            rb = ReturnBean.getErrorReturnBean();
        }
        return rb;
    }

    /**
     * 区域树
     *
     * @param request
     * @return
     */
    @RequiresPermissions("role:res")
    @RequestMapping("admin/system/region/region_treeUI")
    public String region_treeUI(HttpServletRequest request) {
        JSONArray regionTree = regionService.selectRegionTree();
        request.setAttribute("regionTree", regionTree);
        return "admin/system/region/region_treeUI";
    }

    /**
     * 检测区域编码
     *
     * @param id
     * @param code
     * @return
     */
    @RequestMapping("admin/system/region/checkCode")
    @ResponseBody
    public boolean checkCode(Integer id, String code) {
        Integer count = regionService.checkCode(id, code);
        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }
}
