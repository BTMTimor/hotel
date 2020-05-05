package cai.tm.hotel.user.card.service.impl;

import cai.tm.hotel.user.card.model.UserCard;
import cai.tm.hotel.user.card.service.UserCardService;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import org.tm.common.base.model.ICondition;
import org.tm.common.base.service.impl.BaseServiceImpl;

@Bean
public class UserCardServiceImpl extends BaseServiceImpl<UserCard> implements UserCardService {

    @Override
    public Page<UserCard> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }

    @Override
    public UserCard getCardByUserId(String userId) {
        return DAO.template("findCardByUserId", userId).findFirst();
    }
}