package com.krt.admin.system.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.krt.admin.system.entity.Role;
import com.krt.admin.system.mapper.ResMapper;
import com.krt.admin.system.mapper.RoleMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 角色管理服务层
 * @date 2016年7月16日
 */
@Service
public class RoleService extends BaseServiceImpl<Role> {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private ResMapper resMapper;

    @Override
    public BaseMapper<Role> getMapper() {
        return roleMapper;
    }

    /**
     * 检测角色名
     *
     * @param roleName 角色名
     * @param id       角色id
     * @return
     */
    public Integer checkRoleName(String roleName, Integer id) {
        return roleMapper.checkRoleName(roleName, id);
    }

    /**
     * 检测角色编码
     *
     * @param roleCode 角色编码
     * @param id       角色id
     * @return
     */
    public Integer checkRoleCode(String roleCode, Integer id) {
        return roleMapper.checkRoleCode(roleCode, id);
    }

    /**
     * 获取角色资源树
     *
     * @param roleCode
     * @return
     */
    public JSONArray selectRoleResTree(String roleCode) {
        // 查询全部资源
        List allList = resMapper.selectList();
        // 查询角色资源
        List roleResList = resMapper.selectRoleResList(roleCode);
        JSONArray array = new JSONArray();
        if (allList != null && allList.size() > 0) {
            for (int i = 0; i < allList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                Map m1 = (Map) allList.get(i);
                System.out.println(m1.get("id") + "");
                int res_id1 = Integer.valueOf(m1.get("id") + "");
                jsonObject.put("id", res_id1);
                jsonObject.put("name", m1.get("name"));
                jsonObject.put("pId", m1.get("pid"));
                if (roleResList != null && roleResList.size() > 0) {
                    for (int j = 0; j < roleResList.size(); j++) {
                        Map m2 = (Map) roleResList.get(j);
                        logger.debug(m2);
                        int res_id2 = Integer.valueOf(m2.get("id") + "");
                        if (res_id1 == res_id2) {
                            jsonObject.put("open", "true");
                            jsonObject.put("checked", "true");
                        }
                    }
                }
                array.add(jsonObject);
            }
        }
        return array;
    }

    /**
     * 修改角色权限
     *
     * @param roleCode
     * @param res_ids
     * @throws Exception
     */
    public void updateRoleRes(String roleCode, String res_ids) throws Exception {
        // 先删除角色权限
        roleMapper.deleteRoleRes(roleCode);
        if (res_ids != null && !"".equals(res_ids)) {
            List list = new ArrayList();
            String[] ids = res_ids.split(",");
            for (String id : ids) {
                Map pmap = new HashMap();
                pmap.put("roleCode", roleCode);
                pmap.put("res_id", Integer.valueOf(id));
                list.add(pmap);
            }
            roleMapper.insertRoleRes(list);
        }
    }

    /**
     * 删除
     *
     * @param id
     * @param roleCode
     */
    public void delete(Integer id, String roleCode) throws Exception {
        roleMapper.delete(id);
        roleMapper.deleteRoleRes(roleCode);
    }

    /**
     * 获取角色url资源
     *
     * @param roleCode
     * @return
     */
    public List selectRoleUrlRes(String roleCode, Integer pid) {
        List<Map> list = roleMapper.selectRoleUrlRes(roleCode, pid);
        if (list != null && list.size() > 0) {
            for (Map m : list) {
                List chlid = selectRoleUrlRes(roleCode, Integer.valueOf(m.get("id") + ""));
                m.put("child", chlid);
            }
        }
        return list;
    }

}
