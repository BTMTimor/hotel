package com.jfinal.test;

import cn.tm.hotel.system.dict.model.SystemDict;
import lombok.SneakyThrows;

/*
    author: Timor
    date: 2020/4/3 0003
*/
public class SystemDictControllerTest extends JFinalControllerTestBase<SystemDict>{
    private static final String uri = "/api/v1/system/dict";

    public SystemDictControllerTest() {
        super(uri, SystemDict.class);
    }

    public void prepareModel(SystemDict model){
        model.setKey("birthday");
        model.setValue("1122");
        model.setDescription("彭园的生日！");
    }

    public void changeModelAttrs(SystemDict model){
        model.setValue("1221");
    }

    @SneakyThrows
    public void testCurd(){
        Class<SystemDict> clazz = SystemDict.class;
        SystemDict model = TestModelFactory.newEmptyModel(clazz);

        prepareModel(model);
        // 添加测试：可能会有很多测试，但需要返回一个已添加成功的model
        String id = add(model, true);
        model.setId(id);



        // 更新测试
        changeModelAttrs(model);
        update(id, model, true);



        // 删除测试：第一次删除成功，第二次删除因为记录不存在肯定失败
        delete(id, true);
        delete(id, false);
    }

}
