package com.vsii.tsc.utility;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {
	public static WebDriver driver;
	public static Properties p;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static FirefoxProfile fprofile;
	public static List<String> imageList;
	public static String methodName;
	public static String image;
	public static String imageName;
	// trial
	public static HashMap<String, List<String>> tcImageList;

	public RemoteWebDriver remoteDriver;

	@BeforeSuite
	public void setupSuite() throws IOException {

		// Read config file
		p = CommonOperations.readConfig();
		imageList = new ArrayList<String>();

		// trial
		tcImageList = new HashMap<String, List<String>>();

		if (p.getProperty("local").equals("No")) {

			// The parameter to run on sauce lab
			DesiredCapabilities caps = DesiredCapabilities.firefox();
			caps.setCapability("platform", "Windows 7");
			caps.setCapability("version", "42.0");
			caps.setCapability("name", "Guru Test 01");
			caps.setCapability("screenResolution", "1280x1024");

			driver = new RemoteWebDriver(
					new URL("http://trangnguyen:c8f52d3b-7d2a-431c-b217-27f6734f83fe@ondemand.saucelabs.com:80/wd/hub"),
					caps);
			// ((RemoteWebDriver) driver).setFileDetector(new
			// LocalFileDetector());
		} else {
			switch (p.getProperty("browserName")) {
			// Open Firefox browser
			case ("Firefox"):
				fprofile = new FirefoxProfile();

				DownloadUploadFile fileObj = new DownloadUploadFile(TestBase.fprofile);
				// Setup for Firefox profile
				fileObj.setupDownloadFile(p.getProperty("downloadLocation"));

				driver = new FirefoxDriver(fprofile);
				break;
			// Open Chrome browser
			case ("Chrome"):
				System.setProperty(p.getProperty("chromeDriver"), p.getProperty("chromeDriverPath"));
				driver = new ChromeDriver();
				break;
			// Open Internet Explorer browser
			case ("IE"):
				System.setProperty(p.getProperty("ieDriver"), p.getProperty("ieDriverPath"));
				driver = new InternetExplorerDriver();
				break;
			// Open Internet Explorer browser
			case ("Safari"):
				driver = new SafariDriver();
				break;
			default:
				break;
			}
		}
		// Open base URL
		driver.get(p.getProperty("baseUrl"));
		driver.manage().window().maximize();
		// Create instance of report
		// extent = ExtentReporterNG.Instance();
	}

	@AfterSuite
	public void teardownSuite() throws Exception {
		driver.quit();
		SendMail.execute();
	}

}
