package com.utility.constant;

public class ApplicationConstants {

	public static final String SEPARATOR_COMMA = ",";
	
	public static final String APPLICATION_SESSIONID = "authsessionId";

	public static final String EMAIL_ID_FROM = "aniketbharsakale@gmail.com";
	public static final String EMAIL_ID_BCC = "aniketbharsakale@gmail.com";
	public static final String MAIL_TO = "email_to";
	public static final String MAIL_FROM = "email_from";
	public static final String MAIL_SUBJECT = "email_subject";
	public static final String MAIL_BCC = "email_bcc";
	public static final String SUCCESS_MAIL = "Success Mail";
	public static final String FAILURE_MAIL = "Failure Mail";
	
	public static final String RESET_PWD_WELCOME_EMAIL_TEMPLATE = "reset_pwd_welcome_user_email_template.vm";
	public static final String RESET_PWD_REGULAR_EMAIL_TEMPLATE = "reset_pwd_existing_user_email_template.vm";
	
	public static final String RESET_PASSWORD_SUBJECT_LINE_NEW_USER = "Welcome to Email Sending Demo app";
	public static final String FORGOT_PASSWORD_SUBJECT_LINE_EXISTING_USER = "Email Sending Demo app Reset Password";
	
	public static final String APPLICATION_SERVER_RUNNING_MODE = "server-running-mode";
	public static final String PROD_ENVIRONMENT = "prod";
	public static final String STAGING_ENVIRONMENT = "stage";
	public static final String DEV_ENVIRONMENT = "dev";
	
	public static final String PROD_APPLICATION_URL = "app-prod-url.com";
	public static final String STAGING_APPLICATION_URL = "app-stage-url.com";
	public static final String DEV_APPLICATION_URL = "app-dev-url.com";
	
	public static final String PASSWORD_RESET_TOKEN_EXPIRATION_TIME = "resetPasswordTokenExpirationTime";
	public static final String DEFAULT_PASSWORD_RESET_TOKEN_EXPIRATION_TIME = "1440"; // 24 hours = 1440 minutes
	public static final Integer ONE_MIN_MILISECONDS_VALUE = 60000;

	public static final String INPUT_FIELD_EMAIL = "email";
	public static final Integer ONE_HOUR_IN_MINS = 60;
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	
}
