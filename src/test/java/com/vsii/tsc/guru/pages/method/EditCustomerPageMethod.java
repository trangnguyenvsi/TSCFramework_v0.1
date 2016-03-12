package com.vsii.tsc.guru.pages.method;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.vsii.tsc.guru.pages.EditCustomerPage;
import com.vsii.tsc.guru.pages.NewCustomerPage;

public class EditCustomerPageMethod {
	private static final boolean False = false;
	Logger log = Logger.getLogger("trunghung");
	// Create new Web Driver variable
	WebDriver driver;
	// Create new WebElementLogin object
	NewCustomerPage objNewCustomer = new NewCustomerPage();
	EditCustomerPage objEditCustomer = new EditCustomerPage();

	// Initialize all web element
	public EditCustomerPageMethod(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, objNewCustomer);
		PageFactory.initElements(driver, objEditCustomer);
		log.debug("Initiate web driver");
	}

	public void clickEditCustomer() {
		objEditCustomer.getLnkEditCustomer().click();
		log.debug("Click Edit Customer link text");
	}

	public void enterCustomerID(String custID) {
		objEditCustomer.getCustomerID().sendKeys(custID);
		log.debug("Enter Customer ID to edit customer detail");
	}

	public void clickSubmit() {
		objEditCustomer.getBtnSubmit().click();
		log.debug("Click Submit button on Edit Customer page");
	}

	public String getCustomerNameLabel() {
		log.debug("Get Customer name label");
		return objEditCustomer.getCustomerName().getText();
	}

	public boolean checkTextBoxDisabled() {
		objEditCustomer.getTxtCustomerName().isEnabled();
		log.debug("Check customer name textbox is disabled");
		objEditCustomer.getTxtGender().isEnabled();
		log.debug("Check gender textbox is disabled");
		objEditCustomer.getTxtDob().isEnabled();
		log.debug("Check customer date of birth textbox is disabled");
		return False;
	}

	public void editCustomerInfo(String address, String city, String state, String pin, String mobileNo, String email) {
		objEditCustomer.getTxtAddress().clear();
		log.debug("Clear address textbox");
		objEditCustomer.getTxtAddress().sendKeys(address);
		log.debug("Update customer address");
		
		objEditCustomer.getTxtCity().clear();
		log.debug("Clear city textbox");
		objEditCustomer.getTxtCity().sendKeys(city);
		log.debug("Update customer city");
				
		objEditCustomer.getTxtState().clear();
		log.debug("Clear state textbox");
		objEditCustomer.getTxtState().sendKeys(state);
		log.debug("Update customer state");
		
		objEditCustomer.getTxtPin().clear();
		log.debug("Clear PIN textbox");
		objEditCustomer.getTxtPin().sendKeys(pin);
		log.debug("Update customer pin");
		
		objEditCustomer.getTxtTelephoneNo().clear();
		log.debug("Clear phone mobile textbox");
		objEditCustomer.getTxtTelephoneNo().sendKeys(mobileNo);
		log.debug("Update customer mobile number");
		
		objEditCustomer.getTxtEmail().clear();
		log.debug("Clear email textbox");
		objEditCustomer.getTxtEmail().sendKeys(email);
		log.debug("Update customer email");
		
		objEditCustomer.getBtnEditSubmit().click();
		log.debug("Click submit button");
	}

	public String getUpdateSuccessInfo() {
		log.debug("Get customer details updated successfully text");
		return objEditCustomer.getUpdateSuccess().getText();
	}
}
