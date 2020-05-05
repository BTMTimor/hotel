package cai.tm.hotel.user.card.service;

import org.tm.common.base.service.BaseService;
import cai.tm.hotel.user.card.model.UserCard;

public interface UserCardService extends BaseService<UserCard>{

    // 通过用户id获取card信息
    UserCard getCardByUserId(String userId);
}