package com.hito.dataware.scheduler.dao;

import com.hito.dataware.scheduler.bean.ImportLastTime;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 伏长海
 * @title: ImportLastTimeMapper
 * @projectName dataware-ship-service
 * @description: TODO
 * @date 2019/7/410:12
 */
public interface ImportLastTimeMapper extends Mapper<ImportLastTime> {
    ImportLastTime selectByTableName(@Param("tableName") String tableName);
    void updateByTableName(@Param("map") Map<String, Object> map);
    void startTableBatch(@Param("list")List list);
    void shutDownTableBatch(@Param("list")List list);
    void resetSelectiveTables(@Param("list")List list);
    void resertAllTables();
}
