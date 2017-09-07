package com.krt.admin.system.service;

import com.krt.admin.system.entity.Log;
import com.krt.admin.system.mapper.LogMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 日志管理服务层
 * @date 2016年7月21日
 */
@Service
public class LogService extends BaseServiceImpl<Log> {

    @Resource
    private LogMapper logMapper;

    @Override
    public BaseMapper<Log> getMapper() {
        return logMapper;
    }

    public void deleteAll() throws Exception {
        logMapper.deleteAll();
    }

}
