package org.tm.common.base.model;
import com.jfinal.plugin.activerecord.IBean;
import io.jboot.db.model.JbootModel;

import java.util.Date;

/*
    author: Timor
    date: 2020/3/14 0014
*/
public class BaseModel<M extends JbootModel<M>> extends JbootModel<M> implements IBean {

    // 返回数据前过滤掉敏感字段，建议sql查询的时候不查询敏感字段
    public M filterForReturn() {
        return (M) this;
    }

    // 检查字段信息
    public String checkFiled(){
        return "";
    }

    // 检查字段并做预添加处理
    public String checkForAdd() {
        setCreateTime();
        setUpdateTime();
        return checkFiled();
    }

    // 检查字段并做预修改处理
    public String checkForUpdate() {
        setUpdateTime();
        return checkFiled();
    }

    // 没有这个字段的会出错，一般来说系统会自动调用此方法
    public void setUpdateTime(){
        set("update_time", new Date(System.currentTimeMillis()));
    }

    // 没有这个字段的会出错，一般来说系统会自动调用此方法
    public void setCreateTime(){
        set("create_time", new Date(System.currentTimeMillis()));
    }

}
