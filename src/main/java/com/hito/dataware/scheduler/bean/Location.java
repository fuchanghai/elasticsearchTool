package com.hito.dataware.scheduler.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author 伏长海
 * @title: Location
 * @projectName mysql2es
 * @description: TODO
 * @date 2019/5/2210:43
 */
@Data
public class Location {

    @Column(name = "Longitude")
    private Double lon;

   /* private String type;*/
    @Column(name = "Latitude")
    private Double lat;
}
