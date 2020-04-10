package com.kong.data;

import java.io.Serializable;



public class Page implements Serializable {

	private static final long serialVersionUID = -3307670955494340405L;
	/**
	 * 请求页码
	 */
	private int pageNo;
	/**
	 * 每页请求数量
	 */
	private int pageSize;
	/**
	 * 总数量
	 */
	private int total;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * 总页数
	 */
	public int getTotalPage() {
		return (int) Math.ceil((double) total / pageSize);
	}
}