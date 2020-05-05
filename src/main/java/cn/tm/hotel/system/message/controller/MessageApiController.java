package cn.tm.hotel.system.message.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cn.tm.hotel.system.message.model.Message;
import cn.tm.hotel.system.message.service.MessageService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/message")
public class MessageApiController extends BaseApiController<Message> {
    // static Logger logger = LoggerFactory.getLogger(MessageApiController.class);

    @Inject
    private MessageService messageService;

    @ActionKey("/api/v1/message/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/message/add")
    public ApiResult save(@Para("") Message entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") Message entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/message/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/message/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<Message> getService() {
        return messageService;
    }

}