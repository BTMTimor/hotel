package cai.tm.hotel.user.permission.controller;

import cn.tm.hotel.system.permission.model.UserPermission;
import cn.tm.hotel.system.permission.service.SystemPermissionService;
import com.jfinal.aop.Inject;
import io.jboot.web.controller.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tm.common.annotation.ApiController;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.service.BaseService;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/user/permission")
public class UserPermissionApiController extends BaseApiController<UserPermission> {
    static Logger logger = LoggerFactory.getLogger(UserPermissionApiController.class);

    @Inject
    private SystemPermissionService permissionService;

    public ApiResult index() {
        return ApiResult.success(permissionService.getPermissionByUserId(getUserId()));
    }

    @Override
    protected BaseService<UserPermission> getService() {
        return null;
    }

}