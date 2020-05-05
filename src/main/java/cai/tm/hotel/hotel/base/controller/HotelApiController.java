package cai.tm.hotel.hotel.base.controller;

import cai.tm.hotel.hotel.base.model.Hotel;
import cai.tm.hotel.hotel.base.service.HotelService;
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
@RequestMapping("/api/v1/hotel")
public class HotelApiController extends BaseApiController<Hotel> {
    static Logger logger = LoggerFactory.getLogger(HotelApiController.class);

    @Inject
    private HotelService hotelService;

    @ActionKey("/api/v1/hotel/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/hotel/add")
    public ApiResult save(@Para("") Hotel entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") Hotel entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/hotel/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/hotel/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<Hotel> getService() {
        return hotelService;
    }

}