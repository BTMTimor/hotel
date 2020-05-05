package cai.tm.hotel.hotel.subsystem.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cai.tm.hotel.hotel.subsystem.model.HotelSubsystem;
import cai.tm.hotel.hotel.subsystem.service.HotelSubsystemService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/hotel/subsystem")
public class HotelSubsystemApiController extends BaseApiController<HotelSubsystem> {
    // static Logger logger = LoggerFactory.getLogger(HotelSubsystemApiController.class);

    @Inject
    private HotelSubsystemService hotelSubsystemService;

    @ActionKey("/api/v1/hotel/subsystem/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/hotel/subsystem/add")
    public ApiResult save(@Para("") HotelSubsystem entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") HotelSubsystem entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/hotel/subsystem/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/hotel/subsystem/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<HotelSubsystem> getService() {
        return hotelSubsystemService;
    }

}