package com.vsii.tsc.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

public class DownloadUploadFile {
	 FirefoxProfile fprofile;
	 /*
	  * Download file
	  */
	 public DownloadUploadFile(FirefoxProfile fprofile){
		 this.fprofile = fprofile;
	 }
	 
	 public void setupDownloadFile(String selectedFolder){
	  
	  //Set Location to store files after downloading.
	  fprofile.setPreference("browser.download.dir", selectedFolder);
	  fprofile.setPreference("browser.download.folderList", 2);
	  
	  //Set Preference to not show file download confirmation dialogue using MIME types Of different file extension types.
	  fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", 
	    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"//MIME types Of MS Excel File.
	    + "application/pdf;" //MIME types Of PDF File.
	    + "application/vnd.openxmlformats-officedocument.wordprocessingml.document;" //MIME types Of MS doc File.
	    + "text/plain;" //MIME types Of text File.
	    + "text/csv"); //MIME types Of CSV File.
	  fprofile.setPreference( "browser.download.manager.showWhenStarting", false );
	  fprofile.setPreference( "pdfjs.disabled", true );
	  //Pass fprofile parameter In webdriver to use preferences to download file.
	  //driver = new FirefoxDriver(fprofile);  
	 } 
}
