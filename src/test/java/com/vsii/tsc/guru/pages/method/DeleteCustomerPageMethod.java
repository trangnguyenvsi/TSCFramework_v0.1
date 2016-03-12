package com.vsii.tsc.guru.pages.method;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.vsii.tsc.guru.pages.DeleteCustomerPage;

public class DeleteCustomerPageMethod {
	Logger log = Logger.getLogger("trunghung");
	// Create new Web Driver variable
	WebDriver driver;
	// Create new WebElementLogin object
	DeleteCustomerPage objDeleteCustomer = new DeleteCustomerPage();

	public DeleteCustomerPageMethod(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, objDeleteCustomer);
		log.debug("Initialize webdriver in DeleteCustomer page");
	}

	public void clickDeleteCustomer() {
		objDeleteCustomer.getLnkDeleteCustomer().click();
		log.debug("Click Delete Customer link text");
	}

	public void clickSubmit() {
		objDeleteCustomer.getBtnSubmit().click();
		log.debug("Click Submit button");
	}

	public String getPopupText() {
		Alert alert = driver.switchTo().alert();
		log.debug("Get popup text");
		return alert.getText();
	}

	public void closePopup() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
		log.debug("Close popup");
	}
	
	public void deleteCustomer(String customerID) {
		objDeleteCustomer.getCustID().sendKeys(customerID);
		log.debug("Enter customer ID to delete");
	}
}
