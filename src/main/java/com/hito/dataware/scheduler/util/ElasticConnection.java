package com.hito.dataware.scheduler.util;

import lombok.Data;
import org.apache.http.HttpHost;
import org.checkerframework.checker.units.qual.A;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author 伏长海
 * @title: ElasticConnection
 * @projectName dataware-port-service
 * @description: TODO
 * @date 2019/6/1810:57
 */
@Component
@Data
public class ElasticConnection {
    @Value("${elasticsearch.ip}")
    private  String ip ;

    @Value("${elasticsearch.port}")
    private  int port;

    @Value("${elasticsearch.cluster-name}")
    private String clusterName;




    @Bean(name = "myHighClient")
    public  RestHighLevelClient  getClient(){
        RestHighLevelClient client= new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(ip,port,"http")
                )
        );
        return client;
    }


}
