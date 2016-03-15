package com.vsii.tsc.guru.testcase;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.vsii.tsc.utility.DownloadUploadFile;
import com.vsii.tsc.utility.TestBase;

public class TestDownloadUpload {
	
	 @Test
	 public void OpenURL() throws InterruptedException{
	 // DownloadUploadFile fileObj = new DownloadUploadFile(TestBase.fprofile);
	 // fileObj.downloadFile("D:\\SeleniumDownloaded");
	  
	  TestBase.driver.get("http://only-testing-blog.blogspot.in/2014/05/login.html");
	         //Download Text File
	  TestBase.driver.findElement(By.xpath("//a[contains(.,'Download Text File')]")).click();
	  Thread.sleep(5000);//To wait till file gets downloaded.
	                //Download PDF File
	  TestBase.driver.findElement(By.xpath("//a[contains(.,'Download PDF File')]")).click();
	  Thread.sleep(5000);
	                //Download CSV File
	  TestBase.driver.findElement(By.xpath("//a[contains(.,'Download CSV File')]")).click();
	  Thread.sleep(5000);
	                //Download Excel File
	  TestBase.driver.findElement(By.xpath("//a[contains(.,'Download Excel File')]")).click();
	  Thread.sleep(5000);
	                //Download Doc File
	  TestBase.driver.findElement(By.xpath("//a[contains(.,'Download Doc File')]")).click();
	  Thread.sleep(5000);  
	 }
}
