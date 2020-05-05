package cai.tm.hotel.hotel.room.base.service;

import cai.tm.hotel.hotel.room.base.model.Room;
import cai.tm.hotel.hotel.room.base.model.RoomCondition;
import com.jfinal.plugin.activerecord.Page;
import org.tm.common.base.service.BaseService;

public interface RoomService extends BaseService<Room>{

    // 预定房间
    boolean bookRoom(RoomCondition condition);

    // 取消预定房间
    boolean unBookRoom(String id);

    // 入住
    boolean checkInRoom(String id);

    // 退房
    boolean checkOutRoom(String id);

    // 获取已预定的房间
    Page<Room> getBookedRoom(RoomCondition condition);

    // 获取未被预定的房间
    Page<Room> getUnBookedRoom(RoomCondition condition);

    // 获取预定且已入住的房间
    Page<Room> getUsedRoom(RoomCondition condition);

    // 获取预定但未入住的房间
    Page<Room> getUnUsedRoom(RoomCondition condition);

}