package com.vsii.tsc.guru.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	// Identify Web element username
	@FindBy(name = "uid")
	private WebElement webUsername;

	// Identify Web element password
	@FindBy(name = "password")
	private WebElement webPassword;

	// Identify Web element title text
	@FindBy(className = "barone")
	private WebElement webTitleText;

	// Identify Reset button
	@FindBy(name = "btnReset")
	private WebElement webReset;

	// Identify Submit button
	@FindBy(name = "btnLogin")
	private WebElement webLogin;

	// Identify UserID Alert
	@FindBy(id = "message23")
	private WebElement webAlertUserID;

	// Identify Password Alert
	@FindBy(id = "message18")
	private WebElement webAlertPassword;
	
	// Identify Manger ID
	@FindBy(xpath="html/body/table/tbody/tr/td/table/tbody/tr[3]/td")
	private WebElement managerID;

	public WebElement getManagerID() {
		return managerID;
	}

	public void setManagerID(WebElement managerID) {
		this.managerID = managerID;
	}

	public WebElement getWebUsername() {
		return webUsername;
	}

	public void setWebUsername(WebElement webUsername) {
		this.webUsername = webUsername;
	}

	public WebElement getWebPassword() {
		return webPassword;
	}

	public void setWebPassword(WebElement webPassword) {
		this.webPassword = webPassword;
	}

	public WebElement getWebTitleText() {
		return webTitleText;
	}

	public void setWebTitleText(WebElement webTitleText) {
		this.webTitleText = webTitleText;
	}

	public WebElement getWebReset() {
		return webReset;
	}

	public void setWebReset(WebElement webReset) {
		this.webReset = webReset;
	}

	public WebElement getWebLogin() {
		return webLogin;
	}

	public void setWebLogin(WebElement webLogin) {
		this.webLogin = webLogin;
	}

	public WebElement getWebAlertUserID() {
		return webAlertUserID;
	}

	public void setWebAlertUserID(WebElement webAlertUserID) {
		this.webAlertUserID = webAlertUserID;
	}

	public WebElement getWebAlertPassword() {
		return webAlertPassword;
	}

	public void setWebAlertPassword(WebElement webAlertPassword) {
		this.webAlertPassword = webAlertPassword;
	}
	
	@FindBy(linkText="Log out")
	private WebElement webLogOut;

	public WebElement getWebLogOut() {
		return webLogOut;
	}

	public void setWebLogOut(WebElement webLogOut) {
		this.webLogOut = webLogOut;
	}
}
