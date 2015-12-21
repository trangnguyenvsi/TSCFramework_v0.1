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
import com.vsii.tsc.guru.testbase.TestBase;
import com.vsii.tsc.guru.utility.CommonOperations;
import com.vsii.tsc.guru.utility.Utility;

public class NewCustomer extends TestBase {
	NewCustomerPageMethod objNewCust;
	EditCustomerPageMethod objEditCust;
	LoginPageMethod objLogin;
	
	@BeforeClass
	public void setupClass() throws NumberFormatException, IOException{
		//Utility.openExcelFile(p.getProperty("tcFile"),p.getProperty("NewCustomer_Module"),Integer.parseInt(p.getProperty("tcID")));
		objLogin = new LoginPageMethod(driver);
		objNewCust = new NewCustomerPageMethod(driver);
		objEditCust = new EditCustomerPageMethod(driver);
	}
	
	@BeforeMethod
	public void beforMethod() throws IOException{
	}
	
	@Test(priority = 0, dataProvider = "dpLogin_success", dataProviderClass = TestData.class)
	public void Login(String username, String password, String message) throws Exception {
		//Login success
		//objLogin.loginToManagerPage(username, password);
	}

	@Test(priority = 1, description = "Verify Add New Customer", dataProvider = "dpNewCustomer", dataProviderClass = TestData.class)
	public void CN01(String name, String dob, String address, String city, String state, String pin,
			String mobileNumber, String email, String password, String message) throws IOException {
		
		class Local {};
		TestBase.methodName = Local.class.getEnclosingMethod().getName();
		
		//Perform create new customer
		objNewCust.createNewCustomer(name, dob, address, city, state, pin, mobileNumber, email, password);
	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
		
			e1.printStackTrace();
		}
		
		if(driver.getPageSource().contains(message)){
			Assert.assertTrue(driver.getPageSource().contains(message));
		} 
		else{
			Assert.assertTrue(driver.getPageSource().contains(message));
		}

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
		
	}

	@AfterMethod
	public void afterMethod(){

		CommonOperations.takePicture();
		
	}
	
//	@AfterClass
//	public void teardown(){
////		extent.flush();
////		extent.close();
////		driver.quit();
////		//Write test results in test case file
////		Utility.createExcelFile(p.getProperty("tcNewCustomerFile"));
//	}
}












*/