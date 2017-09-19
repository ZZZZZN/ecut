package com.krt.admin.system.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.krt.admin.system.entity.Region;
import com.krt.admin.system.mapper.RegionMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @version 1.0
 * @Description：区域服务层
 * @date：2016-07-26
 */
@SuppressWarnings("rawtypes")
@Service
public class RegionService extends BaseServiceImpl<Region> {

    @Resource
    private RegionMapper regionMapper;

    @Override
    public BaseMapper<Region> getMapper() {
        return regionMapper;
    }

    public List selectListByPid(Map para) {
        return regionMapper.selectListPara(para);
    }

    /**
     * 区域区域树
     *
     * @return
     */
    public JSONArray selectRegionTree() {
        List list = regionMapper.selectList();
        JSONArray array = new JSONArray();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                Map m1 = (Map) list.get(i);
                int res_id1 = Integer.valueOf(m1.get("id") + "");
                jsonObject.put("id", res_id1);
                jsonObject.put("name", m1.get("name"));
                jsonObject.put("pId", m1.get("pid"));
                array.add(jsonObject);
            }
        }
        return array;
    }

    /**
     * 检测区域编码
     *
     * @param id
     * @param code
     * @return
     */
    public Integer checkCode(Integer id, String code) {
        return regionMapper.checkCode(id, code);
    }

    /**
     * 递归删除
     */
    @Override
    public void delete(Integer id) throws Exception {
        super.delete(id);
        // 查询子集
        List<Map> regList = regionMapper.selectChildList(id);
        for (Map dic : regList) {
            Integer regId = Integer.valueOf(dic.get("id") + "");
            delete(regId);
        }
    }

    /**
     * @param pid
     * @return List
     * @Description: 根据父id查询地区
     */
    public List selectRegionByPid(Integer pid) {
        return regionMapper.selectRegionByPid(pid);
    }


    /**
     * 根据type查询地区信息
     *
     * @param type
     * @return
     */
    public List selectRegionByType(String type) {
        return regionMapper.selectRegionByType(type);
    }

    /**
     * 根据皮带查询地区信息
     *
     * @param pid
     * @return
     */
    public List selectListByPid(Integer pid) {
        return regionMapper.selectRegionByPid(pid);
    }
}
