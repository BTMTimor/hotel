package com.jfinal.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import lombok.SneakyThrows;
import org.tm.common.base.api.result.ApiResultTest;
import org.tm.common.base.api.result.ResultCode;

import java.util.Map;

/*
    author: Timor
    date: 2020/3/29 0029
*/
public class MyTestCase {
    private String ID_KEY = "id";
    private final UrlRuleKit uriKit;
    private final JFinalTestKit testKit;

    private static final int DATA_ADD_SUCCESS = ResultCode.DATA_ADD_SUCCESS.getCode();
    private static final int DATA_GET_SUCCESS = ResultCode.SUCCESS.getCode();
    private static final int DATA_DELETE_SUCCESS = ResultCode.DATA_DELETE_ERROR.getCode();
    private static final int DATA_UPDATE_SUCCESS = ResultCode.DATA_UPDATE_SUCCESS.getCode();
    private static final int DATA_NOT_EXIST = ResultCode.DATA_NOT_EXIST.getCode();

    public MyTestCase(String baseUri) {
        if(StrKit.isBlank(baseUri)){
            throw new NullPointerException(baseUri);
        }
        this.testKit = new JFinalTestKit();
        this.uriKit = new UrlRuleKit(baseUri);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


    public JSONObject getById(String id){
        return this.getById(Kv.by(ID_KEY, id));
    }

    public JSONObject getById(Model<?> model){
        return this.getById(ModelKit.toMap(model));
    }

    @SneakyThrows
    public JSONObject getById(Map<String, Object> data) {
        checkId(data);
        ApiResultTest result = jsonToResult(testKit.get(uriKit.getUri(), data));

        if(result.getCode() == DATA_NOT_EXIST){
            return null;
        }else if(result.getCode() != DATA_GET_SUCCESS){
            throw new Exception(result.toJson());
        }
        return (JSONObject) result.getMainData();
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


    // 成功返回id，失败返回失败信息
    public String add(Model<?> model){
        return this.add(ModelKit.toMap(model));
    }

    @SneakyThrows
    public String add(Map<String, Object> data) {
        ApiResultTest result = jsonToResult(testKit.post(uriKit.addUri(), data));

        if(result.getCode() != DATA_ADD_SUCCESS){
            throw new Exception(result.toJson());
        }
        return (String) result.getMainData();
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


    public boolean update(Model<?> model){
        return this.update(ModelKit.toMap(model));
    }

    @SneakyThrows
    public boolean update(Map<String, Object> data) {
        checkId(data);
        ApiResultTest result = jsonToResult(testKit.post(uriKit.updateUri(), data));

        return (result.getCode() == DATA_UPDATE_SUCCESS);
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


    public boolean deleteById(String id){
        return this.deleteById(Kv.by(ID_KEY, id));
    }

    public boolean deleteById(Model<?> model){
        return this.deleteById(ModelKit.toMap(model));
    }

    @SneakyThrows
    public boolean deleteById(Map<String, Object> data) {
        checkId(data);
        ApiResultTest result = jsonToResult(testKit.get(uriKit.deleteUri(), data));
        return (result.getCode() == DATA_DELETE_SUCCESS);
    }

    public void findAll() {
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


    public void checkId(Map<String, Object> data){
        checkId(data.get(ID_KEY));
    }

    public void checkId(Object id){
        // todo
        // assert (null != id) && (!id.equals(""));
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @SneakyThrows
    public static ApiResultTest jsonToResult(String result) {
        // simple check
        if (result.startsWith("{") && result.endsWith("}")){
            // System.out.println("[jsonToResult] json: " + result);
            return new ApiResultTest(JSON.parseObject(result));
        }else {
            // 这里肯定是出现问题了，直接抛出就好了
            throw new Exception(result);
        }
    }
}
