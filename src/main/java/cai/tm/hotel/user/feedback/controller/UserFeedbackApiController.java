package cai.tm.hotel.user.feedback.controller;

import cai.tm.hotel.user.feedback.model.UserFeedback;
import cai.tm.hotel.user.feedback.service.UserFeedbackService;
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
@RequestMapping("/api/v1/user/feedback")
public class UserFeedbackApiController extends BaseApiController<UserFeedback> {
    // static Logger logger = LoggerFactory.getLogger(UserFeedbackApiController.class);

    @Inject
    private UserFeedbackService userFeedbackService;

    // 用户反馈
    public ApiResult index(@Para("") UserFeedback entity){
        return this.save(entity);
    }

    @ActionKey("/api/v1/user/feedback/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/user/feedback/add")
    public ApiResult save(@Para("") UserFeedback entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") UserFeedback entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/user/feedback/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/user/feedback/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<UserFeedback> getService() {
        return userFeedbackService;
    }

}