package com.hito.dataware.scheduler.task;/**
 * @title: L0CleanAisDynamicScheduler
 * @projectName dataware-scheduler
 * @description: TODO
 * @author 伏长海
 * @date 2019/7/1213:10
 */

import com.hito.dataware.scheduler.bean.ImportLastTime;
import com.hito.dataware.scheduler.bean.L0CleanAisDynamicClassA;
import com.hito.dataware.scheduler.bean.L0CleanAisDynamicSouth;
import com.hito.dataware.scheduler.dao.ImportLastTimeMapper;
import com.hito.dataware.scheduler.dao.L0CleanAisDynamicSouthMapper;
import com.hito.dataware.scheduler.util.DateJsonValueProcessor;
import com.hito.dataware.scheduler.util.MyPlugin2;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@author fuchanghai
 *@description
 *@date 2019/7/1213:10
 */
@Service
public class L0CleanAisDynamicScheduler {

    @Autowired
    L0CleanAisDynamicSouthMapper l0CleanAisDynamicSouthMapper;

    @Autowired
    ImportLastTimeMapper importLastTimeMapper;

    @Autowired
    BulkProcessor bulkProcessor;


    public final int pageCount=10000;

    JsonConfig jsonConfig = new JsonConfig();
    PropertyFilter filter1 = new PropertyFilter() {
        @Override
        public boolean apply(Object object, String fieldName, Object fieldValue) {
            if (fieldValue instanceof List) {
                List<Object> list = (List<Object>) fieldValue;
                if (list.size() == 0) {
                    return true;
                }
            }
            if (fieldValue instanceof Date) {

            }
            return null == fieldValue || "".equals(fieldValue);
        }
    };

    @Scheduled(fixedRate = 1000)
    public  void insertClassaDynamicIdentity(){
        jsonConfig.setJsonPropertyFilter(filter1);
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
        ImportLastTime l0_clean_dynamic = importLastTimeMapper.selectByTableName("l0_clean_ais_dynamic_a");
        //TODO 考虑redis 加锁
        // 先去自己的表找看看 是否是解锁状态
        if(l0_clean_dynamic.getShutDown()==0) {


            if (null != l0_clean_dynamic.getStatus() && l0_clean_dynamic.getStatus() == 0) {
                Map updateMap = new HashMap();
                updateMap.put("tableName", "l0_clean_ais_dynamic_a");
                updateMap.put("status", 1);
                // 加锁
                importLastTimeMapper.updateByTableName(updateMap);

                Long nowTime = System.currentTimeMillis();
                // 如果解锁我们就传 起始时间(数据库里有) 结束时间
                Long importTime = l0_clean_dynamic.getImportTime();
                // 查看时间是否符合要求
                if (null != importTime && importTime < nowTime) {
                    // 获取表中符合时间条件的数量
                    Map map = new HashMap();
                    // 时间*1000 因为数据库里存的是纳秒
                    map.put("startTime", importTime * 1000);
                    map.put("endTime", nowTime * 1000);
                    /*totalCount=l1ShipVoyageLastMapper.classaCount(map);*/
                    int i = 0;
                    //起始标记位
                    int startFlag = 0;
                    // 结束标记位
                    int endFlag = 0;
                    while (true) {
                        startFlag = i * pageCount;
                        endFlag = (i + 1) * pageCount;
                        map.put("startFlag", startFlag);
                        map.put("endFlag", endFlag);
                        MyPlugin2.used(true);
                        List<L0CleanAisDynamicClassA> list = l0CleanAisDynamicSouthMapper.fromClassa(map);
                        // 后面没值了
                        if (list.size() < 1) {
                            updateTimeAndStatus(updateMap, nowTime);
                            break;
                        } else {
                            Document doc = (Document) L0CleanAisDynamicSouth.class.getAnnotation(Document.class);
                            String indexName = doc.indexName();
                            String type = doc.type();
                            checkDocument(doc);
                            for (L0CleanAisDynamicClassA l0CleanAisDynamicClassA : list
                            ) {
                                l0CleanAisDynamicClassA.setSourceId(0);
                                //bulkprocess 调用插入es
                                bulkProcessor.add(new IndexRequest(indexName, type, l0CleanAisDynamicClassA.getIdentityUkey()).source(JSONObject.fromObject(l0CleanAisDynamicClassA, jsonConfig)));

                            }
                            bulkProcessor.flush();
                        }
                        i++;
                    }
                }

                updateTimeAndStatus(updateMap, nowTime);
            }
        }
    }

    public void  updateTimeAndStatus(Map <String,Object> updateMap,Long nowTime){
        updateMap.put("status",0);
        // 插入结束 就将自己的表解锁 并更新时间
        updateMap.put("importTime",nowTime);
        importLastTimeMapper.updateByTableName(updateMap);
    }

    public void checkDocument(Document doc){
        if(null!=doc){
            if(StringUtils.isEmpty(doc.indexName())){
                throw new RuntimeException("没有indexName");
            }
            if (StringUtils.isEmpty(doc.type())){
                throw new RuntimeException("没有type");

            }
        }else {
            throw new RuntimeException("没有Document注解");
        }
    }
}
