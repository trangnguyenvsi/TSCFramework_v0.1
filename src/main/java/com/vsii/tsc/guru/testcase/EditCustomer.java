package com.vsii.tsc.guru.testcase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.vsii.tsc.guru.data.TestData;
import com.vsii.tsc.guru.pages.method.EditCustomerPageMethod;
import com.vsii.tsc.guru.pages.method.LoginPageMethod;
import com.vsii.tsc.guru.pages.method.NewCustomerPageMethod;
import com.vsii.tsc.guru.testbase.TestBase;
import com.vsii.tsc.guru.utility.CommonOperations;
import com.vsii.tsc.guru.utility.CreateDirectory;
import com.vsii.tsc.guru.utility.DateTime;
import com.vsii.tsc.guru.utility.Utility;

public class EditCustomer {
	LoginPageMethod objLogin;
	NewCustomerPageMethod objNewCust;
	EditCustomerPageMethod objEditCust;
	String username;
	String password;

	// Use constructor for Factory
	public EditCustomer(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void login() throws Exception {
		// If not login, system perform login
		if (TestBase.driver.findElements(By.name("uid")).size() != 0) {
			objLogin.loginToManagerPage(username, password);
		}
	}

	@BeforeClass
	public void setupClass() throws Exception {
		objLogin = new LoginPageMethod(TestBase.driver);
		objNewCust = new NewCustomerPageMethod(TestBase.driver);
		objEditCust = new EditCustomerPageMethod(TestBase.driver);
		login();
	}

	//@Test(priority = 1, description = "Edit Customer")
	public void ED01() throws IOException {
		// Method name
		TestBase.methodName = "ED01";

		// Perform test steps
		objEditCust.clickEditCustomer();
		objEditCust.enterCustomerID("93390");
		objEditCust.clickSubmit();

		// Verify Expected Results
		String custNameLabel = objEditCust.getCustomerNameLabel();
		Assert.assertTrue(custNameLabel.contains("Customer Name"));
	}

	//@Test(priority = 2, description = "Check for disabled textbox")
	public void ED02() throws IOException {
		// Method name
		TestBase.methodName = "ED02";

		boolean isDisabled = objEditCust.checkTextBoxDisabled();
		// Verify that textbox is disable
		Assert.assertTrue(isDisabled);
	}

	//@Test(priority = 3, description = "Update customer information", dataProvider = "dpEditCustomer", dataProviderClass = TestData.class)
	public void ED03(String customerID, String address, String city, String state, String pin, String mobileNo,
			String email, String message) throws Exception {
		String updateSuccess = null;
		// Method name
		TestBase.methodName = "ED02";
		// Steps
		objEditCust.clickEditCustomer();
		objEditCust.enterCustomerID(customerID);
		objEditCust.clickSubmit();
		objEditCust.editCustomerInfo(address, city, state, pin, mobileNo, email);
		// Verify

		updateSuccess = objEditCust.getUpdateSuccessInfo();
		Assert.assertTrue(updateSuccess.contains(message));
	}

	@AfterMethod
	public void after() throws Exception {
		CommonOperations.takePicture();
	}

	@AfterClass
	public void teardownClass() {
		objEditCust = null;
		objLogin = null;
	}
}
