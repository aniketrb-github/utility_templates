package com.utility.exception;

import com.utility.logger.ILogOnce;

/**
 * @author Aniket Bharsakale
 *
 */
public class AppException extends RuntimeException implements ILogOnce {

	private static final long serialVersionUID = 1L;

	public static final String UNHANDLED_EXCEPTION_TXT = "Unhandled Exception !!! ";

	private String message;
	private boolean logged;
	private final ApplicationCode ApplicationCode;

	private Object exceptionMetadata;

	public AppException(final ApplicationCode code) {
		this.ApplicationCode = code;
	}

	public AppException(final String message, final ApplicationCode code) {
		this.setMessage(message);
		this.ApplicationCode = code;
	}

	public AppException(final String message, final ApplicationCode code, final Throwable throwable) {
		super(throwable);
		this.ApplicationCode = code;
		this.setMessage(message);
	}

	private void setMessage(final String message) {
		this.message = message;

	}

	public boolean isLogged() {
		return this.logged;
	}

	public void setLogged(final boolean logged) {
		this.logged = logged;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	public ApplicationCode getApplicationCode() {
		return this.ApplicationCode;
	}

	public Object getExceptionMetadata() {
		return exceptionMetadata;
	}

	public void setExceptionMetadata(Object exceptionMetadata) {
		this.exceptionMetadata = exceptionMetadata;
	}

}
