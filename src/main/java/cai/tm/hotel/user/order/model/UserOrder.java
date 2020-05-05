package cai.tm.hotel.user.order.model;

import com.jfinal.kit.StrKit;
import io.jboot.db.annotation.Table;
import cn.tm.hotel.common.base.model.BaseUserOrder;

/**
 * Generated by Jboot.
 */
@Table(tableName = "user_order", primaryKey = "id")
public class UserOrder extends BaseUserOrder<UserOrder> {
    public static final String ID = "id"; 
    public static final String AMOUNT = "amount"; 
    public static final String ACTUAL_PAYMENT = "actual_payment"; 
    public static final String COUPON_ID = "coupon_id"; 
    public static final String CHARGE_EXPLAIN = "charge_explain"; 
    public static final String DEADLINE = "deadline"; 
    public static final String STATUS = "status"; 
    public static final String CREATE_TIME = "create_time"; 
    public static final String UPDATE_TIME = "update_time"; 

    @Override
    public String checkForAdd() {
        this.keep(ID, AMOUNT, ACTUAL_PAYMENT, COUPON_ID, CHARGE_EXPLAIN, DEADLINE, STATUS, CREATE_TIME, UPDATE_TIME);
        return super.checkForAdd();
    }

    @Override
    public String checkForUpdate() {
        this.keep(ID, AMOUNT, ACTUAL_PAYMENT, COUPON_ID, CHARGE_EXPLAIN, DEADLINE, STATUS, CREATE_TIME, UPDATE_TIME);
        return super.checkForUpdate();
    }

    @Override
    public String checkFiled() {
        /*if(StrKit.isBlank(getKey())){
            return "XX不能为空！";
        }*/
        return "";
    }
}
