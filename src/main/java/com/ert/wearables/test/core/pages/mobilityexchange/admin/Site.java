package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AdminUtils;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;
import com.ert.wearables.test.core.utilities.ProjectsUtils;

public class Site extends BaseSetup{
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;
	AdminUtils adminUtils;

	// Site Page Locators
	private By siteHeader = By.xpath("//h2[normalize-space()='Sites']");
	
	// Locators
	private By sitesMenuIconButton = By.xpath("//span[@class='es-icon-site-qualification-form es-icon']");
	private By createButton = By.xpath("//button[normalize-space()='Create']");
	
	// Site property locators
	private By nameSite = By.xpath("//label[contains(text(), \"Name\")]/following-sibling::input");
	//private By contactUser = By.xpath("//label[contains(text(), \"Contact user\")]/following-sibling::input");
	private By clickContactUser = By.xpath("//div[@class='es-select']//input");
	private By addressLineFirst = By.xpath("//label[contains(text(), \"Address Line 1\")]/following-sibling::input");
	private By addressLineSecond = By.xpath("//label[contains(text(), \"Address Line 2\")]/following-sibling::input");
	private By citySite = By.xpath("//label[contains(text(), \"City\")]/following-sibling::input");
	private By stateSite = By.xpath("//label[contains(text(), \"State\")]/following-sibling::input");
	private By guidSite = By.xpath("//label[contains(text(), \"GUID\")]/following-sibling::input");
	private By countrySite = By.xpath("//label[contains(text(), \"Country\")]/following-sibling::input");
	private By phoneSite = By.xpath("//label[contains(text(), \"Phone\")]/following-sibling::input");
	private By zipSite = By.xpath("//label[contains(text(), \"Zip\")]/following-sibling::input");
	private By geolocationSite = By.xpath("//label[contains(text(), \"Geolocation\")]/following-sibling::input");
	private By notesSite = By.xpath("/html/body/div/main/div/div/form/section/div[14]/textarea");
	private By submitButton = By.xpath("//button[normalize-space()='Submit']");

	
	// Study Table's Link Locators
	private By tableIdLink = By.xpath("//table//tbody//tr[1]//td[1]//a");
	private By tableNameLink = By.xpath("//table//tbody//tr[1]//td[2]//a");
	private By tableSubjectLink = By.xpath("//table//tbody//tr[1]//td[3]//a");
	
	// Study Form's Locators
	private By siteFormSiteId = By.xpath("/html/body/div/main/div/div/form/section/div[1]/div/a");
	private By siteFormSiteName = By.cssSelector("section > div:nth-child(2) > div");
	private By siteFormDeleteButton = By.xpath("//button[normalize-space()='Delete']");
	private By siteFormPageTitle = By.xpath("//h2[@class='es-page-title']");

	// WebTable Headers and Rows Locators
	private By tableHeaderName = By.xpath("//table//thead//tr[1]//th[2]");
	private By tableRowsName = By.xpath("//table//tbody//tr//td[2]");
	private By tableHeaderSubjects = By.xpath("//table//thead//tr[1]//th[3]");
	private By tableRowsSubjects = By.xpath("//table//tbody//tr//td[3]");
	private By tableHeaderContactUser = By.xpath("//table//thead//tr[1]//th[4]");
	private By tableRowsContactUser = By.xpath("//table//tbody//tr//td[4]");
	private By tableHeaderAdressLineOne = By.xpath("//table//thead//tr[1]//th[5]");
	private By tableRowsAdressLineOne = By.xpath("//table//tbody//tr//td[5]");
	private By tableHeaderAdressLineTwo = By.xpath("//table//thead//tr[1]//th[6]");
	private By tableRowsAdressLineTwo = By.xpath("//table//tbody//tr//td[6]");
	private By tableHeaderCity = By.xpath("//table//thead//tr[1]//th[7]");
	private By tableRowsCity = By.xpath("//table//tbody//tr//td[7]");
	private By tableHeaderState = By.xpath("//table//thead//tr[1]//th[8]");
	private By tableRowsState = By.xpath("//table//tbody//tr//td[8]");
	private By tableHeaderGuid = By.xpath("//table//thead//tr[1]//th[9]");
	private By tableRowsGuid = By.xpath("//table//tbody//tr//td[9]");
	private By tableHeaderCountry = By.xpath("//table//thead//tr[1]//th[10]");
	private By tableRowsCountry = By.xpath("//table//tbody//tr//td[10]");
	private By tableHeaderPhone = By.xpath("//table//thead//tr[1]//th[11]");
	private By tableRowsPhone = By.xpath("//table//tbody//tr//td[11]");
	private By tableHeaderZip = By.xpath("//table//thead//tr[1]//th[12]");
	private By tableRowsZip = By.xpath("//table//tbody//tr//td[12]");
	private By tableHeaderCreationDate = By.xpath("//table//thead//tr[1]//th[13]");
	private By tableRowsCreationDate = By.xpath("//table//tbody//tr//td[13]");
	private By tableHeaderGeolocation = By.xpath("//table//thead//tr[1]//th[14]");
	private By tableRowsGeolocation = By.xpath("//table//tbody//tr//td[14]");
	private By tableHeaderNotes = By.xpath("//table//thead//tr[1]//th[15]");
	private By tableRowsNotes = By.xpath("//table//tbody//tr//td[15]");
	private By tableHeaderDeleted = By.xpath("//table//thead//tr[1]//th[16]");
	private By tableRowsDeleted = By.xpath("//table//tbody//tr//td[16]");
	private By tableHeaderLastModified = By.xpath("//table//thead//tr[1]//th[17]");
	private By tableRowsLastModified = By.xpath("//table//tbody//tr//td[17]");

	public By getTableIdLink() {
		return tableIdLink;
	}

	public By getTableNameLink() {
		return tableNameLink;
	}

	public By getTableSubjectLink() {
		return tableSubjectLink;
	}

	public By getTableHeaderName() {
		return tableHeaderName;
	}

	public By getTableRowsName() {
		return tableRowsName;
	}

	public By getTableHeaderSubjects() {
		return tableHeaderSubjects;
	}

	public By getTableRowsSubjects() {
		return tableRowsSubjects;
	}

	public By getTableHeaderContactUser() {
		return tableHeaderContactUser;
	}

	public By getTableRowsContactUser() {
		return tableRowsContactUser;
	}

	public By getTableHeaderAdressLineOne() {
		return tableHeaderAdressLineOne;
	}

	public By getTableRowsAdressLineOne() {
		return tableRowsAdressLineOne;
	}

	public By getTableHeaderAdressLineTwo() {
		return tableHeaderAdressLineTwo;
	}

	public By getTableRowsAdressLineTwo() {
		return tableRowsAdressLineTwo;
	}

	public By getTableHeaderCity() {
		return tableHeaderCity;
	}

	public By getTableRowsCity() {
		return tableRowsCity;
	}

	public By getTableHeaderState() {
		return tableHeaderState;
	}

	public By getTableRowsState() {
		return tableRowsState;
	}

	public By getTableHeaderGuid() {
		return tableHeaderGuid;
	}

	public By getTableRowsGuid() {
		return tableRowsGuid;
	}

	public By getTableHeaderCountry() {
		return tableHeaderCountry;
	}

	public By getTableRowsCountry() {
		return tableRowsCountry;
	}

	public By getTableHeaderPhone() {
		return tableHeaderPhone;
	}

	public By getTableRowsPhone() {
		return tableRowsPhone;
	}

	public By getTableHeaderZip() {
		return tableHeaderZip;
	}

	public By getTableRowsZip() {
		return tableRowsZip;
	}

	public By getTableHeaderCreationDate() {
		return tableHeaderCreationDate;
	}

	public By getTableRowsCreationDate() {
		return tableRowsCreationDate;
	}

	public By getTableHeaderGeolocation() {
		return tableHeaderGeolocation;
	}

	public By getTableRowsGeolocation() {
		return tableRowsGeolocation;
	}

	public By getTableHeaderNotes() {
		return tableHeaderNotes;
	}

	public By getTableRowsNotes() {
		return tableRowsNotes;
	}

	public By getTableHeaderDeleted() {
		return tableHeaderDeleted;
	}

	public By getTableRowsDeleted() {
		return tableRowsDeleted;
	}

	public By getTableHeaderLastModified() {
		return tableHeaderLastModified;
	}

	public By getTableRowsLastModified() {
		return tableRowsLastModified;
	}

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public Site(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
		adminUtils = new AdminUtils(driver);
	}
	
	/**
	 * This method is used to click Sites Icon button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickSitesIconButton() {
		elementUtils.waitForElementPresent(sitesMenuIconButton);
		elementUtils.doClick(sitesMenuIconButton);
	}
	
	/**
	 * This method is used to click create button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickCreate() {
		elementUtils.waitForElementPresent(createButton);
		elementUtils.doClick(createButton);
	}
	
	/**
	 * This method create new site
	 * 
	 * @param name
	 * @param addLineFirst
	 * @param addLineSecond
	 * @param city
	 * @param state
	 * @param guid
	 * @param country
	 * @param phone
	 * @param zip
	 * @param geolocation
	 * @param notes
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	public void createSite(String name, String addLineFirst, String addLineSecond, String city, String state, String guid, String country, String phone, String zip, String geolocation, String notes) throws InterruptedException {
		elementUtils.waitForElementPresent(nameSite);
		elementUtils.doSendKeys(nameSite, name);
		elementUtils.waitForElementPresent(clickContactUser);
		elementUtils.doClick(clickContactUser);
		elementUtils.doSendKeys(clickContactUser, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickContactUser, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickContactUser, Keys.ENTER);
		elementUtils.waitForElementPresent(addressLineFirst);
		elementUtils.doSendKeys(addressLineFirst, addLineFirst);
		elementUtils.waitForElementPresent(addressLineSecond);
		elementUtils.doSendKeys(addressLineSecond, addLineSecond);
		elementUtils.waitForElementPresent(citySite);
		elementUtils.doSendKeys(citySite, city);
		elementUtils.waitForElementPresent(stateSite);
		elementUtils.doSendKeys(stateSite, state);
		elementUtils.waitForElementPresent(guidSite);
		elementUtils.doSendKeys(guidSite, guid);
		elementUtils.waitForElementPresent(countrySite);
		elementUtils.doSendKeys(countrySite, country);
		elementUtils.waitForElementPresent(phoneSite);
		elementUtils.doSendKeys(phoneSite, phone);
		elementUtils.waitForElementPresent(zipSite);
		elementUtils.doSendKeys(zipSite, zip);
		elementUtils.waitForElementPresent(geolocationSite);
		elementUtils.doSendKeys(geolocationSite, geolocation);
		elementUtils.waitForElementPresent(notesSite);
		elementUtils.doSendKeys(notesSite, notes);
		elementUtils.doClick(submitButton);
	}

	/**
	 * This method is used to get Last Created Site ID from Site Table
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getLastCreatedSiteIdFromSiteTable() {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[1]";
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();
		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}
	
//	public void clickContactUser() {
//		elementUtils.waitForElementPresent(clickContactUser);
//		elementUtils.doClick(clickContactUser);
//		elementUtils.doSendKeys(clickContactUser, Keys.ARROW_DOWN);
//		elementUtils.doSendKeys(clickContactUser, Keys.ARROW_DOWN);
//		elementUtils.doSendKeys(clickContactUser, Keys.ENTER);
//		
//	}

	/**
	 * This method is used to get Last Created Site Name from Site Table
	 * 
	 * @return
	 * @author yavuz.ozturk
	 * @throws InterruptedException 
	 */
	public String getLastCreatedSiteNameFromSiteTable() throws InterruptedException {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[2]";
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();
		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}
	
	/**
	 * This method is used to get site table's site Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSiteTableSiteId() {
		elementUtils.waitForElementPresent(tableIdLink);
		String siteTableSiteId = elementUtils.doGetText(tableIdLink);
		return siteTableSiteId;
	}
	
	/**
	 * This method is used to get site form's site name
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSiteFormSiteId() {
		elementUtils.waitForElementPresent(siteFormSiteId);
		return elementUtils.doGetText(siteFormSiteId);
	}
	
	/**
	 * This method is used to get site table's site name
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSiteTableSiteName() {
		elementUtils.waitForElementPresent(tableNameLink);
		String siteTableSiteName = elementUtils.doGetText(tableNameLink);
		return siteTableSiteName;
	}
	
	/**
	 * This method is used to click site table's name link
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickSiteTableNameLink() {
		elementUtils.waitForElementPresent(tableNameLink);
		elementUtils.doClick(tableNameLink);
	}
	
	/**
	 * This method is used to get site form's site name
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSiteFormSiteName() {
		elementUtils.waitForElementPresent(siteFormSiteName);
		return elementUtils.doGetText(By.cssSelector("section > div:nth-child(2) > div"));
	}
	
	/**
	 * This method is used to delete last created Site from Site Table Via ID Link
	 * 
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	public void deleteLastCreatedSiteViaIdLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedSiteIdFromSiteTable()));
		elementUtils.doClick(siteFormDeleteButton);
		adminUtils.clickAlertOk();
	}
	
	/**
	 * This method is used to delete last created Site from Site Table Via name Link
	 * 
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	public void deleteLastCreatedSiteViaNameLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedSiteNameFromSiteTable()));
		elementUtils.doClick(siteFormDeleteButton);
		ProjectsUtils.pause(2);
		adminUtils.clickAlertOk();
	}
	
	/**
	 * This method is used to edit site
	 * 
	 * @param name
	 * @param addLineFirst
	 * @param addLineSecond
	 * @param city
	 * @param state
	 * @param guid
	 * @param country
	 * @param phone
	 * @param zip
	 * @param geolocation
	 * @param notes
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	public void editSite(String name, String addLineFirst, String addLineSecond, String city, String state, String guid, String country, String phone, String zip, String geolocation, String notes) throws InterruptedException {
		elementUtils.waitForElementPresent(nameSite);
		elementUtils.doSendKeys(nameSite, name);
		elementUtils.waitForElementPresent(clickContactUser);
		elementUtils.doClick(clickContactUser);
		elementUtils.doSendKeys(clickContactUser, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickContactUser, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickContactUser, Keys.ENTER);
		elementUtils.waitForElementPresent(addressLineFirst);
		elementUtils.doSendKeys(addressLineFirst, addLineFirst);
		elementUtils.waitForElementPresent(addressLineSecond);
		elementUtils.doSendKeys(addressLineSecond, addLineSecond);
		elementUtils.waitForElementPresent(citySite);
		elementUtils.doSendKeys(citySite, city);
		elementUtils.waitForElementPresent(stateSite);
		elementUtils.doSendKeys(stateSite, state);
		elementUtils.waitForElementPresent(guidSite);
		elementUtils.doSendKeys(guidSite, guid);
		elementUtils.waitForElementPresent(countrySite);
		elementUtils.doSendKeys(countrySite, country);
		elementUtils.waitForElementPresent(phoneSite);
		elementUtils.doSendKeys(phoneSite, phone);
		elementUtils.waitForElementPresent(zipSite);
		elementUtils.doSendKeys(zipSite, zip);
		elementUtils.waitForElementPresent(geolocationSite);
		elementUtils.doSendKeys(geolocationSite, geolocation);
		elementUtils.waitForElementPresent(notesSite);
		elementUtils.doSendKeys(notesSite, notes);
		elementUtils.doClick(submitButton);
	}
	
	/**
	 * This method is used to get edited site name
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getEditedSiteName() {
		elementUtils.waitForElementPresent(By.cssSelector("div:nth-child(2) > div:nth-child(2)"));
		return elementUtils.doGetText(By.cssSelector("div:nth-child(2) > div:nth-child(2)"));
	}
	
	/**
	 * This method is used to get site form page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSiteFormPageTitle() {
		elementUtils.waitForElementPresent(siteFormPageTitle);
		return elementUtils.doGetText(siteFormPageTitle);
	}
	
	/**
	 * This method is used to get site table's subject count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getSiteTableSubjectCount() {
		elementUtils.waitForElementPresent(tableSubjectLink);
		String subjects = elementUtils.doGetText(tableSubjectLink);
		int subjectCount = Integer.parseInt(subjects.substring(0, 1));
		elementUtils.doClick(tableSubjectLink);
		return subjectCount;
	}
	
	/**
	 * This method retrieves the header from the sites page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSitesHeader() {
		elementUtils.waitForElementPresent(siteHeader);
		return elementUtils.doGetText(siteHeader);
	}
	
	/**
	 * This method provides to get sites page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSitesPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.SITES_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}
	
}
