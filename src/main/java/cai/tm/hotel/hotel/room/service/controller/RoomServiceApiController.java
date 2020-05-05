package cai.tm.hotel.hotel.room.service.controller;

import cai.tm.hotel.hotel.room.base.model.RoomCondition;
import cai.tm.hotel.hotel.room.base.service.RoomService;
import cai.tm.hotel.hotel.room.service.model.RoomEngaged;
import cai.tm.hotel.hotel.room.service.service.RoomServiceService;
import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import io.jboot.web.controller.annotation.RequestMapping;
import org.tm.common.annotation.ApiController;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/room/service")
public class RoomServiceApiController extends BaseApiController<RoomEngaged> {
    // static Logger logger = LoggerFactory.getLogger(RoomEngagedApiController.class);
    @Inject
    RoomServiceService roomServiceService;

    @Inject
    private RoomService roomService;

    @ActionKey("/api/v1/room/engaged/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    public ApiResult book(@Para("") RoomEngaged entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult unBook(String id) {
        // 先校验，防止有人干坏事
        if(roomServiceService.isCustomerRoom(id, getUserId())){
            ApiResult.successOrFailure(roomService.unBookRoom(id));
        }
        return ApiResult.failure("您未预定该房间！");
    }

    public ApiResult used(@Para("") RoomCondition condition) {
        return ApiResult.success(roomService.getUsedRoom(condition));
    }

    // 已去取消预定的房间
    public ApiResult unUsed(@Para("") RoomCondition condition) {
        return ApiResult.success(roomService.getUnUsedRoom(condition));
    }

    public ApiResult booked(@Para("") RoomCondition condition) {
//        JSONObject rawObject = getRawObject();
        return ApiResult.success(roomService.getBookedRoom(condition));
    }

    // 已去取消预定的房间
    public ApiResult unBooked(@Para("") RoomCondition condition) {
        condition.setStatus(3);
        return ApiResult.success(roomService.getUnBookedRoom(condition));
    }

    // 未被预定的房间
    public ApiResult empty(@Para("") RoomCondition condition) {
        condition.setStatus(3);
        return ApiResult.success(roomService.getUnBookedRoom(condition));
    }

    @ActionKey("/api/v1/room/booked/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<RoomEngaged> getService() {
        return null;
    }

}