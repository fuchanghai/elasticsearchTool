package com.hito.dataware.scheduler.util;

/**
 * @author 伏长海
 * @title: DateJsonValueProcessor
 * @projectName mysql2es
 * @description: TODO
 * @date 2019/6/1111:23
 */
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JSON日期格式转换
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    private String format = "yyyy-MM-dd HH:mm:ss";
//    private String format = "yyyy-MM-dd'T'HH:mm:ss.SSS";
//    String formatIn = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ";
//    String formatOut = "yyyy-MM-dd HH:mm:ss";
//    String formatOut2 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ";


    public DateJsonValueProcessor() {

    }

    public DateJsonValueProcessor(String format) {

        this.format = format;
    }

    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {

        String[] obj = {};
        if (value instanceof Date[]) {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date[] dates = (Date[]) value;
            obj = new String[dates.length];
            for (int i = 0; i < dates.length; i++) {
                obj[i] = sf.format(dates[i]);
            }
        }
        return obj;
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {

        if (value instanceof Date) {
            String str = new SimpleDateFormat(format).format((Date) value);
            /*Long str =((Date) value).getTime();*/
            return str;
        }
        return value;
    }

    public String getFormat() {

        return format;
    }

    public void setFormat(String format) {

        this.format = format;
    }

}
