package com.ert.wearables.test.tests.mobilityexchange.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.AllocationGroup;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.AuditLog;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.AdminLogin;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.ConfigurationElement;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.ProtocolDefinition;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.Session;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.Study;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.StudyMembership;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.StudyProperty;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.StudyRole;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.Subject;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.TestDefinition;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.Trial;
import com.ert.wearables.test.core.utilities.AdminUtils;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.Credentials;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.ProjectsUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

/**
 * This class tests Study component in Mobility Exchange Admin Application
 * 
 * @author yavuz.ozturk
 */
@Epic("SB-1871 - https://apdmwearables.atlassian.net/browse/SB-1871")
@Feature("SB-3809 - https://apdmwearables.atlassian.net/browse/SB-3809")
//@Listeners(ExtentReportListener.class)
public class TestStudy {

	WebDriver driver;
	BaseSetup baseSetup;
	Properties prop;
	ElementUtils elementUtils;
	AdminUtils adminUtils;
	ProjectsUtils projectsUtils;
	AdminLogin adminLogin;
	Study study;
	Subject subject;
	Session session;
	Trial trial;
	TestDefinition testDefinition;
	StudyProperty studyProperty;
	ConfigurationElement allocationConfiguration;
	ProtocolDefinition protocolDefinition;
	StudyRole studyRole;
	StudyMembership studyMemberships;
	AllocationGroup allocationGroup;
	Credentials userCred;
	AuditLog auditLog;

	Logger log = Logger.getLogger(TestStudy.class);

	@BeforeMethod(groups = "start")
	public void setUp() {
		log.info("BeforeMethod is starting...");
		log.info("TestStudy class is starting for tests");
		baseSetup = new BaseSetup();
		prop = baseSetup.init_properties();
		String browserName = prop.getProperty("browser");
		driver = baseSetup.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		elementUtils = new ElementUtils(driver);
		adminUtils = new AdminUtils(driver);
		projectsUtils = new ProjectsUtils(driver);
		log.info("url is launched: " + prop.getProperty("url"));
		adminLogin = new AdminLogin(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		adminUtils.doLogin(userCred);
		study = new Study(driver);
		subject = new Subject(driver);
		session = new Session(driver);
		trial = new Trial(driver);
		testDefinition = new TestDefinition(driver);
		studyProperty = new StudyProperty(driver);
		allocationConfiguration = new ConfigurationElement(driver);
		protocolDefinition = new ProtocolDefinition(driver);
		studyRole = new StudyRole(driver);
		studyMemberships = new StudyMembership(driver);
		allocationGroup = new AllocationGroup(driver);
		auditLog = new AuditLog(driver);
		log.info("setUp is ending... ");
	}

	/**
	 * Test full and compact views for study component
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 1, description = "Test testFullCompactViewsOnStudy", enabled = true, groups = {"smoke"})
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testFullCompactViewsOnstudy")
	@Severity(SeverityLevel.CRITICAL)
	public void testFullCompactViewsOnStudy() {
		log.info("testFullCompactViewsOnStudy test is starting... ");
		String fullButtonText = adminUtils.getFullButtonText();
		adminUtils.clickFull();
		String compactText = adminUtils.getCompactText();
		if (compactText.equalsIgnoreCase(compactText)) {
			adminUtils.clickCompact();
		}
		assertEquals(fullButtonText, "Full", "verifying full button text");
		assertEquals(compactText, "Compact", "verifying compact button text");
		log.info("testFullCompactViewsOnStudy test is ending... ");
	}

	/**
	 * Test create new test object with (not set) phase selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 2, description = "Test testCreateNewTestObjectNotSet", invocationCount = 1, enabled = true, groups = {"smoke", "setup"})
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testCreateNewTestObjectNotSet")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewTestObjectNotSet() throws InterruptedException {
		log.info("testCreateNewTestObjectNotSet test is starting... ");
		adminUtils.clickCreate();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.createObject(name, description, guid, "(not set)");
		adminUtils.navigateToLastPage();
		String lastCreatedObjectName = study.getLastCreatedObjectNameFromStudyTable();
		assertEquals(name, lastCreatedObjectName, "verifying the presence of last study object created");
		log.info("testCreateNewTestObjectNotSet test is ending... ");
	}

	/**
	 * Test create new test object with configuring phase selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 3, description = "Test testCreateNewTestObjectWithConfiguring", enabled = true, groups = {"smoke"})
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testCreateNewTestObjectWithConfiguring")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewTestObjectWithConfiguring() throws InterruptedException {
		log.info("testCreateNewTestObjectWithConfiguring test is starting... ");
		adminUtils.clickCreate();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.createObject(name, description, guid, "Configuring");
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedObjectName = study.getLastCreatedObjectNameFromStudyTable();
		assertEquals(name, lastCreatedObjectName, "verifying the presence of last user created");
		log.info("testCreateNewTestObjectWithConfiguring test is ending... ");
	}

	/**
	 * Test create new test object with configured phase selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 4, description = "Test testCreateNewTestObjectWithConfigured", enabled = true, groups = {"smoke"})
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test verifyCreateNewTestObjectWithConfigured")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewTestObjectWithConfigured() throws InterruptedException {
		log.info("testCreateNewTestObjectWithConfigured test is starting... ");
		adminUtils.clickCreate();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.createObject(name, description, guid, "Configured");
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedObjectName = study.getLastCreatedObjectNameFromStudyTable();
		assertEquals(name, lastCreatedObjectName, "verifying the presence of last user created");
		log.info("testCreateNewTestObjectWithConfigured test is ending... ");
	}

//	@Test(priority = 5, description = "create new test object with active phase selected", enabled = true)
//	public void verifyCreateNewTestObjectWithActive() throws InterruptedException {
//		study.clickCreate();
//
//		String name = "Test_name_" + study.generateAlphaNumericString(5);
//		String description = "Test_Description_" + study.generateAlphaNumericString(5);
//		String guid = "Test_GUID_" + study.generateAlphaNumericString(5);
//		study.createObject(name, description, guid, "Active");
//		String lastCreatedUserName = study.getLastCreatedUser();
//	
//		assertEquals(name, lastCreatedUserName, "verifying the presence of last user created");
//	}

//	@Test(priority = 6, description = "create new test object with finished phase selected", enabled = true)
//	public void verifyCreateNewTestObjectWithFinished() throws InterruptedException {
//		study.clickCreate();
//
//		String name = "Test_name_" + study.generateAlphaNumericString(5);
//		String description = "Test_Description_" + study.generateAlphaNumericString(5);
//		String guid = "Test_GUID_" + study.generateAlphaNumericString(5);
//		study.createObject(name, description, guid, "Finished");
//		String lastCreatedUserName = study.getLastCreatedUser();
//	
//		assertEquals(name, lastCreatedUserName, "verifying the presence of last user created");
//	}

//	@Test(priority = 7, description = "create new test object with locked phase selected", enabled = true)
//	public void verifyCreateNewTestObjectWithLocked() throws InterruptedException {
//		study.clickCreate();
//
//		String name = "Test_name_" + study.generateAlphaNumericString(5);
//		String description = "Test_Description_" + study.generateAlphaNumericString(5);
//		String guid = "Test_GUID_" + study.generateAlphaNumericString(5);
//		study.createObject(name, description, guid, "Locked");
//		String lastCreatedUserName = study.getLastCreatedUser();
//	
//		assertEquals(name, lastCreatedUserName, "verifying the presence of last user created");
//	}	

//	@Test(priority = 8, description = "create new test object with Cancelled phase selected", enabled = true)
//	public void verifyCreateNewTestObjectWithCancelled() throws InterruptedException {
//		study.clickCreate();
//
//		String name = "Test_name_" + study.generateAlphaNumericString(5);
//		String description = "Test_Description_" + study.generateAlphaNumericString(5);
//		String guid = "Test_GUID_" + study.generateAlphaNumericString(5);
//		study.createObject(name, description, guid, "Cancelled");
//		String lastCreatedUserName = study.getLastCreatedUser();
//	
//		assertEquals(name, lastCreatedUserName, "verifying the presence of last user created");
//	}	

	/**
	 * Test object create with valid web table data
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 9, description = "Test validateStudyWebTableData", enabled = true, groups = {"smoke"})
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test validateStudyWebTableData")
	@Severity(SeverityLevel.CRITICAL)
	public void testStudyWebTableData() throws InterruptedException {
		log.info("testStudyWebTableData test is starting... ");
		adminUtils.clickCreate();
		Map<String, String> newUserMap = new LinkedHashMap<>();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		newUserMap.put("Name", name);
		newUserMap.put("Description", description);
		newUserMap.put("GUID", guid);
		study.createObject(name, description, guid, "Configured");
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		List<Map<String, String>> tableDataList = projectsUtils.getTableDataAsMapList();
		for (int i = 0; i < tableDataList.size(); i++) {
			Map<String, String> eachMap = tableDataList.get(i);
			String newObjectNameString = newUserMap.get("Name");
			String newObjectDescriptionString = newUserMap.get("Description");
			String newObjectGUID = newUserMap.get("GUID");
			if (eachMap.get("Name") != null) {
				if ((eachMap.get("Name")).equals(newObjectNameString)) {
					assertEquals(eachMap.get("Name"), newObjectNameString);
					log.info("Name from table: " + eachMap.get("Name"));
					log.info("Name from new object: " + newUserMap.get("Name"));
				}
			}
			if (eachMap.get("Description") != null) {
				if ((eachMap.get("Description")).equals(newObjectDescriptionString)) {
					assertEquals(eachMap.get("Description"), newObjectDescriptionString);
				}
			}
			if (eachMap.get("GUID") != null) {
				if ((eachMap.get("GUID")).equals(newObjectGUID)) {
					assertEquals(eachMap.get("GUID"), newObjectGUID);
				}
			}
		}
		adminUtils.clickCompact();
		study.deleteLastCreatedObjectViaNameLink();
		log.info("testStudyWebTableData test is ending... ");
	}

	/**
	 * Test study object history via study table's Id Link from study form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 10, description = "Test testStudyObjectHistoryViaStudiesTablesIdLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectHistoryViaStudiesTableIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectHistoryViaStudiesTableIdLink() {
		log.info("testStudyObjectHistoryViaStudiesTableIdLink test is starting... ");
		String studyTableObjectId = study.getStudyTableObjectId();
		adminUtils.clickTableIdLink();
		String studyFormObjectId = study.getStudyFormObjectId();
		adminUtils.clickHistory();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("DomainTransformEventInfo"));
		assertEquals(studyTableObjectId,auditLogTableObjectId);
		assertEquals(studyFormObjectId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testStudyObjectHistoryViaStudiesTableIdLink test is ending... ");
	}

	/**
	 * Test study object history via study table's Name Link from study form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 11, description = "Test testStudyObjectHistoryViaStudiesTablesNameLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectHistoryViaStudiesTableNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectHistoryViaStudiesTableNameLink() {
		log.info("testStudyObjectHistoryViaStudiesTableNameLink test is starting... ");
		String studyTableObjectName = study.getStudyTableObjectName();
		study.clickStudyTableNameLink();
		String studyFormObjectName = study.getStudyFormObjectName();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableStudyName = auditLog.getAuditLogTableStudyName();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("DomainTransformEventInfo"));
		assertEquals(studyTableObjectName,auditLogTableStudyName);
		assertEquals(studyFormObjectName,auditLogTableStudyName);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testStudyObjectHistoryViaStudiesTableNameLink test is ending... ");
	}	

	/**
	 * Test study object history via study table's Id Link from study form when editing object
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 12, description = "Test testStudyObjectHistoryViaStudiesTablesIdLinkWhenEditingObject", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectHistoryViaStudiesTablesNameLinkWhenEditingObject")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectHistoryViaStudiesTablesIdLinkWhenEditingObject() {
		log.info("testStudyObjectHistoryViaStudiesTablesIdLinkWhenEditingObject test is starting... ");
		String studyTableObjectName = study.getStudyTableObjectName();
		adminUtils.clickTableIdLink();
		String studyFormObjectName = study.getStudyFormObjectName();
		adminUtils.clickEditButton();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableStudyName = auditLog.getAuditLogTableStudyName();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("DomainTransformEventInfo"));
		assertEquals(studyTableObjectName,auditLogTableStudyName);
		assertEquals(studyFormObjectName,auditLogTableStudyName);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testStudyObjectHistoryViaStudiesTablesIdLinkWhenEditingObject test is ending... ");
	}
	
	/**
	 * Test study object history via study table's Name Link from study form when editing object
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 13, description = "Test testStudyObjectHistoryViaStudiesTablesNameLinkWhenEditingObject", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectHistoryViaStudiesTablesNameLinkWhenEditingObject")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectHistoryViaStudiesTablesNameLinkWhenEditingObject() {
		log.info("testStudyObjectHistoryViaStudiesTablesNameLinkWhenEditingObject test is starting... ");
		String studyTableObjectName = study.getStudyTableObjectName();
		study.clickStudyTableNameLink();
		String studyFormObjectName = study.getStudyFormObjectName();
		adminUtils.clickEditButton();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableStudyName = auditLog.getAuditLogTableStudyName();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("DomainTransformEventInfo"));
		assertEquals(studyTableObjectName,auditLogTableStudyName);
		assertEquals(studyFormObjectName,auditLogTableStudyName);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testStudyObjectHistoryViaStudiesTablesNameLinkWhenEditingObject test is ending... ");
	}
	
	/**
	 * Test study form's object deleting with ID link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 14, description = "Test testStudyFormDeleteObjectWithIdLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyFormDeleteObjectWithIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyFormDeleteObjectWithIdLink() throws InterruptedException {
		log.info("testStudyFormDeleteObjectWithIdLink test is starting... ");
		adminUtils.navigateToLastPage();
		String lastCreatedObject = study.getLastCreatedObjectNameFromStudyTable();
		log.info("Last Object Before Delete " + lastCreatedObject);
		study.deleteLastCreatedObjectViaIdLink();
		TimeUnit.SECONDS.sleep(3);
		String lastObject = study.getLastCreatedObjectNameFromStudyTable();
		log.info("Last Object After Delete " + lastObject);
		assertNotEquals(lastCreatedObject, lastObject);
		log.info("testStudyFormDeleteObjectWithIdLink test is ending... ");
	}

	/**
	 * Test study form's object deleting with name link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 15, description = "Test testStudyFormDeleteObjectWithNameLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyFormDeleteObjectWithNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyFormDeleteObjectWithNameLink() throws InterruptedException {
		log.info("testStudyFormDeleteObjectWithNameLink test is starting... ");
		adminUtils.navigateToLastPage();
		String lastCreatedObject = study.getLastCreatedObjectNameFromStudyTable();
		log.info("Last Object Before Delete " + lastCreatedObject);
		study.deleteLastCreatedObjectViaNameLink();
		TimeUnit.SECONDS.sleep(3);
		String lastObject = study.getLastCreatedObjectNameFromStudyTable();
		log.info("Last Object After Delete " + lastObject);
		assertNotEquals(lastCreatedObject, lastObject);
		log.info("testStudyFormDeleteObjectWithNameLink test is ending... ");
	}

	/**
	 * Test study object edit button with (not set) phase selected via id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 16, description = "Test testStudyObjectEditButtonWithNotSetPhaseViaIdLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithNotSetPhaseViaIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithNotSetPhaseViaIdLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithNotSetPhaseViaIdLink test is starting... ");
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "(not set)");
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithNotSetPhaseViaIdLink test is ending... ");
	}

	/**
	 * Test study object edit button with configuring phase selected via id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 17, description = "Test testStudyObjectEditButtonWithConfiguringPhaseViaIdLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithConfiguringPhaseViaIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithConfiguringPhaseViaIdLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithConfiguringPhaseViaIdLink test is starting... ");
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "Configuring");
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithConfiguringPhaseViaIdLink test is ending... ");
	}

	/**
	 * Test study object edit button with configured phase selected via id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 18, description = "Test testStudyObjectEditButtonWithConfiguredPhaseViaIdLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithConfiguredPhaseViaIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithConfiguredPhaseViaIdLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithConfiguredPhaseViaIdLink test is starting... ");
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "Configured");
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithConfiguredPhaseViaIdLink test is ending... ");
	}

	/**
	 * Test study object edit button with active phase selected via id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 19, description = "Test testStudyObjectEditButtonWithActivePhaseViaIdLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithActivePhaseViaIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithActivePhaseViaIdLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithActivePhaseViaIdLink test is starting... ");
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "Active");
		String reasonOfChange = "Reason_for_change_" + projectsUtils.randomString(5);
		adminUtils.enterAreasonForTheseChanges(reasonOfChange);
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithActivePhaseViaIdLink test is ending... ");
	}

	/**
	 * Test study object edit button with finished phase selected via id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 20, description = "Test testStudyObjectEditButtonWithFinishedPhaseViaIdLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithFinishedPhaseViaIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithFinishedPhaseViaIdLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithFinishedPhaseViaIdLink test is starting... ");
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "Finished");
		String reasonOfChange = "Reason_for_change_" + projectsUtils.randomString(5);
		adminUtils.enterAreasonForTheseChanges(reasonOfChange);
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithFinishedPhaseViaIdLink test is ending... ");
	}

	/**
	 * Test study object edit button with locked phase selected via id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 21, description = "Test testStudyObjectEditButtonWithLockedPhaseViaIdLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithLockedPhaseViaIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithLockedPhaseViaIdLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithLockedPhaseViaIdLink test is starting... ");
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "Locked");
		String reasonOfChange = "Reason_for_change_" + projectsUtils.randomString(5);
		adminUtils.enterAreasonForTheseChanges(reasonOfChange);
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithLockedPhaseViaIdLink test is ending... ");
	}

	/**
	 * Test study object edit button with Cancelled phase selected via id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 22, description = "Test testStudyObjectEditButtonWithCancelledPhaseViaIdLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithCancelledPhaseViaIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithCancelledPhaseViaIdLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithCancelledPhaseViaIdLink test is starting... ");
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "Cancelled");
		String reasonOfChange = "Reason_for_change_" + projectsUtils.randomString(5);
		adminUtils.enterAreasonForTheseChanges(reasonOfChange);
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithCancelledPhaseViaIdLink test is ending... ");
	}
	
	/**
	 * Test study object edit button with (not set) phase selected via name link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 23, description = "Test testStudyObjectEditButtonWithNotSetPhaseViaNameLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithNotSetPhaseViaNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithNotSetPhaseViaNameLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithNotSetPhaseViaNameLink test is starting... ");
		study.clickStudyTableNameLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "(not set)");
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithNotSetPhaseViaNameLink test is ending... ");
	}

	/**
	 * Test study object edit button with configuring phase selected via name link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 24, description = "Test testStudyObjectEditButtonWithConfiguringPhaseViaNameLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithConfiguringPhaseViaNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithConfiguringPhaseViaNameLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithConfiguringPhaseViaNameLink test is starting... ");
		study.clickStudyTableNameLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "Configuring");
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithConfiguringPhaseViaNameLink test is ending... ");
	}

	/**
	 * Test study object edit button with configured phase selected via name link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 25, description = "Test testStudyObjectEditButtonWithConfiguredPhaseViaNameLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithConfiguredPhaseViaNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithConfiguredPhaseViaNameLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithConfiguredPhaseViaNameLink test is starting... ");
		study.clickStudyTableNameLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "Configured");
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithConfiguredPhaseViaNameLink test is ending... ");
	}
	
	/**
	 * Test study object edit button with active phase selected via name link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 26, description = "Test testStudyObjectEditButtonWithActivePhaseViaNameLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithActivePhaseViaNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithActivePhaseViaNameLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithActivePhaseViaNameLink test is starting... ");
		study.clickStudyTableNameLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "Active");
		String reasonOfChange = "Reason_for_change_" + projectsUtils.randomString(5);
		adminUtils.enterAreasonForTheseChanges(reasonOfChange);
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithActivePhaseViaNameLink test is ending... ");
	}
	
	/**
	 * Test study object edit button with finished phase selected via name link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 27, description = "Test testStudyObjectEditButtonWithFinishedPhaseViaNameLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithFinishedPhaseViaNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithFinishedPhaseViaNameLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithFinishedPhaseViaNameLink test is starting... ");
		study.clickStudyTableNameLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "Finished");
		String reasonOfChange = "Reason_for_change_" + projectsUtils.randomString(5);
		adminUtils.enterAreasonForTheseChanges(reasonOfChange);
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithFinishedPhaseViaNameLink test is ending... ");
	}
	
	/**
	 * Test study object edit button with locked phase selected via name link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 28, description = "Test testStudyObjectEditButtonWithLockedPhaseViaNameLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithLockedPhaseViaNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithLockedPhaseViaNameLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithLockedPhaseViaNameLink test is starting... ");
		study.clickStudyTableNameLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "Locked");
		String reasonOfChange = "Reason_for_change_" + projectsUtils.randomString(5);
		adminUtils.enterAreasonForTheseChanges(reasonOfChange);
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithLockedPhaseViaNameLink test is ending... ");
	}
	
	/**
	 * Test study object edit button with Cancelled phase selected via name link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 29, description = "Test testStudyObjectEditButtonWithCancelledPhaseViaNameLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyObjectEditButtonWithCancelledPhaseViaNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyObjectEditButtonWithCancelledPhaseViaNameLink() throws InterruptedException {
		log.info("testStudyObjectEditButtonWithCancelledPhaseViaNameLink test is starting... ");
		study.clickStudyTableNameLink();
		adminUtils.clickEditButton();
		String name = "Test_name_" + projectsUtils.randomString(5);
		String description = "Test_Description_" + projectsUtils.randomString(5);
		String guid = "Test_GUID_" + projectsUtils.randomString(5);
		study.editObject(name, description, guid, "Cancelled");
		String reasonOfChange = "Reason_for_change_" + projectsUtils.randomString(5);
		adminUtils.enterAreasonForTheseChanges(reasonOfChange);
		String editedObjectName = study.getEditedObjectName();
		assertEquals(editedObjectName, name);
		log.info("testStudyObjectEditButtonWithCancelledPhaseViaNameLink test is ending... ");
	}

	/**
	 * Test study table's object id link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 30, description = "Test testStudyTableObjectIdLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableObjectIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableObjectIdLink() {
		log.info("testStudyTableObjectIdLink test is starting... ");
		String studyTableObjectId = adminUtils.getTableId();
		String studyFormObjectId = adminUtils.getFormId();
		log.info("Study Table Object Id: " + studyTableObjectId);
		log.info("Study Object Id: " + studyFormObjectId);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		assertTrue(curentURL.endsWith(studyFormObjectId));
		assertEquals(studyTableObjectId, studyFormObjectId);
		log.info("testStudyTableObjectIdLink test is ending... ");
	}

	/**
	 * Test study table's object name link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 31, description = " Test testStudyTableObjectNameLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableObjectNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableObjectNameLink() {
		log.info("testStudyTableObjectNameLink test is starting... ");
		String studyTableObjectName = study.getStudyTableObjectName();
		study.clickStudyTableNameLink();
		String studyFormPageTitle = study.getStudyFormPageTitle();
		String studyFormObjectName = study.getStudyFormObjectName();
		log.info("Study Table Object Name: " + studyTableObjectName);
		log.info("Study Form Page Title: " + studyFormPageTitle);
		log.info("Study Object Name: " + studyFormObjectName);
		assertTrue(studyTableObjectName.equalsIgnoreCase(studyFormPageTitle));
		assertTrue(studyTableObjectName.equalsIgnoreCase(studyFormPageTitle));
		assertTrue(studyFormPageTitle.equalsIgnoreCase(studyFormObjectName));
		log.info("testStudyTableObjectNameLink test is ending... ");
	}

	/**
	 * Test study table's object subject link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 32, description = "Test testStudyTableObjectSubjectLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableObjectSubjectLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableObjectSubjectLink() {
		log.info("testStudyTableObjectSubjectLink test is starting... ");
		int studyTableSubjectCount = study.getStudyTableSubjectCount();
		int studySubjectCount = subject.getSubjectCount();
		log.info("Study Table Subject Count: " + studyTableSubjectCount);
		log.info("Study Form Subject Count: " + studySubjectCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String subjectsHeader = subject.getSubjectsHeader();
		String subjectsPageTitle = subject.getSubjectsPageTitle();
		assertTrue(curentURL.contains("subject"));
		assertEquals(studyTableSubjectCount, studySubjectCount);
		assertEquals(subjectsHeader, AppConstants.SUBJECTS_PAGE_HEADER);
		assertEquals(subjectsPageTitle, AppConstants.SUBJECTS_PAGE_TITLE);
		log.info("testStudyTableObjectSubjectLink test is ending... ");
	}

	/**
	 * Test study table's object session link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 33, description = "Test testStudyTableObjectSessionLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableObjectSessionLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableObjectSessionLink() {
		log.info("testStudyTableObjectSessionLink test is starting... ");
		int studyTableSessionCount = study.getStudyTableSessionCount();
		log.info("Study Table Session Count: " + studyTableSessionCount);
		int sessionCount = session.getSessionCount();
		log.info("Study Form Session Count: " + sessionCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String sessionsHeader = session.getSessionsHeader();
		String sessionsPageTitle = session.getSessionsPageTitle();
		assertTrue(curentURL.contains("session"));
		assertEquals(studyTableSessionCount, sessionCount);
		assertEquals(sessionsHeader, AppConstants.SESSIONS_PAGE_HEADER);
		assertEquals(sessionsPageTitle, AppConstants.SESSIONS_PAGE_TITLE);
		log.info("testStudyTableObjectSessionLink test is ending... ");
	}

	/**
	 * Test study table's object trial link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 34, description = "Test testStudyTableObjectTrialLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableObjectTrialLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableObjectTrialLink() {
		log.info("testStudyTableObjectTrialLink test is starting... ");
		int studyTableTrialCount = study.getStudyTableTrialCount();
		log.info("Study Table Trial Count: " + studyTableTrialCount);
		int trialCount = trial.getTrialCount();
		log.info("Trial Page's Trial Count: " + trialCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String trialsHeader = trial.getTrialsHeader();
		String trialsPageTitle = trial.getTrialsPageTitle();
		assertTrue(curentURL.contains("trial"));
		assertEquals(studyTableTrialCount, trialCount);
		assertEquals(trialsHeader, AppConstants.TRIALS_PAGE_HEADER);
		assertEquals(trialsPageTitle, AppConstants.TRIALS_PAGE_TITLE);
		log.info("testStudyTableObjectTrialLink test is ending... ");
	}

	/**
	 * Test study table's object Test Definitions link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 35, description = "Test testStudyTableObjectTestDefinitionsLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableObjectTestDefinitionsLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableObjectTestDefinitionsLink() {
		log.info("testStudyTableObjectTestDefinitionsLink test is starting... ");
		int studyTableTestDefinitionCount = study.getStudyTableTestDefinitionCount();
		int studyTestDefinitionCount = testDefinition.getTestDefinitionCount();
		log.info("Study Table Test Definitions Count: " + studyTableTestDefinitionCount);
		log.info("Test Definitions Page Test Definitions Count: " + studyTestDefinitionCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String testDefinitonsHeader = testDefinition.getTestDefinitionsHeader();
		String testDefinitonsPageTitle = testDefinition.getTestDefinitionsPageTitle();
		assertTrue(curentURL.contains("testDefinition"));
		assertEquals(studyTableTestDefinitionCount, studyTestDefinitionCount);
		assertEquals(testDefinitonsHeader, AppConstants.TESTDEFINITIONS_PAGE_HEADER);
		assertEquals(testDefinitonsPageTitle, AppConstants.TESTDEFINITIONS_PAGE_TITLE);
		log.info("testStudyTableObjectTestDefinitionsLink test is ending... ");
	}

	/**
	 * Test study table's properties link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 36, description = "Test testStudyTableStudyPropertiesLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableStudyPropertiesLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableStudyPropertiesLink() {
		log.info("testStudyTableStudyPropertiesLink test is starting... ");
		int studyTableStudyPropertiesCount = study.getStudyTablePropertiesCount();
		int studyPropertiesCount = studyProperty.getStudyPropertiesCount();
		log.info("Study Table Properties Count: " + studyTableStudyPropertiesCount);
		log.info("Study Properties Page Count: " + studyPropertiesCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String studyPropertiesHeader = studyProperty.getStudyPropertiesHeader();
		String studyPropertiesPageTitle = studyProperty.getStudyPropertiesPageTitle();
		assertTrue(curentURL.contains("studyProperty"));
		assertEquals(studyTableStudyPropertiesCount, studyPropertiesCount);
		assertEquals(studyPropertiesHeader, AppConstants.STUDYPROPERTIES_PAGE_HEADER);
		assertEquals(studyPropertiesPageTitle, AppConstants.STUDYPROPERTIES_PAGE_TITLE);
		log.info("testStudyTableStudyPropertiesLink test is ending... ");
	}

	/**
	 * Test study table's device allocation configurations link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 37, description = "Test testStudyTableDeviceAllocationConfigurationsLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableDeviceAllocationConfigurationsLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableDeviceAllocationConfigurationsLink() {
		log.info("testStudyTableDeviceAllocationConfigurationsLink test is starting... ");
		int studyTableDeviceAllocationConfigurationsCount = study.getStudyTableDeviceAllocationConfigurationsCount();
		int deviceAllocationConfigurationsCount = allocationConfiguration.getDeviceAllocationConfigurationsCount();
		log.info("Study Table Device Allocation Configurations Count: " + studyTableDeviceAllocationConfigurationsCount);
		log.info("Device Allocation Configurations Page Count: " + deviceAllocationConfigurationsCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String deviceAllocationConfigurationsHeader = allocationConfiguration.getDeviceAllocationConfigurationsHeader();
		String deviceAllocationConfigurationsPageTitle = allocationConfiguration.getDeviceAllocationConfigurationsPageTitle();
		assertTrue(curentURL.contains("DeviceAllocationConfiguration"));
		assertEquals(studyTableDeviceAllocationConfigurationsCount, deviceAllocationConfigurationsCount);
		assertEquals(deviceAllocationConfigurationsHeader, AppConstants.DEVICEALLOCATIONCONFIGURATIONS_PAGE_HEADER);
		assertEquals(deviceAllocationConfigurationsPageTitle, AppConstants.DEVICEALLOCATIONCONFIGURATIONS_PAGE_TITLE);
		log.info("testStudyTableDeviceAllocationConfigurationsLink test is ending... ");
	}

	/**
	 * Test study table's protocol definitions link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 38, description = "Test testStudyTableProtocolDefinitionsLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableProtocolDefinitionsLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableProtocolDefinitionsLink() {
		log.info("testStudyTableProtocolDefinitionsLink test is starting... ");
		int studyTableProtocolDefinitionsCount = study.getStudyTableProtocolDefinitionsCount();
		int protocolDefinitionsCount = protocolDefinition.getProtocolDefinitionsCount();
		log.info("Study Table Protocol Definitions Count: " + studyTableProtocolDefinitionsCount);
		log.info("Protocol Definitions Page Count: " + protocolDefinitionsCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String protocolDefinitionsHeader = protocolDefinition.getProtocolDefinitionsHeader();
		String protocolDefinitionsPageTitle = protocolDefinition.getProtocolDefinitionsPageTitle();
		assertTrue(curentURL.contains("ProtocolDefinition"));
		assertEquals(studyTableProtocolDefinitionsCount, protocolDefinitionsCount);
		assertEquals(protocolDefinitionsHeader, AppConstants.PROTOCOLDEFINITIONS_PAGE_HEADER);
		assertEquals(protocolDefinitionsPageTitle, AppConstants.PROTOCOLDEFINITIONS_PAGE_TITLE);
		log.info("testStudyTableProtocolDefinitionsLink test is ending... ");
	}

	/**
	 * Test study table's roles link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 39, description = "Test testStudyTableRolesLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableRolesLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableRolesLink() {
		log.info("testStudyTableRolesLink test is starting... ");
		int studyTableRolesCount = study.getStudyTableRolesCount();
		int studyRolesCount = studyRole.getStudyRoleCount();
		log.info("Study Table Roles Count: " + studyTableRolesCount);
		log.info("Study Roles Page Count: " + studyRolesCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String studyRolesHeader = studyRole.getStudyRolesHeader();
		String studyRolesPageTitle = studyRole.getStudyRolesPageTitle();
		assertTrue(curentURL.contains("studyRole"));
		assertEquals(studyTableRolesCount, studyRolesCount);
		assertEquals(studyRolesHeader, AppConstants.STUDYROLES_PAGE_HEADER);
		assertEquals(studyRolesPageTitle, AppConstants.STUDYROLES_PAGE_TITLE);
		log.info("testStudyTableRolesLink test is ending... ");
	}

	/**
	 * Test study table's members link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 40, description = "Test testStudyTableMembersLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableMembersLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableMembersLink() {
		log.info("testStudyTableMembersLink test is starting... ");
		int studyTableMembersCount = study.getStudyTableMembersCount();
		int studyMembershipsCount = studyMemberships.getStudyMembershipsCount();
		log.info("Study Table Members Count: " + studyTableMembersCount);
		log.info("Study Memberships Page Count: " + studyMembershipsCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String studyMembershipsHeader = studyMemberships.getStudyMembershipsHeader();
		String studyMembershipsPageTitle = studyMemberships.getStudyMembershipsPageTitle();
		assertTrue(curentURL.contains("studyMembership"));
		assertEquals(studyTableMembersCount, studyMembershipsCount);
		assertEquals(studyMembershipsHeader, AppConstants.STUDYMEMBERSHIPS_PAGE_HEADER);
		assertEquals(studyMembershipsPageTitle, AppConstants.STUDYMEMBERSHIPS_PAGE_TITLE);
		log.info("testStudyTableMembersLink test is ending... ");
	}

	/**
	 * Test study table's device allocation groups link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 41, description = "Test testStudyTableDeviceAllocationGroupsLink", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableDeviceAllocationGroupsLink")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableDeviceAllocationGroupsLink() {
		log.info("testStudyTableDeviceAllocationGroupsLink test is starting... ");
		int studyTableDeviceAllocationGroupsCount = study.getStudyTableDeviceAllocationGroupsCount();
		int deviceAllocationGroupsCount = allocationGroup.getDeviceAllocationGroupsCount();
		log.info("Study Table Device Allocation Groups Count: " + studyTableDeviceAllocationGroupsCount);
		log.info("Device Allocation Groups Page Count: " + deviceAllocationGroupsCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String deviceAllocationGroupsHeader = allocationGroup.getDeviceAllocationGroupsHeader();
		String deviceAllocationGroupsPageTitle = allocationGroup.getDeviceAllocationGroupsPageTitle();
		assertTrue(curentURL.contains("allocationGroup"));
		assertEquals(studyTableDeviceAllocationGroupsCount, deviceAllocationGroupsCount);
		assertEquals(deviceAllocationGroupsHeader, AppConstants.DEVICEALLOCATIONGROUPS_PAGE_HEADER);
		assertEquals(deviceAllocationGroupsPageTitle, AppConstants.DEVICEALLOCATIONGROUPS_PAGE_TITLE);
		log.info("testStudyTableDeviceAllocationGroupsLink test is ending... ");
	}

	/**
	 * Test sorting of the id rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 42, description = "Test testStudyTableIdSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableIdSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableIdSort() {
		log.info("testStudyTableIdSort test is starting... ");
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(adminUtils.getTableRowsId(), adminUtils.getTableHeaderId());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(adminUtils.getTableRowsId(),adminUtils.getTableHeaderId());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableIdSort test is ending... ");
	}
	
	/**
	 * Test sorting of the name rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 43, description = "Test testStudyTableNameSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableNameSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableNameSort() {
		log.info("testStudyTableNameSort test is starting... ");
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsName(),study.getTableHeaderName());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsName(),study.getTableHeaderName());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableNameSort test is ending... ");
	}

	/**
	 * Test sorting of the subjects rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 44, description = "Test testStudyTableSubjectsSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableSubjectsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableSubjectsSort() {
		log.info("testStudyTableSubjectsSort test is starting... ");
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsSubjects(),study.getTableHeaderSubjects());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsSubjects(),study.getTableHeaderSubjects());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableSubjectsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the sessions rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 45, description = "Test testStudyTableSessionsSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableSessionsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableSessionsSort() {
		log.info("testStudyTableSessionsSort test is starting... ");
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsSessions(),study.getTableHeaderSessions());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsSessions(),study.getTableHeaderSessions());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableSessionsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the test definitions rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 46, description = "Test testStudyTableTestDefinitionsSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableTestDefinitionsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableTestDefinitionsSort() {
		log.info("testStudyTableTestDefinitionsSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsTestDefinitions(),study.getTableHeaderTestDefinitions());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsTestDefinitions(),study.getTableHeaderTestDefinitions());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableTestDefinitionsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the trials rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 47, description = "Test testStudyTableTrialsSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableTrialsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableTrialsSort() {
		log.info("testStudyTableTrialsSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsTrials(),study.getTableHeaderTrials());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsTrials(),study.getTableHeaderTrials());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableTrialsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the properties rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 48, description = "Test testStudyTablePropertiesSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTablePropertiesSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTablePropertiesSort() {
		log.info("testStudyTablePropertiesSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsProperties(),study.getTableHeaderProperties());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsProperties(),study.getTableHeaderProperties());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTablePropertiesSort test is ending... ");
	}
	
	/**
	 * Test sorting of the device allocation configurations rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 49, description = "Test testStudyTableDeviceAllocationConfigurationsSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableDeviceAllocationConfigurationsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableDeviceAllocationConfigurationsSort() {
		log.info("testStudyTableDeviceAllocationConfigurationsSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsDeviceAllocationConfigurations(),study.getTableHeaderDeviceAllocationConfigurations());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsDeviceAllocationConfigurations(),study.getTableHeaderDeviceAllocationConfigurations());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableDeviceAllocationConfigurationsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the protocol definitions rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 50, description = "Test testStudyTableProtocolDefinitionsSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableProtocolDefinitionsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableProtocolDefinitionsSort() {
		log.info("testStudyTableProtocolDefinitionsSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsProtocolDefinitions(),study.getTableHeaderProtocolDefinitions());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsProtocolDefinitions(),study.getTableHeaderProtocolDefinitions());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableProtocolDefinitionsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the roles rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 51, description = "Test testStudyTableRolesSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableRolesSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableRolesSort() {
		log.info("testStudyTableRolesSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsRoles(),study.getTableHeaderRoles());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsRoles(),study.getTableHeaderRoles());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableRolesSort test is ending... ");
	}
	
	/**
	 * Test sorting of the members rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 52, description = "Test testStudyTableMembersSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableMembersSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableMembersSort() {
		log.info("testStudyTableMembersSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsMembers(),study.getTableHeaderMembers());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsMembers(),study.getTableHeaderMembers());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableMembersSort test is ending... ");
	}
	
	/**
	 * Test sorting of the device allocation groups rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 53, description = "Test testStudyTableDeviceAllocationGroupsSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableDeviceAllocationGroupsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableDeviceAllocationGroupsSort() {
		log.info("testStudyTableDeviceAllocationGroupsSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsDeviceAllocationGroups(),study.getTableHeaderDeviceAllocationGroups());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsDeviceAllocationGroups(),study.getTableHeaderDeviceAllocationGroups());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableDeviceAllocationGroupsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the description rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 54, description = "Test testStudyTableDescriptionSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableDescriptionSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableDescriptionSort() {
		log.info("testStudyTableDescriptionSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsDescription(),study.getTableHeaderDescription());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsDescription(),study.getTableHeaderDescription());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableDescriptionSort test is ending... ");
	}
	
	/**
	 * Test sorting of the guid rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 55, description = "Test testStudyTableGuidSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableGuidSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableGuidSort() {
		log.info("testStudyTableGuidSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsGUID(),study.getTableHeaderGUID());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsGUID(),study.getTableHeaderGUID());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableGuidSort test is ending... ");
	}
	
	/**
	 * Test sorting of the phase rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 56, description = "Test testStudyTablePhaseSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTablePhaseSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTablePhaseSort() {
		log.info("testStudyTablePhaseSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsPhase(),study.getTableHeaderPhase());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsPhase(),study.getTableHeaderPhase());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTablePhaseSort test is ending... ");
	}
	
	/**
	 * Test sorting of the start date rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 57, description = "Test testStudyTableStartDateSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableStartDateSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableStartDateSort() {
		log.info("testStudyTableStartDateSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsStartDate(),study.getTableHeaderStartDate());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsStartDate(),study.getTableHeaderStartDate());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableStartDateSort test is ending... ");
	}
	
	/**
	 * Test sorting of the uses site rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 58, description = "Test testStudyTableUsesSitesSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableUsesSitesSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableUsesSitesSort() {
		log.info("testStudyTableUsesSitesSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsUsesSites(),study.getTableHeaderUsesSites());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsUsesSites(),study.getTableHeaderUsesSites());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableUsesSitesSort test is ending... ");
	}
	
	/**
	 * Test sorting of the allow device allocation configurations rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 59, description = "Test testStudyTableAllowDeviceAllocationConfigurationsSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableUsesSitesSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableAllowDeviceAllocationConfigurationsSort() {
		log.info("testStudyTableAllowDeviceAllocationConfigurationsSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsAllowDeviceAllocationConfigurations(),study.getTableHeaderDeviceAllocationConfigurations());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsAllowDeviceAllocationConfigurations(),study.getTableHeaderDeviceAllocationConfigurations());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableAllowDeviceAllocationConfigurationsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the creation date rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 60, description = "Test testStudyTableCreationDateSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableCreationDateSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableCreationDateSort() {
		log.info("testStudyTableCreationDateSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsCreationDate(),study.getTableHeaderCreationDate());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsCreationDate(),study.getTableHeaderCreationDate());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableCreationDateSort test is ending... ");
	}
	
	/**
	 * Test sorting of the deleted rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 61, description = "Test testStudyTableDeletedSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableDeletedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableDeletedSort() {
		log.info("testStudyTableDeletedSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsDeleted(),study.getTableHeaderDeleted());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsDeleted(),study.getTableHeaderDeleted());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableDeletedSort test is ending... ");
	}
	
	/**
	 * Test sorting of the last modified rows in the study table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3809"> SB-3809 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 62, description = "Test testStudyTableLastModifiedSort", enabled = true)
	@Story("Test Study Place in UI MX Admin Tests")
	@Description("Test testStudyTableLastModifiedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testStudyTableLastModifiedSort() {
		log.info("testStudyTableLastModifiedSort test is starting... ");
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(study.getTableRowsLastModified(),study.getTableHeaderLastModified());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(study.getTableRowsLastModified(),study.getTableHeaderLastModified());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testStudyTableLastModifiedSort test is ending... ");
	}
	
	/**
	 * Test side bar elements' text and number
	 * 
	 * @see 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 63, description = "Test testSideBarItems", enabled = true)
	@Story("")
	@Description("Test testSideBarItems")
	@Severity(SeverityLevel.CRITICAL)
	public void testSideBarItems() {
		log.info("testSideBarItems test is starting... ");
		adminUtils.clickSideBarButton();
		List<String> sideBarItemsStrings = study.getSideBarItems();
		int size = sideBarItemsStrings.size();
		Assert.assertEquals(sideBarItemsStrings, AppConstants.SIDE_BAR_ITEMS);
		Assert.assertEquals(size, 10);
		log.info("testSideBarItems test is ending... ");
	}
	
	@AfterMethod(groups = "finish")
	public void tearDown() {
		log.info("AfterMethod is starting... ");
		driver.quit();
		log.info("tearDown is ending... ");
	}

}
