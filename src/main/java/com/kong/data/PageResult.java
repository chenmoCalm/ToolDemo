package com.kong.data;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

	private List<T> resultList;
	private Page page;

	public PageResult(List<T> list, Integer totalCount, Page page) {
		this.resultList = list;
		page.setTotal(totalCount);
		this.page = page;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
