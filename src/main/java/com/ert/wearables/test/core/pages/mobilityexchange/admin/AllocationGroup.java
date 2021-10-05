package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class AllocationGroup extends BaseSetup{
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;

	// Device Allocation Groups Page Locators
	private By deviceAllocationGroupsHeader = By.xpath("//h2[normalize-space()='DeviceAllocationGroups']");
	
	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public AllocationGroup(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	/**
	 * This method provides to get device allocation groups page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceAllocationGroupsPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.DEVICEALLOCATIONGROUPS_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from the device allocation groups page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceAllocationGroupsHeader() {
		elementUtils.waitForElementPresent(deviceAllocationGroupsHeader);
		return elementUtils.doGetText(deviceAllocationGroupsHeader);
	}

	/**
	 * This method returns device allocation group count from device allocation groups page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getDeviceAllocationGroupsCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int deviceAllocationGroupsCount = tableRowList.size();
		return deviceAllocationGroupsCount;
	}

}
