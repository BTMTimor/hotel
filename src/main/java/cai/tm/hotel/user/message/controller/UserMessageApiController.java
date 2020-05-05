package cai.tm.hotel.user.message.controller;

import cn.tm.hotel.system.message.model.Message;
import cn.tm.hotel.system.message.service.MessageService;
import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import io.jboot.web.controller.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tm.common.annotation.ApiController;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/user/message")
public class UserMessageApiController extends BaseApiController<Message> {
    static Logger logger = LoggerFactory.getLogger(UserMessageApiController.class);

    @Inject
    private MessageService messageService;

    public ApiResult index(@Para("") Message entity) {
        return super.checkAndAdd(entity);
    }

    @ActionKey("/api/v1/user/message/new")
    public ApiResult newReceivedMessage() {
        return null;
    }

    @ActionKey("/api/v1/user/message/mark")
    public ApiResult update(@Para("") Message entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/user/message/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/user/message/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<Message> getService() {
        return messageService;
    }

}