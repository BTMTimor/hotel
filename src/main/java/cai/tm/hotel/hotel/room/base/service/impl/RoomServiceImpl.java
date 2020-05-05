package cai.tm.hotel.hotel.room.base.service.impl;

import cai.tm.hotel.hotel.room.base.model.Room;
import cai.tm.hotel.hotel.room.base.model.RoomCondition;
import cai.tm.hotel.hotel.room.base.service.RoomService;
import cai.tm.hotel.hotel.room.service.model.RoomEngaged;
import cai.tm.hotel.hotel.room.service.service.RoomServiceService;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import org.tm.common.base.model.ICondition;
import org.tm.common.base.service.impl.BaseServiceImpl;

import java.util.function.Function;

@Bean
public class RoomServiceImpl extends BaseServiceImpl<Room> implements RoomService {
    @Inject
    RoomServiceService roomServiceService;

    @Override
    public Page<Room> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @Override
    public boolean bookRoom(RoomCondition condition) {
        return 1 == Db.update("hotel.room.bookRoom", condition);
    }

    @Override
    public boolean unBookRoom(String id) {
        return changeStatus(id, RoomEngaged::unBookRoom);
    }

    @Override
    public boolean checkInRoom(String id) {
        return changeStatus(id, RoomEngaged::checkInRoom);
    }

    @Override
    public boolean checkOutRoom(String id) {
        return changeStatus(id, RoomEngaged::checkOutRoom);
    }

    // 改变预定状态的函数
    protected boolean changeStatus(String id, Function<RoomEngaged, RoomEngaged> func) {
        return func.apply(new RoomEngaged().setId(id)).save();
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @Override
    public Page<Room> getBookedRoom(RoomCondition condition) {
        condition.setStatus(0);
        return findAllByCondition("hotel.room.getBookedRoom", condition);
    }

    @Override
    public Page<Room> getUnBookedRoom(RoomCondition condition) {
        condition.setStatus(3);
        return findAllByCondition("hotel.room.getUnBookedRoom", condition);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @Override
    public Page<Room> getUsedRoom(RoomCondition condition) {
        condition.setStatus(1);
        return findAllByCondition("hotel.room.getUsedRoom", condition);
    }

    @Override
    public Page<Room> getUnUsedRoom(RoomCondition condition) {
        condition.setStatus(0);
        return findAllByCondition("hotel.room.getUnUsedRoom", condition);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

}