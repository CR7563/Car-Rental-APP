package com.pony.model;

import java.io.Serializable;

/**
 * 
 * @author wangxuan
 * 
 */
public class UserModel implements Serializable{

	private String uid;
	private String utime;
	private String uphone;
	private String upswd;
	private String uname;
	private String utype;
	private String ostate;
	private String oid;
	private String adminState;
	private String uaddress;
	
	private String uRealName;
	private String uEmail;
	private String uSex;
	private String uCardImg;
	private String uCarImg;
	
	private String uCode;
	private String scoreMessage = "0";
	private String uMoney;
	
	
	public String getuMoney() {
		return uMoney;
	}

	public void setuMoney(String uMoney) {
		this.uMoney = uMoney;
	}

	public String getScoreMessage() {
		return scoreMessage;
	}

	public void setScoreMessage(String scoreMessage) {
		this.scoreMessage = scoreMessage;
	}

	public String getuCode() {
		return uCode;
	}

	public void setuCode(String uCode) {
		this.uCode = uCode;
	}

	public String getuRealName() {
		return uRealName;
	}

	public void setuRealName(String uRealName) {
		this.uRealName = uRealName;
	}

	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	public String getuSex() {
		return uSex;
	}

	public void setuSex(String uSex) {
		this.uSex = uSex;
	}

	public String getuCardImg() {
		return uCardImg;
	}

	public void setuCardImg(String uCardImg) {
		this.uCardImg = uCardImg;
	}

	public String getuCarImg() {
		return uCarImg;
	}

	public void setuCarImg(String uCarImg) {
		this.uCarImg = uCarImg;
	}

	public String getUaddress() {
		return uaddress;
	}

	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}

	public String getAdminState() {
		return adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOstate() {
		return ostate;
	}

	public void setOstate(String ostate) {
		this.ostate = ostate;
	}

	public String getUtype() {
		return utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUtime() {
		return utime;
	}

	public void setUtime(String utime) {
		this.utime = utime;
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	public String getUpswd() {
		return upswd;
	}

	public void setUpswd(String upswd) {
		this.upswd = upswd;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

}
