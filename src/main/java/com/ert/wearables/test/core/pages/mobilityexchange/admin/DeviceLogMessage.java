package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class DeviceLogMessage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;

	// Device Allocations Page Locators
	private By deviceLogMessageHeader = By.xpath("//h2[normalize-space()='DeviceDataFiles']");
	
	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public DeviceLogMessage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	/**
	 * This method provides to get device log message page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceLogMessagePageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.DEVICELOGMESSAGE_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from the device log message page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceLogMessageHeader() {
		elementUtils.waitForElementPresent(deviceLogMessageHeader);
		return elementUtils.doGetText(deviceLogMessageHeader);
	}

	/**
	 * This method returns device log message count from device log message page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getDeviceLogMessagesCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int deviceAllocationCount = tableRowList.size();
		return deviceAllocationCount;
	}
}
