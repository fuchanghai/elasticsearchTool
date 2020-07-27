package com.hito.dataware.scheduler.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 18816250716
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Point {
    /**
     *
     * 经度
     */
    String lon() default "lon";

    /**
     *
     * 纬度
     */
    String lat() default "lat";
}
