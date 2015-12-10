package com.vsii.tsc.guru.testcase;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
 
import java.net.URL;
 
public class SauceLab {
 
  public static final String USERNAME = "YOUR_USERNAME";
  public static final String ACCESS_KEY = "YOUR_ACCESS_KEY";
  public static final String URL = "http://trangnguyen:7d3c3bec-6461-4901-bb91-02607b5ac51a@ondemand.saucelabs.com:80/wd/hub";
 
  public static void main(String[] args) throws Exception {
 
    DesiredCapabilities caps = DesiredCapabilities.chrome();
    caps.setCapability("platform", "Windows XP");
    caps.setCapability("version", "43.0");
    caps.setCapability("name", "Sauce Sample Test");
 
    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
 
    /**
 * Goes to Sauce Lab's guinea-pig page and prints title
 */
 
    driver.get("https://saucelabs.com/test/guinea-pig");
    System.out.println("title of page is: " + driver.getTitle());
 
    driver.quit();
  }
}