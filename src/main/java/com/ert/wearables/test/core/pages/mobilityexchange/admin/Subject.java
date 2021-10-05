package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class Subject extends BaseSetup{

	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;

	// Subjects Page Locators
	private By subjectsHeader = By.xpath("//h2[normalize-space()='Subjects']");

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public Subject(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	/**
	 * This method provides to get subjects page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSubjectsPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.SUBJECTS_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from the subjects page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSubjectsHeader() {
		elementUtils.waitForElementPresent(subjectsHeader);
		return elementUtils.doGetText(subjectsHeader);
	}

	/**
	 * This method returns subject count from subjects page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getSubjectCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);

		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int subjectCount = tableRowList.size();

		return subjectCount;
	}
	
}
