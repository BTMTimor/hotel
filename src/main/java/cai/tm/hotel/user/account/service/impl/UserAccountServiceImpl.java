package cai.tm.hotel.user.account.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.user.account.service.UserAccountService;
import cai.tm.hotel.user.account.model.UserAccount;

@Bean
public class UserAccountServiceImpl extends BaseServiceImpl<UserAccount> implements UserAccountService {

    @Override
    public Page<UserAccount> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}