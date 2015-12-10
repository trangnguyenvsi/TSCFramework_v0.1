package com.vsii.tsc.guru.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditCustomerPage {
	@FindBy(name = "cusid")
	private WebElement customerID;

	@FindBy(linkText = "Edit Customer")
	private WebElement lnkEditCustomer;

	@FindBy(name = "AccSubmit")
	private WebElement btnSubmit;

	@FindBy(xpath = "html/body/table/tbody/tr/td/table/tbody/tr[4]/td[1]")
	private WebElement customerName;

	@FindBy(xpath = "html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]")
	private WebElement txtCustomerName;

	@FindBy(xpath = "html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]")
	private WebElement txtGender;

	@FindBy(xpath = "html/body/table/tbody/tr/td/table/tbody/tr[6]/td[2]")
	private WebElement txtDob;

	@FindBy(name = "addr")
	private WebElement txtAddress;

	@FindBy(name = "city")
	private WebElement txtCity;

	@FindBy(name = "state")
	private WebElement txtState;

	@FindBy(name = "pinno")
	private WebElement txtPin;

	@FindBy(name = "telephoneno")
	private WebElement txtTelephoneNo;

	@FindBy(name = "emailid")
	private WebElement txtEmail;
	
	@FindBy(name = "sub")
	private WebElement btnEditSubmit;
	
	@FindBy(xpath=".//*[@id='customer']/tbody/tr[1]/td/p")
	private WebElement updateSuccess;

	public WebElement getUpdateSuccess() {
		return updateSuccess;
	}

	public void setUpdateSuccess(WebElement updateSuccess) {
		this.updateSuccess = updateSuccess;
	}

	public WebElement getBtnEditSubmit() {
		return btnEditSubmit;
	}

	public void setBtnEditSubmit(WebElement btnEditSubmit) {
		this.btnEditSubmit = btnEditSubmit;
	}

	public WebElement getTxtCustomerName() {
		return txtCustomerName;
	}

	public void setTxtCustomerName(WebElement txtCustomerName) {
		this.txtCustomerName = txtCustomerName;
	}

	public WebElement getTxtGender() {
		return txtGender;
	}

	public void setTxtGender(WebElement txtGender) {
		this.txtGender = txtGender;
	}

	public WebElement getTxtDob() {
		return txtDob;
	}

	public void setTxtDob(WebElement txtDob) {
		this.txtDob = txtDob;
	}

	public WebElement getTxtAddress() {
		return txtAddress;
	}

	public void setTxtAddress(WebElement txtAddress) {
		this.txtAddress = txtAddress;
	}

	public WebElement getTxtCity() {
		return txtCity;
	}

	public void setTxtCity(WebElement txtCity) {
		this.txtCity = txtCity;
	}

	public WebElement getTxtState() {
		return txtState;
	}

	public void setTxtState(WebElement txtState) {
		this.txtState = txtState;
	}

	public WebElement getTxtPin() {
		return txtPin;
	}

	public void setTxtPin(WebElement txtPin) {
		this.txtPin = txtPin;
	}

	public WebElement getTxtTelephoneNo() {
		return txtTelephoneNo;
	}

	public void setTxtTelephoneNo(WebElement txtTelephoneNo) {
		this.txtTelephoneNo = txtTelephoneNo;
	}

	public WebElement getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(WebElement txtEmail) {
		this.txtEmail = txtEmail;
	}

	public WebElement getCustomerName() {
		return customerName;
	}

	public void setCustomerName(WebElement customerName) {
		this.customerName = customerName;
	}

	public WebElement getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(WebElement btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public WebElement getLnkEditCustomer() {
		return lnkEditCustomer;
	}

	public void setLnkEditCustomer(WebElement lnkEditCustomer) {
		this.lnkEditCustomer = lnkEditCustomer;
	}

	public WebElement getCustomerID() {
		return customerID;
	}

	public void setCustomerID(WebElement customerID) {
		this.customerID = customerID;
	}
}
