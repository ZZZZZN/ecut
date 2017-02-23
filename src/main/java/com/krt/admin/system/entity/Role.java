package com.krt.admin.system.entity;

import com.krt.common.base.BaseEntity;


/**
 * @Description: 角色实体类
 * @author 殷帅
 * @date 2016年5月20日
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Role extends BaseEntity{
	
	private String roleName;//角色名
	private String roleCode;//角色编码
	private String status;//状态 0：正常  1：禁用
	private String remark;//备注
	private Integer sortNo;//排序
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	
	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", roleCode=" + roleCode + ", status=" + status + ", remark=" + remark + ", sortNo=" + sortNo + "]";
	}
	
}