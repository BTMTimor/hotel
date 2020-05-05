package com.jfinal.test;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Model;

import java.util.Map;

/*
    author: Timor
    date: 2020/4/3 0003
*/
public class ModelKit {

    // 比较所有字段
    public static boolean compare(Model<?> one, Model<?> another) {
        return toMap(one).equals(toMap(another));
    }

    // 只比较相同的字段
    public static boolean compareCommonAttr(Model<?> one, Model<?> another) {
        return compareCommonAttrForMap(toMap(one), toMap(another));
    }


    private static boolean compareCommonAttrForMap(Map<String, Object> one, Map<String, Object> another) {
        if(null == one || null == another){
            return one == another;
        }

        for (String key : one.keySet()) {
            Object value = one.get(key);
            // null值不进行比较
            if(null != value && another.containsKey(key)){
                if(!value.equals(another.get(key))){
                    return false;
                }
            }
        }
        return true;
    }



    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -



    public static Map<String, Object> toMap(Model<?> model){
        return model.toRecord().getColumns();
    }

    public static <T> T fromJson(JSONObject json, Class<T> clazz){
        return json.toJavaObject(clazz);
    }

}
