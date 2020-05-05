package cai.tm.hotel.hotel.base.service.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cai.tm.hotel.hotel.base.service.model.HotelService;
import cai.tm.hotel.hotel.base.service.service.HotelServiceService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/hotel/service")
public class HotelServiceApiController extends BaseApiController<HotelService> {
    // static Logger logger = LoggerFactory.getLogger(HotelServiceApiController.class);

    @Inject
    private HotelServiceService hotelServiceService;

    @ActionKey("/api/v1/hotel/service/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/hotel/service/add")
    public ApiResult save(@Para("") HotelService entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") HotelService entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/hotel/service/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/hotel/service/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<HotelService> getService() {
        return hotelServiceService;
    }

}