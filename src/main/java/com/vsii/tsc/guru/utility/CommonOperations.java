package com.vsii.tsc.guru.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MultiHashMap;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vsii.tsc.guru.report.ExtentReporterNG;
import com.vsii.tsc.guru.testbase.TestBase;

@SuppressWarnings("deprecation")
public class CommonOperations {

	List<String> list = null;

	// Close Alert
	public static void closePopup() {
		Alert alert = TestBase.driver.switchTo().alert();
		alert.accept();
	}

	// Get Alert
	public static boolean isAlertPresent(WebDriver driver) {
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver,
				0 /* timeout in seconds */);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (TimeoutException eTO) {
			foundAlert = false;
		}
		return foundAlert;
	}

	// Check duplicated in list
	public static boolean checkDuplicate(List<String> methodList) {
		HashSet set = new HashSet();
		for (int i = 0; i < methodList.size(); i++) {
			boolean val = set.add(methodList.get(i));
			if (val == false) {
				return val;
			}
		}
		return true;
	}

	// Count duplicated Value in list
	public static int countDuplicatedValue(List<String> methodList, String searchText) {
		int i;
		for (i = 0; i < methodList.size(); i++) {
			for (String s : methodList) {
				if (methodList.get(i).equals(searchText))
					;
				i++;
			}
		}
		return i;

	}

	// Take picture after test
	public static void takePicture() throws Exception {
		TestBase.imageName = TestBase.methodName + "_" + DateTime.createDateText();
		// Capture popup
		if (CommonOperations.isAlertPresent(TestBase.driver)) {
			String dir = TestBase.p.getProperty("imagePath");
			CreateDirectory.createDir(dir);
			ExtentReporterNG.captureScreenShotPopUp(dir + TestBase.imageName);

			createTestCaseList();
			closePopup();
		}
		// Capture screen
		else {
			ExtentReporterNG.CaptureScreen(TestBase.driver, TestBase.imageName);
			createTestCaseList();
		}
		System.out.println("Image List Size" + TestBase.imageList.size());

	}
	//Create the test case image list
	private static void createTestCaseList() {
		if (TestBase.tcList.size() == 0) {
			TestBase.imageList = new ArrayList<String>();
			TestBase.imageList.add(TestBase.imageName);
			TestBase.tcList.put(TestBase.methodName, TestBase.imageList);
		} else {
			if (TestBase.tcList.containsKey(TestBase.methodName)) {
				TestBase.tcList.get(TestBase.methodName).add(TestBase.imageName);
			} else {
				TestBase.imageList = new ArrayList<String>();
				TestBase.imageList.add(TestBase.imageName);
				TestBase.tcList.put(TestBase.methodName, TestBase.imageList);
			}
		}
	}

	// Verify Element Present
	public static boolean verifyElementPresent(WebElement element) {

		if (element.isDisplayed()) {
			return true;
		} else
			return false;

	}
}
