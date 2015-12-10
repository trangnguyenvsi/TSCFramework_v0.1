package com.vsii.tsc.guru.testcase;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.vsii.tsc.guru.data.TestData;
import com.vsii.tsc.guru.pages.method.EditCustomerPageMethod;
import com.vsii.tsc.guru.pages.method.LoginPageMethod;
import com.vsii.tsc.guru.pages.method.NewCustomerPageMethod;
import com.vsii.tsc.guru.report.Report;
import com.vsii.tsc.guru.testbase.TestBase;
import com.vsii.tsc.guru.utility.Utility;

public class NewCustomer extends TestBase {
	NewCustomerPageMethod objNewCust;
	EditCustomerPageMethod objEditCust;
	LoginPageMethod objLogin;
	
	@BeforeClass
	public void setupClass() throws NumberFormatException, IOException{
		Utility.openExcelFile(p.getProperty("tcFile"),p.getProperty("NewCustomer_Module"),Integer.parseInt(p.getProperty("tcID")));
	}
	
	@BeforeMethod
	public void beforMethod() throws IOException{
		objLogin = new LoginPageMethod(driver);
		objNewCust = new NewCustomerPageMethod(driver);
		objEditCust = new EditCustomerPageMethod(driver);
		//extent = Report.Instance();
	}
	
	@Test(priority = 0, dataProvider = "dpLogin_success", dataProviderClass = TestData.class)
	public void Login(String username, String password, String message) throws Exception {
		//Login success
		objLogin.loginToManagerPage(username, password);
		//String managerID = objLogin.getManagerIDInManagerPage();
	}

	@Test(priority = 1, description = "Verify Add New Customer", dataProvider = "dpNewCustomer", dataProviderClass = TestData.class)
	public void TC_02(String name, String dob, String address, String city, String state, String pin,
			String mobileNumber, String email, String password, String message) throws IOException {
		
		objNewCust.createNewCustomer(name, dob, address, city, state, pin, mobileNumber, email, password);
	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
		
			e1.printStackTrace();
		}
		//Write log to test report
		test = extent.startTest("Create New Customer");
		test.log(LogStatus.INFO, "<b>VERIFY CREATE NEW CUSTOMER FUNCTION</b>");
		
		if(driver.getPageSource().contains(message)){
			test.log(LogStatus.PASS, "Create new customer successfully");
			Assert.assertTrue(driver.getPageSource().contains(message));
		} 
		else{
			
			test.log(LogStatus.FAIL, "Create new customer not successfully");
			Assert.assertTrue(driver.getPageSource().contains(message));
		}
		test.log(LogStatus.INFO, test.addScreenCapture(Report.CaptureScreen(driver, "TC11")));
		extent.endTest(test);
		
		class Local {};
	    String methodName = Local.class.getEnclosingMethod().getName();
	    System.out.println("Method Name:"+methodName);
	    Utility.writeTestResults(methodName,5,"Passed");
	}
	
	//@Test(priority = 2, description = "TC_12")
	public void enter_Customer_ID_To_Edit() throws IOException {
		String newCustomerID = objNewCust.getCustomerID();	
		objEditCust.clickEditCustomer();
		objEditCust.enterCustomerID(newCustomerID);
		objEditCust.clickSubmit();
		String custName = objEditCust.getCustomerNameLabel();
		
		//Write Log to test report
		test = extent.startTest("Edit Customer");
		test.log(LogStatus.INFO, "<b>EDIT CUSTOMER</b>");
		
		if(custName.contains("Customer Name")){				
			test.log(LogStatus.PASS, "Edit customer successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Edit customer page successfully");
			Assert.assertTrue(custName.contains("Customer Name"));
		}
		test.log(LogStatus.INFO, test.addScreenCapture(Report.CaptureScreen(driver, "TC_12")));
	}

	@AfterMethod
	public void afterMethod(){
		objLogin = null;
		objNewCust = null;
		objEditCust = null;
		
	}
	
	@AfterClass
	public void teardownClass(){
		extent.flush();
		extent.close();
		driver.quit();
		//Write test results in test case file
		Utility.createExcelFile(p.getProperty("tcNewCustomerFile"));
		
	}
	
	
}













