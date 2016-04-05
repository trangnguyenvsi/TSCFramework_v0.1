package com.vsii.tsc.utility;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.IResultMap;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.vsii.tsc.model.TestCase;

public class ReportHandle {
	/* Use for exportExcelReport()*/
	File source;
	File dest;
	ExcelHandle excelHandle;
	List<TestCase> tcList;
	XSSFWorkbook workbook;
	
	/*
	 * Initialize for all reports 
	 */
	public void initReport(String testName){
		excelHandle = new ExcelHandle();
		source = new File(TestBase.p.getProperty("tcFile"));
		dest = new File(TestBase.p.getProperty("copyTCFile"));
		/* Copy the original test case to another location to process*/
		excelHandle.copyFile(source, dest);
		/* Get the test case table temple*/
		
		try {
			workbook = new XSSFWorkbook(dest);
		} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		tcList = excelHandle.getTCTemplateTable(workbook, testName, dest.toPath().toString());
		
	}
	/*
	 * Export the test result to excel file
	 */
	public void exportExcelReport(String testName){
		initReport(testName);
		/* Write the test result to excel file*/
		excelHandle.writeToExcel(workbook, tcList, testName, dest);
		/* Create the result file*/
		String dir = TestBase.p.getProperty("resultpath");
		CommonOperations.createDir(dir);
		excelHandle.createExcelFile(workbook,dir +TestBase.p.getProperty("resultFile")+ testName + ".xlsx");
	}
	
	/*
	 * Export the test result to HTML file
	 */
	public void buildTestNodes(ExtentReports extentReport, IResultMap tests, LogStatus status, String testName) throws IOException {
		initReport(testName);
		ExtentTest test;
		String methodName;
		if (tests.size() > 0) {
				
			for (ITestResult result : tests.getAllResults()) {
				
				methodName = result.getMethod().getMethodName();
				test = extentReport.startTest(methodName);
				test.assignCategory(testName);
				String message = "Test " + status.toString().toLowerCase() + "ed";
		
				for(TestCase tc:tcList){
					/* if TCID is equals to method Name, loop in test result*/
					if(tc.getTcID().equals(methodName)){
						
						for(int i=0;i<tc.getTcImageResults().size();i++){
							String testResult = tc.getTcImageResults().get(i).getTcResult();
							/* If test result is same as Status-> log to report*/
							if(testResult.equals(status.toString())){
							test.log(LogStatus.INFO,tc.getTcDesc());
							test.log(LogStatus.INFO,tc.getTcStep());					
							test.log(LogStatus.INFO,
									test.addScreenCapture(TestBase.p.getProperty("imagePath") + tc.getTcImageResults().get(i).getTcImage() + ".jpg"));	
							tc.getTcImageResults().remove(i);
							break;
							}
						}
						break;
					}		
				}
				test.log(status,message);
				extentReport.endTest(test);	
			}	
		}
	}

}
