package com.vsii.tsc.model;

import java.util.List;

public class TestCase {
	private String tcID;
	private String tcDesc;
	private String tcPrec;
	private String tcStep;
	private String tcExpt;
	private List<TCImageResults> tcImageResults;
	
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
	public String getTcPrec() {
		return tcPrec;
	}
	public void setTcPrec(String tcPrec) {
		this.tcPrec = tcPrec;
	}
	public String getTcStep() {
		return tcStep;
	}
	public void setTcStep(String tcStep) {
		this.tcStep = tcStep;
	}
	public String getTcExpt() {
		return tcExpt;
	}
	public void setTcExpt(String tcExpt) {
		this.tcExpt = tcExpt;
	}
	public List<TCImageResults> getTcImageResults() {
		return tcImageResults;
	}
	public void setTcImageResults(List<TCImageResults> tcImageResults) {
		this.tcImageResults = tcImageResults;
	}
	
	
	

	
	
	

}
