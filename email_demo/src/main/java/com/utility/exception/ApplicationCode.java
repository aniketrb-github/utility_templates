package com.utility.exception;

import java.util.NoSuchElementException;

/**
 * @author Aniket Bharsakale
 *
 */
public enum ApplicationCode {
	UNEXPECTED_ERROR("response.error.unexpected"),
	STATUS_OK("response.ok"),
	INVALID_EMAIL("response.invalid.email");

	private final String codeId;

	private ApplicationCode(final String codeId) {
		this.codeId = codeId;
	}

	public String getCodeId() {
		return this.codeId;
	}
	
	/**
	 * Converts an int value into an ErrorCode
	 *
	 * @param errorCode
	 * @return {@link ApplicationCode}
	 */
	public static ApplicationCode getExceptionCode(final String errorCode) {

		ApplicationCode eErrorCode = null;
		for (final ApplicationCode status : ApplicationCode.values()) {
			if (status.getCodeId().equals(errorCode)){
				eErrorCode = status;
				break;
			}
		}
		if (null == eErrorCode) {
			throw new NoSuchElementException("The received code " + errorCode + " is not valid !!!");
		} else {
			return eErrorCode;
		}
	}
}
