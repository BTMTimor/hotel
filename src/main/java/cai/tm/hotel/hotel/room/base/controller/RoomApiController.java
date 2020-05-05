package cai.tm.hotel.hotel.room.base.controller;

import cai.tm.hotel.hotel.room.base.model.Room;
import cai.tm.hotel.hotel.room.base.service.RoomService;
import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import io.jboot.web.controller.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tm.common.annotation.ApiController;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/room")
public class RoomApiController extends BaseApiController<Room> {
    static Logger logger = LoggerFactory.getLogger(RoomApiController.class);

    @Inject
    private RoomService roomService;

    @ActionKey("/api/v1/room/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/room/add")
    public ApiResult save(@Para("") Room entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") Room entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/room/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/room/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }



    // - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    // 获取已预定的酒店房间列表
    @ActionKey("hotel/room/booked")
    public ApiResult booked(String hotelId){
        return null;
    }

    // 获取未被预定的酒店房间列表
    @ActionKey("hotel/room/unbooked")
    public ApiResult unbooked(String hotelId){
        return null;
    }

    // 获取已入住的酒店房间列表
    @ActionKey("hotel/room/used")
    public ApiResult used(String hotelId){
        return null;
    }

    // 获取已预定但没有入住的酒店房间列表
    @ActionKey("hotel/room/unused")
    public ApiResult unused(String hotelId){
        return null;
    }

    // 获取房间的物品信息
    @ActionKey("hotel/room/goods")
    public ApiResult goods(String hotelId){
        return null;
    }

    // 获取房间所有的设备（智能设备等）
    @ActionKey("hotel/room/devices")
    public ApiResult devices(String hotelId){
        return null;
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    @Override
    protected BaseService<Room> getService() {
        return roomService;
    }

}