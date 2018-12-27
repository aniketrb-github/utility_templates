package com.utility.message;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.utility.constant.ApplicationConstants;
import com.utility.logger.AppLogger;

public class TomcatUtils {
	private static final AppLogger logger = new AppLogger(TomcatUtils.class);

	public static String getParam(String name, String defaultStr) {
		final String foundStr= getParam(name);
		if(null == foundStr) {
			return defaultStr;
		}
		return foundStr;
	}

	public static String getParam(String name){
		try {
			return (String)((new InitialContext()).lookup("java:comp/env/"+name));
		} catch (NamingException e) {
			logger.error("getParam {} not found", name);
			return null;
		}
	}

	public static Integer getIntParam(String name, Integer defaultInt) {
		final Integer foundInt = getIntParam(name);

		if (foundInt == null) {
			return defaultInt;
		}

		return foundInt;
	}

	public static Integer getIntParam(String name){
		Object value = null;

		try {
			value = ((new InitialContext()).lookup("java:comp/env/"+name));
			return (Integer) value;
		}
		catch (Exception e) {
			logger.warn("Unexpected exception in getIntParam; name= {}; value= {}", new Object[] { name, value });
			return null;
		}
	}

	public static Boolean getBoolParam(String name, Boolean defaultBool) {
		final Boolean foundBool = getBoolParam(name);

		if (foundBool == null) {
			return defaultBool;
		}

		return foundBool;
	}

	public static Boolean getBoolParam(String name){
		Object value = null;

		try {
			value = ((new InitialContext()).lookup("java:comp/env/"+name));
			return (Boolean) value;
		}
		catch (Exception e) {
			logger.warn("Unexpected exception in getBoolParam; name= {}; value= {}", new Object[] { name, value });
			return null;
		}
	}
	
	public static String getCurrentAppEnvironmentURL() {
		String currentAppEnvURL = null;
		String currentAppEnvironment = getParam(ApplicationConstants.APPLICATION_SERVER_RUNNING_MODE, ApplicationConstants.DEV_ENVIRONMENT);
		if (currentAppEnvironment.equalsIgnoreCase(ApplicationConstants.PROD_ENVIRONMENT)) {
			currentAppEnvURL = ApplicationConstants.PROD_APPLICATION_URL;
		} else if (currentAppEnvironment.equalsIgnoreCase(ApplicationConstants.STAGING_ENVIRONMENT)) {
			currentAppEnvURL = ApplicationConstants.STAGING_APPLICATION_URL;
		} else if (currentAppEnvironment.equalsIgnoreCase(ApplicationConstants.DEV_ENVIRONMENT)) {
			currentAppEnvURL = ApplicationConstants.DEV_APPLICATION_URL;
		}
		return currentAppEnvURL;
	}
}
