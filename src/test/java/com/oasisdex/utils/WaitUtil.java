package com.oasisdex.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.function.Function;

public class WaitUtil {

	/**
	 * waiting for seconds
	 * 
	 * @author Narottam
	 * @param timeoutInSeconds
	 *            timeout in seconds for wait
	 * 
	 */
	public static void waitForSeconds(int timeoutInSeconds) {
		try {
			Thread.sleep(timeoutInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * Used to wait for element to be Present till max 120s second with polling frequency 5s
     * @author Narottam
     * @param <T>
     * @param driver
     * @param element
     */
	public static <T> void waitForElementPresent(WebDriver driver, WebElement element) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(120, SECONDS).pollingEvery(5, SECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		wait.until((Function<? super WebDriver, T>) ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Used to wait for element to be clickable till max 120 s with polling frequency 5s
	 * @author Narottam
	 * @param <T>
	 * @param driver
	 * @param element
	 */
	public static <T> void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(120, SECONDS).pollingEvery(5, SECONDS)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		wait.until((Function<? super WebDriver, T>) ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * Used to wait for element to be selected till max 120 s with polling frequency 5s
	 * @author Narottam
	 * @param <T>
	 * @param driver
	 * @param element
	 */
	public static <T> void waitForElementToBeSelected(WebDriver driver, WebElement element) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(120, SECONDS).pollingEvery(5, SECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		wait.until((Function<? super WebDriver, T>) ExpectedConditions.elementToBeSelected(element));
	}

	/**
	 * Used to wait for element to be visible till max 120 s with polling frequency 5s
	 * @author Narottam
	 * @param <T>
	 * @param driver
	 * @param element
	 */
	public static <T> void waitForElementToBeVisible(WebDriver driver, WebElement element) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(120, SECONDS).pollingEvery(5, SECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		wait.until((Function<? super WebDriver, T>) ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * Used to wait for url to be contain some text till max 120 s with polling frequency 5s
	 * @author Narottam
	 * @param <T>
	 * @param driver
	 * @param element
	 */
	public static <T> void waitForURLContains(WebDriver driver, String text) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(120, SECONDS).pollingEvery(5, SECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		wait.until((Function<? super WebDriver, T>) ExpectedConditions.urlContains(text));
	}

	/**
	 * Used to wait for element to be contain some text till max 120 s with polling frequency 5s
	 * @author Narottam
	 * @param <T>
	 * @param driver
	 * @param element
	 */
	public static <T> void waitForTextToBePresentInElement(WebDriver driver, WebElement element, String text) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(120, SECONDS).pollingEvery(5, SECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		wait.until((Function<? super WebDriver, T>) ExpectedConditions.textToBePresentInElement(element, text));
	}

	public static <T> void waitForLoadingToFinish(WebDriver driver) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(120, SECONDS).pollingEvery(5, SECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		wait.until((Function<? super WebDriver, T>) ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar")));
	}
}
