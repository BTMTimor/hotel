package cai.tm.hotel.hotel.coupon.controller;

import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.model.BaseCondition;
import org.tm.common.base.service.BaseService;
import cai.tm.hotel.hotel.coupon.model.HotelCoupon;
import cai.tm.hotel.hotel.coupon.service.HotelCouponService;
import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import org.tm.common.annotation.ApiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/hotel/coupon")
public class HotelCouponApiController extends BaseApiController<HotelCoupon> {
    // static Logger logger = LoggerFactory.getLogger(HotelCouponApiController.class);

    @Inject
    private HotelCouponService hotelCouponService;

    @ActionKey("/api/v1/hotel/coupon/get")
    public ApiResult getById(String id) {
        return super.getById(id);
    }

    @ActionKey("/api/v1/hotel/coupon/add")
    public ApiResult save(@Para("") HotelCoupon entity) {
        return super.checkAndAdd(entity);
    }

    public ApiResult update(@Para("") HotelCoupon entity) {
        return super.checkAndUpdate(entity);
    }

    @ActionKey("/api/v1/hotel/coupon/delete")
    public ApiResult deleteById(String id) {
        return super.deleteById(id);
    }

    @ActionKey("/api/v1/hotel/coupon/list")
    public ApiResult findAll(@Para("") BaseCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<HotelCoupon> getService() {
        return hotelCouponService;
    }

}