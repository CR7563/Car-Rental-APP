package com.pony.model;

import java.io.Serializable;

public class NoticeModel implements Serializable{
	private String nCreatime;
	private String nContent;
	private String nid;
	private String nName;
	private String nDoctorName;
	private String nDortorId;
	
	
	
	public String getnDoctorName() {
		return nDoctorName;
	}
	public void setnDoctorName(String nDoctorName) {
		this.nDoctorName = nDoctorName;
	}
	public String getnDortorId() {
		return nDortorId;
	}
	public void setnDortorId(String nDortorId) {
		this.nDortorId = nDortorId;
	}
	public String getnCreatime() {
		return nCreatime;
	}
	public void setnCreatime(String nCreatime) {
		this.nCreatime = nCreatime;
	}
	public String getnContent() {
		return nContent;
	}
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getnName() {
		return nName;
	}
	public void setnName(String nName) {
		this.nName = nName;
	}



}
