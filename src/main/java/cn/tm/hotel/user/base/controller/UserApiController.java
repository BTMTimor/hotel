package cn.tm.hotel.user.base.controller;

import cn.tm.hotel.user.base.model.User;
import cn.tm.hotel.user.base.service.UserService;
import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import io.jboot.web.controller.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tm.common.annotation.ApiController;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.api.result.ResultCode;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import org.tm.common.token.MyTokenManager;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/user")
public class UserApiController extends BaseApiController<User> {
    static Logger logger = LoggerFactory.getLogger(UserApiController.class);

    @Inject
    private UserService userService;

    @ActionKey("/api/v1/user/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/user/add")
    public ApiResult save(@Para("") User entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") User entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/user/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/user/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    // 注册
    public ApiResult register(@Para("") User entity){
        return add(entity);
    }

    // 退出登录
    public ApiResult logout(){
        MyTokenManager.clearToken(this);
        return ApiResult.success(ResultCode.SUCCESS, "退出登录成功！");
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


    @Override
    protected BaseService<User> getService() {
        return userService;
    }

}