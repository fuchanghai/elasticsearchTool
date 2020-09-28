package com.hito.dataware.scheduler.dao;

import com.hito.dataware.scheduler.bean.L0CleanAisDynamicClassA;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 伏长海
 * @title: L0CleanAisDynamicSouthMapper
 * @projectName mysql2es
 * @description: TODO
 * @date 2019/6/2714:08
 */
public interface L0CleanAisDynamicSouthMapper extends Mapper<L0CleanAisDynamicSouth> {
    List <L0CleanAisDynamicSouth>fromUnknown(@Param("map") Map<String, Object> map);
    List <L0CleanAisDynamicClassA>fromClassa(@Param("map") Map<String, Object> map);
    List <L0CleanAisDynamicClassB>fromClassb(@Param("map") Map<String, Object> map);

}
