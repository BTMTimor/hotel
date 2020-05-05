package cn.tm.hotel.user.base.service;

import org.tm.common.base.service.BaseService;
import cn.tm.hotel.user.base.model.User;

public interface UserService extends BaseService<User>{

    User getUser(String username, String password);
}