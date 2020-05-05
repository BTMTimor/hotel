package cai.tm.hotel.user.operator.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.user.operator.service.UserOperatorService;
import cai.tm.hotel.user.operator.model.UserOperator;

@Bean
public class UserOperatorServiceImpl extends BaseServiceImpl<UserOperator> implements UserOperatorService {

    @Override
    public Page<UserOperator> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}