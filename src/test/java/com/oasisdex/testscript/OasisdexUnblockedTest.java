package com.oasisdex.testscript;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.oasisdex.drivermanager.WebDriverManager;
import com.oasisdex.models.BrowserTypes;
import com.oasisdex.pages.MetaMaskExtensionPage;
import com.oasisdex.pages.OasisdexHomePage;
import com.oasisdex.utils.Constants;;

/**
 * Used Check Blocked and Unblocked scenarios for oasisdex with Metamask
 * @author narottam
 * @Email narottamgla@gmail.com
 *
 */

public class OasisdexUnblockedTest {
	private static final Logger LOG = LogManager.getLogger(OasisdexUnblockedTest.class);
	WebDriverManager driverManager;
	ChromeDriver driver;
	MetaMaskExtensionPage metaMaskExtensionPage;
	OasisdexHomePage oasisdexHomePage;
	
	@BeforeClass
	public void setUpDriver(){
		LOG.info("Intilizing driver...");
	    driverManager = new WebDriverManager();
		driver = driverManager.getDriver(BrowserTypes.CHROME);
		metaMaskExtensionPage = new MetaMaskExtensionPage(driver);
		oasisdexHomePage = new OasisdexHomePage(driver);	
	}
	
	@Test(priority=1 , description ="verifyOasisdexBlocked page displayed when MetaMask account is not set")
	public void verifyOasisdexBlocked(){
		LOG.info("Verifying OasisdexBlocked page");
		driver.get(Constants.OASISDEX_APP_URL);
		driver.switchTo().activeElement();
		Assert.assertTrue(oasisdexHomePage.isOasisdexBlocked(), "OasisdexBlocked blocked page is not displaying");	
		metaMaskExtensionPage.closeAdditionalWindow();	
	}
	
	@Test(priority=2 , description ="verifyOasisdexUnBlocked page displayed when MetaMask account is set")
	public void verifyOasisdexUnBlocked(){
		LOG.info("Verifying OasisdexUnBlocked page");
		driver.navigate().to(Constants.META_MASK_EXT_URL);
		metaMaskExtensionPage.clickAcceptButton();
		metaMaskExtensionPage.scrollToTermOfUse();
		metaMaskExtensionPage.clickAcceptButton();
		if(metaMaskExtensionPage.isAcceptButtonDisplayed()){
			LOG.info("Clicking accept button");
			metaMaskExtensionPage.clickAcceptButton();
			LOG.info("Clicked accept button");
		}
		metaMaskExtensionPage.setMetaMaskPassword(Constants.META_MAST_PASSWORD, Constants.META_MAST_PASSWORD);
		metaMaskExtensionPage.clickCopiedSomeWhereSafeButton();
		driver.navigate().to(Constants.OASISDEX_APP_URL);
		Assert.assertTrue(oasisdexHomePage.isOasisdexUnblocked(), "OasisdexBlocked blocked page is not displaying");	
	}
	
	@AfterClass
	public void quitDriver(){
		LOG.info("Quiting driver");
		driverManager.quitDriver();
	}

}
