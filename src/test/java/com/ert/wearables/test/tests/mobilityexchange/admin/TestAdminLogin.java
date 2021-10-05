package com.ert.wearables.test.tests.mobilityexchange.admin;

import static org.testng.Assert.assertEquals;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.AdminLogin;
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
 * This class tests Admin Login page in Mobility Exchange Admin Application
 * 
 * @author yavuz.ozturk
 */
@Epic("SB-1871 - https://apdmwearables.atlassian.net/browse/SB-1871")
@Feature("SB-3833 - https://apdmwearables.atlassian.net/browse/SB-3833")
//@Listeners(ExtentReportListener.class)
public class TestAdminLogin {
	
	WebDriver driver;
	BaseSetup baseSetup;
	Properties prop;
	AdminLogin adminLogin;
	Credentials userCred;
	ProjectsUtils projectsUtils;
	AdminUtils adminUtils;

	Logger log = Logger.getLogger(TestAdminLogin.class);
	
	@BeforeMethod()
	public void setUp() {
		log.info("BeforeMethod is starting...");
		log.info("TestAppLogin class is starting for tests");
		baseSetup = new BaseSetup();
		prop = baseSetup.init_properties();
		String browserName = prop.getProperty("browser");
		driver = baseSetup.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		log.info("url is launched: "+ prop.getProperty("url")); 
		adminLogin = new AdminLogin(driver);
		adminUtils = new AdminUtils(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		log.info("setUp is ending... ");
	}
	
	/**
	 * Test get login page title from admin
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3833"> SB-3833 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 1, description = "Test testGetTitle", enabled = true, groups = "smoke")
	@Story("")
	@Description("Test testGetTitle")
	@Severity(SeverityLevel.NORMAL)
	public void testGetTitle() {
		log.info("testGetTitle test is starting... "); 
		String title = adminLogin.getLoginPageTitle();
		log.info("Studies page title: "+ title);
		assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
		log.info("testGetTitle test is ending... ");
//		log.warn("Warning for testGetTitle");
//		log.error("Error for testGetTitle");
//		log.fatal("Fatal error for testGetTitle");
	}
	
	/**
	 * Test login page verification
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3833"> SB-3833 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 2, description = "Test testLoginPage", enabled = true)
	@Story("")
	@Description("Test testLoginPage")
	@Severity(SeverityLevel.CRITICAL)
	public void testLoginPage() {
		log.info("testLoginPage test is starting... "); 
		adminUtils.doLogin(userCred);
		String title = adminLogin.getPageTitle();
		log.info("Studies login page title: " + title);
		
		assertEquals(title, AppConstants.STUDIES_PAGE_HEADER);
		log.info("testLoginPage test is ending... ");
	}
	
	/**
	 * Test account verification
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3833"> SB-3833 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 3, description  = "test testAccountVerification", enabled = true)
	@Story("")
	@Description("test testAccountVerification")
	@Severity(SeverityLevel.CRITICAL)
	public void testAccountVerification() {
		log.info("testAccountVerification test is starting... ");
		adminUtils.doLogin(userCred);
		String userEmail = adminLogin.getUserEmail();
		log.info("Application user email: " + userEmail);
		assertEquals(userEmail, AppConstants.USER_EMAIL);
		
		String username = adminLogin.getUserName();
		log.info("Application user name: " + username);
		assertEquals(username, AppConstants.USER_NAME);
		log.info("testAccountVerification test is ending... ");
	}
	
	/**
	 * Test data provide for invalid login credentials
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	@DataProvider
	@Description("invalid mock data")
	public Object[][] getLoginInvalidData() {
		Object data[][] = {{"lars@gmail.com", "test1234"}, 
				           {"nick34@yahoo.com", "1234"},
				           {"yavuz!", "test4567"},
				           {"jess", "jess"},
				           {"TestAdmin", "Test"}};
		return data;
	}
	
	/**
	 * Test login with invalid credentials
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3833"> SB-3833 </a>
	 * @param username
	 * @param pwd
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 4, description = "Test invalid login credentials", dataProvider = "getLoginInvalidData", enabled = true)
	@Story("")
	@Description("Test invalid login credentials")
	@Severity(SeverityLevel.CRITICAL)
	public void testLoginInvalidCreds(String username, String pwd) throws InterruptedException {
		log.info("testLoginInvalidCreds test is starting... ");
		adminLogin.invalidLoginCredentials(username, pwd);
		String errorMessage = adminLogin.getInvalidCredentialMessage();
		
		assertEquals(errorMessage, "Invalid credentials", "Verify Invalid credentials text after entering invalid username and password");
		log.info("testLoginInvalidCreds test is ending... ");
	}
	
	/**
	 * Test data provide for empty user email
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	@DataProvider
	@Description("invalid mock data")
	public Object[][] emptyUserEmailData() {
		Object data[][] = {{""}, 
				           {""}};
		return data;
	}
	
	/**
	 * Test login with empty user email
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3833"> SB-3833 </a>
	 * @param username
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 5, description = "Test login with empty email", dataProvider = "emptyUserEmailData", enabled = true)
	@Story("")
	@Description("Test login with empty email")
	@Severity(SeverityLevel.CRITICAL)
	public void testLoginEmptyUserEmail(String username) throws InterruptedException {
		log.info("testLoginEmptyUserEmail test is starting... ");
		adminLogin.emptyEmailLogin(username);
		String emailErrorMessage = adminLogin.getEmptyUsernameMessage();
		
		assertEquals(emailErrorMessage, "An email is required", "Verify empty email text after entering empty email");
		log.info("testLoginEmptyUserEmail test is ending... ");
	}
	
	/**
	 * Test data provide for empty user password
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	@DataProvider
	@Description("invalid mock data")
	public Object[][] emptyUserPasswordData() {
		Object data[][] = {{"Test1", ""}, 
		                   {" ", ""}};
		return data;
	}
	
	/**
	 * Test login with empty password
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3833"> SB-3833 </a>
	 * @param username
	 * @param pwd
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 6, description = "Test login with empty password", dataProvider = "emptyUserPasswordData", enabled = true)
	@Story("")
	@Description("Test login with empty password")
	@Severity(SeverityLevel.CRITICAL)
	public void testLoginEmptyUserPassword(String username, String pwd) throws InterruptedException {
		log.info("testLoginEmptyUserPassword test is starting... ");
		adminLogin.invalidLoginCredentials(username, pwd);
		String passwordErrorMessage = adminLogin.getEmptyPasswordMessage();
		
		assertEquals(passwordErrorMessage, "Password is required", "Verify empty password text after entering invalid email");
		log.info("testLoginEmptyUserPassword test is ending... ");
	}
	
	/**
	 * Test Logout from App
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3833"> SB-3833 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 7, description = "Test testLogout", enabled = true)
	@Story("Verify ...")
	@Description("Test testLogout")
	@Severity(SeverityLevel.CRITICAL)
	public void testLogout() {
		log.info("testLogout test is starting... ");
		adminUtils.doLogin(userCred);
		String title = adminLogin.getPageTitle();
		log.info("Studies page title: " + title);
		assertEquals(title, AppConstants.STUDIES_PAGE_HEADER);
		
		adminLogin.adminLogout();
	
		String appTitle = adminLogin.getAdminTitle();
		log.info("App title: " + appTitle);
		assertEquals(appTitle, AppConstants.APP_lOGIN_TITLE);
		log.info("testLogout test is ending... ");
	}
	
	@AfterMethod()
	public void tearDown() {
		log.info("tearDown test is starting... ");
		driver.quit();
		log.info("tearDown is ending... ");
	}

}
