package cai.tm.hotel.hotel.coupon.service.impl;

import cai.tm.hotel.hotel.coupon.model.UserCoupon;
import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.hotel.coupon.service.HotelCouponService;
import cai.tm.hotel.hotel.coupon.model.HotelCoupon;

@Bean
public class HotelCouponServiceImpl extends BaseServiceImpl<HotelCoupon> implements HotelCouponService {

    public Page<HotelCoupon> getCouponsByUserId(ICondition condition){
        return super.findAllByCondition("hotel.coupon.getAllForUser", condition);
    }

    public Page<HotelCoupon> getPublicCoupons(ICondition condition){
        return super.findAll("hotel.coupon.getAllPublic", condition);
    }

    @Override
    public boolean addCouponToUser(UserCoupon userCoupon) {
        return userCoupon.remove("id").save();
    }

    @Override
    public boolean deleteCouponForUser(UserCoupon userCoupon) {
        return userCoupon.delete();
    }


    @Override
    public Page<HotelCoupon> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}