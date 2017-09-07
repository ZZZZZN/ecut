package com.krt.admin.system.service;

import com.krt.admin.system.entity.DictionaryType;
import com.krt.admin.system.mapper.DictionaryMapper;
import com.krt.admin.system.mapper.DictionaryTypeMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 字典管理服务层
 * @date 2016年7月25日
 */
@Service
public class DictionaryTypeService extends BaseServiceImpl<DictionaryType> {

    @Resource
    private DictionaryTypeMapper dictionaryTypeMapper;
    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public BaseMapper<DictionaryType> getMapper() {
        return dictionaryTypeMapper;
    }

    /**
     * 删除字典
     *
     * @param id
     * @param code
     * @throws Exception
     */
    public void delete(Integer id, String code) throws Exception {
        dictionaryTypeMapper.delete(id);
        dictionaryMapper.deleteByType(code);
    }

    /**
     * 检测字典编码
     *
     * @param code
     * @param id
     * @return
     */
    public Integer checkCode(String code, Integer id) {
        return dictionaryTypeMapper.checkCode(code, id);
    }

}
