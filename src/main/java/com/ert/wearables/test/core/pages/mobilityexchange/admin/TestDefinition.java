package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class TestDefinition extends BaseSetup{

	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;

	// Subjects Page Locators
	private By testDefinitionsHeader = By.xpath("//h2[normalize-space()='TestDefinitions']");
	
	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public TestDefinition(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}
	
	/**
	 * This method provides to get test definitions page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getTestDefinitionsPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.TESTDEFINITIONS_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from the test definitions page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getTestDefinitionsHeader() {
		elementUtils.waitForElementPresent(testDefinitionsHeader);
		return elementUtils.doGetText(testDefinitionsHeader);
	}

	/**
	 * This method returns test definition count from test definitions page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getTestDefinitionCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int testDefinitionCount = tableRowList.size();
		return testDefinitionCount;
	}

}
