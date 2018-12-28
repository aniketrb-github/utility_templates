package com.utility.email;

/**
 * @author Aniket Bharsakale
 *
 */
public enum EMailType {
	TEXT("text/txt"), HTML("text/html");

	private String code;

	private EMailType(final String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
