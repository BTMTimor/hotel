package cn.tm.hotel.test.controller;

import org.tm.common.annotation.ApiController;
import cn.tm.hotel.test.model.TestModel;
import com.jfinal.test.SystemDictControllerTest;
import io.jboot.web.controller.annotation.RequestMapping;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.service.BaseService;

/*
    author: Timor
    date: 2020/4/3 0003
*/
@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/test")
public class ApiTest extends BaseApiController<TestModel> {

    public void curd(){
        new SystemDictControllerTest().testCurd();
        renderJson(true);
    }

    public void debug(){
        renderJson(getRequest().getQueryString());
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @Override
    protected BaseService<TestModel> getService() {
        return null;
    }

}
