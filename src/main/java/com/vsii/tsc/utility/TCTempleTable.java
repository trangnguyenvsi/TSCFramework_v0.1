package com.vsii.tsc.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import com.vsii.tsc.model.TCImageResults;
import com.vsii.tsc.model.TestCase;

public class TCTempleTable {
	public static List<TestCase> tcList;
	public static TestCase testcase;
	public static List<TestCase> getTCTemplateTable(String testName){
	try {
		ExcelHandle.openExcelFile(TestBase.p.getProperty("tcFile"),testName,
				Integer.parseInt(TestBase.p.getProperty("tcIDCol")),
				Integer.parseInt(TestBase.p.getProperty("tcDescCol")),
				Integer.parseInt(TestBase.p.getProperty("tcPreCol")),
				Integer.parseInt(TestBase.p.getProperty("tcStepCol")),
				Integer.parseInt(TestBase.p.getProperty("tcExptCol")));
	
		tcList = new ArrayList<TestCase>();
		// Create test case List
		for (String id : ExcelHandle.tcIDList) {

			int index = ExcelHandle.tcIDList.indexOf(id);
			
			if (TestBase.tcImageResultsList.containsKey(id)) {
				testcase = new TestCase();
				testcase.setTcID(id);
				testcase.setTcDesc(ExcelHandle.tcDescList.get(index));
				testcase.setTcPrec(ExcelHandle.tcPreconList.get(index));
				testcase.setTcStep(ExcelHandle.tcStepList.get(index));
				testcase.setTcStep(ExcelHandle.tcExptList.get(index));
				List<TCImageResults> imageResult = TestBase.tcImageResultsList.get(id);
				testcase.setTcImageResults(imageResult);
				tcList.add(testcase);
			}
		}

	} catch (NumberFormatException e1) {
		//print
		e1.printStackTrace();
	} catch (IOException e1) {
		// print
		e1.printStackTrace();
	}
	return tcList;
 }
}
