package org.tm.doc.model;

import org.junit.Test;

import static org.junit.Assert.*;

/*
    author: Timor
    date: 2020/4/12 0012
*/
public class ColumnMetaTest {

    @Test
    public void changeToJavaFiled() {
        System.out.println(ColumnMeta.changeToJavaFiled("adb_Abstract"));
        System.out.println(ColumnMeta.changeToJavaFiled("adb_Account_boy"));
        System.out.println(ColumnMeta.changeToJavaFiled("abc"));
        System.out.println(ColumnMeta.changeToJavaFiled("_"));
        System.out.println(ColumnMeta.changeToJavaFiled("a_"));
    }
}