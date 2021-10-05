package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AdminUtils;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;
import com.ert.wearables.test.core.utilities.ProjectsUtils;

public class AuditLog extends BaseSetup{

	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;
	AdminUtils adminUtils;
	ProjectsUtils projectsUtils;
	
	//Locators
	private By auditLogHeader = By.xpath("//h2[normalize-space()='Audit Log']");
	private By auditLogTableObjectId = By.xpath("//tbody/tr[1]/td[4]/div[1]/a[1]");
	private By auditLogTableStudy = By.xpath("//tbody/tr[1]/td[11]/div[1]");
	private By auditLogTableSite = By.xpath("//tbody/tr[1]/td[12]/div[1]");
	
	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public AuditLog(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
		adminUtils = new AdminUtils(driver);
		projectsUtils = new ProjectsUtils(driver); 
	}
	
	public String getAuditLogTableObjectId() {
		elementUtils.waitForElementPresent(auditLogTableObjectId);
		return elementUtils.doGetText(auditLogTableObjectId);
	}
	
	public String getAuditLogTableStudyName() {
		elementUtils.waitForElementPresent(auditLogTableStudy);
		return elementUtils.doGetText(auditLogTableStudy);
	}
	
	public String getAuditLogTableSiteName() {
		elementUtils.waitForElementPresent(auditLogTableSite);
		return elementUtils.doGetText(auditLogTableSite);
	}
	
	/**
	 * This method provides to get audit log page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getAuditLogPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.AUDITlOG_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from audit log page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getAuditLogHeader() {
		elementUtils.waitForElementPresent(auditLogHeader);
		return elementUtils.doGetText(auditLogHeader);
	}
	
}
