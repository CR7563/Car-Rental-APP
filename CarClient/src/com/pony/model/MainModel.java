package com.pony.model;

import java.io.Serializable;
import java.util.List;

public class MainModel implements Serializable{

	private List<InforModel> flagFood;
	private List<InforModel> flagTop;
	public List<InforModel> getFlagFood() {
		return flagFood;
	}
	public void setFlagFood(List<InforModel> flagFood) {
		this.flagFood = flagFood;
	}
	public List<InforModel> getFlagTop() {
		return flagTop;
	}
	public void setFlagTop(List<InforModel> flagTop) {
		this.flagTop = flagTop;
	}

	

}
