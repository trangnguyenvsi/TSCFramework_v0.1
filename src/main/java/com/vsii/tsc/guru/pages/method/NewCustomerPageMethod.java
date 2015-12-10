package com.vsii.tsc.guru.pages.method;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.vsii.tsc.guru.pages.NewCustomerPage;

public class NewCustomerPageMethod {
	Logger log = Logger.getLogger("trunghung");
	WebDriver driver;
	NewCustomerPage objNewCust = new NewCustomerPage();

	public NewCustomerPageMethod(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, objNewCust);
	}

	// Enter data and create new customer
	public void createNewCustomer(String name, String dob, String address, String city, String state, String pin,
			String mobileNumber, String email, String password) {
		objNewCust.getWebNewCustomer().click();
		objNewCust.getWebCustomerName().sendKeys(name);
		objNewCust.getWebGender().click();
		objNewCust.getWebDob().sendKeys(dob);
		objNewCust.getWebAddress().sendKeys(address);
		objNewCust.getWebCity().sendKeys(city);
		objNewCust.getWebState().sendKeys(state);
		objNewCust.getWebPin().sendKeys(pin);
		objNewCust.getWebPhoneNo().sendKeys(mobileNumber);
		objNewCust.getWebEmail().sendKeys(email);
		objNewCust.getWebPassword().sendKeys(password);
		log.debug("Enter customer information");
		objNewCust.getWebSubmit().click();
		log.debug("Click Submit button");
	}
	
	public String getCreateCustomerSuccessful() {
		log.debug("Get text Customer Registered Successfully!!!");
		return objNewCust.getWebRegisterSuccess().getText();
	}	
	
	public String getCustomerID() {
		log.debug("Get customer ID after register successfully");
		return objNewCust.getCustomerID().getText();
	}
}
