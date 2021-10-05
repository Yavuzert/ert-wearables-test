package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class Trial extends BaseSetup{
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;
	
	// Trial Page Locators
	private By createButton = By.xpath("//button[normalize-space()='Create']");
	private By trialsHeader = By.xpath("//h2[normalize-space()='Trials']");
	
	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public Trial(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}
	
	/**
	 * This method provides to get trial page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getTrialsPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.TRIALS_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from the trials page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getTrialsHeader() {
		elementUtils.waitForElementPresent(trialsHeader);
		return elementUtils.doGetText(trialsHeader);
	}
	
	/**
	 * This method returns trial count from trials page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getTrialCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int trialCount = tableRowList.size();
		return trialCount;
	}
	
}
