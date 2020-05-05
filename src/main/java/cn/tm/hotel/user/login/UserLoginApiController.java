package cn.tm.hotel.user.login;


import cn.tm.hotel.user.base.model.User;
import cn.tm.hotel.user.base.service.UserService;
import cn.tm.hotel.user.face.controller.UserFaceController;
import cn.tm.hotel.user.face.service.impl.UserFaceServiceImpl;
import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.StrKit;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tm.common.annotation.ApiController;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.api.result.ResultCode;
import org.tm.common.token.MyTokenManager;

import java.util.concurrent.ExecutionException;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/user/login")
public class UserLoginApiController extends JbootController {
    static Logger logger = LoggerFactory.getLogger(UserLoginApiController.class);

    @Inject
    private UserService userService;

    public void index() {
        forwardAction("/api/v1/user/login/account");
    }

    private void loginProcessor(String userId){
        MyTokenManager.createToken(this, userId);
    }

    public ApiResult account(String username, String password, String code) {
        if(StrKit.isBlank(code)){
            return ApiResult.failure(ResultCode.PARAM_IS_MISSING, "需要提交验证码！");
        }
        // 验证码校验逻辑
        // todo
//        validateToken()

        User user = userService.getUser(username, password);
        if(null != user){
            loginProcessor(user.getId());
            System.out.println("[user/login] " + (String) getJwtAttr("user_id"));
            return ApiResult.success(user.getId());
        }
        return ApiResult.failure(ResultCode.USER_LOGIN_ERROR);
    }

    public ApiResult face(String faceInfo) {
        return UserFaceController.faceProcessor(faceInfo, (imageInfo)->{
            ApiResult result;
            try {
                String userId = new UserFaceServiceImpl().getUserIdByFace(imageInfo);
                loginProcessor(userId);
                result = ApiResult.success(userId);
            } catch (ExecutionException | InterruptedException e) {
                result = ApiResult.failure(ResultCode.ERROR, "获取人脸信息失败！");
            }
            return result;
        });
    }

    public ApiResult fingerprint(String fingerprint) {
        // todo
        return ApiResult.failure(ResultCode.SERVICE_IS_INVALID);
    }

    public ApiResult third() {
        // todo
        return ApiResult.failure(ResultCode.SERVICE_IS_INVALID);
    }

}
