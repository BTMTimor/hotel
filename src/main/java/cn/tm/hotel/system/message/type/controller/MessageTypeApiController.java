package cn.tm.hotel.system.message.type.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cn.tm.hotel.system.message.type.model.MessageType;
import cn.tm.hotel.system.message.type.service.MessageTypeService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/message/type")
public class MessageTypeApiController extends BaseApiController<MessageType> {
    // static Logger logger = LoggerFactory.getLogger(MessageTypeApiController.class);

    @Inject
    private MessageTypeService messageTypeService;

    @ActionKey("/api/v1/message/type/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/message/type/add")
    public ApiResult save(@Para("") MessageType entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") MessageType entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/message/type/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/message/type/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<MessageType> getService() {
        return messageTypeService;
    }

}