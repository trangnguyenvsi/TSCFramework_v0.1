package com.vsii.tsc.guru.report;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.vsii.tsc.guru.utility.Utility;

public class Report {
	static Properties p;

	public static ExtentReports Instance() throws IOException {
		ExtentReports extent;
		p = Utility.readConfig();
		String path = p.getProperty("reportPath");
		extent = new ExtentReports(path, false);
		extent.config().documentTitle("Guru Test Report");
		extent.config().reportHeadline("Automation Test Report for Guru");
	
		//extent.config().documentTitle("Automation Report").reportName("Regression");
		return extent;
	}

	public static String CaptureScreen(WebDriver driver, String captureName) throws IOException {
		p = Utility.readConfig();
		String imagePath = p.getProperty("imagePath") + captureName;
		TakesScreenshot oScn = (TakesScreenshot) driver;
		File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		File oDest = new File(imagePath + ".jpg");
		try {
			FileUtils.copyFile(oScnShot, oDest);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return imagePath + ".jpg";
	}

	public String captureScreenShotPopUp(String img) throws Exception {
		BufferedImage bfImage = new Robot()
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(bfImage, "png", new File(img + ".jpg"));
		return img + ".jpg";
	}
}
