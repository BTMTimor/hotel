package cai.tm.hotel.hotel.room.base.model;

import io.jboot.db.annotation.Table;
import cn.tm.hotel.common.base.model.BaseRoom;

/**
 * Generated by Jboot.
 */
@Table(tableName = "room", primaryKey = "id")
public class Room extends BaseRoom<Room> {
    public static final String ID = "id"; 
    public static final String NAME = "name"; 
    public static final String TYPE_ID = "type_id"; 
    public static final String CREATE_TIME = "create_time"; 
    public static final String UPDATE_TIME = "update_time"; 

    @Override
    public String checkForAdd() {
        this.keep(ID, NAME, TYPE_ID, CREATE_TIME, UPDATE_TIME);
        return super.checkForAdd();
    }

    @Override
    public String checkForUpdate() {
        this.keep(ID, NAME, TYPE_ID, CREATE_TIME, UPDATE_TIME);
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
