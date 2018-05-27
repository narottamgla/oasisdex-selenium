package com.oasisdex.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oasisdex.utils.WaitUtil;

/**
 * Contains Pageobject for MetaMask extension
 * @author narottam
 * @Email narottamgla@gmail.com
 *
 */
public class MetaMaskExtensionPage {

	private ChromeDriver driver;
	private static final Logger LOG = LogManager.getLogger(MetaMaskExtensionPage.class);

	public MetaMaskExtensionPage(ChromeDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ".//button[text()='Accept']")
	WebElement acceptButton;

	@FindBy(linkText = "Terms of Use")
	WebElement termOfUseLink;

	@FindBy(id = "password-box")
	WebElement passwordTxBx;

	@FindBy(id = "password-box-confirm")
	WebElement passwordBoxConfirmTxBx;

	@FindBy(xpath = ".//button[text()='Create']")
	WebElement createButton;

	@FindBy(xpath = "//button[contains(text(),'copied it somewhere safe')]")
	WebElement copiedSomeWhereSafeButton;

	@FindBy(name = "edit")
	WebElement accountPageText;

	public void clickAcceptButton() {
		try {
			WaitUtil.waitForSeconds(3);
			WaitUtil.waitForElementToBeVisible(driver, acceptButton);
			acceptButton.click();
			acceptButton.click();
		} catch (Exception e) {
			LOG.info("Accept button is not displaying");
		}
	}

	public boolean isAcceptButtonDisplayed() {
		try {
			WaitUtil.waitForSeconds(5);
			return acceptButton.isDisplayed();
		} catch (Exception e) {
			try {
				acceptButton.click();
			} catch (Exception e2) {
				return false;
			}
			return true;
		}
	}

	public void scrollToTermOfUse() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", termOfUseLink);
		WaitUtil.waitForElementToBeVisible(driver, termOfUseLink);
		WaitUtil.waitForSeconds(3);
	}

	public void clickTermOfUseLink() {
		WaitUtil.waitForElementToBeVisible(driver, termOfUseLink);
		termOfUseLink.click();
	}

	public void clickCopiedSomeWhereSafeButton() {
		WaitUtil.waitForElementToBeVisible(driver, copiedSomeWhereSafeButton);
		copiedSomeWhereSafeButton.click();
	}

	public boolean isMetaMaskExtensionAccountPageDisplayed() {
		WaitUtil.waitForElementToBeVisible(driver, accountPageText);
		return accountPageText.isDisplayed();
	}

	public void setMetaMaskPassword(String password, String cpassword) {
		LOG.info("Setting MetaMask Password");
		WaitUtil.waitForElementToBeVisible(driver, passwordTxBx);
		passwordTxBx.clear();
		passwordTxBx.sendKeys(password);
		passwordBoxConfirmTxBx.clear();
		passwordBoxConfirmTxBx.sendKeys(cpassword);
		createButton.click();
		LOG.info("MetaMask Password setted successfully");
	}
	
	public void closeAdditionalWindow(){
		LOG.info("Closing Additional browser window");
		Set<String> openedWindow = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(openedWindow);
		LOG.info(openedWindow.size());
		String base = driver.getWindowHandle();
		driver.switchTo().window(list.get(1));
	    driver.close();
	    driver.switchTo().window(base);
	    LOG.info("Closed Additional browser window");
	}
}
