package com.ert.wearables.test.core.utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class is used for JavaScript executor which is based on Selenium. 
 * It provides  methods alternatively for selenium webdriver
 * 
 * @author yavuz.ozturk
 *
 */
public class JavaScriptUtil {

	WebDriver driver;

	Logger log = Logger.getLogger(JavaScriptUtil.class);
	
	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method provides highlighted element in test 
	 * 
	 * @param element
	 * @author yavuz.ozturk
	 */
	public void flash(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}
	
	/**
	 * This method provides is change color to element in test
	 * 
	 * @param color
	 * @param element
	 * @author yavuz.ozturk
	 */
	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			log.error(e.getCause().getMessage());
		}
	}

	/**
	 * This method provides draw selected element in a webpage
	 * 
	 * @param element
	 * @author yavuz.ozturk
	 */
	public void drawBorder(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	/**
	 * This method provides generate alert to show some info in test
	 * 
	 * @param message
	 * @author yavuz.ozturk
	 */
	public void generateAlert(String message) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("alert('" + message + "')");
	}

	/**
	 * This method provides to click on with web element
	 * 
	 * @param element
	 * @author yavuz.ozturk
	 */
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);

	}
	
	/**
	 * This method provides to click on with locator
	 * 
	 * @param locator
	 * @author yavuz.ozturk
	 */
	public void clickElementByJS(By locator) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", locator);

	}

	/**
	 * This method provides refresh browser
	 * 
	 * @author yavuz.ozturk
	 */
	public void refreshBrowserByJS() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("history.go(0)");
	}

	/**
	 * This method provides get title from webpage
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getTitleByJS() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String title = js.executeScript("return document.title;").toString();
		return title;
	}

	/**
	 * This method provides get text from web page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getPageInnerText() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		return pageText;
	}

	/**
	 * This method provides scroll down the bottom of the page
	 * 
	 * @author yavuz.ozturk
	 */
	public void scrollPageDown() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	/**
	 * This methods provides scroll middle of the page
	 * 
	 * @param element
	 * @author yavuz.ozturk
	 */
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * This method provides to get browser info like browser, version, browser name and available browsers in your machine
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getBrowserInfo() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String uAgent = js.executeScript("return navigator.userAgent;").toString();
		return uAgent;
	}

	/**
	 * This method provides send keys using id element
	 * 
	 * @param id
	 * @param value
	 * @author yavuz.ozturk
	 */
	public void sendKeysUsingJSWithId(String id, String value) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}

	/**
	 * This method provides send keys using name element
	 * 
	 * @param name
	 * @param value
	 * @author yavuz.ozturk
	 */
	public void sendKeysUsingJSWithName(String name, String value) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementByName('" + name + "').value='" + value + "'");
	}

	/**
	 * This method provides page loading every one second it will check page loading
	 * 
	 * @author yavuz.ozturk
	 */
	public void checkPageIsReady() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
			return;
		}

		// This loop will rotate for 25 times to check If page Is ready after
		// every 1 second.
		// You can replace your value with 25 If you wants to Increase or
		// decrease wait time.
		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.error(e.getCause().getMessage());
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}
	
}
