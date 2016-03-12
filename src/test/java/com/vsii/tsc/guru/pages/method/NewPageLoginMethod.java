package com.vsii.tsc.guru.pages.method;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.vsii.tsc.guru.pages.NewPageLogin;

public class NewPageLoginMethod {
	Logger log = Logger.getLogger("trunghung");
	WebDriver driver;
	NewPageLogin objLogin = new NewPageLogin();
	
	public NewPageLoginMethod(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, objLogin);
	log.debug("Initiate web driver");
	}
	
	// Enter username in UserID textbox
	public void enterUserID(String txtUsername) {
		objLogin.getTxtUID().sendKeys(txtUsername);
		log.debug("Set username");
	}
	
	// Enter password in password textbox
	public void enterPassword(String txtPass) {
		objLogin.getTxtPass().sendKeys(txtPass);
		log.debug("Set password");
	}
	
	// Click login button
	public void clickLogin() {
		objLogin.getBtnLogin().click();
		log.debug("Click login button");
	}
	
	// Get the title of Login page
	public String getLoginTitle() {
		log.debug("Get web title text");
		return objLogin.getPageTitle().getText();
	}
	
	// Log in manager page
	public void loginToManagerPage(String username, String password) {
		objLogin.getTxtUID().sendKeys(username);
		objLogin.getTxtPass().sendKeys(password);
		objLogin.getBtnLogin().click();
		log.debug("Login to Manager page");
	}

	// Get Manager ID in Manager page
	public String getManagerIDInManagerPage() {
		log.debug("Get Manager ID in manager page");
		return objLogin.getManagerID().getText();
	}
	
}









