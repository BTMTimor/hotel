package org.tm.common.base.api.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/*
    author: Timor
    date: 2020/4/3 0003
*/
public class ApiResultTest{
    private int code;
    private String message;
    private Map<String, Object> data;
    private long timestamp;

    public ApiResultTest(JSONObject result) {
        this.code = result.getInteger("code");
        this.message = result.getString("message");
        this.timestamp = result.getLong("timestamp");
        if(null != result.getJSONObject("data")){
            this.data = result.getJSONObject("data").getInnerMap();
        }else {
            this.data = new HashMap<>();
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Object getMainData() {
        return data.get("main");
    }

    public int getDataCount() {
        return data.size();
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }



    public String toJson() {
        return JSON.toJSONString(this);
    }

    public Object getDataValue(String key) {
        return data.get(key);
    }
}
