package com.oasisdex.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oasisdex.utils.WaitUtil;

/**
 * Contains Pageobject for OasisDex home page
 * @author narottam
 * @Email narottamgla@gmail.com
 *
 */
public class OasisdexHomePage {

	private ChromeDriver driver;
	public OasisdexHomePage(ChromeDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".no-ethereum-section")
	WebElement noEthereumSection;
	
	@FindBy(css= ".text-success")
	WebElement oasisdexHomePage;
	
	public boolean isOasisdexBlocked(){
		WaitUtil.waitForElementToBeVisible(driver, noEthereumSection);
		return noEthereumSection.isDisplayed();
	}
	
	public boolean isOasisdexUnblocked(){
		WaitUtil.waitForElementToBeVisible(driver, oasisdexHomePage);
		return oasisdexHomePage.isDisplayed();
	}
	
}
