package com.vsii.tsc.guru.testdata;

import org.testng.annotations.Factory;

import com.vsii.tsc.guru.testcase.EditCustomer;
import com.vsii.tsc.guru.testcase.NewCustomer;

public class TestDataFactory {
	 //@Factory
	 public Object[] loginToCreateNewCustomer() {
		 Object[] data = new Object[1];
		// data[0]= new NewCustomer("mngr30380","vAtepYs");
		 //data[1]= new EditCustomer("mngr30380","vAtepYs");
		// return new Object[]{new NewCustomer("mngr26605","bAqYpep")};
		return data;
	 }
	 
//	 @Factory
//	 public Object[] loginToEditCustomer() {
//		 return new Object[]{new EditCustomer("mngr22018","nAgezam")};
//	 }

}
