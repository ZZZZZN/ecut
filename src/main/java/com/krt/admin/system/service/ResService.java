package com.krt.admin.system.service;

import com.krt.admin.system.entity.Res;
import com.krt.admin.system.mapper.ResMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;
import com.krt.core.config.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 资源管理服务层
 * @date 2016年7月19日
 */
@SuppressWarnings("rawtypes")
@Service
public class ResService extends BaseServiceImpl<Res> {

    @Resource
    private ResMapper resMapper;

    @Override
    public BaseMapper<Res> getMapper() {
        return resMapper;
    }

    /**
     * 查询全部资源
     *
     * @return
     */
    public List selectAll() {
        return resMapper.selectList();
    }

    /**
     * 获取顶级资源
     *
     * @return
     */
    public List selectRootList() {
        return resMapper.selectRootList();
    }

    /**
     * 查询全部资源
     *
     * @return
     */
    public List selectAllTree() {
        return resMapper.selectAllTree();
    }

    /**
     * 根据角色code获取资源
     *
     * @param roleCode
     * @return
     */
    public List<Map> selectRoleResList(String roleCode) {
        if (Constant.ADMIN.equals(roleCode)) {
            //超级管理员用所有权限
            return resMapper.selectList();
        } else {
            return resMapper.selectRoleResList(roleCode);
        }
    }

    /**
     * 根据pid查询资源
     *
     * @param para
     * @return
     */
    public List selectListByPid(Map para) {
        return resMapper.selectListPara(para);
    }

    /**
     * 递归删除模块
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(Integer id) throws Exception {
        super.delete(id);
        List<Map> resList = resMapper.selectChildResList(id);
        for (Map map : resList) {
            String idd = map.get("id") + "";
            delete(Integer.valueOf(idd));
        }
    }

}
