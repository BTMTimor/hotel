package cai.tm.hotel.hotel.room.type.service;

import cai.tm.hotel.hotel.room.type.model.RoomType;
import org.tm.common.base.service.BaseService;

import java.util.List;

public interface RoomTypeService extends BaseService<RoomType>{

    List<RoomType> getAllRoomTypeForHotel(String hotelId);
}