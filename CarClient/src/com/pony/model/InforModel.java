package com.pony.model;

import java.io.Serializable;

public class InforModel implements Serializable{

	private String fCreatTime;
	private String fid;
	private String fFile;
	private String fTitle;
	private String fMessage;
	private String fAddress;
	private String collectState;
	private String focusState;
	public String getfCreatTime() {
		return fCreatTime;
	}
	public void setfCreatTime(String fCreatTime) {
		this.fCreatTime = fCreatTime;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getfFile() {
		return fFile;
	}
	public void setfFile(String fFile) {
		this.fFile = fFile;
	}
	public String getfTitle() {
		return fTitle;
	}
	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}
	public String getfMessage() {
		return fMessage;
	}
	public void setfMessage(String fMessage) {
		this.fMessage = fMessage;
	}
	public String getfAddress() {
		return fAddress;
	}
	public void setfAddress(String fAddress) {
		this.fAddress = fAddress;
	}
	public String getCollectState() {
		return collectState;
	}
	public void setCollectState(String collectState) {
		this.collectState = collectState;
	}
	public String getFocusState() {
		return focusState;
	}
	public void setFocusState(String focusState) {
		this.focusState = focusState;
	}
	
}
