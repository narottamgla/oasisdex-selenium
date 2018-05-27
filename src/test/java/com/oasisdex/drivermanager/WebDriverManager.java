package com.oasisdex.drivermanager;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.oasisdex.models.BrowserTypes;

/**
 * Provides ChromeDriver implimentation
 * @author narottam
 * @Email narottamgla@gmail.com
 *
 */
public class WebDriverManager {
	private static final Logger LOG = LogManager.getLogger(WebDriverManager.class);
	ChromeDriver driver = null;
	public ChromeDriver getDriver(BrowserTypes browser) {
		DesiredCapabilities capabilities;
		switch (browser.getLabel()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "lib//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addExtensions(new File("./lib/4.6.0_0.crx"));
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
			return driver;
		default:
			LOG.info("unrecognized browser");
			System.exit(0);
		}
		return driver;
	}
	
	public void quitDriver(){
		driver.quit();
	}
}
