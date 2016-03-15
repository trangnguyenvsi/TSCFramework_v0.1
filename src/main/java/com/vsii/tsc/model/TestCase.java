package com.vsii.tsc.model;

import java.util.List;

public class TestCase {
	private String tcID;
	private String tcDesc;
	private String tcStep;
	private List<String> tcImage;
	
	public String getTcID() {
		return tcID;
	}
	public void setTcID(String tcID) {
		this.tcID = tcID;
	}
	public String getTcDesc() {
		return tcDesc;
	}
	public void setTcDesc(String tcDesc) {
		this.tcDesc = tcDesc;
	}
	public String getTcStep() {
		return tcStep;
	}
	public void setTcStep(String tcStep) {
		this.tcStep = tcStep;
	}
	public List<String> getTcImage() {
		return tcImage;
	}
	public void setTcImage(List<String> tcImage) {
		this.tcImage = tcImage;
	}
	

}
