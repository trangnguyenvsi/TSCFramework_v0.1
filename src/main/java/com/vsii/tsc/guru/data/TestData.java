package com.vsii.tsc.guru.data;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import com.vsii.tsc.guru.testcase.Login;
import com.vsii.tsc.guru.testcase.NewCustomer;
import com.vsii.tsc.guru.utility.Utility;;

public class TestData {
	@DataProvider(name = "dpLogin_success")
	public static Object[][] getLoginValid() {
		Object[][] loginData = Utility.getTable("./data/TestData.xlsx", "DataSet", "Login");
		return loginData;
	}
	
	@DataProvider(name = "dpLogin")
	public static Object[][] getLoginData() {
		Object[][] loginData = Utility.getTable("./data/TestData.xlsx", "DataSet", "TC_01");
		return loginData;
	}

	@DataProvider(name = "dpNewCustomer")
	public static Object[][] getCustomerData() {
		Object[][] loginData = Utility.getTable("./data/TestData.xlsx", "Customer", "TC_02");
		return loginData;
	}

	@DataProvider(name = "dpEditCustomer")
	public static Object[][] editCustomerData() {
		Object[][] loginData = Utility.getTable("./data/TestData.xlsx", "Customer", "TC_03");
		return loginData;
	}
	
	
}
