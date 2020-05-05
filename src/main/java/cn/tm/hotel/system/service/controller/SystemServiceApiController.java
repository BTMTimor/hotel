package cn.tm.hotel.system.service.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cn.tm.hotel.system.service.model.SystemService;
import cn.tm.hotel.system.service.service.SystemServiceService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/system/service")
public class SystemServiceApiController extends BaseApiController<SystemService> {
    // static Logger logger = LoggerFactory.getLogger(SystemServiceApiController.class);

    @Inject
    private SystemServiceService systemServiceService;

    @ActionKey("/api/v1/system/service/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/system/service/add")
    public ApiResult save(@Para("") SystemService entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") SystemService entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/system/service/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/system/service/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<SystemService> getService() {
        return systemServiceService;
    }

}