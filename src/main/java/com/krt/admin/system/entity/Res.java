package com.krt.admin.system.entity;
import com.krt.common.base.BaseEntity;

/**
 * @Description: 资源实体类
 * @author 殷帅
 * @date 2016年7月19日
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Res extends BaseEntity {
	
	private String name;//权限名称
	private String url; //url
	private Integer pid; //父id
	private String icon; //图标
	private String permission; //权限
	private Integer sortNo;//排序
	private String type;//类型
	private String target;//类型

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "Res [name=" + name + ", url=" + url + ", pid=" + pid + ", icon=" + icon + ", permission=" + permission + ", sortNo=" + sortNo + ", type=" + type + ", target=" + target + "]";
	}
	
	
}