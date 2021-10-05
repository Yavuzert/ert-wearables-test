package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class StudyProperty extends BaseSetup{

	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;

	// Study Properties Page Locators
	private By studyPropertiesHeader = By.xpath("//h2[normalize-space()='StudyProperties']");

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public StudyProperty(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}
	
	/**
	 * This method provides to get study properties page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getStudyPropertiesPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.STUDYPROPERTIES_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from the study properties page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getStudyPropertiesHeader() {
		elementUtils.waitForElementPresent(studyPropertiesHeader);
		return elementUtils.doGetText(studyPropertiesHeader);
	}

	/**
	 * This method returns study property count from study properties page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudyPropertiesCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int studyPropertiesCount = tableRowList.size();
		return studyPropertiesCount;
	}
	
}
