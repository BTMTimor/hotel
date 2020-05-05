package cai.tm.hotel.hotel.device.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cai.tm.hotel.hotel.device.model.HotelDevice;
import cai.tm.hotel.hotel.device.service.HotelDeviceService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/hotel/device")
public class HotelDeviceApiController extends BaseApiController<HotelDevice> {
    // static Logger logger = LoggerFactory.getLogger(HotelDeviceApiController.class);

    @Inject
    private HotelDeviceService hotelDeviceService;

    @ActionKey("/api/v1/hotel/device/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/hotel/device/add")
    public ApiResult save(@Para("") HotelDevice entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") HotelDevice entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/hotel/device/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/hotel/device/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<HotelDevice> getService() {
        return hotelDeviceService;
    }

}