package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class ConfigurationElement extends BaseSetup{
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;
	
	// Device Allocation Configurations Page Locators
	private By deviceAllocationConfigurationsHeader = By.xpath("//h2[normalize-space()='DeviceAllocationConfigurations']");

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public ConfigurationElement(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}
	
	/**
	 * This method provides to get device allocation configurations page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceAllocationConfigurationsPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.DEVICEALLOCATIONCONFIGURATIONS_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from device allocation configurations page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceAllocationConfigurationsHeader() {
		elementUtils.waitForElementPresent(deviceAllocationConfigurationsHeader);
		return elementUtils.doGetText(deviceAllocationConfigurationsHeader);
	}

	/**
	 * This method returns test definition count from device allocation configurations page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getDeviceAllocationConfigurationsCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int deviceAllocationConfigurationsCount = tableRowList.size();
		return deviceAllocationConfigurationsCount;
	}

}
