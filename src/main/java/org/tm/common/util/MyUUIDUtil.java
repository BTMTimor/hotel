package org.tm.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
    author: Timor
    date: 2019/11/18 0018
*/
public class MyUUIDUtil {
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().toUpperCase();
        // return uuid.replaceAll("-", "");
        StringBuilder sb = new StringBuilder(uuid);
        sb.deleteCharAt(23).deleteCharAt(18);
        sb.deleteCharAt(13).deleteCharAt(8);
        return sb.toString();
    }

    public static String[] getUUIDArray(int num) {
        if(num < 1){
            return null;
        }else{
            String[] uuid = new String[num];
            for (int i = 0; i < num; i++) {
                uuid[i] = getUUID();
            }
            return uuid;
        }
    }
    public static List<String> getUUIDList(int num) {
        if(num < 1){
            return null;
        }else{
            List<String> uuid = new ArrayList<String>(num);
            for (int i = 0; i < num; i++) {
                uuid.add(getUUID());
            }
            return uuid;
        }
    }
}
