package com.vsii.tsc.guru.data;

import org.testng.annotations.Factory;

import com.vsii.tsc.guru.testcase.NewCustomer;

public class TestDataFactory {
	 @Factory
	 public Object[] factoryMethod() {
		 return new Object[]{new NewCustomer("mngr22018","nAgezam")};
	 }

}
