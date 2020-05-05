package org.tm.common.annotation;

/*
    author: Timor
    date: 2020/2/11 0011
*/

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface TableDesc {
    String label();// 表格标题
    String width() default "860px";// 表格宽度
    String height() default "300px";// 表格高度
    String remark() default "";// 备注
}
