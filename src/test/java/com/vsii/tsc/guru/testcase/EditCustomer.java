package com.vsii.tsc.guru.testcase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vsii.tsc.guru.commethods.CommonMethods;
import com.vsii.tsc.guru.pages.method.EditCustomerPageMethod;
import com.vsii.tsc.guru.pages.method.LoginPageMethod;
import com.vsii.tsc.guru.pages.method.NewCustomerPageMethod;
import com.vsii.tsc.utility.CommonOperations;
import com.vsii.tsc.utility.TestBase;

public class EditCustomer {
	LoginPageMethod objLogin;
	NewCustomerPageMethod objNewCust;
	EditCustomerPageMethod objEditCust;
	String username;
	String password;

	@BeforeClass
	public void setupClass() throws Exception {
		objLogin = new LoginPageMethod(TestBase.driver);
		objNewCust = new NewCustomerPageMethod(TestBase.driver);
		objEditCust = new EditCustomerPageMethod(TestBase.driver);
		CommonMethods.checkLogin();
	}

	@Test(priority = 1, description = "Edit Customer")
	public void ED01() throws IOException {
		// Method name
		TestBase.methodName = "ED01";

		// Perform test steps
		objEditCust.clickEditCustomer();
		objEditCust.enterCustomerID("86067");
		objEditCust.clickSubmit();

		// Verify Expected Results
		String custNameLabel = objEditCust.getCustomerNameLabel();
		//Verify Label Name
		Assert.assertTrue(custNameLabel.contains("Customer Name"));
		//Verify Textbox is disabled
		boolean isDisabled = objEditCust.checkTextBoxDisabled();
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
