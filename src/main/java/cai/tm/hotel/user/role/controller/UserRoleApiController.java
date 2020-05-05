package cai.tm.hotel.user.role.controller;

import cn.tm.hotel.system.role.model.UserRole;
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
import org.tm.common.base.service.BaseService;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/user/role")
public class UserRoleApiController extends BaseApiController<UserRole> {
     static Logger logger = LoggerFactory.getLogger(UserRoleApiController.class);

    @Inject
    private SystemRoleService roleService;

    @ActionKey("/api/v1/user/role/add")
    public ApiResult save(@Para("") UserRole entity) {
        return ApiResult.successOrFailure(roleService.addRoleToUser(entity));
    }

//    @ActionKey("/api/v1/user/role/deleteById")
    public ApiResult deleteById(String id) {
        return ApiResult.successOrFailure(roleService.deleteById(id));
    }

//    @ActionKey("/api/v1/user/role/delete")
    public ApiResult delete(@Para("") UserRole entity) {
        return ApiResult.successOrFailure(roleService.deleteRoleForUser(entity));
    }

    @ActionKey("/api/v1/user/role/list")
    public ApiResult findAll() {
        return ApiResult.success(roleService.getRoleByUserId(getUserId()));
    }

    @Override
    protected BaseService<UserRole> getService() {
        return null;
    }

}