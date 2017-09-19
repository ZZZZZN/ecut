package com.krt.admin.system.entity;


import com.krt.core.base.BaseEntity;
/**
 * @Description: 字典类型实体类
 * @date 2016年7月25日
 * @version 1.0
 */
@SuppressWarnings("serial")
public class DictionaryType extends BaseEntity {
	
	private String code;
	private String name;
	private String remark;
	private Integer sortNo;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "DictionaryType [code=" + code + ", name=" + name + ", remark=" + remark + ", sortNo=" + sortNo + "]";
	}
	
	
}