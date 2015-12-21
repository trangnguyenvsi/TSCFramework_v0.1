/*package com.vsii.tsc.guru.testcase;


import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.vsii.tsc.guru.data.TestData;
import com.vsii.tsc.guru.pages.method.DeleteCustomerPageMethod;
import com.vsii.tsc.guru.pages.method.LoginPageMethod;
import com.vsii.tsc.guru.report.Report;
import com.vsii.tsc.guru.testbase.TestBase;

public class DeleteCustomer extends TestBase {

	LoginPageMethod objLogin;
	DeleteCustomerPageMethod objDeleteCustomer;

	@Test(priority = 0, dataProvider = "dpLogin", dataProviderClass = TestData.class)
	public void verify_Login(String username, String password) throws Exception {
		objDeleteCustomer = new DeleteCustomerPageMethod(driver);
		objLogin = new LoginPageMethod(driver);
		objLogin.loginToManagerPage(username, password);
		String managerID = objLogin.getManagerIDInManagerPage();
		test.log(LogStatus.INFO, "<b>TC10_VERIFY LOGIN FUNCTION</b>");
		if (managerID.contains("Manger Id : mngr18267")) {
			test.log(LogStatus.PASS, "User logs in successfully");
		} else {
			test.log(LogStatus.FAIL, "User logs in not successfully");
		}
		test.log(LogStatus.INFO, test.addScreenCapture(Report.CaptureScreen(driver, "TC10")));
	}

	@Test(priority = 1, description = "Verify popup display")
	public void verify_Popup_Text() throws Exception {
		objDeleteCustomer.clickDeleteCustomer();
		objDeleteCustomer.clickSubmit();
		String popupText = objDeleteCustomer.getPopupText();
		test.log(LogStatus.INFO, "<b>TC10_VERIFY POPUP TEXT</b>");
		if (popupText.contains("Please fill all fields")) {
			test.log(LogStatus.PASS, "Popup text displays correctly");
		} else {
			test.log(LogStatus.FAIL, "Popup text displays incorrectly");
		}
		test.log(LogStatus.INFO, test.addScreenCapture(
				Report.captureScreenShotPopUp("C:/Users/hungnt2/git/selenium/report/screenshot/TC20")));
		objDeleteCustomer.closePopup();
	}

	@Test(priority = 2, dataProvider="dpDeleteCustomer", dataProviderClass = TestData.class)
	public void verify_Delete_Customer(String custID) throws IOException {
		objDeleteCustomer.deleteCustomer(custID);
		objDeleteCustomer.clickSubmit();
		objDeleteCustomer.closePopup();
		objDeleteCustomer.closePopup();
		test.log(LogStatus.INFO, "<b>TC11_VERIFY DELETE CUSTOMER</b>");
		test.log(LogStatus.PASS, "Delete successfully");
		test.log(LogStatus.INFO, test.addScreenCapture(Report.CaptureScreen(driver, "TC11")));
	}
}
*/