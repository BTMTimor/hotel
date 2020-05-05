package cai.tm.hotel.user.order.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.user.order.service.UserOrderService;
import cai.tm.hotel.user.order.model.UserOrder;

@Bean
public class UserOrderServiceImpl extends BaseServiceImpl<UserOrder> implements UserOrderService {

    @Override
    public Page<UserOrder> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}