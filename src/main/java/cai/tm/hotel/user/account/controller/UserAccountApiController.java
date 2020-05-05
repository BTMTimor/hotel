package cai.tm.hotel.user.account.controller;

import cai.tm.hotel.user.account.model.UserAccount;
import cai.tm.hotel.user.account.service.UserAccountService;
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
@RequestMapping("/api/v1/user/account")
public class UserAccountApiController extends BaseApiController<UserAccount> {
    // static Logger logger = LoggerFactory.getLogger(UserAccountApiController.class);

    @Inject
    private UserAccountService userAccountService;

    public void index(){
        renderJson(getById(getUserId()));
    }

    @ActionKey("/api/v1/user/account/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/user/account/add")
    public ApiResult save(@Para("") UserAccount entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") UserAccount entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/user/account/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/user/account/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<UserAccount> getService() {
        return userAccountService;
    }

}