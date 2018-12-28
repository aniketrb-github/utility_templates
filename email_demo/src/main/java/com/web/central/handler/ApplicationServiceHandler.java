package com.web.central.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dao.password.reset.token.EmailVO;
import com.services.user.IUserServices;
import com.utility.config.UnauthenticatedRestAction;
import com.utility.exception.AppException;
import com.utility.exception.ApplicationCode;
import com.utility.logger.AppLogger;
import com.utility.message.LocaleMessageUtility;
import com.utility.vo.response.RestResponse;

/**
 * @author Aniket Bharsakale
 *
 */
public class ApplicationServiceHandler implements IApplicationServiceHandler {

	private final ThreadLocal<AppLogger> appLogger = new ThreadLocal<>();

	@Autowired
	private IUserServices userServices;

	@Autowired
	private LocaleMessageUtility localeMessageUtility;

	public ResponseEntity<RestResponse> process(UnauthenticatedRestAction action, String sessionId, Object param,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

		AppLogger logger = getAppLogger(true);
		HttpStatus status = HttpStatus.OK;
		RestResponse response = null;

		try {
			switch (action) {
			case SEND_EMAIL:
				EmailVO emailVO = null;
				if (param instanceof EmailVO) {
					emailVO = (EmailVO) param;
				}
				response = userServices.sendEmail(emailVO);
				break;

			default:
				response = new RestResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
						ApplicationCode.UNEXPECTED_ERROR.getCodeId(),
						localeMessageUtility.getErrorMessage(ApplicationCode.UNEXPECTED_ERROR.getCodeId()));
				break;
			}
		} catch (AppException exception) {

			logger.warn(exception.getMessage());
			switch (exception.getApplicationCode()) {
			case INVALID_EMAIL:
				status = HttpStatus.BAD_REQUEST;
				response = new RestResponse(Response.Status.BAD_REQUEST.getStatusCode(),
						ApplicationCode.INVALID_EMAIL.getCodeId(),
						localeMessageUtility.getErrorMessage(ApplicationCode.INVALID_EMAIL.getCodeId()));
				break;
			default:
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				response = new RestResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), ApplicationCode.UNEXPECTED_ERROR.getCodeId(),
						localeMessageUtility.getErrorMessage(ApplicationCode.UNEXPECTED_ERROR.getCodeId()));
				break;
			}
		} catch (Exception e) {
			logger.error("Unexpected exception", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response = new RestResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), ApplicationCode.UNEXPECTED_ERROR.getCodeId(),
					localeMessageUtility.getErrorMessage(ApplicationCode.UNEXPECTED_ERROR.getCodeId()));
		}

		return ResponseEntity.status(status).body(response);
	}

	/**
	 * Returns a AppLogger instance for this thread
	 * 
	 * @return
	 */
	private AppLogger getAppLogger(boolean resetMdc) {

		AppLogger appLogger = this.appLogger.get();

		if (null == appLogger) {
			appLogger = new AppLogger(ApplicationServiceHandler.class);
			this.appLogger.set(appLogger);
		}

		if (resetMdc) {
			appLogger.clearMdc();
			appLogger.putMdc("api", "email_app");
		}

		return appLogger;
	}

}
