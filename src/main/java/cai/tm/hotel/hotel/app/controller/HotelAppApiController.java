package cai.tm.hotel.hotel.app.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cai.tm.hotel.hotel.app.model.HotelApp;
import cai.tm.hotel.hotel.app.service.HotelAppService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/hotel/app")
public class HotelAppApiController extends BaseApiController<HotelApp> {
    // static Logger logger = LoggerFactory.getLogger(HotelAppApiController.class);

    @Inject
    private HotelAppService hotelAppService;

    @ActionKey("/api/v1/hotel/app/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/hotel/app/add")
    public ApiResult save(@Para("") HotelApp entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") HotelApp entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/hotel/app/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/hotel/app/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<HotelApp> getService() {
        return hotelAppService;
    }

}