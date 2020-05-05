package cn.tm.hotel.system.message.type.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cn.tm.hotel.system.message.type.service.MessageTypeService;
import cn.tm.hotel.system.message.type.model.MessageType;

@Bean
public class MessageTypeServiceImpl extends BaseServiceImpl<MessageType> implements MessageTypeService {

    @Override
    public Page<MessageType> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}