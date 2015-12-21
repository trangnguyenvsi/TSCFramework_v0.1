package com.vsii.tsc.guru.testcase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.vsii.tsc.guru.data.TestData;
import com.vsii.tsc.guru.pages.method.LoginPageMethod;
import com.vsii.tsc.guru.report.ExtentReporterNG;
import com.vsii.tsc.guru.testbase.TestBase;
import com.vsii.tsc.guru.utility.CommonOperations;
import com.vsii.tsc.guru.utility.CreateDirectory;
import com.vsii.tsc.guru.utility.DateTime;
import com.vsii.tsc.guru.utility.Utility;

public class Login{
	LoginPageMethod objLogin;
	ExtentReporterNG report;
	ExtentTest loginTest;

	/*
	 * Before class, open the test case file by file name, sheet name and test
	 * case ID column
	 */
	@BeforeClass
	public void setupClass() throws NumberFormatException, IOException {
		// create object of Login and Report
		objLogin = new LoginPageMethod(TestBase.driver);

		// Open test case file
	//	Utility.openExcelFile(TestBase.p.getProperty("tcFile"), TestBase.p.getProperty("Login_Module"),
		//		Integer.parseInt(TestBase.p.getProperty("tcID_Column")));

	}

	@Test(priority = 0, description = "Verify Title of Login Page")
	public void TC01() throws IOException {

		// Get method name
		class Local {}	;
		TestBase.methodName = Local.class.getEnclosingMethod().getName();

		// Verify Login page title
		String loginPageTitle = objLogin.getLoginTitle();
		Assert.assertTrue(loginPageTitle.contains("Guru99 Bank"));
	}
	//
	//
	// //@Test(priority = 1, description =
	// "verify_Alert_When_UserID_Textbox_Is_EmptyTC02")
	// public void TC_02() throws Exception {
	// objLogin.clickUserID();
	// objLogin.clickPassword();
	// String userIdAlert = objLogin.getUserIdAlert();
	// Assert.assertTrue(userIdAlert.contains("User-ID must not be blank"));
	// test.log(LogStatus.INFO, "<b>TC02_VERIFY ALERT WHEN USER ID TEXTBOX IS
	// EMPTY</b>");
	// test.log(LogStatus.PASS, "User-ID must not be blank text displays");
	// test.log(LogStatus.INFO,
	// test.addScreenCapture(Report.CaptureScreen(driver, "TC02")));
	// }
	//
	// //@Test(priority = 2, description =
	// "verify_Alert_When_Password_Textbox_Is_Empty")
	// public void TC_03() throws Exception {
	// objLogin.clickPassword();
	// objLogin.clickUserID();
	// String passwordAlert = objLogin.getPasswordAlert();
	// Assert.assertTrue(passwordAlert.contains("Password must not be blank"));
	//
	// test = extent.startTest("Test");
	//
	// test.log(LogStatus.INFO, "<b>TC03_VERIFY ALERT WHEN PASSWORD TEXTBOX IS
	// EMPTY</b>");
	// test.log(LogStatus.PASS, "Password must not be blank text displays");
	// test.log(LogStatus.INFO,
	// test.addScreenCapture(Report.CaptureScreen(driver, "TC_03")));
	// }
	//
	// @Test(priority = 3, description =
	// "verify_Alert_When_Both_UserID_And_Password_Is_Empty")
	// public void TC_04() throws Exception {
	// objLogin.clickLogin();
	// String txtPopup = objLogin.getPopupText();
	// Assert.assertTrue(txtPopup.contains("User or Password is not valid"));
	//
	// test = extent.startTest("Test Test2");
	//
	// test.log(LogStatus.INFO, "<b>TC04_VERIFY ALERT WHEN BOTH USER ID AND
	// PASSWORD IS EMPTY</b>");
	// test.log(LogStatus.PASS, "User or Password is not valid alert displays");
	// test.log(LogStatus.INFO, test.addScreenCapture(
	// Report.captureScreenShotPopUp(p.getProperty("imagePath")+"TC_04")));
	// objLogin.closePopup();
	// }

	/*
	 * Create test script for TC_01
	 */

	@Test(priority = 1, description = "verify Login", dataProvider = "dpLogin", dataProviderClass = TestData.class)
	public void TC02(String username, String password, String message) throws Exception {

		// Define class to get method's name
		class Local {};
		TestBase.methodName = Local.class.getEnclosingMethod().getName();

		// perform login
		objLogin.loginToManagerPage(username, password);

		// input invalid account, having popup. If not, login successfully
		if (CommonOperations.isAlertPresent(TestBase.driver)) {

			// Get popup's text
			String txtPopup = objLogin.getPopupText();
			Assert.assertTrue(txtPopup.contains(message));
		} else {
		//	TestBase.test = TestBase.extent.startTest(">Test Login with valid account");
			String managerID;
			managerID = objLogin.getManagerIDInManagerPage();
			Assert.assertTrue(managerID.contains(message));

		}

	}

	// @Test(priority = 7, dataProvider = "dpLogin_success", dataProviderClass =
	// TestData.class,description="Login successfully" )
	// public void TC_08(String username, String password, String message)
	// throws Exception {
	//
	// objLogin.loginToManagerPage(username, password);
	// String managerID = objLogin.getManagerIDInManagerPage();
	// test.log(LogStatus.INFO, "<b>TC09_VERIFY LOGIN FUNCTION</b>");
	// if(managerID.contains(message)){
	// test.log(LogStatus.PASS, "User logs in successfully");
	// }
	// else
	// {
	// test.log(LogStatus.FAIL, "User logs in not successfully");
	// Assert.assertTrue(managerID.contains(message));
	// }
	// test.log(LogStatus.INFO,
	// test.addScreenCapture(Report.CaptureScreen(driver, "TC08")));
	// }
	//
	// //@Test(priority = 6, description = "verify_Reset_Button")
	// public void TC07() throws Exception {
	// objLogin.setUserID("trunghung");
	// objLogin.setPassword("123456");
	// objLogin.clickReset();
	// Assert.assertEquals(objLogin.getUserID(), "");
	// Assert.assertEquals(objLogin.getPassword(), "");
	// test.log(LogStatus.INFO, "<b>TC08_VERIFY RESET BUTTON</b>");
	// test.log(LogStatus.PASS, "User and Password are reset");
	// test.log(LogStatus.INFO,
	// test.addScreenCapture(Report.CaptureScreen(driver, "TC07")));
	// }

	@AfterMethod
	public void afterMethod() throws Exception {
		CommonOperations.takePicture();
	}

	@AfterClass
	public void teardownClass() {
		objLogin = null;
		//TestBase.extent.flush();
		//TestBase.extent.endTest(TestBase.test);
		/////// TestBase.extent.close();
		//Utility.createExcelFile(TestBase.p.getProperty("tcLoginFile"));
	}

}


