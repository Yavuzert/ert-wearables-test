package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class DeviceAllocation {

	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;

	// Device Allocations Page Locators
	private By deviceAllocationHeader = By.xpath("//h2[normalize-space()='DeviceDataFiles']");
	
	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public DeviceAllocation(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	/**
	 * This method provides to get device allocations page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceAllocationsPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.DEVICEALLOCATIONS_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from the device allocations page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceAllocationHeader() {
		elementUtils.waitForElementPresent(deviceAllocationHeader);
		return elementUtils.doGetText(deviceAllocationHeader);
	}

	/**
	 * This method returns device allocations count from device allocations page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getDeviceAllocationsCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int deviceAllocationCount = tableRowList.size();
		return deviceAllocationCount;
	}
	
}
