package com.pony.model;

import java.io.Serializable;

public class CollectModel implements Serializable {

	private String collectUserId;
	private String collectUserName;
	private String collectTime;
	private String collectMessageId;
	private String collectId;
	private CarModel carMsg;
	public String getCollectUserId() {
		return collectUserId;
	}
	public void setCollectUserId(String collectUserId) {
		this.collectUserId = collectUserId;
	}
	public String getCollectUserName() {
		return collectUserName;
	}
	public void setCollectUserName(String collectUserName) {
		this.collectUserName = collectUserName;
	}
	public String getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}
	public String getCollectMessageId() {
		return collectMessageId;
	}
	public void setCollectMessageId(String collectMessageId) {
		this.collectMessageId = collectMessageId;
	}
	public String getCollectId() {
		return collectId;
	}
	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}
	public CarModel getCarMsg() {
		return carMsg;
	}
	public void setCarMsg(CarModel carMsg) {
		this.carMsg = carMsg;
	}
	

	

}
