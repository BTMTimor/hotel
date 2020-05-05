package cn.tm.hotel.system.message.model;

import io.jboot.db.annotation.Table;
import cn.tm.hotel.common.base.model.BaseMessage;

/**
 * Generated by Jboot.
 */
@Table(tableName = "message", primaryKey = "id")
public class Message extends BaseMessage<Message> {
    public static final String ID = "id"; 
    public static final String TITLE = "title"; 
    public static final String CONTENT = "content"; 
    public static final String TYPE = "type"; 
    public static final String FILE = "file"; 
    public static final String SEND_TIME = "send_time"; 
    public static final String RECIVED_TIME = "recived_time"; 
    public static final String CREATE_TIME = "create_time"; 
    public static final String UPDATE_TIME = "update_time"; 

    @Override
    public String checkForAdd() {
        this.keep(ID, TITLE, CONTENT, TYPE, FILE, SEND_TIME, RECIVED_TIME, CREATE_TIME, UPDATE_TIME);
        return super.checkForAdd();
    }

    @Override
    public String checkForUpdate() {
        this.keep(ID, TITLE, CONTENT, TYPE, FILE, SEND_TIME, RECIVED_TIME, CREATE_TIME, UPDATE_TIME);
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
