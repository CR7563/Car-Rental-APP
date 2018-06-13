package com.pony.model;

import java.io.Serializable;

public class CarModel implements Serializable{
	private String carId;
	private String carTypeId;
	private String stateMessage;
	private String carBrand;
	private String carTime;
	private String carNo;
	private String carMoney;
	private String carMessage;
	private String carTypeName;
	private String carImage;
	private boolean collectState =false;
	private String scoreMsg;
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(String carTypeId) {
		this.carTypeId = carTypeId;
	}
	public String getStateMessage() {
		return stateMessage;
	}
	public void setStateMessage(String stateMessage) {
		this.stateMessage = stateMessage;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarTime() {
		return carTime;
	}
	public void setCarTime(String carTime) {
		this.carTime = carTime;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCarMoney() {
		return carMoney;
	}
	public void setCarMoney(String carMoney) {
		this.carMoney = carMoney;
	}
	public String getCarMessage() {
		return carMessage;
	}
	public void setCarMessage(String carMessage) {
		this.carMessage = carMessage;
	}
	public String getCarTypeName() {
		return carTypeName;
	}
	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}
	public String getCarImage() {
		return carImage;
	}
	public void setCarImage(String carImage) {
		this.carImage = carImage;
	}
	public boolean isCollectState() {
		return collectState;
	}
	public void setCollectState(boolean collectState) {
		this.collectState = collectState;
	}
	public String getScoreMsg() {
		return scoreMsg;
	}
	public void setScoreMsg(String scoreMsg) {
		this.scoreMsg = scoreMsg;
	}
	


}
