package cai.tm.hotel.user.account.flow.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cai.tm.hotel.user.account.flow.model.UserAccountFlow;
import cai.tm.hotel.user.account.flow.service.UserAccountFlowService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/user/account/flow")
public class UserAccountFlowApiController extends BaseApiController<UserAccountFlow> {
    // static Logger logger = LoggerFactory.getLogger(UserAccountFlowApiController.class);

    @Inject
    private UserAccountFlowService userAccountFlowService;

    @ActionKey("/api/v1/user/account/flow/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/user/account/flow/add")
    public ApiResult save(@Para("") UserAccountFlow entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") UserAccountFlow entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/user/account/flow/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/user/account/flow/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<UserAccountFlow> getService() {
        return userAccountFlowService;
    }

}