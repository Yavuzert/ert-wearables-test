package com.ert.wearables.test.core.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class provides webdriver instances and provides parallel execution
 * 
 * @author yavuz.ozturk
 * @email: yavuz.ozturk@ert.com
 *
 */
public class BaseSetup {

	Properties prop;
	
	public static boolean highlightElement;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This methods provides to get webdriver instances from BaseSetup
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	/**
	 * This method provides browser instances from selenium webdriver
	 * In the method, WebDriver Management is used (Boni Garcia)
	 * @param browserName
	 * @return
	 */
	public WebDriver init_driver(String browserName) {
		highlightElement = prop.get("highlight").equals("yes") ? true : false;
		System.out.println("Browser name: "+ browserName);
		optionsManager = new OptionsManager(prop);
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browserName.equals("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			tldriver.set(new SafariDriver());
		}
		else {
			System.out.println("Browser name "+ browserName + " is not found, please pass the correct browser");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	/**
	 * This method provides config environments for tests
	 * @return
	 * @author yavuz.ozturk
	 */
	public Properties init_properties() {
		
		prop = new Properties();
		
		FileInputStream ip = null;
		String env = System.getProperty("env");
		
		if (env == null) {
			try {
				ip = new FileInputStream("./src/main/java/com/ert/wearables/test/core/config/qa.admin.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (env) {
				case "qa":
					ip = new FileInputStream("./src/main/java/com/ert/wearables/test/core/config/qa.admin.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/main/java/com/ert/wearables/test/core/config/dev.admin.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/main/java/com/ert/wearables/test/core/config/stage.admin.properties");
					break;
				default:
					System.out.println("Please pass the right env value...");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} 
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * This method provides to take screenshot if test case is failed
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+ "/screenshots/" + System.currentTimeMillis()+ ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.err.println("screenshot captured failed...");
		}
		return path;
	}

}
