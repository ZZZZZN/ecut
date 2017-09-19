package com.krt.admin.system.mapper;

import com.krt.admin.system.entity.Region;
import com.krt.core.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description：区域映射层
 * @date：2016-07-26
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface RegionMapper extends BaseMapper<Region>{

	Integer checkCode(@Param("id") Integer id, @Param("code") String code);

	List<Map> selectChildList(@Param("id") Integer id);

    List selectRegionByPid(@Param("pid") Integer pid);

	List selectRegionByType(@Param("type") String type);
}
