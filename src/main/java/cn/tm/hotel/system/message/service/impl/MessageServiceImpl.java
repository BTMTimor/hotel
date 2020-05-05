package cn.tm.hotel.system.message.service.impl;

import cn.tm.hotel.system.message.model.Message;
import cn.tm.hotel.system.message.service.MessageService;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import org.tm.common.base.model.ICondition;
import org.tm.common.base.service.impl.BaseServiceImpl;

import java.util.List;

@Bean
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {

    @Override
    public Page<Message> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }

    @Override
    public List<Message> getMessageByUserId(String userId) {
        // todo
        return DAO.template("getMessageByUserId", userId).find();
    }

    @Override
    public List<Message> getNewReceivedMessageByUserId(String userId) {
        // todo
        return DAO.template("getNewReceivedMessageByUserId", userId).find();
    }

    @Override
    public boolean markMessage(String messageId, int status) {
        // todo
        return false;
    }

    @Override
    public boolean deleteMessage(String id) {
        // todo
        return false;
    }
}