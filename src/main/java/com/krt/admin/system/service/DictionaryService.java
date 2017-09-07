package com.krt.admin.system.service;

import com.krt.admin.system.entity.Dictionary;
import com.krt.admin.system.mapper.DictionaryMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 字典管理服务层
 * @date 2016年7月25日
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Service
public class DictionaryService extends BaseServiceImpl<Dictionary> {

    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public BaseMapper<Dictionary> getMapper() {
        return dictionaryMapper;
    }

    /**
     * 检测字典代码是否重复
     *
     * @param type 字典类型
     * @param code 字典代码
     * @param id
     * @return
     */
    public Integer checkCode(String type, String code, Integer id) {
        return dictionaryMapper.checkCode(type, code, id);
    }

    /**
     * 递归删除字典
     *
     * @param id 字典id
     * @throws Exception
     */
    @Override
    public void delete(Integer id) throws Exception {
        super.delete(id);
        // 查询子集
        List<Map> dicList = dictionaryMapper.selectChildList(id);
        for (Map dic : dicList) {
            Integer dicId = Integer.valueOf(dic.get("id") + "");
            delete(dicId);
        }
    }

    /**
     * 根据pid 和 type查询字典
     *
     * @param pid
     * @param type
     * @return
     */
    public List selectDicByPidAndType(Integer pid, String type) {
        return dictionaryMapper.selectDicByPidAndType(pid, type);
    }

    /**
     * type查询字典
     *
     * @param type
     * @return
     */
    public List selectDicByType(String type) {
        return dictionaryMapper.selectDicByPidAndType(0, type);
    }

}
