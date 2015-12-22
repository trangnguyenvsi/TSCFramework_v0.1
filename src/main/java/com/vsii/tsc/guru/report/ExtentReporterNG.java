package com.vsii.tsc.guru.report;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.vsii.tsc.guru.testbase.TestBase;
import com.vsii.tsc.guru.utility.CommonOperations;
import com.vsii.tsc.guru.utility.DateTime;
import com.vsii.tsc.guru.utility.Utility;

public class ExtentReporterNG implements IReporter {
	static Properties p;
	public static ExtentReports extent;
	public ITestResult result;
	static String imagePath;
	static String imageName;
	static String method;


	// WebDriver driver=null;
	String filePath = "D:/MyJobs/RemarkMedia/Build_Project_for_TSC/NewSelenium/report/screenshot/";

	// Write test results
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {		
		extent = new ExtentReports("D:/MyJobs/RemarkMedia/Build_Project_for_TSC/NewSelenium/report/Extent.html", false);
		extent.config().documentTitle("Guru Test Report");

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				
				//Open excel file with the sheet name is the test name in testng.xml
				try {
					Utility.openExcelFile(TestBase.p.getProperty("tcFile"), context.getName(),
							Integer.parseInt(TestBase.p.getProperty("tcIDCol")),Integer.parseInt(TestBase.p.getProperty("tcDescCol")),Integer.parseInt(TestBase.p.getProperty("tcStepCol")));
					
					 System.out.println(TestBase.p.getProperty("tcIDCol"));
					 System.out.println(TestBase.p.getProperty("tcDescCol"));
					 System.out.println(TestBase.p.getProperty("tcStepCol"));
					 
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
					try {
						buildTestNodes(context.getPassedTests(), LogStatus.PASS);
						buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
						buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Create test result file for each test
					Utility.createExcelFile(TestBase.p.getProperty("resultpath")+"Guru_TestCase_"+context.getName()+".xlsx");
			}
			
		}

		extent.flush();
		extent.close();

		TestBase.driver.quit();
	}

	private static void buildTestNodes(IResultMap tests, LogStatus status) throws IOException {
		
		
		
		ExtentTest test;		
		if (tests.size() > 0) {
			
			for (ITestResult result : tests.getAllResults()) {
				method = result.getMethod().getMethodName();
				test = extent.startTest(result.getMethod().getMethodName());
				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				String message = "Test " + status.toString().toLowerCase() + "ed";

				if (result.getThrowable() != null)
					message = result.getThrowable().getMessage();
				if(status.toString().equals("pass")){
					System.out.println("test status:"+status.toString());
					Utility.writeTestResults(method, 5, "Passed");
				}
				else if (status.toString().equals("fail")){
					Utility.writeTestResults(ExtentReporterNG.method, 5, "Failed");
				}

				// Write status to report
				test.log(status, message);
				
				// Add image to report
				for (String image : TestBase.imageList) {
					
					String imageTestCase = image.substring(0,4);
					//Get exactly image name by cut timestamp
//					String[] imageText = image.split("_");
//					for (String s : imageText) {
//						imageTestCase = s.substring(0, s.indexOf("_")+1);
//						System.out.println("Image:"+imageTestCase);
//					}
					
						if (imageTestCase.equals(method)) {
							test.log(LogStatus.INFO, test.addScreenCapture(p.getProperty("imagePath") + image + ".jpg"));
							TestBase.imageList.remove(image);	
							break;
						} 
						
					}
				 	Iterator it = Utility.createHashMap(Utility.tcIDList, Utility.tcDescList).entrySet().iterator();
				    while (it.hasNext()) {
				        Map.Entry pair = (Map.Entry)it.next();
				        if(pair.getKey().equals(method)){
				        	test.log(LogStatus.INFO,(Throwable) pair.getValue());
				        }
				    	break;
				    }
				
				extent.endTest(test);
				
			}
		}
		
	}

	public static String CaptureScreen(WebDriver driver, String captureName) throws IOException {
		p = Utility.readConfig();
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

	public static String captureScreenShotPopUp(String img) throws Exception {
		BufferedImage bfImage = new Robot()
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(bfImage, "png", new File(img + ".jpg"));
		String imageName = img + ".jpg";
		return imageName;

	}

}
