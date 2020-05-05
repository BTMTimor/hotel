package cn.tm.hotel.system.permission.controller;

import cn.tm.hotel.system.permission.model.SystemPermission;
import cn.tm.hotel.system.permission.service.SystemPermissionService;
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
@RequestMapping("/api/v1/system/permission")
public class SystemPermissionApiController extends BaseApiController<SystemPermission> {
     static Logger logger = LoggerFactory.getLogger(SystemPermissionApiController.class);

    @Inject
    private SystemPermissionService systemPermissionService;

    @ActionKey("/api/v1/system/permission/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/system/permission/add")
    public ApiResult save(@Para("") SystemPermission entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") SystemPermission entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/system/permission/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/system/permission/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<SystemPermission> getService() {
        return systemPermissionService;
    }

}