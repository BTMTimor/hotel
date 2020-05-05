package cai.tm.hotel.user.account.flow.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.user.account.flow.service.UserAccountFlowService;
import cai.tm.hotel.user.account.flow.model.UserAccountFlow;

@Bean
public class UserAccountFlowServiceImpl extends BaseServiceImpl<UserAccountFlow> implements UserAccountFlowService {

    @Override
    public Page<UserAccountFlow> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}