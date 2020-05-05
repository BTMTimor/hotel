package com.jfinal.test;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.ext.test.ControllerTestCase;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

import java.io.IOException;

/*
    author: Timor
    date: 2020/3/29 0029
*/
@RequestMapping("/testing")
public class TestingController extends JbootController {

    public void index() throws IOException {
        String uri = "/api/v1/test/get";
        String paras = "id=1122";
        String result = new JFinalTestKit().get(uri, paras);
        renderJson(result);
    }

    public void testCase() throws IOException {
        ControllerTestCase testCase = new ControllerTestCase();
        testCase.use("/api/v1/test/get?id=1221");
        String ret = testCase.invoke();

        JSONObject jsonRet = JSONObject.parseObject(ret);
        renderJson(jsonRet);
    }
}
