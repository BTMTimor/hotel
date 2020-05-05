package cai.tm.hotel.hotel.coupon.model;

import cn.tm.hotel.common.base.model.BaseUserRole;
import io.jboot.db.annotation.Table;

/*
    author: Timor
    date: 2020/4/6 0006
*/
@Table(tableName = "user_coupon_mapping", primaryKey = "id")
public class UserCoupon extends BaseUserRole<UserCoupon> {
}
