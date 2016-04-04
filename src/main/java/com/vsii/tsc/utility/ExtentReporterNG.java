package com.vsii.tsc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.vsii.tsc.utility.ExcelHandle;

public class ExtentReporterNG implements IReporter {
	public static ExtentReports extent;
	public ITestResult result;
	public String categoryName;
	ITestContext context;
	public List<TestCase> tcTempleTable;
	public List<TestCase> tcTempleTable1;
	ExtentTest test;

	// Write test results into test report
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		extent = new ExtentReports(TestBase.p.getProperty("reportPath"), true);
		// create Test case Table
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				context = r.getTestContext();
				categoryName = context.getName();
				/*
				 * Get test case temple table
				 */
				
//				try {
//					buildTestNodes(context.getPassedTests(), LogStatus.PASS);
//					buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
//					buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
				
				tcTempleTable1 = TCTempleTable.getTCTemplateTable(categoryName);
				createExcelReport(tcTempleTable1);
				
				String dir = TestBase.p.getProperty("resultpath");
				CommonOperations.createDir(dir);
				ExcelHandle.createExcelFile(dir +TestBase.p.getProperty("resultFile")+ context.getName() + ".xlsx");
			}

		}
		// get Test Case Table
		//extent.flush();
		//extent.close();
		TestBase.driver.quit();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) throws IOException {

		ExtentTest test;
		String methodName;
		tcTempleTable = TCTempleTable.getTCTemplateTable(categoryName);
		if (tests.size() > 0) {
				
			for (ITestResult result : tests.getAllResults()) {
				
				methodName = result.getMethod().getMethodName();
				test = extent.startTest(methodName);
				test.assignCategory(categoryName);
				String message = "Test " + status.toString().toLowerCase() + "ed";
		
				for(TestCase tc:tcTempleTable){
					System.out.println("TCID"+tc.getTcID());
					/*
					 * if TCID is equals to method Name, loop in test result
					 */
					if(tc.getTcID().equals(methodName)){
						
						for(int i=0;i<tc.getTcImageResults().size();i++){
							String testResult = tc.getTcImageResults().get(i).getTcResult();
							/*
							 * If test result is same as Status-> log to report			
							 */
							if(testResult.equals(status.toString())){
							//test.log(LogStatus.INFO, tc.getTcID());
							test.log(LogStatus.INFO,tc.getTcDesc());
							test.log(LogStatus.INFO,tc.getTcStep());					
							test.log(LogStatus.INFO,
									test.addScreenCapture(TestBase.p.getProperty("imagePath") + tc.getTcImageResults().get(i).getTcImage() + ".jpg"));
							//test.log(LogStatus.INFO, tc.getTcResults().get(i).getTcResult());		
							tc.getTcImageResults().remove(i);
							break;
							}
						}
						break;
					}		
				}
				test.log(status,message);
				extent.endTest(test);	
			}	
		}
	}

	/*
	 * Write results to excel
	 */
	@SuppressWarnings("deprecation")
	public void createExcelReport(List<TestCase> tcTemple) {
		File source = new File("./testcase/GuruDemoSite_Testcase.xlsx");
		File dest = new File("./testresults/GuruDemoSite_Testcase_v01.xlsx");
		
		try {
			FileUtils.copyFile(source,dest);
			System.out.println("copy success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileInputStream file;
		XSSFWorkbook workbook = null;
	    XSSFSheet sheet;
		XSSFRow row;
		Cell cell;
		
		try {
			workbook = new XSSFWorkbook(dest);
		} catch (InvalidFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		sheet = workbook.getSheet(categoryName);

		//tcTemple = TCTempleTable.getTCTemplateTable(categoryName);
		/*Define test result file*/
		int rowNum = Integer.parseInt(TestBase.p.getProperty("resultRow"));
		int resultIDCol = Integer.parseInt(TestBase.p.getProperty("resultIDCol"));
		int resultDesCol = Integer.parseInt(TestBase.p.getProperty("resultDesCol"));
		int resultPreCol = Integer.parseInt(TestBase.p.getProperty("resultPreCol"));
		int resultStepCol = Integer.parseInt(TestBase.p.getProperty("resultStepCol"));
		int resultExptCol = Integer.parseInt(TestBase.p.getProperty("resultExptCol"));
		int resultTestCol = Integer.parseInt(TestBase.p.getProperty("resultTestCol"));
		
			// Change cell color
			/* Get access to XSSFCellStyle */
			XSSFCellStyle myStyle = ExcelHandle.workbook.createCellStyle();
			/*
			 * We will now specify a background cell color
		     */

			myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			myStyle.setBorderTop((short)1);
			myStyle.setBorderBottom((short)1);
			myStyle.setBorderLeft((short)1);
			myStyle.setBorderRight((short)1);
			
			for(TestCase tc:tcTemple){
				System.out.println("tc size:"+tcTemple.size());
				System.out.println("size:"+tc.getTcImageResults().size());
				int i;
				int rowTC = rowNum;
				for(i=0;i<tc.getTcImageResults().size();i++){
				
					ExcelHandle.sheet.getRow(rowNum).getCell(resultIDCol).setCellValue(tc.getTcID());
					ExcelHandle.sheet.getRow(rowNum).getCell(resultDesCol).setCellValue(tc.getTcDesc());
					ExcelHandle.sheet.getRow(rowNum).getCell(resultPreCol).setCellValue(tc.getTcPrec());
					ExcelHandle.sheet.getRow(rowNum).getCell(resultStepCol).setCellValue(tc.getTcStep());
					ExcelHandle.sheet.getRow(rowNum).getCell(resultExptCol).setCellValue(tc.getTcExpt());
					
					System.out.println("TC ID:"+tc.getTcID());
					System.out.println("TC Des"+tc.getTcDesc());
					System.out.println("TC pre"+tc.getTcPrec());
					System.out.println("TC step"+tc.getTcStep());
					System.out.println("TC expt"+tc.getTcExpt());
					
					String result = tc.getTcImageResults().get(i).getTcResult();
					if (result.equals("pass")) {
						myStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					} else if (result.equals("fail")) {
						myStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
					}
					ExcelHandle.sheet.getRow(rowNum).getCell(resultTestCol).setCellStyle(myStyle);
					ExcelHandle.sheet.getRow(rowNum).getCell(resultTestCol).setCellValue(tc.getTcImageResults().get(i).getTcResult());
					rowNum++;
					System.out.println("Row Number:"+rowNum);
									
				}
				int j = rowTC+i;
				sheet.addMergedRegion(new CellRangeAddress(13,20,1,2));
				System.out.println("rowTC"+rowTC);
				System.out.println("row to Merge"+j);
			}
								
	}
}

