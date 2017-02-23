package com.krt.common.base;
import java.io.Serializable;
import java.util.Date;

/**  
 * @Description: 实体基类
 * @author 殷帅
 * @date 2016年7月16日
 * @version 1.0
 */
@SuppressWarnings("serial")
public class BaseEntity implements Serializable{
	
	private Integer id;
	private Integer inserter;
	private Date insertTime;
	private Integer updater;
	private Date updateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInserter() {
		return inserter;
	}
	public void setInserter(Integer inserter) {
		this.inserter = inserter;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Integer getUpdater() {
		return updater;
	}
	public void setUpdater(Integer updater) {
		this.updater = updater;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
