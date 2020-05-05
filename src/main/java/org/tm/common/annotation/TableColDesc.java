package org.tm.common.annotation;

/*
    author: Timor
    date: 2020/2/11 0011
*/

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface TableColDesc {
    String name();//字段名称
    String paraName();//参数名称
    String label() default ""; //中文名称
    String type() default "text";//HTML组件类型: hidden|text|textarea|view|upload
    String cssClass() default ""; //HTML组件Class
    int width() default 100; //list宽度
    int minWidth() default -1; //最小值
    int maxWidth() default -1; //最大值
    boolean isDate() default false;//是否日期控件
    String dateFormat() default "";//日期格式
    String remark() default ""; //备注
}
