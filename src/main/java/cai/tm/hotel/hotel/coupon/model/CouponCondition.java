package cai.tm.hotel.hotel.coupon.model;

import lombok.Getter;
import lombok.Setter;
import org.tm.common.base.model.BaseCondition;

/*
    author: Timor
    date: 2020/4/7 0007
*/
public class CouponCondition extends BaseCondition {
    @Getter @Setter
    private String userId;

    @Override
    public String check() {
        return null;
    }
}
