package cai.tm.hotel.user.order.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cai.tm.hotel.user.order.model.UserOrder;
import cai.tm.hotel.user.order.service.UserOrderService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/user/order")
public class UserOrderApiController extends BaseApiController<UserOrder> {
    // static Logger logger = LoggerFactory.getLogger(UserOrderApiController.class);

    @Inject
    private UserOrderService userOrderService;

    @ActionKey("/api/v1/user/order/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/user/order/add")
    public ApiResult save(@Para("") UserOrder entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") UserOrder entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/user/order/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/user/order/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<UserOrder> getService() {
        return userOrderService;
    }

}