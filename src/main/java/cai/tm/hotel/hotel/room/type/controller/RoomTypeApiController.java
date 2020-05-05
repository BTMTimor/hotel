package cai.tm.hotel.hotel.room.type.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cai.tm.hotel.hotel.room.type.model.RoomType;
import cai.tm.hotel.hotel.room.type.service.RoomTypeService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/room/type")
public class RoomTypeApiController extends BaseApiController<RoomType> {
    // static Logger logger = LoggerFactory.getLogger(RoomTypeApiController.class);

    @Inject
    private RoomTypeService roomTypeService;

    public ApiResult index(String hotelId){
        return ApiResult.success(roomTypeService.getAllRoomTypeForHotel(hotelId));
    }

    @ActionKey("/api/v1/room/type/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/room/type/add")
    public ApiResult save(@Para("") RoomType entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") RoomType entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/room/type/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/room/type/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<RoomType> getService() {
        return roomTypeService;
    }

}