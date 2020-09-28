package com.hito.dataware.scheduler;/**
 * @title: SchedulerApplication
 * @projectName dataware-scheduler
 * @description: TODO
 * @author 伏长海
 * @date 2019/7/1211:54
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 *@author fuchanghai
 *@description
 *@date 2019/7/1211:54
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@MapperScan("com.hito.dataware.scheduler.dao")
public class SchedulerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class,args);

    }

}
