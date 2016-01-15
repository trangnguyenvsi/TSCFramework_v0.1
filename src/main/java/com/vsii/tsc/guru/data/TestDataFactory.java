package com.vsii.tsc.guru.data;

import org.testng.annotations.Factory;

import com.vsii.tsc.guru.testcase.EditCustomer;
import com.vsii.tsc.guru.testcase.NewCustomer;

public class TestDataFactory {
	 @Factory
	 public Object[] loginToCreateNewCustomer() {
		 return new Object[]{new NewCustomer("mngr26605","bAqYpep")};
	 }
	 
//	 @Factory
//	 public Object[] loginToEditCustomer() {
//		 return new Object[]{new EditCustomer("mngr22018","nAgezam")};
//	 }

}
