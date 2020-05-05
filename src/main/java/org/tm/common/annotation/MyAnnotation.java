package org.tm.common.annotation;

/*
    author: Timor
    date: 2020/2/20 0020
*/
public @interface MyAnnotation {
    String name() default "abc";
    int age() default 18;
}
