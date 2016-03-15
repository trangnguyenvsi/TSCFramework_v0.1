package com.vsii.tsc.guru.commethods;

import org.openqa.selenium.By;

import com.vsii.tsc.guru.pages.method.LoginPageMethod;
import com.vsii.tsc.utility.TestBase;

public class CommonMethods {
  //Check login or not
	public static void checkLogin(){
		LoginPageMethod objLogin;
		objLogin = new LoginPageMethod(TestBase.driver);
		if(TestBase.driver.findElements(By.name("uid")).size()!=0){
			objLogin.loginToManagerPage("mngr30380", "vAtepYs");
		}
	}
}
