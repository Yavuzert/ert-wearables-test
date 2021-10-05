package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class Session extends BaseSetup{
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;

	// Sessions Page Locators
	private By sessionsHeader = By.xpath("//h2[normalize-space()='Sessions']");

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public Session(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	/**
	 * This method provides to get sessions page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSessionsPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.SESSIONS_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}
	
	/**
	 * This method retrieves the header from the sessions page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSessionsHeader() {
		elementUtils.waitForElementPresent(sessionsHeader);
		return elementUtils.doGetText(sessionsHeader);
	}
	
	/**
	 * This method returns session count from sessions page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getSessionCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int sessionCount = tableRowList.size();
		return sessionCount;
	}
	
}
