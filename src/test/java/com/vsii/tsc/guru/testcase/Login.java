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

import com.relevantcodes.extentreports.LogStatus;
import com.vsii.tsc.guru.data.TestData;
import com.vsii.tsc.guru.pages.method.LoginPageMethod;
import com.vsii.tsc.guru.report.Report;
import com.vsii.tsc.guru.testbase.TestBase;
import com.vsii.tsc.guru.utility.CommonOperations;
import com.vsii.tsc.guru.utility.CreateDirectory;
import com.vsii.tsc.guru.utility.DateTime;
import com.vsii.tsc.guru.utility.Utility;

public class Login{
	LoginPageMethod objLogin;
	Report report;

	/*
	 * Before class, open the test case file by file name, sheet name and test case ID column
	 */
	@BeforeClass
	public void setupClass() throws NumberFormatException, IOException{
		Utility.openExcelFile(TestBase.p.getProperty("tcFile"),
								TestBase.p.getProperty("Login_Module"),
									Integer.parseInt(TestBase.p.getProperty("tcID_Column")));
	}

	/*
	 * Before method, create object of Login and Report
	 */
	@BeforeMethod
	public void beforeMethod() throws IOException {
		objLogin = new LoginPageMethod(TestBase.driver);	
		report = new Report();
	}

//	//@Test(priority = 0, description = "verify_Title_Login_Page")
//	public void TC01 () throws IOException {
//		// Verify Login page title
//		String loginPageTitle = objLogin.getLoginTitle();
//		// Assert.assertTrue(loginPageTitle.contains("Guru99 Bank"));
//		test.log(LogStatus.INFO, "<b>TC01_VERIFY TITLE OF LOGIN PAGE</b>");
//		Assert.assertTrue(loginPageTitle.contains("Guru99 Bank")) ;
//		test.log(LogStatus.INFO, test.addScreenCapture(Report.CaptureScreen(driver, "TC01")));
//	}
//
//	
//	//@Test(priority = 1, description = "verify_Alert_When_UserID_Textbox_Is_EmptyTC02")
//	public void TC_02() throws Exception {
//		objLogin.clickUserID();
//		objLogin.clickPassword();
//		String userIdAlert = objLogin.getUserIdAlert();
//		Assert.assertTrue(userIdAlert.contains("User-ID must not be blank"));
//		test.log(LogStatus.INFO, "<b>TC02_VERIFY ALERT WHEN USER ID TEXTBOX IS EMPTY</b>");
//		test.log(LogStatus.PASS, "User-ID must not be blank text displays");
//		test.log(LogStatus.INFO, test.addScreenCapture(Report.CaptureScreen(driver, "TC02")));
//	}
//
//	//@Test(priority = 2, description = "verify_Alert_When_Password_Textbox_Is_Empty")
//	public void TC_03() throws Exception {
//		objLogin.clickPassword();
//		objLogin.clickUserID();
//		String passwordAlert = objLogin.getPasswordAlert();
//		Assert.assertTrue(passwordAlert.contains("Password must not be blank"));
//		
//		test = extent.startTest("Test");
//		
//		test.log(LogStatus.INFO, "<b>TC03_VERIFY ALERT WHEN PASSWORD TEXTBOX IS EMPTY</b>");
//		test.log(LogStatus.PASS, "Password must not be blank text displays");
//		test.log(LogStatus.INFO, test.addScreenCapture(Report.CaptureScreen(driver, "TC_03")));
//	}
//
//	@Test(priority = 3, description = "verify_Alert_When_Both_UserID_And_Password_Is_Empty")
//	public void TC_04() throws Exception {
//		objLogin.clickLogin();
//		String txtPopup = objLogin.getPopupText();
//		Assert.assertTrue(txtPopup.contains("User or Password is not valid"));
//		
//		test = extent.startTest("Test Test2");
//		
//		test.log(LogStatus.INFO, "<b>TC04_VERIFY ALERT WHEN BOTH USER ID AND PASSWORD IS EMPTY</b>");
//		test.log(LogStatus.PASS, "User or Password is not valid alert displays");
//		test.log(LogStatus.INFO, test.addScreenCapture(
//				Report.captureScreenShotPopUp(p.getProperty("imagePath")+"TC_04")));
//		objLogin.closePopup();
//	}
	
	/*
	 * Create test script for TC_01
	 */
	
	@Test(priority = 0, description = "verify Login", dataProvider = "dpLogin", dataProviderClass = TestData.class)
	public void TC_01(String username, String password, String message) throws Exception {	
		//Write Title to Report
		TestBase.test = TestBase.extent.startTest("Test Login");
			
		//Define class to get method's name
		class Local {};
		String methodName = Local.class.getEnclosingMethod().getName();
		
		//Define image's name
		String imageName = DateTime.createDateText()+methodName;
		
		//perform login
		objLogin.loginToManagerPage(username, password);
		//input invalid account, having popup. If not, login successfully
		if(CommonOperations.isAlertPresent(TestBase.driver)){
			String txtPopup = objLogin.getPopupText();
			//Assert Alert
			if(txtPopup.contains(message)){
				//Write test results to test report
				//TestBase.test.log(LogStatus.INFO, "<b>VERIFY ALERT WHEN ACCOUNT IS INVALID</b>");
				TestBase.test.log(LogStatus.PASS, "User or Password is not valid, alert displays");
				
				//Write Test results to test case file
				Utility.writeTestResults(methodName,5,"Passed");
				
				objLogin.closePopup();
			}
			else {
				TestBase.test.log(LogStatus.FAIL, "User or Password is not valid alert displays");
				Utility.writeTestResults(methodName,5,"Failed");
			}
				String dir = TestBase.p.getProperty("imagePath");
				CreateDirectory.createDir(dir);
				String img = report.captureScreenShotPopUp(dir+imageName);
				
				//Take picture for popup
				TestBase.test.log(LogStatus.INFO, TestBase.test.addScreenCapture(img));
		}
		else {		
				//TestBase.test.log(LogStatus.INFO, "<b>VERIFY LOGIN WHEN ACCOUNT IS VALID</b>");
				//get message when user login successfully
				String managerID; managerID = objLogin.getManagerIDInManagerPage();
			
				if(managerID.contains(message)){
				
					TestBase.test.log(LogStatus.PASS, "User logs in successfully");
					Utility.writeTestResults(methodName,5,"Passed");
			}
				else
			{
					Assert.assertTrue(managerID.contains(message));
					TestBase.test.log(LogStatus.FAIL, "User logs in not successfully");
					Utility.writeTestResults(methodName,5,"Failed");
			}
				//Take screenshot
				TestBase.test.log(LogStatus.INFO, TestBase.test.addScreenCapture(Report.CaptureScreen(TestBase.driver, imageName)));
		
		}
		
		
	    
		
	}
	
	//@Test(priority = 7, dataProvider = "dpLogin_success", dataProviderClass = TestData.class,description="Login successfully" )
//	public void TC_08(String username, String password, String message) throws Exception {
//		
//		objLogin.loginToManagerPage(username, password);
//		String managerID = objLogin.getManagerIDInManagerPage();
//		test.log(LogStatus.INFO, "<b>TC09_VERIFY LOGIN FUNCTION</b>");
//		if(managerID.contains(message)){
//			test.log(LogStatus.PASS, "User logs in successfully");
//		}
//		else
//		{
//			test.log(LogStatus.FAIL, "User logs in not successfully");
//			Assert.assertTrue(managerID.contains(message));
//		}
//		test.log(LogStatus.INFO, test.addScreenCapture(Report.CaptureScreen(driver, "TC08")));
//	}
//	
//	//@Test(priority = 6, description = "verify_Reset_Button")
//		public void TC07() throws Exception {
//			objLogin.setUserID("trunghung");
//			objLogin.setPassword("123456");
//			objLogin.clickReset();
//			Assert.assertEquals(objLogin.getUserID(), "");
//			Assert.assertEquals(objLogin.getPassword(), "");
//			test.log(LogStatus.INFO, "<b>TC08_VERIFY RESET BUTTON</b>");
//			test.log(LogStatus.PASS, "User and Password are reset");
//			test.log(LogStatus.INFO, test.addScreenCapture(Report.CaptureScreen(driver, "TC07")));
//		}
		
	@AfterMethod
	public void afterMethod() {
		objLogin = null;
		TestBase.extent.endTest(TestBase.test);
	}
	
	@AfterClass
	public void teardownClass(){
		TestBase.extent.flush();
		TestBase.extent.close();
		Utility.createExcelFile(TestBase.p.getProperty("tcLoginFile"));
		
	}
	
}
