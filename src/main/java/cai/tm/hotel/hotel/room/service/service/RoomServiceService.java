package cai.tm.hotel.hotel.room.service.service;

import org.tm.common.base.service.BaseService;
import cai.tm.hotel.hotel.room.service.model.RoomEngaged;

public interface RoomServiceService extends BaseService<RoomEngaged>{

    boolean isCustomerRoom(String id, String userId);
}