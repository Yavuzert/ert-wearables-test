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
import com.ert.wearables.test.core.pages.mobilityexchange.admin.AuditLog;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.StudyMembership;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.User;
import com.ert.wearables.test.core.utilities.AdminUtils;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.Credentials;
import com.ert.wearables.test.core.utilities.ProjectsUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

/**
 * This class tests User component in Mobility Exchange Admin Application
 * 
 * @author yavuz.ozturk
 */
@Epic("SB-1871 - https://apdmwearables.atlassian.net/browse/SB-1871")
@Feature("SB-3889 - https://apdmwearables.atlassian.net/browse/SB-3889")
//@Listeners(ExtentReportListener.class)
public class TestUser {

	WebDriver driver;
	BaseSetup baseSetup;
	Properties prop;
	AdminUtils adminUtils;
	Credentials userCred;
	ProjectsUtils projectsUtils;
	User user;
	AuditLog auditLog;
	StudyMembership studyMembership;
	
	Logger log = Logger.getLogger(TestUser.class);
	
	@BeforeMethod(groups = "start")
	public void setUp() {
		log.info("BeforeMethod is starting...");
		log.info("TestTrial class is starting for tests");
		baseSetup = new BaseSetup();
		prop = baseSetup.init_properties();
		String browserName = prop.getProperty("browser");
		driver = baseSetup.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		log.info("url is launched: " + prop.getProperty("url"));
		adminUtils = new AdminUtils(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		adminUtils.doLogin(userCred);
		projectsUtils = new ProjectsUtils(driver);
		user = new User(driver);
		auditLog = new AuditLog(driver);
		studyMembership = new StudyMembership(driver);
		log.info("setUp is ending... ");
	}
	
	/**
	 * Test full and compact views for user component
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 1, description = "Test testFullCompactViewsOnUser", enabled = true, groups = {"smoke"})
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testFullCompactViewsOnUser")
	@Severity(SeverityLevel.CRITICAL)
	public void testFullCompactViewsOnUser() {
		log.info("testFullCompactViewsOnUser test is starting... ");
		user.clickUsersSideBarButton();
		String fullButtonText = adminUtils.getFullButtonText();
		adminUtils.clickFull();
		String compactText = adminUtils.getCompactText();
		if (compactText.equalsIgnoreCase(compactText)) {
			adminUtils.clickCompact();
		}
		assertEquals(fullButtonText, "Full", "verifying full button text");
		assertEquals(compactText, "Compact", "verifying compact button text");
		log.info("testFullCompactViewsOnUser test is ending... ");
	}
	
	/**
	 * Test create new user with Administrators groups selected, (not set) must change password selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 2, description = "Test testCreateNewUserWithAdministratorsGroups", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testCreateNewUserWithAdministratorsGroups")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewUserWithAdministratorsGroups() throws InterruptedException {
		log.info("testCreateNewUserWithAdministratorsGroups test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickCreate();
		String userNameOrEmail = projectsUtils.randomString(5) + "@nodomain.com";
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		user.createUser(userNameOrEmail, firstName, lastName, "Administrators", "(not set)");
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedUserNameOrEmail = user.getLastCreatedUserNameOrEmailFromUserTable();
		System.out.println(lastCreatedUserNameOrEmail);
		assertEquals(userNameOrEmail, lastCreatedUserNameOrEmail, "verifying the presence of last user created");
		log.info("testCreateNewUserWithAdministratorsGroups test is ending... ");
	}
	
	/**
	 * Test create new user with Auditors groups selected, Yes must change password selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 3, description = "Test testCreateNewUserWithAuditorsGroups", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testCreateNewUserWithAuditorsGroups")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewUserWithAuditorsGroups() throws InterruptedException {
		log.info("testCreateNewUserWithAuditorsGroups test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickCreate();
		String userNameOrEmail = projectsUtils.randomString(5) + "@nodomain.com";
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		user.createUser(userNameOrEmail, firstName, lastName, "Auditors", "Yes");
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedUserNameOrEmail = user.getLastCreatedUserNameOrEmailFromUserTable();
		System.out.println(lastCreatedUserNameOrEmail);
		assertEquals(userNameOrEmail, lastCreatedUserNameOrEmail, "verifying the presence of last user created");
		log.info("testCreateNewUserWithAuditorsGroups test is ending... ");
	}
	
	/**
	 * Test create new user with User Managers groups selected, First time must change password selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 4, description = "Test testCreateNewUserWithUserManagersGroups", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testCreateNewUserWithUserManagersGroups")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewUserWithUserManagersGroups() throws InterruptedException {
		log.info("testCreateNewUserWithUserManagersGroups test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickCreate();
		String userNameOrEmail = projectsUtils.randomString(5) + "@nodomain.com";
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		user.createUser(userNameOrEmail, firstName, lastName, "User Managers", "First time");
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedUserNameOrEmail = user.getLastCreatedUserNameOrEmailFromUserTable();
		System.out.println(lastCreatedUserNameOrEmail);
		assertEquals(userNameOrEmail, lastCreatedUserNameOrEmail, "verifying the presence of last user created");
		log.info("testCreateNewUserWithUserManagersGroups test is ending... ");
	}
	
	/**
	 * Test user create with valid web table data
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 5, description = "Test validateUserWebTableData", enabled = true, groups = {"smoke"})
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test validateUserWebTableData")
	@Severity(SeverityLevel.CRITICAL)
	public void validateUserWebTableData() throws InterruptedException {
		log.info("validateUserWebTableData test is starting... ");
		TimeUnit.SECONDS.sleep(3);
		user.clickUsersSideBarButton();
		adminUtils.clickCreate();
		Map<String, String> newUserMap = new LinkedHashMap<>();
		String userNameOrEmail = projectsUtils.randomString(5) + "@nodomain.com";
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		newUserMap.put("UserNameOrEmail", userNameOrEmail);
		newUserMap.put("FirstName", firstName);
		newUserMap.put("LastName", lastName);
		user.createUser(userNameOrEmail, firstName, lastName, "Administrators", "Yes");
		adminUtils.clickFull();
		List<Map<String, String>> tableDataList = projectsUtils.getTableDataAsMapList();
		for (int i = 0; i < tableDataList.size(); i++) {
			Map<String, String> eachMap = tableDataList.get(i);
			String newUserNameOrEmail = newUserMap.get("UserNameOrEmail");
			String newFirstName = newUserMap.get("FirstName");
			String newLastName = newUserMap.get("LastName");
			if (eachMap.get("UserNameOrEmail") != null) {
				if ((eachMap.get("UserNameOrEmail")).equals(newUserNameOrEmail)) {
					assertEquals(eachMap.get("UserNameOrEmail"), newUserNameOrEmail);
					log.info("UserNameOrEmail from table: " + eachMap.get("UserNameOrEmail"));
					log.info("UserNameOrEmail from new user UserNameOrEmail: " + newUserMap.get("UserNameOrEmail"));
				}
			}
			if (eachMap.get("FirstName") != null) {
				if ((eachMap.get("FirstName")).equals(newFirstName)) {
					assertEquals(eachMap.get("FirstName"), newFirstName);
				}
			}
			if (eachMap.get("LastName") != null) {
				if ((eachMap.get("LastName")).equals(newLastName)) {
					assertEquals(eachMap.get("LastName"), newLastName);
				}
			}
		}
		adminUtils.clickCompact();
		log.info("validateUserWebTableData test is ending... ");
	}
	
	
	/**
	 * Test user history via user table's Id Link from user form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 6, description = "Test testUserHistoryViaUserTablesIdLink", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserHistoryViaUserTablesIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testUserHistoryViaUserTablesIdLink()  {
		log.info("testUserHistoryViaUserTablesIdLink test is starting... ");
		user.clickUsersSideBarButton();
		String userTableId = user.getUserTableId();
		user.clickUserTableIdLink();
		String userFormUserId = user.getUserFormId();
		adminUtils.clickHistory();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals(userTableId,auditLogTableObjectId);
		assertEquals(userFormUserId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testUserHistoryViaUserTablesIdLink test is ending... ");
	}
	
	/**
	 * Test user history via user table's User name(Email) Link from user form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 7, description = "Test testUserHistoryViaUserTablesUserEmailLink", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testTrialHistoryViaTrialTablesTestDateLink")
	@Severity(SeverityLevel.NORMAL)
	public void testUserHistoryViaUserTablesUserEmailLink()  {
		log.info("testUserHistoryViaUserTablesUserEmailLink test is starting... ");
		user.clickUsersSideBarButton();
		String userTableId = user.getUserTableId();
		user.clickUserTableUserNameAndEmail();
		String userFormUserId = user.getUserFormId();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals(userTableId,auditLogTableObjectId);
		assertEquals(userFormUserId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testUserHistoryViaUserTablesUserEmailLink test is ending... ");
	}
	
	/**
	 * Test user history via user table's Id Link from user form when editing group
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 8, description = "Test testUserHistoryViaUserTablesIdLinkWhenEditingUser", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserHistoryViaUserTablesIdLinkWhenEditingUser")
	@Severity(SeverityLevel.NORMAL)
	public void testUserHistoryViaUserTablesIdLinkWhenEditingUser()  {
		log.info("testUserHistoryViaUserTablesIdLinkWhenEditingUser test is starting... ");
		user.clickUsersSideBarButton();
		String userTableId = user.getUserTableId();
		user.clickUserTableIdLink();
		String userFormUserId = user.getUserFormId();
		adminUtils.clickEditButton();
		adminUtils.clickHistory();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals(userTableId,auditLogTableObjectId);
		assertEquals(userFormUserId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testUserHistoryViaUserTablesIdLinkWhenEditingUser test is ending... ");
	}
	
	/**
	 * Test user history via user table's User name(Email) Link from user form when editing group
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 9, description = "Test testUserHistoryViaUserTablesUserNameAndEmailLinkWhenEditingGroup", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserHistoryViaUserTablesUserNameAndEmailLinkWhenEditingGroup")
	@Severity(SeverityLevel.NORMAL)
	public void testUserHistoryViaUserTablesUserNameAndEmailLinkWhenEditingGroup()  {
		log.info("testUserHistoryViaUserTablesUserNameAndEmailLinkWhenEditingGroup test is starting... ");
		user.clickUsersSideBarButton();
		String userTableId = user.getUserTableId();
		user.clickUserTableUserNameAndEmail();
		String userFormUserId = user.getUserFormId();
		adminUtils.clickEditButton();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals(userTableId,auditLogTableObjectId);
		assertEquals(userFormUserId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testUserHistoryViaUserTablesUserNameAndEmailLinkWhenEditingGroup test is ending... ");
	}
	
	/**
	 * Test User form's user deleting with table ID link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 10, description = "Test testUserFormDeleteUserWithIdLink", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserFormDeleteUserWithIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testUserFormDeleteUserWithIdLink() throws InterruptedException {
		log.info("testUserFormDeleteUserWithIdLink test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.navigateToLastPage();
		String lastCreatedUser = user.getLastCreatedUserNameOrEmailFromUserTable();
		log.info("Last User Before Delete " + lastCreatedUser);
		user.deleteLastCreatedUserViaIdLink();
		TimeUnit.SECONDS.sleep(3);
		String lastUser = user.getLastCreatedUserNameOrEmailFromUserTable();
		log.info("Last User After Delete " + lastUser);
		assertNotEquals(lastCreatedUser, lastUser);
		log.info("testUserFormDeleteUserWithIdLink test is ending... ");
	}
	
	/**
	 * Test User form's user deleting with table User name(Email) link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 11, description = "Test testUserFormDeleteUserWithUserNameAndEmailLink", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserFormDeleteUserWithUserNameAndEmailLink")
	@Severity(SeverityLevel.NORMAL)
	public void testUserFormDeleteUserWithUserNameAndEmailLink() throws InterruptedException {
		log.info("testUserFormDeleteUserWithUserNameAndEmailLink test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.navigateToLastPage();
		String lastCreatedUser = user.getLastCreatedUserNameOrEmailFromUserTable();
		log.info("Last User Before Delete " + lastCreatedUser);
		user.deleteLastCreatedUserViaUserNameAndEmailLink();
		TimeUnit.SECONDS.sleep(3);
		String lastUser = user.getLastCreatedUserNameOrEmailFromUserTable();
		log.info("Last User After Delete " + lastUser);
		assertNotEquals(lastCreatedUser, lastUser);
		log.info("testUserFormDeleteUserWithUserNameAndEmailLink test is ending... ");
	}
	
	/**
	 * Test User edit button via id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 12, description = "Test testUserEditButtonViaIdLink", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserEditButtonViaIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void  testUserEditButtonViaIdLink() throws InterruptedException {
		log.info("testUserEditButtonViaIdLink test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();
		String userNameOrEmail = projectsUtils.randomString(5) + "@nodomain.com";
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		user.editUser(userNameOrEmail, firstName, lastName, "Auditors", "First time");
		String editedUserNameAndEmail = user.getEditedUserNameandEmail();
		assertEquals(userNameOrEmail, editedUserNameAndEmail);
		log.info("testUserEditButtonViaIdLink test is ending... ");
	}
	
	/**
	 * Test User edit button via User Name(Email) link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 13, description = "Test testUserEditButtonViaUserNameAndEmailLinkk", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test  testUserEditButtonViaUserNameAndEmailLink")
	@Severity(SeverityLevel.NORMAL)
	public void  testUserEditButtonViaUserNameAndEmailLink() throws InterruptedException {
		log.info("testUserEditButtonViaUserNameAndEmailLink test is starting... ");
		user.clickUsersSideBarButton();
		user.clickUserTableUserNameAndEmail();
		adminUtils.clickEditButton();
		String userNameOrEmail = projectsUtils.randomString(5) + "@nodomain.com";
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		user.editUser(userNameOrEmail, firstName, lastName, "Auditors", "First time");
		String editedUserNameAndEmail = user.getEditedUserNameandEmail();
		assertEquals(userNameOrEmail, editedUserNameAndEmail);
		log.info("testUserEditButtonViaUserNameAndEmailLink test is ending... ");
	}
	
	/**
	 * Test user table's id link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 14, description = "Test testUserTableIdLink", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableIdLink() {
		log.info("testUserTableIdLink test is starting... ");
		user.clickUsersSideBarButton();
		String userTableId = adminUtils.getTableId();
		String userFormId = adminUtils.getFormId();
		log.info("User Table Id: " + userTableId);
		log.info("User Form Id: " + userFormId);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		assertTrue(curentURL.endsWith(userFormId));
		assertEquals(userTableId, userFormId);
		log.info("testUserTableIdLink test is ending... ");
	}
	
	/**
	 * Test user table's User name(Email) link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 15, description = "Test testUserTableUserNameAndEmailLink", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableUserNameAndEmailLink")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableUserNameAndEmailLink() {
		log.info("testUserTableUserNameAndEmailLink test is starting... ");
		user.clickUsersSideBarButton();
		String userTableUserNameAndEmail = user.getUserTableUserNameAndEmail();
		user.clickUserTableUserNameAndEmail();
		String userFormPageTitle = user.getUserFormPageTitle();
		String userFormUserNameAndEmail = user.getUserFormUserNameAndEmail();
		log.info("User Table User Name And Email: " + userTableUserNameAndEmail);
		log.info("User Form Page Title: " + userFormPageTitle);
		log.info("User Form User Name And Email: " + userFormUserNameAndEmail);
		assertTrue(userTableUserNameAndEmail.equalsIgnoreCase(userFormUserNameAndEmail));
		assertTrue(userTableUserNameAndEmail.equalsIgnoreCase(userFormPageTitle));
		assertTrue(userFormUserNameAndEmail.equalsIgnoreCase(userFormPageTitle));
		log.info("testUserTableUserNameAndEmailLink test is ending... ");
	}
	
	/**
	 * Test user table's Study memberships link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 16, description = "Test testUserTableStudyMembershipsLink", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableStudyMembershipsLink")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableStudyMembershipsLink() {
		log.info("testUserTableStudyMembershipsLink test is starting... ");
		user.clickUsersSideBarButton();
		int userTableStudyMembershipsCount = user.getUserTableMembershipsCount();
		int studyMembershipsTableElementCount = studyMembership.getStudyMembershipsCount();
		log.info("User Table Study Memberships Count: " + userTableStudyMembershipsCount);
		log.info("Study memberships Table Element Count: " + studyMembershipsTableElementCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String studyMembershipsHeader = studyMembership.getStudyMembershipsHeader();
		String studyMembershipsPageTitle = studyMembership.getStudyMembershipsPageTitle();
		assertTrue(curentURL.contains("studyMembership"));
		assertEquals(userTableStudyMembershipsCount, studyMembershipsTableElementCount);
		assertEquals(studyMembershipsHeader, AppConstants.STUDYMEMBERSHIPS_PAGE_HEADER);
		assertEquals(studyMembershipsPageTitle, AppConstants.STUDYMEMBERSHIPS_PAGE_TITLE);
		log.info("testUserTableStudyMembershipsLink test is ending... ");
	}
	
	/**
	 * Test sorting of the User id rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 17, description = "Test testUserTableIdSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableIdSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableIdSort() {
		log.info("testUserTableIdSort test is starting... ");
		user.clickUsersSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(adminUtils.getTableRowsId(), adminUtils.getTableHeaderId());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(adminUtils.getTableRowsId(),adminUtils.getTableHeaderId());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableIdSort test is ending... ");
	}
	
	/**
	 * Test sorting of the User name(Email) rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 18, description = "Test testUserTableUserNameAndEmailSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableUserNameAndEmailSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableUserNameAndEmailSort() {
		log.info("testUserTableUserNameAndEmailSort test is starting... ");
		user.clickUsersSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsUserNameAndEmail(),user.getTableHeaderUserNameAndEmail());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsUserNameAndEmail(),user.getTableHeaderUserNameAndEmail());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableUserNameAndEmailSort test is ending... ");
	}
	
	/**
	 * Test sorting of the study memberships rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 19, description = "Test testUserTableStudyMembershipsSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableStudyMembershipsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableStudyMembershipsSort() {
		log.info("testUserTableStudyMembershipsSort test is starting... ");
		user.clickUsersSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsStudyMemberships(),user.getTableHeaderStudyMemberships());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsStudyMemberships(),user.getTableHeaderStudyMemberships());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableStudyMembershipsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the groups rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 20, description = "Test testUserTableGroupsSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableGroupsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableGroupsSort() {
		log.info("testUserTableGroupsSort test is starting... ");
		user.clickUsersSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsGroups(),user.getTableHeaderGroups());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsGroups(),user.getTableHeaderGroups());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableGroupsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the first name rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 21, description = "Test testUserTableFirstNameSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableFirstNameSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableFirstNameSort() {
		log.info("testUserTableFirstNameSort test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsFirstName(),user.getTableHeaderFirstName());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsFirstName(),user.getTableHeaderFirstName());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableFirstNameSort test is ending... ");
	}
	
	/**
	 * Test sorting of the last name rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 22, description = "Test testUserTableLastNameSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableLastNameSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableLastNameSort() {
		log.info("testUserTableLastNameSort test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsLastName(),user.getTableHeaderLastName());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsLastName(),user.getTableHeaderLastName());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableLastNameSort test is ending... ");
	}
	
	/**
	 * Test sorting of the date of training rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 23, description = "Test testUserTableDateOfTrainingSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableDateOfTrainingSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableDateOfTrainingSort() {
		log.info("testUserTableDateOfTrainingSort test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsDateOfTraining(),user.getTableHeaderDateOfTraining());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsDateOfTraining(),user.getTableHeaderDateOfTraining());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableDateOfTrainingSort test is ending... ");
	}
	
	/**
	 * Test sorting of the creation date rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 24, description = "Test testUserTableCreationDateSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableCreationDateSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableCreationDateSort() {
		log.info("testUserTableCreationDateSort test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsCreationDate(),user.getTableHeaderCreationDate());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsCreationDate(),user.getTableHeaderCreationDate());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableCreationDateSort test is ending... ");
	}
	
	/**
	 * Test sorting of the last login rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 25, description = "Test testUserTableLastLoginSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableLastLoginSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableLastLoginSort() {
		log.info("testUserTableLastLoginSort test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsLastLogin(),user.getTableHeaderLastLogin());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsLastLogin(),user.getTableHeaderLastLogin());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableLastLoginSort test is ending... ");
	}
	
	/**
	 * Test sorting of the account disabled rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 26, description = "Test testUserTableAccountDisabledSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableAccountDisabledSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableAccountDisabledsort() {
		log.info("testUserTableAccountDisabledSort test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsAccountDisabled(),user.getTableHeaderAccountDisabled());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsAccountDisabled(),user.getTableHeaderAccountDisabled());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableAccountDisabledSort test is ending... ");
	}
	
	/**
	 * Test sorting of the deleted rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 27, description = "Test testUserTableDeletedSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableDeletedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableDeletedSort() {
		log.info("testUserTableDeletedSort test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsDeleted(),user.getTableHeaderDeleted());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsDeleted(),user.getTableHeaderDeleted());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableDeletedSort test is ending... ");
	}
	
	/**
	 * Test sorting of the last modified rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 28, description = "Test testUserTableLastModifiedSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableLastModifiedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableLastModifiedSort() {
		log.info("testUserTableLastModifiedSort test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsLastModified(),user.getTableHeaderLastModified());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsLastModified(),user.getTableHeaderLastModified());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableLastModifiedSort test is ending... ");
	}
	
	/**
	 * Test sorting of the password last modified rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 29, description = "Test testUserTablePasswordLastModifiedSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTablePasswordLastModifiedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTablePasswordLastModifiedSort() {
		log.info("testUserTablePasswordLastModifiedSort test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsPasswordLastModified(),user.getTableHeaderPasswordLastModified());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsPasswordLastModified(),user.getTableHeaderPasswordLastModified());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTablePasswordLastModifiedSort test is ending... ");
	}
	
	/**
	 * Test sorting of the must change password rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 30, description = "Test testUserTableMustChangePasswordSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableMustChangePasswordSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableMustChangePasswordSort() {
		log.info("testUserTableMustChangePasswordSort test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsMustChangePassword(),user.getTableHeaderMustChangePassword());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsMustChangePassword(),user.getTableHeaderMustChangePassword());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableMustChangePasswordSort test is ending... ");
	}
	
	/**
	 * Test sorting of the system rows in the user table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3889"> SB-3889 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 31, description = "Test testUserTableSystemSort", enabled = true)
	@Story("Test User Place in UI MX Admin Tests")
	@Description("Test testUserTableSystemSort")
	@Severity(SeverityLevel.NORMAL)
	public void testUserTableSystemSort() {
		log.info("testUserTableSystemSort test is starting... ");
		user.clickUsersSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(user.getTableRowsMustChangePassword(),user.getTableHeaderMustChangePassword());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(user.getTableRowsMustChangePassword(),user.getTableHeaderMustChangePassword());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testUserTableSystemSort test is ending... ");
	}
	
	@AfterMethod(groups = "finish")
	public void tearDown() {
		log.info("AfterMethod is starting... ");
		driver.quit();
		log.info("tearDown is ending... ");
	}
	
}
