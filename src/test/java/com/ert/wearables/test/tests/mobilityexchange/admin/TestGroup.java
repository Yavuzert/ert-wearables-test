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
import com.ert.wearables.test.core.pages.mobilityexchange.admin.Group;
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
 * This class tests Group component in Mobility Exchange Admin Application
 * 
 * @author yavuz.ozturk
 */
@Epic("SB-1871 - https://apdmwearables.atlassian.net/browse/SB-1871")
@Feature("SB-3890 - https://apdmwearables.atlassian.net/browse/SB-3890")
//@Listeners(ExtentReportListener.class)
public class TestGroup {

	WebDriver driver;
	BaseSetup baseSetup;
	Properties prop;
	AdminUtils adminUtils;
	Credentials userCred;
	ProjectsUtils projectsUtils;
	Group group;
	AuditLog auditLog;
	
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
		group = new Group(driver);
		auditLog =new AuditLog(driver);
		log.info("setUp is ending... ");
	}
	
	/**
	 * Test full and compact views for group component
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 1, description = "Test testFullCompactViewsOnGroup", enabled = true, groups = {"smoke"})
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testFullCompactViewsOnGroup")
	@Severity(SeverityLevel.CRITICAL)
	public void testFullCompactViewsOnGroup() {
		log.info("testFullCompactViewsOnGroup test is starting... ");
		group.clickGroupsSideBarButton();
		String fullButtonText = adminUtils.getFullButtonText();
		adminUtils.clickFull();
		String compactText = adminUtils.getCompactText();
		if (compactText.equalsIgnoreCase(compactText)) {
			adminUtils.clickCompact();
		}
		assertEquals(fullButtonText, "Full", "verifying full button text");
		assertEquals(compactText, "Compact", "verifying compact button text");
		log.info("testFullCompactViewsOnGroup test is ending... ");
	}
	
	/**
	 * Test create new group with one member user selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 2, description = "Test testCreateNewGroupWithOneMemberUser", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testCreateNewGroupWithOneMemberUser")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewGroupWithOneMemberUser() throws InterruptedException {
		log.info("testCreateNewGroupWithOneMemberUser test is starting... ");
		group.clickGroupsSideBarButton();
		adminUtils.clickCreate();
		String name = "Test_FirstName_" + projectsUtils.randomString(5);
		group.createUser(name, "admin@nodomain.com");
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedGroup = group.getLastCreatedGroupFromGroupTable();
		System.out.println(lastCreatedGroup);
		assertEquals(name, lastCreatedGroup, "verifying the presence of last group created");
		log.info("testCreateNewGroupWithOneMemberUser test is ending... ");
	}
	
	/**
	 * Test group create with valid web table data
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 3, description = "Test validateGroupWebTableData", enabled = true, groups = {"smoke"})
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test validateGroupWebTableData")
	@Severity(SeverityLevel.CRITICAL)
	public void validateGroupWebTableData() throws InterruptedException {
		log.info("validateGroupWebTableData test is starting... ");
		TimeUnit.SECONDS.sleep(3);
		group.clickGroupsSideBarButton();
		adminUtils.clickCreate();
		Map<String, String> newUserMap = new LinkedHashMap<>();
		String name = "Test_FirstName_" + projectsUtils.randomString(5);
		newUserMap.put("Name", name);
		group.createUser(name, "admin@nodomain.com");
		adminUtils.clickFull();
		List<Map<String, String>> tableDataList = projectsUtils.getTableDataAsMapList();
		for (int i = 0; i < tableDataList.size(); i++) {
			Map<String, String> eachMap = tableDataList.get(i);
			String newName = newUserMap.get("Name");
			if (eachMap.get("Name") != null) {
				if ((eachMap.get("Name")).equals(newName)) {
					assertEquals(eachMap.get("Name"), newName);
					log.info("Name from table: " + eachMap.get("Name"));
					log.info("Name from new group name: " + newUserMap.get("Name"));
				}
			}
		}
		adminUtils.clickCompact();
		log.info("validateGroupWebTableData test is ending... ");
	}
	
	/**
	 * Test group history via group table's Id Link from group form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 4, description = "Test testGroupHistoryViaGroupTablesIdLink", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupHistoryViaGroupTablesIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupHistoryViaGroupTablesIdLink()  {
		log.info("testGroupHistoryViaGroupTablesIdLink test is starting... ");
		group.clickGroupsSideBarButton();
		String groupTableId = group.getGroupTableId();
		group.clickGroupTableIdLink();
		String groupFormGroupId = group.getGroupFormId();
		adminUtils.clickHistory();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals(groupTableId,auditLogTableObjectId);
		assertEquals(groupFormGroupId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testGroupHistoryViaGroupTablesIdLink test is ending... ");
	}
	
	/**
	 * Test group history via group table's Name Link from group form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 5, description = "Test testGroupHistoryViaGroupTablesNameLink", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupHistoryViaGroupTableNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupHistoryViaGroupTableNameLink() {
		log.info("testStudyObjectHistoryViaStudiesTableNameLink test is starting... ");
		group.clickGroupsSideBarButton();
		String groupTableName = group.getGroupTableName();
		group.clickGroupTableNameLink();
		String groupFormGroupId = group.getGroupFormId();
		String groupFormName = group.getGroupFormName();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals(groupTableName,groupFormName);
		assertEquals(groupFormGroupId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testGroupHistoryViaGroupTableNameLink test is ending... ");
	}
	
	/**
	 * Test group history via group table's Id Link from group form when editing group
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 5, description = "Test testGroupHistoryViaGroupTablesIdLinkWhenEditingGroup", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupHistoryViaGroupTablesIdLinkWhenEditingGroup")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupHistoryViaGroupTablesIdLinkWhenEditingGroup() {
		log.info("testGroupHistoryViaGroupTablesIdLinkWhenEditingGroup test is starting... ");
		group.clickGroupsSideBarButton();
		String groupTableId = group.getGroupTableId();
		group.clickGroupTableIdLink();
		String groupFormGroupId = group.getGroupFormId();
		adminUtils.clickEditButton();
		adminUtils.clickHistory();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals(groupTableId,auditLogTableObjectId);
		assertEquals(groupFormGroupId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testGroupHistoryViaGroupTablesIdLinkWhenEditingGroup test is ending... ");
	}
	
	/**
	 * Test group history via group table's Name Link from group form when editing group
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 5, description = "Test testGroupHistoryViaGroupTablesNameLinkWhenEditingGroup", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupHistoryViaGroupTablesNameLinkWhenEditingGroup")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupHistoryViaGroupTablesNameLinkWhenEditingGroup() {
		log.info("testGroupHistoryViaGroupTablesNameLinkWhenEditingGroup test is starting... ");
		group.clickGroupsSideBarButton();
		String groupTableName = group.getGroupTableName();
		group.clickGroupTableNameLink();
		String groupFormGroupId = group.getGroupFormId();
		String groupFormName = group.getGroupFormName();
		adminUtils.clickEditButton();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals(groupTableName,groupFormName);
		assertEquals(groupFormGroupId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testGroupHistoryViaGroupTablesNameLinkWhenEditingGroup test is ending... ");
	}
	
	/**
	 * Test group form's group deleting with table ID link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 6, description = "Test testGroupFormDeleteGroupWithIdLink", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupFormDeleteGroupWithIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupFormDeleteGroupWithIdLink() throws InterruptedException {
		log.info("testGroupFormDeleteGroupWithIdLink test is starting... ");
		group.clickGroupsSideBarButton();
		adminUtils.navigateToLastPage();
		String lastCreatedGroup = group.getLastCreatedGroupFromGroupTable();
		log.info("Last Group Before Delete " + lastCreatedGroup);
		group.deleteLastCreatedGroupViaIdLink();
		TimeUnit.SECONDS.sleep(3);
		String lastGroup = group.getLastCreatedGroupFromGroupTable();
		log.info("Last Group After Delete " + lastGroup);
		assertNotEquals(lastCreatedGroup, lastGroup);
		log.info("testGroupFormDeleteGroupWithIdLink test is ending... ");
	}
	
	/**
	 * Test Group form's group deleting with table name link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 7, description = "Test testGroupFormDeleteGroupWithNameLink", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupFormDeleteGroupWithNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupFormDeleteGroupWithNameLink() throws InterruptedException {
		log.info("testGroupFormDeleteGroupWithNameLink test is starting... ");
		group.clickGroupsSideBarButton();
		adminUtils.navigateToLastPage();
		String lastCreatedGroup = group.getLastCreatedGroupFromGroupTable();
		log.info("Last Group Before Delete " + lastCreatedGroup);
		group.deleteLastCreatedGroupViaNameLink();
		TimeUnit.SECONDS.sleep(3);
		String lastGroup = group.getLastCreatedGroupFromGroupTable();
		log.info("Last Group After Delete " + lastGroup);
		assertNotEquals(lastCreatedGroup, lastGroup);
		log.info("testGroupFormDeleteGroupWithNameLink test is ending... ");
	}
	
	/**
	 * Test Group edit button via id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 8, description = "Test testGroupEditButtonViaIdLink", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupEditButtonViaIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void  testGroupEditButtonViaIdLink() throws InterruptedException {
		log.info("testGroupEditButtonViaIdLink test is starting... ");
		group.clickGroupsSideBarButton();
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();
		String name = "Test_FirstName_" + projectsUtils.randomString(5);
		group.editGroup(name, "admin@nodomain.com");
		String editedName = group.getEditedName();
		assertEquals(name, editedName);
		log.info("testGroupEditButtonViaIdLink test is ending... ");
	}
	
	/**
	 * Test Group edit button via Name link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 9, description = "Test testGroupEditButtonViaNameLink", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test  testGroupEditButtonViaNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void  testGroupEditButtonViaNameLink() throws InterruptedException {
		log.info("testGroupEditButtonViaNameLink test is starting... ");
		group.clickGroupsSideBarButton();
		group.clickGroupTableName();
		adminUtils.clickEditButton();
		String name = "Test_FirstName_" + projectsUtils.randomString(5);
		group.editGroup(name, "admin@nodomain.com");
		String editedName = group.getEditedName();
		assertEquals(name, editedName);
		log.info("testGroupEditButtonViaNameLink test is ending... ");
	}
	
	/**
	 * Test group table's id link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 10, description = "Test testGroupTableIdLink", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupTableIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupTableIdLink() {
		log.info("testGroupTableIdLink test is starting... ");
		group.clickGroupsSideBarButton();
		String groupTableId = adminUtils.getTableId();
		String groupFormId = adminUtils.getFormId();
		log.info("Group Table Id: " + groupTableId);
		log.info("Group Form Id: " + groupFormId);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		assertTrue(curentURL.endsWith(groupFormId));
		assertEquals(groupTableId, groupFormId);
		log.info("testGroupTableIdLink test is ending... ");
	}
	
	/**
	 * Test group table's Name link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 11, description = "Test testGroupTableNameLink", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupTableNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupTableNameLink() {
		log.info("testGroupTableNameLink test is starting... ");
		group.clickGroupsSideBarButton();
		String groupTableName = group.getGroupTableName();
		group.clickGroupTableNameLink();
		String groupFormPageTitle = group.getGroupFormPageTitle();
		String groupFormName = group.getGroupFormName();
		log.info("Group Table Name: " + groupTableName);
		log.info("Group Form Page Title: " + groupFormPageTitle);
		log.info("Group Form Name: " + groupFormName);
		assertTrue(groupTableName.equalsIgnoreCase(groupFormName));
		assertTrue(groupTableName.equalsIgnoreCase(groupFormPageTitle));
		assertTrue(groupFormName.equalsIgnoreCase(groupFormPageTitle));
		log.info("testGroupTableNameLink test is ending... ");
	}
	
	/**
	 * Test sorting of the Group id rows in the group table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 12, description = "Test testGroupTableIdSort", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupTableIdSort")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupTableIdSort() {
		log.info("testGroupTableIdSort test is starting... ");
		group.clickGroupsSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(adminUtils.getTableRowsId(), adminUtils.getTableHeaderId());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(adminUtils.getTableRowsId(),adminUtils.getTableHeaderId());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testGroupTableIdSort test is ending... ");
	}
	
	/**
	 * Test sorting of the name rows in the group table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 13, description = "Test testGroupTableNameSort", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupTableNameSort")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupTableNameSort() {
		log.info("testGroupTableNameSort test is starting... ");
		group.clickGroupsSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(group.getTableRowsName(),group.getTableHeaderName());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(group.getTableRowsName(),group.getTableHeaderName());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testGroupTableNameSort test is ending... ");
	}
	
	/**
	 * Test sorting of the member users rows in the group table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 13, description = "Test testGroupTableMemberUsersSort", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupTableMemberUsersSort")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupTableMemberUsersSort() {
		log.info("testGroupTableMemberUsersSort test is starting... ");
		group.clickGroupsSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(group.getTableRowsMemberUsers(),group.getTableHeaderMemberUsers());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(group.getTableRowsMemberUsers(),group.getTableHeaderMemberUsers());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testGroupTableMemberUsersSort test is ending... ");
	}
	
	/**
	 * Test sorting of the creation date rows in the group table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 14, description = "Test testGroupTableCreationDateSort", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupTableCreationDateSort")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupTableCreationDateSort() {
		log.info("testGroupTableCreationDateSort test is starting... ");
		group.clickGroupsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(group.getTableRowsCreationDate(),group.getTableHeaderCreationDate());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(group.getTableRowsCreationDate(),group.getTableHeaderCreationDate());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testGroupTableCreationDateSort test is ending... ");
	}
	
	/**
	 * Test sorting of the deleted rows in the group table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 15, description = "Test testGroupTableDeletedSort", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupTableDeletedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupTableDeletedSort() {
		log.info("testGroupTableDeletedSort test is starting... ");
		group.clickGroupsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(group.getTableRowsDeleted(),group.getTableHeaderDeleted());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(group.getTableRowsDeleted(),group.getTableHeaderDeleted());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testGroupTableDeletedSort test is ending... ");
	}
	
	/**
	 * Test sorting of the last modified rows in the group table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3890"> SB-3890 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 15, description = "Test testGroupTableLastModifiedSort", enabled = true)
	@Story("Test Group Place in UI MX Admin Tests")
	@Description("Test testGroupTableLastModifiedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testGroupTableLastModifiedSort() {
		log.info("testGroupTableLastModifiedSort test is starting... ");
		group.clickGroupsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(group.getTableRowsLastModified(),group.getTableHeaderLastModified());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(group.getTableRowsLastModified(),group.getTableHeaderLastModified());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testGroupTableLastModifiedSort test is ending... ");
	}
	
	@AfterMethod(groups = "finish")
	public void tearDown() {
		log.info("AfterMethod is starting... ");
		driver.quit();
		log.info("tearDown is ending... ");
	}
	
}
