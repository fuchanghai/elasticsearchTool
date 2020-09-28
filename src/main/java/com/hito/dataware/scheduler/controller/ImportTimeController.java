package com.hito.dataware.scheduler.controller;

import com.hito.common.api.ApiResponse;
import com.hito.dataware.scheduler.service.ImportTimeService;
import com.hito.dataware.scheduler.vo.ImportTimeUpdateVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@author fuchanghai
 */
@RestController
@RequestMapping("/importTime")
public class ImportTimeController {
    @Autowired
    ImportTimeService importTimeService;

     /**
     　　* @param startTableNames 需要开启的定时任务表名 多个表逗号隔开 ， shutDownTableNames 需要关闭的定时任务表名 多个表逗号隔开
     　　* @author fuchanghai
     　　*/
    @PutMapping("/startOrShutDown")
    public ApiResponse startOrShutDown(ImportTimeUpdateVo importTimeUpdateVo){

        importTimeService.startOrShutDown(importTimeUpdateVo);
        return ApiResponse.responseSuccess();
    }

    /**
     　　* @param  tableNames 需要重置的表名 多个表逗号隔开 ，如果tableNames 为null  或者"" 则默认重置所有表
     　　* @author fuchanghai
     　　*/

    @PutMapping("reset")
    public ApiResponse resetOriginalValue(@Param("tableNames") String tableNames){
       return importTimeService.resetOriginalValue(tableNames);
    }
}
