package com.hito.dataware.scheduler.util;

import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 伏长海
 * @title: Geo
 * @projectName mysql2es
 * @description: TODO
 * @date 2019/5/2914:37
 */
@Data
public class Geo {


    public static List<Point> getPoint(Class<?> classes) {
        List<Point> points = new ArrayList<>();
        Field[] declaredFields = classes.getDeclaredFields();
        for (Field field:declaredFields){
            com.hito.dataware.scheduler.annotation.Point pointAnnotation = field.getAnnotation(com.hito.dataware.scheduler.annotation.Point.class);
            if(ObjectUtils.isEmpty(pointAnnotation)){
                Point point = new Point();
                point.setLat(pointAnnotation.lat());
                point.setLon(pointAnnotation.lon());
            }
        }
        return points;
    }
}
