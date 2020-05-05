package cai.tm.hotel.user.group.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.user.group.service.UserGroupService;
import cai.tm.hotel.user.group.model.UserGroup;

@Bean
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroup> implements UserGroupService {

    @Override
    public Page<UserGroup> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}