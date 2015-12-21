/*package com.vsii.tsc.guru.testcase;


import java.io.IOException;

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
import com.vsii.tsc.guru.report.Report;
import com.vsii.tsc.guru.testbase.TestBase;
import com.vsii.tsc.guru.utility.CommonOperations;
import com.vsii.tsc.guru.utility.CreateDirectory;
import com.vsii.tsc.guru.utility.DateTime;
import com.vsii.tsc.guru.utility.Utility;

public class EditCustomer{
	private static final boolean False = false;
	EditCustomerPageMethod objEditCust;
	LoginPageMethod objLogin;
	ExtentTest editTest;
	Report report;
	String methodName;
	String imageName;
	
	@BeforeClass
	public void setupClass() throws NumberFormatException, IOException{
		Utility.openExcelFile(TestBase.p.getProperty("tcFile"),TestBase.p.getProperty("EditCustomer_Module"),Integer.parseInt(TestBase.p.getProperty("tcID_Column")));
		objEditCust = new EditCustomerPageMethod(TestBase.driver);
		objLogin = new LoginPageMethod(TestBase.driver);
		report = new Report();
		//Write title for Test Suite
		editTest = TestBase.extent.startTest("<b>VERIFY EDIT CUSTOMER</b>");
	}
	
	@BeforeMethod
	public void before() throws IOException{
//		objEditCust = new EditCustomerPageMethod(TestBase.driver);
//		objLogin = new LoginPageMethod(TestBase.driver);
		//extent = Report.Instance();
	}

	//Login system 
	@Test(priority = 0, dataProvider = "dpLogin_success", dataProviderClass = TestData.class)
	public void verify_Login(String username, String password, String message) throws Exception {
		objLogin.loginToManagerPage(username, password);
	}

	@Test(priority = 1, description = "Edit Customer")
	public void TC_23() throws IOException {
		//Write title to report
		TestBase.test = TestBase.extent.startTest(">Verify when go to Edit Customer page");
		//editTest.appendChild(TestBase.test);

		//Define class to get method's name
		class Local {};
		methodName = Local.class.getEnclosingMethod().getName();
		
		//Define image's name
		imageName = DateTime.createDateText()+methodName;
		
		//Perform test steps
		objEditCust.clickEditCustomer();
		objEditCust.enterCustomerID("93390");
		objEditCust.clickSubmit();
		
		//Verify Expected Results
		String custNameLabel = objEditCust.getCustomerNameLabel();
		
		if (custNameLabel.contains("Customer Name")) {
			//Write test results to report
			TestBase.test.log(LogStatus.PASS, "Go to Edit customer screen successfully");
			//Write test results to test case file
			Utility.writeTestResults(methodName,5,"Passed");
			 
		} else {
			Assert.assertTrue(custNameLabel.contains("Customer Name"));
			TestBase.test.log(LogStatus.FAIL, "Go to Edit customer screen unsuccessfully");
			Utility.writeTestResults(methodName,5,"Failed");	
		}
	}

	@Test(priority = 2, description = "Check for disabled textbox")
	public void TC_24() throws IOException {
		//Write title test to report
		TestBase.test = TestBase.extent.startTest(">Check for disabled textbox");
	
		//TestBase.test.log(LogStatus.INFO, "Check for disabled textbox");	
		boolean isDisabled = objEditCust.checkTextBoxDisabled();
		
		//Define class to get method's name
		class Local {};
		methodName = Local.class.getEnclosingMethod().getName();
				
		//Define image's name
		String imageName = DateTime.createDateText()+methodName;
		
		if (isDisabled == False) {
			TestBase.test.log(LogStatus.PASS, "Customer Name, Gender, Date of Birth textbox are disabled");
			Utility.writeTestResults(methodName,5,"Passed");
		} 
		else {
			TestBase.test.log(LogStatus.FAIL, "Customer Name, Gender, Date of Birth textbox are not disabled");
			Utility.writeTestResults(methodName,5,"Failed");
		}
		//TestBase.test.log(LogStatus.INFO, TestBase.test.addScreenCapture(Report.CaptureScreen(TestBase.driver, imageName)));
	}

	@Test(priority = 3, description = "Update customer information", dataProvider = "dpEditCustomer", dataProviderClass = TestData.class)
	public void TC_25(String customerID, String address, String city, String state, String pin, String mobileNo, String email, String message) throws Exception {
		//Write title to report
		TestBase.test = TestBase.extent.startTest(">Verify Edit Customer successfully");
		//editTest.appendChild(TestBase.test);
		
		//Define class to get method's name
		 class Local {};
		 String methodName = Local.class.getEnclosingMethod().getName();
				
		 //Define image's name
		 String imageName = DateTime.createDateText()+methodName;
		 String updateSuccess = null;
		
		 //Steps 
		objEditCust.clickEditCustomer();
		objEditCust.enterCustomerID(customerID);
		objEditCust.clickSubmit();
		objEditCust.editCustomerInfo(address, city, state, pin, mobileNo, email);
		
		//get message
		if(CommonOperations.isAlertPresent(TestBase.driver)==false){
			
			updateSuccess = objEditCust.getUpdateSuccessInfo();
			//Verify expected results
			if (updateSuccess.contains(message)) {
				TestBase.test.log(LogStatus.PASS, "Update user information successfully");
				Utility.writeTestResults(methodName,5,"Passed");
			} 
			else {
				TestBase.test.log(LogStatus.FAIL, "Update user information not successfully");
				Utility.writeTestResults(methodName,5,"Failed");
			}
			//TestBase.test.log(LogStatus.INFO, TestBase.test.addScreenCapture(Report.CaptureScreen(TestBase.driver, imageName)));
		}
		else {
			//TestBase.driver.switchTo().alert();
			TestBase.test.log(LogStatus.FAIL, "Update user information not successfully");
			Utility.writeTestResults(methodName,5,"Failed");	
		}
			
		//Take screenshot
		Assert.assertTrue(updateSuccess.contains(message));
	}
	
	@AfterMethod
	public void after() throws Exception{
		
		if(CommonOperations.isAlertPresent(TestBase.driver)){
			String dir = TestBase.p.getProperty("imagePath");
			CreateDirectory.createDir(dir);
			String imagePopup = report.captureScreenShotPopUp(dir+imageName);
			TestBase.test.log(LogStatus.INFO, TestBase.test.addScreenCapture(imagePopup));
		}
		else{
			TestBase.test.log(LogStatus.INFO, TestBase.test.addScreenCapture(Report.CaptureScreen(TestBase.driver, imageName)));
		}
		//Adding child test
		editTest.appendChild(TestBase.test);
	}
	
	@AfterClass
	public void teardownClass(){
	
		objEditCust = null;
		objLogin = null;
		TestBase.extent.endTest(TestBase.test);
		TestBase.extent.flush();
		//TestBase.extent.close();
		//Write test results in test case file
		Utility.createExcelFile(TestBase.p.getProperty("tcEditCustomerFile"));
		
	}
}
*/