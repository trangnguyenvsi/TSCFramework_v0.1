package com.vsii.tsc.utility;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.vsii.tsc.model.TestCase;

public class Reports implements IReporter {
	public static ExtentReports extent;
	public ITestResult result;
	public String categoryName;
	ITestContext context;

	public ReportHandle reportHandle;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		extent = new ExtentReports(TestBase.p.getProperty("reportPath"), true);
		reportHandle = new ReportHandle();
		// create Test case Table
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				context = r.getTestContext();
				categoryName = context.getName();
				/* Excel Report*/
				reportHandle.exportExcelReport(categoryName);
				/* HTML Report*/
				try {
					reportHandle.buildTestNodes(extent,context.getPassedTests(), LogStatus.PASS,categoryName);
					reportHandle.buildTestNodes(extent,context.getFailedTests(), LogStatus.FAIL,categoryName);
					reportHandle.buildTestNodes(extent,context.getSkippedTests(), LogStatus.SKIP, categoryName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		extent.flush();
		extent.close();
		TestBase.driver.quit();
	}

	
}

	
