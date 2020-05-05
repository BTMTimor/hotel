package org.tm.common.annotation.service;

/*
    author: Timor
    date: 2020/2/23 0023
*/
public class DefaultNameFormatter implements NameFormatter{
    @Override
    public String format(String name) {
        return name;
    }
}
