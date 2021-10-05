package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;

public class AdminLogin extends BaseSetup{
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	
	//Locators
	private By username = By.id("__localdom__1");
	private By password = By.id("__localdom__3");
	private By nextButton = By.xpath("//span[@class='gwt-InlineLabel label']");
	private By errorInvalidCredentialsMessage= By.xpath("//div[@class='lux-status-panel lux']//div");
	private By errorEmptyUserAndPasswordMsg = By.xpath("//div[@class='gwt-Label']");
	private By studies = By.xpath("//h2[normalize-space()='Studies']");
	private By accountEmail = By.xpath("//span[normalize-space()='admin@nodomain.com']");
	private By accountName = By.cssSelector("label[class='es-label-stack'] span:nth-child(1)");
	private By iconButton = By.xpath("//span[@class='es-icon-user-name es-icon']");
	private By logoutButton = By.xpath("//a[normalize-space()='Logout']");
	private By appTitle = By.xpath("//div[@class='title']");

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public AdminLogin(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}
	
	/**
	 * This method provides to get Admin Application's title before login
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getAdminTitle() {
		elementUtils.waitForElementPresent(appTitle);
		return elementUtils.doGetText(appTitle);
	}
	
	/**
	 * This method provides to get login page title before login
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getLoginPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE); 
		return elementUtils.doGetPageTitle();
	}
	
	/**
	 * This method is used to get page title after login
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getPageTitle() {
		elementUtils.waitForElementPresent(studies);
		String pageTitle = elementUtils.doGetText(studies);
		return pageTitle;
	}
	
	/**
	 * This method is used get user email after login
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getUserEmail() {
		elementUtils.waitForElementPresent(accountEmail);
		String userEmail = elementUtils.doGetText(accountEmail);
		return userEmail;
	}
	
	/**
	 * This method is used get user name after login
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getUserName() {
		elementUtils.waitForElementPresent(accountName);
		String username = elementUtils.doGetText(accountName);
		return username;
	}
	
	/**
	 * This method is used to logout from Admin app
	 * 
	 * @author yavuz.ozturk
	 */
	public void adminLogout() {
		elementUtils.doClick(iconButton);
		elementUtils.doClick(logoutButton);
	}

	/**
	 * This method is used to do invalid login credentials
	 * 
	 * @param userName
	 * @param pwd
	 * @author yavuz.ozturk
	 */
	public void invalidLoginCredentials(String userName, String pwd) {
		elementUtils.waitForElementPresent(username);
		elementUtils.doSendKeys(username, userName);
		elementUtils.waitForElementPresent(nextButton);
		elementUtils.doClick(nextButton);
		elementUtils.waitForElementPresent(password);
		elementUtils.doSendKeys(password, pwd);
		elementUtils.waitForElementPresent(nextButton);
		elementUtils.doClick(nextButton);
	}
	
	/**
	 * This method is used to get invalid credentials (username/password) message 
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getInvalidCredentialMessage() {
		elementUtils.waitForElementPresent(errorInvalidCredentialsMessage);
		return elementUtils.doGetText(errorInvalidCredentialsMessage);	
	}
	
	/**
	 * This method is used to do empty email login
	 * 
	 * @param userName
	 * @author yavuz.ozturk
	 */
	public void emptyEmailLogin(String userName) {
		elementUtils.waitForElementPresent(username);
		elementUtils.doSendKeys(username, userName);
		elementUtils.waitForElementPresent(nextButton);
		elementUtils.doClick(nextButton);
	}
	
	/**
	 * This method is used to get empty username message after try empty email
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getEmptyUsernameMessage() {
		elementUtils.waitForElementPresent(errorEmptyUserAndPasswordMsg);
		elementUtils.doIsDisplayed(errorEmptyUserAndPasswordMsg);
		return elementUtils.doGetText(errorEmptyUserAndPasswordMsg);	
	}
	
	/**
	 * This method is used to get empty password message after try empty password
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getEmptyPasswordMessage() {
		elementUtils.waitForElementPresent(errorEmptyUserAndPasswordMsg);
		return elementUtils.doGetText(errorEmptyUserAndPasswordMsg);	
	}

}
