package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class Allocation {
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;

	// Device Data Files Page Locators
	private By deviceDataFilesHeader = By.xpath("//h2[normalize-space()='DeviceDataFiles']");
	
	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public Allocation(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	/**
	 * This method provides to get device data files page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceDataFilesPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.DEVICEDATAFILES_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from the device data files page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceDataFilesHeader() {
		elementUtils.waitForElementPresent(deviceDataFilesHeader);
		return elementUtils.doGetText(deviceDataFilesHeader);
	}

	/**
	 * This method returns device data files count from device data files page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getDeviceDataFilesCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int deviceDataFilesCount = tableRowList.size();
		return deviceDataFilesCount;
	}
	
}
