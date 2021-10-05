package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class StudyRole extends BaseSetup{
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;

	// Study Roles Page Locators
	private By studyRolesHeader = By.xpath("//h2[normalize-space()='StudyRoles']");
	
	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public StudyRole(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	/**
	 * This method provides to get study roles page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getStudyRolesPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.STUDYROLES_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from the study roles page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getStudyRolesHeader() {
		elementUtils.waitForElementPresent(studyRolesHeader);
		return elementUtils.doGetText(studyRolesHeader);
	}

	/**
	 * This method returns study role count from study roles page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudyRoleCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int studyRoleCount = tableRowList.size();
		return studyRoleCount;
	}
	
}
