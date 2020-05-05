package org.tm.common.base.api.result;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/*
    user: Timor
    date: 2019/10/17 0017
*/
public class ApiResult {
    private int code;
    private String message;
    private Map<String, Object> data;
    private long timestamp;

    private ApiResult(ResultCode resultCode) {
        this(resultCode, null);
    }

    private ApiResult(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.data = new HashMap<>();
        if(null != data){
            this.data.put("main", data);
        }
        this.message = resultCode.getMessage();
        this.timestamp = System.currentTimeMillis();
    }

    private ApiResult(boolean bool, Object data){
        if(bool) success(data);
        else failure(data);
    }

    // * * * * * * * * 常用状态* * * * * * * * * * * *
    public static ApiResult successOrFailure(boolean bool) {
        return bool ? success() : failure();
    }

    public static ApiResult successOrFailure(boolean bool, String successMsg, String failureMsg) {
        return bool ? success(successMsg) : failure(failureMsg);
    }

    // 成功
    public static ApiResult success() {
        return new ApiResult(null);
    }
    public static ApiResult success(Object data) {
        return new ApiResult(ResultCode.SUCCESS, data);
    }
    public static ApiResult success(ResultCode resultCode) {
        return new ApiResult(resultCode);
    }
    public static ApiResult success(ResultCode resultCode, Object data) {
        return new ApiResult(resultCode, data);
    }
    // 失败
    public static ApiResult failure(){
        return new ApiResult(ResultCode.FAILURE);
    }
    public static ApiResult failure(Object data) {
        return new ApiResult(ResultCode.FAILURE, data);
    }
    public static ApiResult failure(ResultCode resultCode) {
        return new ApiResult(resultCode);
    }
    public static ApiResult failure(ResultCode resultCode, Object data) {
        return new ApiResult(resultCode, data);
    }
    // 服务器错误
    public static ApiResult error(){
        return new ApiResult(ResultCode.ERROR);
    }
    public static ApiResult error(Object data) {
        return new ApiResult(ResultCode.ERROR, data);
    }
    public static ApiResult error(ResultCode resultCode) {
        return new ApiResult(resultCode);
    }
    public static ApiResult error(ResultCode resultCode, Object data) {
        return new ApiResult(resultCode, data);
    }

    // * * * * * * * * * * * * * * * * * * * *

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Object getDataValue(String key) {
        return data.get(key);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ApiResult addData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ApiResult addAllData(Map<String, Object> data) {
        this.data.putAll(data);
        return this;
    }

    public void setData(Map<String, Object> dataMap) {
        this.data = dataMap;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
}
