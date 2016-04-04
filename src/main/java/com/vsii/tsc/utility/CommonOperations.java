package com.vsii.tsc.utility;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.vsii.tsc.model.TCImageResults;

@SuppressWarnings("deprecation")
public class CommonOperations {

	List<String> list = null;

	/*
	 * Read from configuration file
	 */
	public static Properties readConfig() throws IOException {
		// Create new properties variable
		Properties p = new Properties();
		// Read object properties file
		InputStream stream = new FileInputStream("./properties/config.properties");
		// Load input stream file
		p.load(stream);
		return p;
	}

	//---------------------------------------------------------------------------
	/*
	 * Accept Alert
	 */
	
	public static void closePopup() {
		Alert alert = TestBase.driver.switchTo().alert();
		alert.accept();
	}
	//---------------------------------------------------------------------------
	/*
	 *  Get Alert
	 */
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
	//---------------------------------------------------------------------------
	/*
	 * Take picture after test
	 */
	public static void takePicture() throws Exception {
		TestBase.imageName = TestBase.methodName + "_" + DateTime.createDateText()+"-"+TestBase.testStatus;
		// Capture popup
		if (CommonOperations.isAlertPresent(TestBase.driver)) {
			String dir = TestBase.p.getProperty("imagePath");
			createDir(dir);
			captureScreenShotPopUp(dir + TestBase.imageName);

			createTestCaseList();
			closePopup();
		}
		// Capture screen
		else {
			CaptureScreen(TestBase.driver, TestBase.imageName);
			createTestCaseList();
		}
		//System.out.println("Image List Size" + TestBase.imageList.size());
		

	}
	/*
	 * Capture screen
	 */
	public static String CaptureScreen(WebDriver driver, String captureName) throws IOException {
		Properties p = readConfig();
		String imagePath;
		String imageName;
		imagePath = p.getProperty("imagePath") + captureName;
		TakesScreenshot oScn = (TakesScreenshot) driver;
		File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		File oDest = new File(imagePath + ".jpg");
		try {
			FileUtils.copyFile(oScnShot, oDest);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		imageName = imagePath + ".jpg";
		return imageName;
}
	/*
	 * Capture screenshot
	 */
	public static String captureScreenShotPopUp(String img) throws Exception {
		BufferedImage bfImage = new Robot()
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(bfImage, "png", new File(img + ".jpg"));
		String imageName = img + ".jpg";
		return imageName;

}

	
	
	//-----------------------------------------------------------------------------
	/*
	 * Create the test case's image list
	 * 1. If tcImageList is empty, create new imageList then add image to imageList.
	 * After that put test method and imageList to tcImageList
	 * 2. If tcImageList is not empty, and tcImageList's key contain the test method
	 * add imageName to the tcImageList by key (method name)
	 * 3. If tcImageList is not empty and the key doesn't contain method name
	 * create new ImageList then add image to this list. 
	 * After that put the method name and imageList to tcImageList
	 */
	private static void createTestCaseList() {
		TCImageResults tcResult = new TCImageResults();
		if(TestBase.tcImageResultsList.size()==0){
			TestBase.imageResultList = new ArrayList<TCImageResults>();			
			tcResult.setTcImage(TestBase.imageName);
			tcResult.setTcResult(TestBase.testStatus);
			TestBase.imageResultList.add(tcResult);
			TestBase.tcImageResultsList.put(TestBase.methodName, TestBase.imageResultList);
		}
		else{
			if(TestBase.tcImageResultsList.containsKey(TestBase.methodName)){
				tcResult.setTcImage(TestBase.imageName);
				tcResult.setTcResult(TestBase.testStatus);
				//TestBase.imageResultList.add(tcResult);
				TestBase.tcImageResultsList.get(TestBase.methodName).add(tcResult);
			}
			else {
				TestBase.imageResultList = new ArrayList<TCImageResults>();			
				tcResult.setTcImage(TestBase.imageName);
				tcResult.setTcResult(TestBase.testStatus);
				TestBase.imageResultList.add(tcResult);
				TestBase.tcImageResultsList.put(TestBase.methodName, TestBase.imageResultList);
			}
		}
		
		
		
	}	
	//-------------------------------------------------------------------------------
	/*
	 *  Verify Element Present
	 */
	public static boolean verifyElementPresent(WebElement element) {

		if (element.isDisplayed()) {
			return true;
		} else
			return false;

	}
	//-------------------------------------------------------------------------------
	/*
	 * Create Directory
	 */
	public static void createDir(String dir){
		String directoryName = dir;
		File theDir = new File(directoryName);
		if(!theDir.exists())
		{
			theDir.mkdir();
		}
	} 
	/*
	 * get test result of each test method
	 */
	public static String getMethodTestResult(ITestResult testResult){
		int resultCode = testResult.getStatus();
		if(resultCode==1){
			TestBase.testStatus="pass";
		}
		else if (resultCode==2){
			TestBase.testStatus="fail";
		}
		else if (resultCode==3){
			TestBase.testStatus="skip";
		}
		return TestBase.testStatus;
	}
	
}
