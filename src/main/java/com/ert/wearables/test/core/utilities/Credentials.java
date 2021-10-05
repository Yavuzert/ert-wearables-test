package com.ert.wearables.test.core.utilities;

/**
 * This class provides credentials for page classes 
 * 
 * @author yavuz.ozturk
 *
 */
public class Credentials {
	
	private String appUsername;
	private String appPassword;
	
	/**
	 * Constructor
	 * 
	 * @param appUsername
	 * @param appPassword
	 */
	public Credentials(String appUsername, String appPassword) {
		this.appUsername = appUsername;
		this.appPassword = appPassword;
	}

	/**
	 * This method provides get username
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getAppUsername() {
		return appUsername;
	}

	/**
	 * This method provides set username
	 * 
	 * @param appUsername
	 * @author yavuz.ozturk
	 */
	public void setAppUsername(String appUsername) {
		this.appUsername = appUsername;
	}

	/**
	 * This method provides get password
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getAppPassword() {
		return appPassword;
	}

	/**
	 * This method provides set password
	 * 
	 * @param appPassword
	 * @author yavuz.ozturk
	 */
	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}

}
