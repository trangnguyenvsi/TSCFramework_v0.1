package com.vsii.tsc.guru.testcase;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.vsii.tsc.guru.data.TestData;
import com.vsii.tsc.guru.pages.LoginPage;
import com.vsii.tsc.guru.pages.method.EditCustomerPageMethod;
import com.vsii.tsc.guru.pages.method.LoginPageMethod;
import com.vsii.tsc.guru.pages.method.NewCustomerPageMethod;
import com.vsii.tsc.guru.testbase.TestBase;
import com.vsii.tsc.guru.utility.CommonOperations;

public class NewCustomer{
	LoginPageMethod objLogin;
	NewCustomerPageMethod objNewCust;
	EditCustomerPageMethod objEditCust;
	String username;
	String password;
	
	//Use constructor for Factory
	public NewCustomer(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public void login() throws Exception {
		//If not login, system perform login
		if(TestBase.driver.findElements(By.name("uid")).size()!=0){
			objLogin.loginToManagerPage(username, password);
		}
	}
	
	@BeforeClass
	public void setupClass() throws Exception{
		objLogin = new LoginPageMethod(TestBase.driver);
		objNewCust = new NewCustomerPageMethod(TestBase.driver);
		objEditCust = new EditCustomerPageMethod(TestBase.driver);
		login();
	}
	
	@Test(priority = 0)
	public void CN01(){
		//get method's name
		TestBase.methodName = "CN01";
		
		objNewCust.gotoNewCustomerForm();
		Assert.assertEquals(objNewCust.getTitleForm(),"Add New Customer");
	}
	
	@Test(priority = 1, description = "Verify Add New Customer", dataProvider = "dpNewCustomer", dataProviderClass = TestData.class)
	public void CN02(String name, String dob, String address, String city, String state, String pin,String mobileNumber, String email, String password, String message) throws Exception {
		//get method's name
		TestBase.methodName = "CN02";
			
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