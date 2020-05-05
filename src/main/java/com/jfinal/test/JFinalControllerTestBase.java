package com.jfinal.test;

import com.jfinal.plugin.activerecord.Model;
import lombok.SneakyThrows;

/*
    author: Timor
    date: 2020/4/3 0003
*/
public class JFinalControllerTestBase<T extends Model<T>> {
    private MyTestCase testCase;
    private Class<T> clazz;

    public JFinalControllerTestBase(String baseUri, Class<T> clazz) {
        this(new MyTestCase(baseUri), clazz);
    }

    public JFinalControllerTestBase(MyTestCase testCase, Class<T> clazz) {
        this.testCase = testCase;
        this.clazz = clazz;
    }

    public T getByIdToModel(Object id){
        return (T) ModelKit.fromJson(testCase.getById(String.valueOf(id)), clazz);
    }

    @SneakyThrows
    public String add(T model, boolean except){
        // 添加记录且能查询到
        String id = testCase.add(model);
        T received = getByIdToModel(id);
        if(except != ModelKit.compareCommonAttr(model, received)){
            logErrorInfo(except, received);
            throw new Exception("add data error.");
        }

        return id;
    }

    @SneakyThrows
    public void update(String id, T model, boolean except){
        // 更新字段成功且查询更新后的字段与更新前的字段一样
        if(testCase.update(model)){
            T received = (T) getByIdToModel(id);
            if(except != ModelKit.compareCommonAttr(model, received)){
                logErrorInfo(except, received);
                throw new Exception("add data error.");
            }
        }
    }

    @SneakyThrows
    public void delete(String id, boolean except) {
        // 删除成功且查询不到已删除的记录
        if(except == testCase.deleteById(id)){
            // 删除失败
            if(except != (null == testCase.getById(id))){
                logErrorInfo(except, !except);
                throw new Exception("[delete] failure.");
            }
        }
    }

    public void logErrorInfo(Object except, Object result){
        System.out.println("except  : " + except);
        System.out.println("received: " + result);
    }
}
