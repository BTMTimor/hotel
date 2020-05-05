package org.tm.common.annotation;

/*
    author: Timor
    date: 2020/3/18 0018
*/

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ApiController {
    String value() default "json";
}
