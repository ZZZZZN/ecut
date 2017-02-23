package com.krt.admin.system.entity;


import com.krt.common.base.BaseEntity;

/**
 * @Description: 字典实体类
 * @author 殷帅
 * @date 2016年7月25日
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Dictionary extends BaseEntity {

    private String type;
    private Integer pid;
    private String code;
    private String name;
    private String remark;
    private Integer sortNo;

   
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

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
		return "Dictionary [type=" + type + ", pid=" + pid + ", code=" + code + ", name=" + name + ", remark=" + remark + ", sortNo=" + sortNo + "]";
	}
    
    
}