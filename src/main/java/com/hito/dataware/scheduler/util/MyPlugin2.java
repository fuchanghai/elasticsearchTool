package com.hito.dataware.scheduler.util;


import com.hito.dataware.scheduler.bean.EsBaseField;
import com.hito.dataware.scheduler.bean.Location;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * @author 伏长海
 * @title: MyPlugin
 * @projectName mysql2es
 * @description: TODO
 * @date 2019/5/2817:20
 */
@Component
@Intercepts({
        @Signature(type = ResultSetHandler.class,method = "handleResultSets",args = {Statement.class})}
)
public class MyPlugin2 implements Interceptor,ApplicationContextAware{
    // 此处放是否需要 执行拦截
    private static final ThreadLocal<Boolean> LOCAL_PAGE = new ThreadLocal();

    private ApplicationContext applicationContext;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object proceed =null;
        //执行完 后
        if(null!=invocation){

            proceed = invocation.proceed();
        }
        // 判断是否拦截
        if(oldLocal()){
            LOCAL_PAGE.remove();
            if(proceed instanceof ArrayList){
                // 表名
                ArrayList resultList = (ArrayList) proceed;
                if(!CollectionUtils.isEmpty(resultList)){
                    //获得类中点的字段名
                    List<Point> pointList = Geo.getPoint(resultList.get(0).getClass());
                    convertPonit(pointList,resultList);

                }

            }

        }

        if(null!=LOCAL_PAGE.get()){
            LOCAL_PAGE.remove();
        }
        return proceed;

    }

    public void convertPonit(List<Point> pointList,ArrayList resultList){
        for (int i = 0; i < resultList.size(); i++) {
            EsBaseField resultMap = (EsBaseField) resultList.get(i);
            if(!CollectionUtils.isEmpty(pointList)){
                Location location=new Location();
                for (int j=0;j<pointList.size();j++){
                    Point point = pointList.get(j);
                    try {
                        Field fieldOfLon = resultList.get(i).getClass().getDeclaredField(point.getLon());
                        Field fieldOfLat = resultList.get(i).getClass().getDeclaredField(point.getLat());
                        fieldOfLon.setAccessible(true);fieldOfLat.setAccessible(true);
                        location.setLon((Integer)fieldOfLon.get(resultList.get(i))/1.00);
                        location.setLat((Integer)fieldOfLat.get(resultList.get(i))/1.00);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
                if(location.getLat()>90.00||location.getLat()<-90.00||location.getLon()>180.00||location.getLon()<-180.00){

                }else {

                    resultMap.setPoint(location); //取出相应的字段进行加密
                }
            }

        }
    }
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        /*System.out.println("设置参数");*/

    }
    public static boolean oldLocal(){
        if(null!=LOCAL_PAGE.get()){

            if(LOCAL_PAGE.get()){
                return true;
            }else {
                return false;
            }
        }else {
            LOCAL_PAGE.set(false);
            return false;
        }
    }
    public static void   used(Boolean used){
            LOCAL_PAGE.set(used);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
        // spring容器初始化之后获取特殊字段表
       /* System.out.println("有"+applicationContext.getBeansOfType(SpecialFieldMapper.class).size()+"个");*/
       /* this.specialFieldMapper = (SpecialFieldMapper)applicationContext.getBean("specialFieldMapper");*/
    }

}
