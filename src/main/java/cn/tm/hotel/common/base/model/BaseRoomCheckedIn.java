package cn.tm.hotel.common.base.model;

import org.tm.common.base.model.BaseModel;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRoomCheckedIn<M extends BaseModel<M>> extends BaseModel<M>{

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.String getId() {
		return getStr("id");
	}

	public M setRoomId(java.lang.String roomId) {
		set("room_id", roomId);
		return (M)this;
	}

	public java.lang.String getRoomId() {
		return getStr("room_id");
	}

	public M setUserId(java.lang.String userId) {
		set("user_id", userId);
		return (M)this;
	}

	public java.lang.String getUserId() {
		return getStr("user_id");
	}

	public M setCheckedInTime(java.util.Date checkedInTime) {
		set("checked_in_time", checkedInTime);
		return (M)this;
	}

	public java.util.Date getCheckedInTime() {
		return get("checked_in_time");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
		return (M)this;
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public M setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
		return (M)this;
	}

	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

}
