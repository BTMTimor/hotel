package cai.tm.hotel.user.operator.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cai.tm.hotel.user.operator.model.UserOperator;
import cai.tm.hotel.user.operator.service.UserOperatorService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/user/operator")
public class UserOperatorApiController extends BaseApiController<UserOperator> {
    // static Logger logger = LoggerFactory.getLogger(UserOperatorApiController.class);

    @Inject
    private UserOperatorService userOperatorService;

    @ActionKey("/api/v1/user/operator/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/user/operator/add")
    public ApiResult save(@Para("") UserOperator entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") UserOperator entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/user/operator/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/user/operator/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<UserOperator> getService() {
        return userOperatorService;
    }

}