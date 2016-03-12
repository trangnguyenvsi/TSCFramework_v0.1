package com.vsii.tsc.guru.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteCustomerPage {
	@FindBy(linkText = "Delete Customer")
	private WebElement lnkDeleteCustomer;
	
	@FindBy(name="cusid")
	private WebElement custID;
	
	@FindBy(name="AccSubmit")
	private WebElement btnSubmit;

	public WebElement getLnkDeleteCustomer() {
		return lnkDeleteCustomer;
	}

	public void setLnkDeleteCustomer(WebElement lnkDeleteCustomer) {
		this.lnkDeleteCustomer = lnkDeleteCustomer;
	}

	public WebElement getCustID() {
		return custID;
	}

	public void setCustID(WebElement custID) {
		this.custID = custID;
	}

	public WebElement getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(WebElement btnSubmit) {
		this.btnSubmit = btnSubmit;
	}
}
