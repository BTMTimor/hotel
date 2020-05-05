package cn.tm.hotel.user.base.service.impl;

import cn.tm.hotel.user.base.model.User;
import cn.tm.hotel.user.base.service.UserService;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import org.tm.common.base.model.ICondition;
import org.tm.common.base.service.impl.BaseServiceImpl;

@Bean
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Override
    public Page<User> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }

    @Override
    public User getUser(String username, String password) {
        return DAO.findFirst("findByNameAndPassword", username, password);
    }
}