package cai.tm.hotel.user.card.controller;

import cai.tm.hotel.user.card.model.UserCard;
import cai.tm.hotel.user.card.service.UserCardService;
import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import io.jboot.web.controller.annotation.RequestMapping;
import org.tm.common.annotation.ApiController;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/user/card")
public class UserCardApiController extends BaseApiController<UserCard> {
    // static Logger logger = LoggerFactory.getLogger(UserCardApiController.class);

    @Inject
    private UserCardService userCardService;

    // 获取用户的卡组（虚拟卡、门禁卡等）
    public ApiResult index(){
        UserCard cardByUserId = userCardService.getCardByUserId(getUserId());
        if(null == cardByUserId){
            return ApiResult.failure("用户没有启用卡！");
        }
        return ApiResult.success(cardByUserId);
    }

    @ActionKey("/api/v1/user/card/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/user/card/add")
    public ApiResult save(@Para("") UserCard entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") UserCard entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/user/card/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/user/card/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<UserCard> getService() {
        return userCardService;
    }

}