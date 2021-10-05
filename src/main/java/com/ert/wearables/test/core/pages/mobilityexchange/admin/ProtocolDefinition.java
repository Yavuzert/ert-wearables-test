package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class ProtocolDefinition extends BaseSetup{
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;
	
	// Protocol Definitions Page Locators
	private By protocolDefinitionsHeader = By.xpath("//h2[normalize-space()='ProtocolDefinitions']");

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public ProtocolDefinition(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}
	
	/**
	 * This method provides to get protocol definitions page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getProtocolDefinitionsPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.PROTOCOLDEFINITIONS_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from protocol definitions page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getProtocolDefinitionsHeader() {
		elementUtils.waitForElementPresent(protocolDefinitionsHeader);
		return elementUtils.doGetText(protocolDefinitionsHeader);
	}

	/**
	 * This method returns protocol definition count from protocol definitions page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getProtocolDefinitionsCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int protocolDefinitionsCount = tableRowList.size();
		return protocolDefinitionsCount;
	}

}
