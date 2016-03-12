package com.vsii.tsc.guru.pages.method;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.vsii.tsc.guru.pages.LoginPage;

public class LoginPageMethod {	
	ExtentReports extent;
	
	Logger log = Logger.getLogger("trunghung");
	
	
	// Create new Web Driver variable
	WebDriver driver;
	// Create new WebElementLogin object
	public LoginPage objLoginPage = new LoginPage();

	// Initialize all web element
	public LoginPageMethod(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, objLoginPage);
		log.debug("Initiate web driver");
	}

	// Enter user name in UserID text box
	public void setUserID(String txtUsername) {		
		objLoginPage.getWebUsername().sendKeys(txtUsername);
		log.debug("Set username");
	}

	// Enter password in password text box
	public void setPassword(String txtPassword) {		
		objLoginPage.getWebPassword().sendKeys(txtPassword);
		log.debug("Set password");
	}

	// Click Reset button
	public void clickReset() {
		objLoginPage.getWebReset().click();
		log.debug("Click reset button");
	}

	// Click Login button
	public void clickLogin() {
		objLoginPage.getWebLogin().click();
		log.debug("Click login button");
	}

	// Click mouse in UserID text box
	public void clickUserID() {
		objLoginPage.getWebUsername().click();
		log.debug("Click mouse in user ID text box");
	}

	// Click mouse in Password text box
	public void clickPassword() {
		objLoginPage.getWebPassword().click();
		log.debug("Click mouse in password text box");
	}

	// Get the title of Login page
	public String getLoginTitle() {		
		log.debug("Get web title text");
		return objLoginPage.getWebTitleText().getText();
	}

	// Get the alert text when leaving User ID text box blank
	public String getUserIdAlert() {
		log.debug("Get User ID alert");
		return objLoginPage.getWebAlertUserID().getText();
	}

	// Get the alert text when leaving Password text box blank
	public String getPasswordAlert() {
		log.debug("Get password alert");
		return objLoginPage.getWebAlertPassword().getText();
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
		return objLoginPage.getWebUsername().getText();
	}

	// Get Password in Login page
	public String getPassword() {
		log.debug("Get password");
		return objLoginPage.getWebPassword().getText();
	}

	// Get Manager ID in Manager page
	public String getManagerIDInManagerPage() {
		log.debug("Get Manager ID in manager page");
		return objLoginPage.getManagerID().getText();
	}

	public void loginToManagerPage(String username, String password) {
	
		objLoginPage.getWebUsername().sendKeys(username);
	//	ExtentReporterNG.test.log(LogStatus.INFO, "Input user name");
		
		objLoginPage.getWebPassword().sendKeys(password);
	//	ExtentReporterNG.test.log(LogStatus.INFO, "Input password");
		
		objLoginPage.getWebLogin().click();
	//	ExtentReporterNG.test.log(LogStatus.INFO, "Click Login");
		log.debug("Login to Manager page");
	}

	public void logout() {
		objLoginPage.getWebLogOut().click();
		log.debug("Click log out button");
	}
}
