package com.vsii.tsc.guru.pages.method;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.vsii.tsc.guru.pages.LoginPage;

public class LoginPageMethod {	
	Logger log = Logger.getLogger("trunghung");
	// Create new Web Driver variable
	WebDriver driver;
	// Create new WebElementLogin object
	LoginPage objLogin = new LoginPage();

	// Initialize all web element
	public LoginPageMethod(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, objLogin);
		log.debug("Initiate web driver");
	}

	// Enter user name in UserID text box
	public void setUserID(String txtUsername) {		
		objLogin.getWebUsername().sendKeys(txtUsername);
		log.debug("Set username");
	}

	// Enter password in password text box
	public void setPassword(String txtPassword) {		
		objLogin.getWebPassword().sendKeys(txtPassword);
		log.debug("Set password");
	}

	// Click Reset button
	public void clickReset() {
		objLogin.getWebReset().click();
		log.debug("Click reset button");
	}

	// Click Login button
	public void clickLogin() {
		objLogin.getWebLogin().click();
		log.debug("Click login button");
	}

	// Click mouse in UserID text box
	public void clickUserID() {
		objLogin.getWebUsername().click();
		log.debug("Click mouse in user ID text box");
	}

	// Click mouse in Password text box
	public void clickPassword() {
		objLogin.getWebPassword().click();
		log.debug("Click mouse in password text box");
	}

	// Get the title of Login page
	public String getLoginTitle() {		
		log.debug("Get web title text");
		return objLogin.getWebTitleText().getText();
	}

	// Get the alert text when leaving User ID text box blank
	public String getUserIdAlert() {
		log.debug("Get User ID alert");
		return objLogin.getWebAlertUserID().getText();
	}

	// Get the alert text when leaving Password text box blank
	public String getPasswordAlert() {
		log.debug("Get password alert");
		return objLogin.getWebAlertPassword().getText();
	}

	// Get the prompt popup when both leaving UserID and/or Password text box
	// blank and click Submit button
	public String getPopupText() {
		Alert alert = driver.switchTo().alert();
		log.debug("Get popup text");
		return alert.getText();		
	}

	// Close prompt popup
	public void closePopup() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
		log.debug("Close popup");
	}

	// Get User ID in Login page
	public String getUserID() {
		log.debug("Get User ID");
		return objLogin.getWebUsername().getText();
	}

	// Get Password in Login page
	public String getPassword() {
		log.debug("Get password");
		return objLogin.getWebPassword().getText();
	}

	// Get Manager ID in Manager page
	public String getManagerIDInManagerPage() {
		log.debug("Get Manager ID in manager page");
		return objLogin.getManagerID().getText();
	}

	public void loginToManagerPage(String username, String password) {
		objLogin.getWebUsername().sendKeys(username);
		objLogin.getWebPassword().sendKeys(password);
		objLogin.getWebLogin().click();
		log.debug("Login to Manager page");
	}

	public void logout() {
		objLogin.getWebLogOut().click();
		log.debug("Click log out button");
	}
}
