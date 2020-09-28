package com.hito.dataware.scheduler.service;/**
 * @author 伏长海
 */

import com.hito.common.api.ApiResponse;
import com.hito.common.api.ResponseCodeEnum;
import com.hito.dataware.scheduler.dao.ImportLastTimeMapper;
import com.hito.dataware.scheduler.vo.ImportTimeUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@author fuchanghai
 */
@Service
public class ImportTimeService {
    /**
     *
     */
    @Autowired
    ImportLastTimeMapper importLastTimeMapper;

    public void startOrShutDown(ImportTimeUpdateVo importTimeUpdateVo){
        String startTableNames=importTimeUpdateVo.getStartTableNames();
        String shutDownTableNames = importTimeUpdateVo.getShutDownTableNames();
        if(!StringUtils.isEmpty(startTableNames)&&startTableNames.trim().length()>0){

                updateStartTables(importTimeUpdateVo.getStartTableNames());

        }
        if(!StringUtils.isEmpty(shutDownTableNames)&&shutDownTableNames.trim().length()>0){
            updateShutDownTables(shutDownTableNames);
        }
    }

    public ApiResponse resetOriginalValue(String tableNames){

        if(isTableNamesEmpty(tableNames)){
            resetAllTables();
        }else {
            resetSelectiveTables(tableNames);
        }
        return ApiResponse.responseSuccess();
    }

    public void updateStartTables(String startTableNames){
        String[] startTableName = startTableNames.trim().split(",");
        List list= Arrays.asList(startTableName);

        importLastTimeMapper.startTableBatch(list);
    }

    public void updateShutDownTables(String shutDownTables){
        String[] shutTableName = shutDownTables.trim().split(",");
        List list= Arrays.asList(shutTableName);
        importLastTimeMapper.shutDownTableBatch(list);
    }

    public boolean isTableNamesEmpty(String tableNames){
        if(null==tableNames||StringUtils.isEmpty(tableNames.trim())){
            return true;
        }else {
            return false;
        }
    }

    public void resetAllTables(){
        importLastTimeMapper.resertAllTables();
    }

    public void resetSelectiveTables(String tableNames){
        String[] tableName = tableNames.split(",");
        List  list = Arrays.asList(tableName);
        importLastTimeMapper.resetSelectiveTables(list);
    }
}
