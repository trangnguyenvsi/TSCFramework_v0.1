package com.vsii.tsc.guru.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewPageLogin {
	@FindBy(className = "barone")
	private WebElement pageTitle;

	@FindBy(name = "uid")
	private WebElement txtUID;

	@FindBy(name = "password")
	private WebElement txtPass;

	@FindBy(name = "btnLogin")
	private WebElement btnLogin;

	@FindBy(xpath = "html/body/table/tbody/tr/td/table/tbody/tr[3]/td")
	private WebElement managerID;

	public WebElement getManagerID() {
		return managerID;
	}

	public void setManagerID(WebElement managerID) {
		this.managerID = managerID;
	}

	public WebElement getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(WebElement pageTitle) {
		this.pageTitle = pageTitle;
	}

	public WebElement getTxtUID() {
		return txtUID;
	}

	public void setTxtUID(WebElement txtUID) {
		this.txtUID = txtUID;
	}

	public WebElement getTxtPass() {
		return txtPass;
	}

	public void setTxtPass(WebElement txtPass) {
		this.txtPass = txtPass;
	}

	public WebElement getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(WebElement btnLogin) {
		this.btnLogin = btnLogin;
	}
}
