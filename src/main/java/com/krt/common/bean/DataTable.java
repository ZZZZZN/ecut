package com.krt.common.bean;

import java.io.Serializable;
import java.util.List;

/**  
 * @Description: DataTable数据返回
 * @author 殷帅
 * @date 2016年8月1日
 * @version 1.0
 */
@SuppressWarnings({"serial","rawtypes"})
public class DataTable implements Serializable{
	
	private Integer length = 10; //每页显示数据
	private Integer pageNum = 1; //页数
	private int draw; //客户端请求次数
	private Long recordsTotal; // 总数
	private Long recordsFiltered; // 条件过滤后总数
	private List data; // 数据
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public Long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer start) {
		Integer page = start/this.getLength();
		this.pageNum = page+1;
	}
	
}
