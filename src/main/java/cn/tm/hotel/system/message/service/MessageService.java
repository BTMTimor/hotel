package cn.tm.hotel.system.message.service;

import org.tm.common.base.service.BaseService;
import cn.tm.hotel.system.message.model.Message;

import java.util.List;

public interface MessageService extends BaseService<Message>{

    List<Message> getMessageByUserId(String userId);

    List<Message> getNewReceivedMessageByUserId(String userId);

    boolean markMessage(String messageId, int status);

    boolean deleteMessage(String id);

}