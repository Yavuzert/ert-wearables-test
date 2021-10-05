package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AdminUtils;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;
import com.ert.wearables.test.core.utilities.ProjectsUtils;

public class Study extends BaseSetup {

	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;
	AdminUtils adminUtils;
	ProjectsUtils projectsUtils;

	// Study property locators
	private By nameUser = By.xpath("//label[contains(text(), \"Name\")]/following-sibling::input");
	private By description = By.xpath("//label[contains(text(), \"Description\")]/following-sibling::input");
	private By Guid = By.xpath("//label[contains(text(), \"GUID\")]/following-sibling::input");
	private By phase = By.xpath("//div[@class='es-select']/select");
	private By date = By.xpath("//div[@class='es-date-input']/input");
	//private By tableDate = By.xpath("//table[@class='datePickerDays']");
	private By tableTg = By.xpath("//div[@__uiobjectid='30']");
	private By usesSites = By.xpath("//input[@type='checkbox']/following-sibling::label[1]");
	private By clickAllowDevice = By.xpath("/html/body/div/main/div/div/form/section/div[8]/div/label");
	private By submitButton = By.xpath("//button[normalize-space()='Submit']");

	// Study property locators
	//private By tableIdColumn = By.xpath("//span[normalize-space()='Id']"); 
	//private By tableIDFirstColumn = By.xpath("//tbody/tr[1]/td[1]/div[1]/a[1]");

	// Study Table's Link Locators
	private By tableIdLink = By.xpath("//table//tbody//tr[1]//td[1]//a");
	private By tableNameLink = By.xpath("//table//tbody//tr[1]//td[2]//a");
	private By tableSubjectLink = By.xpath("//table//tbody//tr[1]//td[3]//a");
	//private By tableSessionsLink = By.xpath("//table//tbody//tr[1]//td[4]//a");
	private By tableSessionsLink = By.xpath("/html/body/div/main/div/div/div[3]/div/table/tbody/tr[1]/td[4]/div/a");
	private By tableTestDefinitionsLink = By.xpath("//table//tbody//tr[1]//td[5]//a");
	private By tableTrialsLink = By.xpath("//tbody/tr[1]/td[5]/div[1]/a[1]");
	private By tablePropertiesLink = By.xpath("//table//tbody//tr[1]//td[7]//a");
	private By tableDeviceAllocationConfigurationsLink = By.xpath("//table//tbody//tr[1]//td[8]//a");
	private By tableProtocolDefinitionsLink = By.xpath("//table//tbody//tr[1]//td[9]//a");
	private By tableRolesLink = By.xpath("//table//tbody//tr[1]//td[10]//a");
	private By tableMembersLink = By.xpath("//table//tbody//tr[1]//td[11]//a");
	private By tableDeviceAllocationGroupsLink = By.xpath("//table//tbody//tr[1]//td[12]//a");

	// Study Form's Locators
	private By studiesFormDeleteButton = By.xpath("//button[normalize-space()='Delete']");
	//private By studiesFormObjectEditButton = By.xpath("//button[normalize-space()='Edit']");
	private By studiesFormPageTitle = By.xpath("//h2[@class='es-page-title']");
	private By studiesFormObjectId = By.xpath("/html/body/div/main/div/div/form/section/div[1]/div/a");
	private By studiesFormObjectName = By.cssSelector("section > div:nth-child(2) > div");

	// Study Form's link Locators
	private By studiesFormSubjectsLink = By.xpath("//a[contains(text(),'Subjects')]");

	// WebTable Headers and Rows Locators
	private By tableHeaderName = By.xpath("//table//thead//tr[1]//th[2]");
	private By tableRowsName = By.xpath("//table//tbody//tr//td[2]");
	private By tableHeaderSubjects = By.xpath("//table//thead//tr[1]//th[3]");
	private By tableRowsSubjects = By.xpath("//table//tbody//tr//td[3]");
	private By tableHeaderSessions = By.xpath("//table//thead//tr[1]//th[4]");
	private By tableRowsSessions = By.xpath("//table//tbody//tr//td[4]");
	private By tableHeaderTestDefinitions = By.xpath("//table//thead//tr[1]//th[5]");
	private By tableRowsTestDefinitions = By.xpath("//table//tbody//tr//td[5]");
	private By tableHeaderTrials = By.xpath("//table//thead//tr[1]//th[6]");
	private By tableRowsTrials = By.xpath("//table//tbody//tr//td[6]");
	private By tableHeaderProperties = By.xpath("//table//thead//tr[1]//th[7]");
	private By tableRowsProperties = By.xpath("//table//tbody//tr//td[7]");
	private By tableHeaderDeviceAllocationConfigurations = By.xpath("//table//thead//tr[1]//th[8]");
	private By tableRowsDeviceAllocationConfigurations = By.xpath("//table//tbody//tr//td[8]");
	private By tableHeaderProtocolDefinitions = By.xpath("//table//thead//tr[1]//th[9]");
	private By tableRowsProtocolDefinitions = By.xpath("//table//tbody//tr//td[9]");
	private By tableHeaderRoles = By.xpath("//table//thead//tr[1]//th[10]");
	private By tableRowsRoles = By.xpath("//table//tbody//tr//td[10]");
	private By tableHeaderMembers = By.xpath("//table//thead//tr[1]//th[11]");
	private By tableRowsMembers = By.xpath("//table//tbody//tr//td[11]");
	private By tableHeaderDeviceAllocationGroups = By.xpath("//table//thead//tr[1]//th[12]");
	private By tableRowsDeviceAllocationGroups = By.xpath("//table//tbody//tr//td[12]");
	private By tableHeaderDescription = By.xpath("//table//thead//tr[1]//th[13]");
	private By tableRowsDescription = By.xpath("//table//tbody//tr//td[13]");
	private By tableHeaderGUID = By.xpath("//table//thead//tr[1]//th[14]");
	private By tableRowsGUID = By.xpath("//table//tbody//tr//td[14]");
	private By tableHeaderPhase = By.xpath("//table//thead//tr[1]//th[15]");
	private By tableRowsPhase = By.xpath("//table//tbody//tr//td[15]");
	private By tableHeaderStartDate = By.xpath("//table//thead//tr[1]//th[16]");
	private By tableRowsStartDate = By.xpath("//table//tbody//tr//td[16]");
	private By tableHeaderUsesSites = By.xpath("//table//thead//tr[1]//th[17]");
	private By tableRowsUsesSites = By.xpath("//table//tbody//tr//td[17]");
	private By tableHeaderAllowDeviceAllocationConfigurations = By.xpath("//table//thead//tr[1]//th[18]");
	private By tableRowsAllowDeviceAllocationConfigurations = By.xpath("//table//tbody//tr//td[18]");
	private By tableHeaderCreationDate = By.xpath("//table//thead//tr[1]//th[19]");
	private By tableRowsCreationDate = By.xpath("//table//tbody//tr//td[19]");
	private By tableHeaderDeleted = By.xpath("//table//thead//tr[1]//th[20]");
	private By tableRowsDeleted = By.xpath("//table//tbody//tr//td[20]");
	private By tableHeaderLastModified = By.xpath("//table//thead//tr[1]//th[21]");
	private By tableRowsLastModified = By.xpath("//table//tbody//tr//td[21]");
	
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

	public By getTableHeaderSessions() {
		return tableHeaderSessions;
	}

	public By getTableRowsSessions() {
		return tableRowsSessions;
	}

	public By getTableHeaderTestDefinitions() {
		return tableHeaderTestDefinitions;
	}

	public By getTableRowsTestDefinitions() {
		return tableRowsTestDefinitions;
	}

	public By getTableHeaderTrials() {
		return tableHeaderTrials;
	}

	public By getTableRowsTrials() {
		return tableRowsTrials;
	}

	public By getTableHeaderProperties() {
		return tableHeaderProperties;
	}

	public By getTableRowsProperties() {
		return tableRowsProperties;
	}

	public By getTableHeaderDeviceAllocationConfigurations() {
		return tableHeaderDeviceAllocationConfigurations;
	}

	public By getTableRowsDeviceAllocationConfigurations() {
		return tableRowsDeviceAllocationConfigurations;
	}

	public By getTableHeaderProtocolDefinitions() {
		return tableHeaderProtocolDefinitions;
	}

	public By getTableRowsProtocolDefinitions() {
		return tableRowsProtocolDefinitions;
	}

	public By getTableHeaderRoles() {
		return tableHeaderRoles;
	}

	public By getTableRowsRoles() {
		return tableRowsRoles;
	}

	public By getTableHeaderMembers() {
		return tableHeaderMembers;
	}

	public By getTableRowsMembers() {
		return tableRowsMembers;
	}

	public By getTableHeaderDeviceAllocationGroups() {
		return tableHeaderDeviceAllocationGroups;
	}

	public By getTableRowsDeviceAllocationGroups() {
		return tableRowsDeviceAllocationGroups;
	}

	public By getTableHeaderDescription() {
		return tableHeaderDescription;
	}

	public By getTableRowsDescription() {
		return tableRowsDescription;
	}

	public By getTableHeaderGUID() {
		return tableHeaderGUID;
	}

	public By getTableRowsGUID() {
		return tableRowsGUID;
	}

	public By getTableHeaderPhase() {
		return tableHeaderPhase;
	}

	public By getTableRowsPhase() {
		return tableRowsPhase;
	}

	public By getTableHeaderStartDate() {
		return tableHeaderStartDate;
	}

	public By getTableRowsStartDate() {
		return tableRowsStartDate;
	}

	public By getTableHeaderUsesSites() {
		return tableHeaderUsesSites;
	}

	public By getTableRowsUsesSites() {
		return tableRowsUsesSites;
	}

	public By getTableHeaderAllowDeviceAllocationConfigurations() {
		return tableHeaderAllowDeviceAllocationConfigurations;
	}

	public By getTableRowsAllowDeviceAllocationConfigurations() {
		return tableRowsAllowDeviceAllocationConfigurations;
	}

	public By getTableHeaderCreationDate() {
		return tableHeaderCreationDate;
	}

	public By getTableRowsCreationDate() {
		return tableRowsCreationDate;
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
	public Study(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
		adminUtils = new AdminUtils(driver);
		projectsUtils = new ProjectsUtils(driver);
	}
	
	/**
	 * This method create new object
	 * 
	 * @author yavuz.ozturk
	 */
	public void createObject(String name, String desc, String guid, String phaseParam) throws InterruptedException {
		By phaseOption = By.xpath("//option[contains(text(), '" + phaseParam + "')]");
		elementUtils.waitForElementPresent(nameUser);
		elementUtils.doSendKeys(nameUser, name);
		elementUtils.waitForElementPresent(description);
		elementUtils.doSendKeys(description, desc);
		elementUtils.waitForElementPresent(Guid);
		elementUtils.doSendKeys(Guid, guid);
		elementUtils.waitForElementPresent(phase);
		elementUtils.doClick(phase);
		elementUtils.waitForElementPresent(phaseOption);
		elementUtils.doClick(phaseOption);
		elementUtils.waitForElementPresent(date);
		elementUtils.doSendKeys(date, "12/12/2021");
		driver.findElement(By.xpath("//div[@class='es-date-input']/input")).sendKeys(Keys.ENTER);
//		elementUtils.doClick(date);
//		elementUtils.doClick(tableTg);
		elementUtils.waitForElementVisible(usesSites);
		elementUtils.doClick(usesSites);
		elementUtils.waitForElementVisible(clickAllowDevice);
		elementUtils.doClick(clickAllowDevice);
		elementUtils.waitForElementVisible(submitButton);
		elementUtils.doClick(submitButton);
	}

	/**
	 * This method is used to get Last Created Study Table's Object ID
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getLastCreatedObjectIdFromStudyTable() {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[1]";

		By tableRowsXpath = By.xpath("//table//tbody//tr");

		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();

		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		
		return lastCreatedUser.getText();
	}

	/**
	 * This method is used to delete last created Study Table's Object Via ID Link
	 * 
	 * @author yavuz.ozturk
	 * @throws InterruptedException 
	 */
	public void deleteLastCreatedObjectViaIdLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedObjectIdFromStudyTable()));
		elementUtils.doClick(studiesFormDeleteButton);
		adminUtils.clickAlertOk();
	}

	/**
	 * This method is used to get Last Created Study Table's Object Name
	 * 
	 * @return
	 * @author yavuz.ozturk
	 * @throws InterruptedException 
	 */
	public String getLastCreatedObjectNameFromStudyTable() throws InterruptedException {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[2]";

		By tableRowsXpath = By.xpath("//table//tbody//tr");

		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();

		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}

	/**
	 * This method is used to delete last created Study Table's Object Via Name Link
	 * 
	 * @author yavuz.ozturk
	 * @throws InterruptedException 
	 */
	public void deleteLastCreatedObjectViaNameLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedObjectNameFromStudyTable()));
		elementUtils.doClick(studiesFormDeleteButton);
		ProjectsUtils.pause(2);
		adminUtils.clickAlertOk();
	}

	/**
	 * This method is used to get study table's object Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getStudyTableObjectId() {
		elementUtils.waitForElementPresent(tableIdLink);
		String studyTableObjectId = elementUtils.doGetText(tableIdLink);
		return studyTableObjectId;
	}

	/**
	 * This method is used to get study table's object name
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getStudyTableObjectName() {
		elementUtils.waitForElementPresent(tableNameLink);
		String studyTableObjectName = elementUtils.doGetText(tableNameLink);
		return studyTableObjectName;
	}
	
	/**
	 * This method is used to get study form's object name
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getStudyFormObjectId() {
		elementUtils.waitForElementPresent(studiesFormObjectId);
		return elementUtils.doGetText(studiesFormObjectId);
	}
	
	/**
	 * This method is used to get study form's object name
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getStudyFormObjectName() {
		elementUtils.waitForElementPresent(studiesFormObjectName);
		return elementUtils.doGetText(By.cssSelector("section > div:nth-child(2) > div"));
	}

	/**
	 * This method is used to get study form page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getStudyFormPageTitle() {
		elementUtils.waitForElementPresent(studiesFormPageTitle);
		return elementUtils.doGetText(studiesFormPageTitle);
	}

	/**
	 * This method is used to get study table's subject count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudyTableSubjectCount() {
		elementUtils.waitForElementPresent(tableSubjectLink);
		String subjects = elementUtils.doGetText(tableSubjectLink);
		int subjectCount = Integer.parseInt(subjects.substring(0, 1));
		elementUtils.doClick(tableSubjectLink);
		return subjectCount;
	}

	/**
	 * This method is used to get study table's session count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudyTableSessionCount() {
		elementUtils.waitForElementPresent(tableSessionsLink);
		String sessions = elementUtils.doGetText(tableSessionsLink);
		int sessionCount = Integer.parseInt(sessions.substring(0, 1));
		elementUtils.doClick(tableSessionsLink);
		return sessionCount;
	}

	/**
	 * This method is used to get study table's trial count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudyTableTrialCount() {
		elementUtils.waitForElementPresent(tableTrialsLink);
		String trial = elementUtils.doGetText(tableTrialsLink);
		int trialCount = Integer.parseInt(trial.substring(0, 1));
		elementUtils.doClick(tableTrialsLink);
		return trialCount;
	}

	/**
	 * This method is used to get study table's test definition count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudyTableTestDefinitionCount() {
		adminUtils.clickFull();
		elementUtils.waitForElementPresent(tableTestDefinitionsLink);
		String testDefinitions = elementUtils.doGetText(tableTestDefinitionsLink);
		int testDefinitionCount = Integer.parseInt(testDefinitions.substring(0, 1));
		elementUtils.doClick(tableTestDefinitionsLink);
		return testDefinitionCount;
	}

	/**
	 * This method is used to get study table's properties count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudyTablePropertiesCount() {
		adminUtils.clickFull();
		elementUtils.waitForElementPresent(tablePropertiesLink);
		String studyProperties = elementUtils.doGetText(tablePropertiesLink);
		int studyPropertiesCount = Integer.parseInt(studyProperties.substring(0, 2));
		elementUtils.doClick(tablePropertiesLink);
		return studyPropertiesCount;
	}

	/**
	 * This method is used to get study table's device allocation configurationscount
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudyTableDeviceAllocationConfigurationsCount() {
		adminUtils.clickFull();
		elementUtils.waitForElementPresent(tableDeviceAllocationConfigurationsLink);
		String deviceAllocationConfigurations = elementUtils.doGetText(tableDeviceAllocationConfigurationsLink);
		int deviceAllocationConfigurationsCount = Integer.parseInt(deviceAllocationConfigurations.substring(0, 1));
		elementUtils.doClick(tableDeviceAllocationConfigurationsLink);
		return deviceAllocationConfigurationsCount;
	}

	/**
	 * This method is used to get study table's protocol definitions count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudyTableProtocolDefinitionsCount() {
		adminUtils.clickFull();
		elementUtils.waitForElementPresent(tableProtocolDefinitionsLink);
		String protocolDefinitions = elementUtils.doGetText(tableProtocolDefinitionsLink);
		int protocolDefinitionsCount = Integer.parseInt(protocolDefinitions.substring(0, 1));
		elementUtils.doClick(tableProtocolDefinitionsLink);
		return protocolDefinitionsCount;
	}

	/**
	 * This method is used to get study table's roles count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudyTableRolesCount() {
		adminUtils.clickFull();
		elementUtils.waitForElementPresent(tableRolesLink);
		String roles = elementUtils.doGetText(tableRolesLink);
		int rolesCount = Integer.parseInt(roles.substring(0, 1));
		elementUtils.doClick(tableRolesLink);
		return rolesCount;
	}

	/**
	 * This method is used to get study table's members count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudyTableMembersCount() {
		adminUtils.clickFull();
		elementUtils.waitForElementPresent(tableMembersLink);
		String members = elementUtils.doGetText(tableMembersLink);
		int membersCount = Integer.parseInt(members.substring(0, 1));
		elementUtils.doClick(tableMembersLink);
		return membersCount;
	}

	/**
	 * This method is used to get study table's device allocation groups count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudyTableDeviceAllocationGroupsCount() {
		adminUtils.clickFull();
		elementUtils.waitForElementPresent(tableDeviceAllocationGroupsLink);
		String deviceAllocationGroups = elementUtils.doGetText(tableDeviceAllocationGroupsLink);
		int deviceAllocationGroupsCount = Integer.parseInt(deviceAllocationGroups.substring(0, 1));
		elementUtils.doClick(tableDeviceAllocationGroupsLink);
		return deviceAllocationGroupsCount;
	}
	
	/**
	 * This method is used to click study table's name link
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickStudyTableNameLink() {
		elementUtils.waitForElementPresent(tableNameLink);
		elementUtils.doClick(tableNameLink);
	}

	/**
	 * This method is used to edit object
	 * 
	 * @param name
	 * @param desc
	 * @param guid
	 * @param phaseParam
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	public void editObject(String name, String desc, String guid, String phaseParam) throws InterruptedException {
		By phaseOption = By.xpath("//option[contains(text(), '" + phaseParam + "')]");
		elementUtils.waitForElementPresent(nameUser);
		elementUtils.getElement(nameUser).clear();
		elementUtils.doSendKeys(nameUser, name);
		elementUtils.waitForElementPresent(description);
		elementUtils.getElement(description).clear();
		elementUtils.doSendKeys(description, desc);
		elementUtils.waitForElementPresent(Guid);
		elementUtils.getElement(Guid).clear();
		elementUtils.doSendKeys(Guid, guid);
		elementUtils.waitForElementPresent(phase);
		elementUtils.doClick(phase);
		elementUtils.waitForElementPresent(phaseOption);
		elementUtils.doClick(phaseOption);
		elementUtils.waitForElementPresent(date);
		elementUtils.doClick(date);
		elementUtils.doSendKeys(date, "12/12/2021");
		driver.findElement(By.xpath("//div[@class='es-date-input']/input")).sendKeys(Keys.ENTER);
//		elementUtils.doClick(tableTg);
		elementUtils.waitForElementVisible(usesSites);
		elementUtils.doClick(usesSites);
		elementUtils.waitForElementVisible(clickAllowDevice);
		elementUtils.doClick(clickAllowDevice);
		elementUtils.waitForElementVisible(submitButton);
		elementUtils.doClick(submitButton);
	}

	/**
	 * This method is used to get edited object name
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getEditedObjectName() {
		elementUtils.waitForElementPresent(By.cssSelector("div:nth-child(2) > div:nth-child(2)"));
		return elementUtils.doGetText(By.cssSelector("div:nth-child(2) > div:nth-child(2)"));

	}

	/**
	 * This method is used to get study form's subject count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getStudiesFormSubjectsCount() {
		elementUtils.waitForElementPresent(studiesFormSubjectsLink);
		String subjects = elementUtils.doGetText(studiesFormSubjectsLink);
		int subjectCount = Integer.parseInt(subjects.substring(0, 1));
		elementUtils.doClick(studiesFormSubjectsLink);
		return subjectCount;
	}
	
}
