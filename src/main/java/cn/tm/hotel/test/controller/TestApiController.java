package cn.tm.hotel.test.controller;

import org.tm.common.annotation.ApiController;
import cn.tm.hotel.test.model.TestModel;
import cn.tm.hotel.test.service.TestService;
import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import io.jboot.web.controller.annotation.RequestMapping;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/test")
public class TestApiController extends BaseApiController<TestModel> {
    // static Logger logger = LoggerFactory.getLogger(TestApiController.class);

    @Inject
    private TestService testService;

    public void debug(){
        renderJson(getRequest().getQueryString());
    }

    @ActionKey("/api/v1/test/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/test/add")
    public ApiResult save(@Para("") TestModel entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") TestModel entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/test/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/test/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<TestModel> getService() {
        return testService;
    }

}