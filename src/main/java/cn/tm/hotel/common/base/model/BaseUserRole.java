package cn.tm.hotel.common.base.model;

import org.tm.common.base.model.BaseModel;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUserRole<M extends BaseModel<M>> extends BaseModel<M>{

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.String getId() {
		return getStr("id");
	}

	public M setUserId(java.lang.String userId) {
		set("user_d", userId);
		return (M)this;
	}

	public java.lang.String getUserId() {
		return getStr("user_id");
	}

	public M setRoleId(java.lang.String roleId) {
		set("role_id", roleId);
		return (M)this;
	}

	public java.lang.String getRoleId() {
		return getStr("role_id");
	}

	public M setCreatorId(java.lang.String creatorId) {
		set("creator_id", creatorId);
		return (M)this;
	}

	public java.lang.String getCreatorId() {
		return getStr("creator_id");
	}

	public M setStatus(java.lang.Integer status) {
		set("status", status);
		return (M)this;
	}

	public java.lang.Integer getStatus() {
		return getInt("status");
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
