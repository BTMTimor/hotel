package cai.tm.hotel.user.group.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cai.tm.hotel.user.group.model.UserGroup;
import cai.tm.hotel.user.group.service.UserGroupService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/user/group")
public class UserGroupApiController extends BaseApiController<UserGroup> {
    // static Logger logger = LoggerFactory.getLogger(UserGroupApiController.class);

    @Inject
    private UserGroupService userGroupService;

    @ActionKey("/api/v1/user/group/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/user/group/add")
    public ApiResult save(@Para("") UserGroup entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") UserGroup entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/user/group/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/user/group/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<UserGroup> getService() {
        return userGroupService;
    }

}