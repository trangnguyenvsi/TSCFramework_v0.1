package com.vsii.tsc.guru.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagerPage {
	// Identify Manger ID
	@FindBy(xpath = "html/body/table/tbody/tr/td/table/tbody/tr[3]/td")
	private WebElement managerID;

	public WebElement getManagerID() {
		return managerID;
	}

	public void setManagerID(WebElement managerID) {
		this.managerID = managerID;
	}
}
