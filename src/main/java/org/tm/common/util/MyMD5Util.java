package org.tm.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/*
    author: Timor
    date: 2019/11/18 0018
*/
public class MyMD5Util {

    /**
     * 生成MD5
     * null + null = nullnull
     * null与String连接时，null会变成"null"与String想连
     * @param dataStr 字符串数据
     * @return MD5
     */
    public static String getMD5(String dataStr){
        return getMD5(dataStr, "");
    }

    /**
     * 生成MD5
     * null + null = nullnull
     * null与String连接时，null会变成"null"与String想连
     * @param dataStr 字符串数据
     * @param slat 盐
     * @return MD5
     */
    public static String getMD5(String dataStr, String slat) {
        StringBuilder result = new StringBuilder();
        try {
            dataStr = dataStr + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes(StandardCharsets.UTF_8));
            byte[] s = m.digest();
            for (byte b : s) {
                result.append(Integer.toHexString((0x000000FF & b) | 0xFFFFFF00).substring(6));
            }
        } catch (Exception e) {
            System.out.println("generate error");
        }
        return result.toString();
    }
}