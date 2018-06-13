package com.pony.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;


public class CategoryModel {
	
	private int id;
	private String categoryName;
	private String categoryTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryTime() {
		return categoryTime;
	}
	public void setCategoryTime(String categoryTime) {
		this.categoryTime = categoryTime;
	}
	
	
	
	
	
}
