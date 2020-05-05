package cai.tm.hotel.hotel.room.type.service.impl;

import cai.tm.hotel.hotel.room.type.model.RoomType;
import cai.tm.hotel.hotel.room.type.service.RoomTypeService;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import org.tm.common.base.model.ICondition;
import org.tm.common.base.service.impl.BaseServiceImpl;

import java.util.List;

@Bean
public class RoomTypeServiceImpl extends BaseServiceImpl<RoomType> implements RoomTypeService {

    @Override
    public Page<RoomType> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }

    @Override
    public List<RoomType> getAllRoomTypeForHotel(String hotelId) {
        return DAO.template("getAllRoomTypeForHotel", hotelId).find();
    }
}