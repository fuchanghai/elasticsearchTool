package com.hito.dataware.scheduler.util;/**
 * @title: MyBulkProcess
 * @projectName dataware-scheduler
 * @description: TODO
 * @author 伏长海
 * @date 2019/7/1216:20
 */

import lombok.Data;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 *@author fuchanghai
 *@description
 *@date 2019/7/1216:20
 */
@Component
@Data
public class MyBulkProcess {
    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Bean(name = "myBulk")
    @DependsOn("myHighClient")
    public BulkProcessor getbulkProcessor(){
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
                //bulk请求前执行
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                //bulk请求后执行
                System.out.println(response.buildFailureMessage());
                /*     System.err.println("失败了" + executionId);*/
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                //失败后执行
            }
        };

        BulkProcessor.Builder builder = BulkProcessor.builder(
                (request, bulkListener) ->
                        restHighLevelClient.bulkAsync(request, RequestOptions.DEFAULT, bulkListener),
                listener);
        builder.setBulkActions(500);
        builder.setBulkSize(new ByteSizeValue(1L, ByteSizeUnit.MB));
        builder.setConcurrentRequests(0);
        builder.setFlushInterval(TimeValue.timeValueSeconds(3L));
        builder.setBackoffPolicy(BackoffPolicy
                .constantBackoff(TimeValue.timeValueSeconds(1L), 3));
        BulkProcessor bulkProcessor = builder.build();
        return bulkProcessor;
    }
}
