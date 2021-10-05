package com.ert.wearables.test.core.utilities;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ert.wearables.test.core.base.BaseSetup;

/**
 * This class provides common selenium webdriver methods to test pages
 * 
 * @author yavuz.ozturk
 */
public class ElementUtils extends BaseSetup {

	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil jsUtil;
	Properties prop;

	Logger log = Logger.getLogger(ElementUtils.class);

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIMEOUT);
		jsUtil = new JavaScriptUtil(driver);
	}

	/**
	 * This method provides wait for title
	 * 
	 * @param title
	 * @return
	 * @author yavuz.ozturk
	 */
	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}

	/**
	 * This method provides wait for element
	 * 
	 * @param locator
	 * @return
	 * @author yavuz.ozturk
	 */
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}

	/**
	 * This method provides wait for element visibility
	 * 
	 * @param locator
	 * @return
	 * @author yavuz.ozturk
	 */
	public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}

	/**
	 * This method provides get title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String doGetPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			log.error(e.getCause().getMessage());
		}
		return null;
	}

	/**
	 * This method provides get element
	 * 
	 * @param locator
	 * @return
	 * @author yavuz.ozturk
	 */
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			if (highlightElement) {
				jsUtil.flash(element);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// log.error(e.getCause().getMessage());
		}
		return element;
	}

	/**
	 * This method provides click
	 * 
	 * @param locator
	 * @author yavuz.ozturk
	 */
	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			log.error(e.getCause().getMessage());
		}
	}

	/**
	 * This method provides send keys
	 * 
	 * @param locator
	 * @param value
	 * @author yavuz.ozturk
	 */
	public void doSendKeys(By locator, String value) {
		try {
			WebElement element = getElement(locator);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			log.error(e.getCause().getMessage());
		}
	}

	/**
	 * This method provides send keys
	 * 
	 * @param locator
	 * @param value
	 * @author yavuz.ozturk
	 */
	public void doSendKeys(By locator, Keys func) {
		try {
			WebElement element = getElement(locator);
			element.clear();
			element.sendKeys(func);
		} catch (Exception e) {
			log.error(e.getCause().getMessage());
		}
	}

	/**
	 * This method verifies element is displayed
	 * 
	 * @param locator
	 * @return
	 * @author yavuz.ozturk
	 */
	public boolean doIsDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			log.error(e.getCause().getMessage());
		}
		return false;
	}

	/**
	 * This method verifies element is enabled
	 * 
	 * @param locator
	 * @return
	 * @author yavuz.ozturk
	 */
	public boolean doIsEnabled(By locator) {
		try {
			return getElement(locator).isEnabled();
		} catch (Exception e) {
			log.error(e.getCause().getMessage());
		}
		return false;
	}

	/**
	 * This method determine is an element is selected
	 * 
	 * @param locator
	 * @return
	 * @author yavuz.ozturk
	 */
	public boolean doIsSelected(By locator) {
		try {
			return getElement(locator).isSelected();
		} catch (Exception e) {
			log.error(e.getCause().getMessage());
		}
		return false;
	}

	/**
	 * This method provides get text
	 * 
	 * @param locator
	 * @return
	 * @author yavuz.ozturk
	 */
	public String doGetText(By locator) {
		try {
			return getElement(locator).getText();
		} catch (Exception e) {
			log.error(e.getCause().getMessage());
		}
		return null;
	}

}
