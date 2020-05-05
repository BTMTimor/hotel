package cn.tm.hotel.system.dict.controller;

import org.tm.common.annotation.ApiController;
import cn.tm.hotel.system.dict.model.SystemDict;
import cn.tm.hotel.system.dict.service.SystemDictService;
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
@RequestMapping("/api/v1/system/dict")
public class SystemDictApiController extends BaseApiController<SystemDict> {
    // static Logger logger = LoggerFactory.getLogger(SystemDictApiController.class);

    @Inject
    private SystemDictService systemDictService;

    @ActionKey("/api/v1/system/dict/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/system/dict/add")
    public ApiResult save(@Para("") SystemDict entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") SystemDict entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/system/dict/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/system/dict/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<SystemDict> getService() {
        return systemDictService;
    }

}