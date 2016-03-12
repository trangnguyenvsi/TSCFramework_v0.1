package com.vsii.tsc.guru.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewCustomerPage {
	@FindBy(name = "name")
	private WebElement webCustomerName;

	@FindBy(xpath = "html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[2]")
	private WebElement webGender;

	@FindBy(id = "dob")
	private WebElement webDob;

	@FindBy(name = "addr")
	private WebElement webAddress;

	@FindBy(name = "city")
	private WebElement webCity;

	@FindBy(name = "state")
	private WebElement webState;

	@FindBy(name = "pinno")
	private WebElement webPin;

	@FindBy(name = "telephoneno")
	private WebElement webPhoneNo;

	@FindBy(name = "emailid")
	private WebElement webEmail;

	@FindBy(name = "password")
	private WebElement webPassword;

	@FindBy(name = "sub")
	private WebElement webSubmit;
	
	@FindBy(xpath=".//*[@id='customer']/tbody/tr[1]/td/p")
	private WebElement webRegisterSuccess;
	
	@FindBy(linkText="New Customer")
	private WebElement webNewCustomer;
	
	@FindBy(xpath="html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p")
	private WebElement titleFormNewCustomer;
	
	public WebElement getTitleFormNewCustomer() {
		return titleFormNewCustomer;
	}

	public void setTitleFormNewCustomer(WebElement titleFormNewCustomer) {
		this.titleFormNewCustomer = titleFormNewCustomer;
	}

	public WebElement getCustomerID() {
		return customerID;
	}

	public void setCustomerID(WebElement customerID) {
		this.customerID = customerID;
	}

	@FindBy(xpath=".//*[@id='customer']/tbody/tr[4]/td[2]")
	private WebElement customerID;

	public WebElement getWebNewCustomer() {
		return webNewCustomer;
	}

	public void setWebNewCustomer(WebElement webNewCustomer) {
		this.webNewCustomer = webNewCustomer;
	}

	public WebElement getWebRegisterSuccess() {
		return webRegisterSuccess;
	}

	public void setWebRegisterSuccess(WebElement webRegisterSuccess) {
		this.webRegisterSuccess = webRegisterSuccess;
	}

	public WebElement getWebCustomerName() {
		return webCustomerName;
	}

	public void setWebCustomerName(WebElement webCustomerName) {
		this.webCustomerName = webCustomerName;
	}

	public WebElement getWebGender() {
		return webGender;
	}

	public void setWebGender(WebElement webGender) {
		this.webGender = webGender;
	}

	public WebElement getWebDob() {
		return webDob;
	}

	public void setWebDob(WebElement webDob) {
		this.webDob = webDob;
	}

	public WebElement getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(WebElement webAddress) {
		this.webAddress = webAddress;
	}

	public WebElement getWebCity() {
		return webCity;
	}

	public void setWebCity(WebElement webCity) {
		this.webCity = webCity;
	}

	public WebElement getWebState() {
		return webState;
	}

	public void setWebState(WebElement webState) {
		this.webState = webState;
	}

	public WebElement getWebPin() {
		return webPin;
	}

	public void setWebPin(WebElement webPin) {
		this.webPin = webPin;
	}

	public WebElement getWebPhoneNo() {
		return webPhoneNo;
	}

	public void setWebPhoneNo(WebElement webPhoneNo) {
		this.webPhoneNo = webPhoneNo;
	}

	public WebElement getWebEmail() {
		return webEmail;
	}

	public void setWebEmail(WebElement webEmail) {
		this.webEmail = webEmail;
	}

	public WebElement getWebPassword() {
		return webPassword;
	}

	public void setWebPassword(WebElement webPassword) {
		this.webPassword = webPassword;
	}

	public WebElement getWebSubmit() {
		return webSubmit;
	}

	public void setWebSubmit(WebElement webSubmit) {
		this.webSubmit = webSubmit;
	}
}