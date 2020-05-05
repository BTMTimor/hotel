package cai.tm.hotel.hotel.room.service.service.impl;

import com.jfinal.plugin.activerecord.Db;
import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.hotel.room.service.service.RoomServiceService;
import cai.tm.hotel.hotel.room.service.model.RoomEngaged;

@Bean
public class RoomServiceServiceImpl extends BaseServiceImpl<RoomEngaged> implements RoomServiceService {

    @Override
    public Page<RoomEngaged> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }

    @Override
    public boolean isCustomerRoom(String id, String userId) {
        return 1 == Db.template("hotel.room.isCustomerRoom", id, userId).queryInt();
    }
}