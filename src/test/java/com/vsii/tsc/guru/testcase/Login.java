package com.vsii.tsc.guru.testcase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vsii.tsc.guru.pages.method.LoginPageMethod;
import com.vsii.tsc.guru.testdata.TestData;
import com.vsii.tsc.utility.CommonOperations;
import com.vsii.tsc.utility.TestBase;

public class Login{
	LoginPageMethod objLogin;

	@BeforeClass
	public void setupClass() throws NumberFormatException, IOException {
		objLogin = new LoginPageMethod(TestBase.driver);
	}

	@Test(priority = 0, description = "Verify Title of Login Page")
	public void LO01() throws IOException {
		TestBase.methodName = "LO01";
		// Verify Login page title
		String loginPageTitle = objLogin.getLoginTitle();
		Assert.assertTrue(loginPageTitle.contains("Guru99 Bank"));
	}
	@Test(priority = 2, description = "verify Login", dataProvider = "dpLogin", dataProviderClass = TestData.class)
	public void LO02(String username, String password, String message) throws Exception {

		//get method's name
		TestBase.methodName = "LO02";
		// perform login
		objLogin.loginToManagerPage(username, password);

		// input invalid account, having popup. If not, login successfully
		if (CommonOperations.isAlertPresent(TestBase.driver)) {
			// Get popup's text
			String txtPopup = objLogin.getPopupText();
			Assert.assertTrue(txtPopup.contains(message));
		} else {
			String managerID;
			managerID = objLogin.getManagerIDInManagerPage();
			Assert.assertTrue(managerID.contains(message));
		}
	}
	
	 @Test(priority = 1, description = "verify_Reset_Button", dataProvider = "dpReset", dataProviderClass = TestData.class)
	 public void LO03(String username, String password) throws Exception {
		//get method's name
		TestBase.methodName = "LO03";
		objLogin.setUserID(username);
		objLogin.setPassword(password);
		objLogin.clickReset();
		Assert.assertEquals(objLogin.getUserID(), "");
		Assert.assertEquals(objLogin.getPassword(), "");
	 }

	@AfterMethod
	public void afterMethod() throws Exception {
		CommonOperations.takePicture();
	}

	@AfterClass
	public void teardownClass() {
		objLogin = null;	
	}

}


