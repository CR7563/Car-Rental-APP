package com.pony.model;

import java.io.Serializable;

public class OrderModel implements Serializable {

	private String orderCreateTime;
	private String orderState;
	private String orderMessageId;
	private String orderUserId;
	private String orderUserName;
	private String orderId;
	private String orderMoney;
	
	
	public String getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	private CarModel carMessage;
	public String getOrderCreateTime() {
		return orderCreateTime;
	}
	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getOrderMessageId() {
		return orderMessageId;
	}
	public void setOrderMessageId(String orderMessageId) {
		this.orderMessageId = orderMessageId;
	}
	public String getOrderUserId() {
		return orderUserId;
	}
	public void setOrderUserId(String orderUserId) {
		this.orderUserId = orderUserId;
	}
	public String getOrderUserName() {
		return orderUserName;
	}
	public void setOrderUserName(String orderUserName) {
		this.orderUserName = orderUserName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public CarModel getCarMessage() {
		return carMessage;
	}
	public void setCarMessage(CarModel carMessage) {
		this.carMessage = carMessage;
	}

	
}
