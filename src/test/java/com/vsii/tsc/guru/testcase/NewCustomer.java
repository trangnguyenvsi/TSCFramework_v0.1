package com.vsii.tsc.guru.testcase;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vsii.tsc.guru.commethods.CommonMethods;
import com.vsii.tsc.guru.pages.method.EditCustomerPageMethod;
import com.vsii.tsc.guru.pages.method.LoginPageMethod;
import com.vsii.tsc.guru.pages.method.NewCustomerPageMethod;
import com.vsii.tsc.guru.testdata.TestData;
import com.vsii.tsc.utility.CommonOperations;
import com.vsii.tsc.utility.TestBase;

public class NewCustomer{
	LoginPageMethod objLogin;
	NewCustomerPageMethod objNewCust;
	EditCustomerPageMethod objEditCust;
	String username;
	String password;
		
	@BeforeClass
	public void setupClass() throws Exception{
		objLogin = new LoginPageMethod(TestBase.driver);
		objNewCust = new NewCustomerPageMethod(TestBase.driver);
		objEditCust = new EditCustomerPageMethod(TestBase.driver);
		CommonMethods.checkLogin();
	}
	
	@Test(priority = 0, description="Verify New Customer link")
	public void CN01(){
		//get method's name
		TestBase.methodName = "CN01";
		
		objNewCust.gotoNewCustomerForm();
		Assert.assertEquals(objNewCust.getTitleForm(),"Add New Customer");
	}
	
	@Test(priority=1, description="Validate Create New Customer form", dataProvider="dpValidateCustomer",dataProviderClass = TestData.class)
	public void CN02(String message){
		//get method's name
		TestBase.methodName = "CN02";
		objNewCust.clickToEachField();
		Assert.assertTrue(TestBase.driver.getPageSource().contains(message));
	
	}
	
	@Test(priority = 2, description = "Verify Add New Customer", dataProvider = "dpNewCustomer", dataProviderClass = TestData.class)
	public void CN03(String name, String dob, String address, String city, String state, String pin,String mobileNumber, String email, String password, String message) throws Exception {
		//get method's name
		TestBase.methodName = "CN03";
			
		//create new customer
		objNewCust.createNewCustomer(name, dob, address, city, state, pin, mobileNumber, email, password);
	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Verify the message after create new customer
		Assert.assertTrue(TestBase.driver.getPageSource().contains(message));
		
	}
	
	@AfterMethod
	public void afterMethod() throws Exception{
		CommonOperations.takePicture();
	}

}