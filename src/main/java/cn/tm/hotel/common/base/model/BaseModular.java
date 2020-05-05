package cn.tm.hotel.common.base.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseModular<M extends BaseModular<M>> extends Model<M> implements IBean {

	/**
	 * id
	 */
	public void setId(Integer id) {
		set("id", id);
	}
	
	/**
	 * id
	 */
	public Integer getId() {
		return getInt("id");
	}

	/**
	 * 模块名称
	 */
	public void setName(String name) {
		set("name", name);
	}
	
	/**
	 * 模块名称
	 */
	public String getName() {
		return getStr("name");
	}

	/**
	 * 前台模块名
	 */
	public void setWebMode(String webMode) {
		set("webMode", webMode);
	}
	
	/**
	 * 前台模块名
	 */
	public String getWebMode() {
		return getStr("webMode");
	}

	/**
	 * 状态：禁用：-1；测试：0；启用：1；
	 */
	public void setStatus(Integer status) {
		set("status", status);
	}
	
	/**
	 * 状态：禁用：-1；测试：0；启用：1；
	 */
	public Integer getStatus() {
		return getInt("status");
	}

}
