package com.dao.password.reset.token;

/**
 * @author Aniket Bharsakale
 *
 */
public class EmailVO {
	private String emailId;
	private String recaptchaResponse;
	private boolean isIngestionRequest;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getRecaptchaResponse() {
		return recaptchaResponse;
	}

	public void setRecaptchaResponse(String recaptchaResponse) {
		this.recaptchaResponse = recaptchaResponse;
	}

	@Override
	public String toString() {
		return "EmailVO [email=" + emailId + ", isIngestionRequest=" + isIngestionRequest + ", reCaptchResponse="
				+ recaptchaResponse + "]";
	}

	public boolean isIngestionRequest() {
		return isIngestionRequest;
	}

	public void setIngestionRequest(boolean isIngestionRequest) {
		this.isIngestionRequest = isIngestionRequest;
	}
}
