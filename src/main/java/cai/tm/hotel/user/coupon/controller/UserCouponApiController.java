package cai.tm.hotel.user.coupon.controller;

import cai.tm.hotel.hotel.coupon.model.CouponCondition;
import cai.tm.hotel.hotel.coupon.model.UserCoupon;
import cai.tm.hotel.hotel.coupon.service.HotelCouponService;
import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.paragetter.Para;
import io.jboot.web.controller.annotation.RequestMapping;
import org.tm.common.annotation.ApiController;
import org.tm.common.base.api.result.ApiResult;
import org.tm.common.base.controller.BaseApiController;
import org.tm.common.base.service.BaseService;

@SuppressWarnings("unused")
@ApiController
@RequestMapping("/api/v1/user/coupon")
public class UserCouponApiController extends BaseApiController<UserCoupon> {
    // static Logger logger = LoggerFactory.getLogger(UserCouponApiController.class);

    @Inject
    private HotelCouponService hotelCouponService;

    @ActionKey("/api/v1/user/coupon/add")
    public ApiResult save(@Para("") UserCoupon entity) {
        return ApiResult.successOrFailure(hotelCouponService.addCouponToUser(entity));
    }

    public ApiResult delete(@Para("") UserCoupon entity) {
        return ApiResult.successOrFailure(hotelCouponService.deleteCouponForUser(entity));
    }

    @ActionKey("/api/v1/user/coupon/list")
    public ApiResult findAll(@Para("") CouponCondition condition) {
        return super.findAll(condition);
    }

    @Override
    protected BaseService<UserCoupon> getService() {
        return null;
    }

}