package com.ert.wearables.test.core.base;

import java.util.Properties;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * This class provides options for chrome and firefox like incognito mode, certificate ignore, headless etc.
 * 
 * @author yavuz.ozturk
 *
 */
public class OptionsManager {
	
	public ChromeOptions co;
	public FirefoxOptions fo;
	public Properties prop;
	
	/**
	 * Constructor
	 * 
	 * @param prop
	 */
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	/**
	 * This method provides Chrome options
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(prop.getProperty("incognito").equals("yes")) co.addArguments("--incognito");
		if(prop.getProperty("headless").equals("yes")) co.addArguments("--headless");
		return co;
	}
	
	/**
	 * This method provides Firefox options
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(prop.getProperty("headless").equals("yes")) fo.addArguments("--headless");
		return fo;
	}
	
}
