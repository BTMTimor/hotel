package cai.tm.hotel.hotel.coupon.service;

import cai.tm.hotel.hotel.coupon.model.HotelCoupon;
import cai.tm.hotel.hotel.coupon.model.UserCoupon;
import com.jfinal.plugin.activerecord.Page;
import org.tm.common.base.model.ICondition;
import org.tm.common.base.service.BaseService;

public interface HotelCouponService extends BaseService<HotelCoupon>{

    Page<HotelCoupon> getCouponsByUserId(ICondition condition);

    Page<HotelCoupon> getPublicCoupons(ICondition condition);

//    List<HotelCoupon> getRoleByUserId(String id);

    boolean addCouponToUser(UserCoupon userCoupon);

    boolean deleteCouponForUser(UserCoupon userCoupon);
}