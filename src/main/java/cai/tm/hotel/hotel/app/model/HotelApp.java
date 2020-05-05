package cai.tm.hotel.hotel.app.model;

import com.jfinal.kit.StrKit;
import io.jboot.db.annotation.Table;
import cn.tm.hotel.common.base.model.BaseHotelApp;

/**
 * Generated by Jboot.
 */
@Table(tableName = "hotel_app", primaryKey = "id")
public class HotelApp extends BaseHotelApp<HotelApp> {
    public static final String ID = "id"; 
    public static final String NAME = "name"; 
    public static final String DESCRIBE = "describe"; 
    public static final String HOTEL_ID = "hotel_id"; 
    public static final String TYPE = "type"; 
    public static final String STATUS = "status"; 
    public static final String CREATE_TIME = "create_time"; 
    public static final String UPDATE_TIME = "update_time"; 

    @Override
    public String checkForAdd() {
        this.keep(ID, NAME, DESCRIBE, HOTEL_ID, TYPE, STATUS, CREATE_TIME, UPDATE_TIME);
        return super.checkForAdd();
    }

    @Override
    public String checkForUpdate() {
        this.keep(ID, NAME, DESCRIBE, HOTEL_ID, TYPE, STATUS, CREATE_TIME, UPDATE_TIME);
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
