package cn.tm.hotel.system.role.controller;

import cn.tm.hotel.system.role.model.SystemRole;
import cn.tm.hotel.system.role.service.SystemRoleService;
import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import io.jboot.web.controller.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tm.common.annotation.ApiController;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/system/role")
public class SystemRoleApiController extends BaseApiController<SystemRole> {
    static Logger logger = LoggerFactory.getLogger(SystemRoleApiController.class);

    @Inject
    private SystemRoleService systemRoleService;

    @ActionKey("/api/v1/system/role/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/system/role/add")
    public ApiResult save(@Para("") SystemRole entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") SystemRole entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/system/role/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/system/role/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<SystemRole> getService() {
        return systemRoleService;
    }

}