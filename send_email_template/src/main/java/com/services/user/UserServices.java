package com.services.user;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.password.reset.token.EmailVO;
import com.services.email.IEmailService;
import com.utility.constant.ApplicationConstants;
import com.utility.exception.AppException;
import com.utility.exception.ApplicationCode;
import com.utility.message.LocaleMessageUtility;
import com.utility.message.TomcatUtils;
import com.utility.vo.response.RestResponse;

public class UserServices implements IUserServices {
	@Autowired
	private LocaleMessageUtility localeMessageUtility;
	
	@Autowired
	private IEmailService emailService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public RestResponse sendResetPasswordLink(EmailVO emailVO) throws AppException {
		RestResponse response = new RestResponse(Response.Status.OK.getStatusCode(),
				ApplicationCode.STATUS_OK.getCodeId(),
				localeMessageUtility.getMessage(ApplicationCode.STATUS_OK.getCodeId()));

		if (StringUtils.isNotBlank(emailVO.getEmailId())) {

			Calendar tokenCreationDate = Calendar.getInstance();
			tokenCreationDate.setTime(new Date());

			String emailTemplateName = null;
			Map<String, Object> paramContentMap = this.generateResetPasswordURL(tokenCreationDate.getTime(),
					emailVO.getEmailId());

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(ApplicationConstants.MAIL_FROM, ApplicationConstants.EMAIL_ID_FROM);
			paramMap.put(ApplicationConstants.MAIL_TO, emailVO.getEmailId());

			//if (null == user.getPassword()) {
				emailTemplateName = ApplicationConstants.RESET_PWD_WELCOME_EMAIL_TEMPLATE;
				paramMap.put(ApplicationConstants.MAIL_SUBJECT,
						ApplicationConstants.RESET_PASSWORD_SUBJECT_LINE_NEW_USER);
			/*} else {
				emailTemplateName = ApplicationConstants.RESET_PWD_REGULAR_EMAIL_TEMPLATE;
				paramMap.put(ApplicationConstants.MAIL_SUBJECT,
						ApplicationConstants.FORGOT_PASSWORD_SUBJECT_LINE_EXISTING_USER);
			}*/

			emailService.sendEmail(emailTemplateName, paramMap, paramContentMap);

		} else {
			throw new AppException(localeMessageUtility.getMessage(ApplicationCode.INVALID_EMAIL.getCodeId()),
					ApplicationCode.INVALID_EMAIL);
		}

		return response;
	}
	
	/**
	 * Generates Token if not present & stores in DB & generates Reset Password URL
	 * @return reset password URL
	 */
	public Map<String, Object> generateResetPasswordURL(Date creationDate, String email) {
		Map<String, Object> resetPasswordData = new HashMap<String, Object>();
		
		int configResetPwdExpiryTime = 0;
		
		String configExpiryTime = TomcatUtils.getParam(ApplicationConstants.PASSWORD_RESET_TOKEN_EXPIRATION_TIME); 
		
		if(StringUtils.isNotBlank(configExpiryTime)) {
			configResetPwdExpiryTime = Integer.parseInt(configExpiryTime);
		} else {
			configResetPwdExpiryTime = Integer.parseInt(ApplicationConstants.DEFAULT_PASSWORD_RESET_TOKEN_EXPIRATION_TIME);
		}
		
		Calendar tokenExpirationDate = Calendar.getInstance();
		tokenExpirationDate.setTime(new Date());
		tokenExpirationDate.add(Calendar.MINUTE, configResetPwdExpiryTime);

		Integer expirationTimeInHours = configResetPwdExpiryTime / ApplicationConstants.ONE_HOUR_IN_MINS;
		resetPasswordData.put(ApplicationConstants.INPUT_FIELD_EMAIL, email);
		resetPasswordData.put(ApplicationConstants.PASSWORD_RESET_TOKEN_EXPIRATION_TIME, expirationTimeInHours);
		resetPasswordData.put(ApplicationConstants.FIRST_NAME, StringUtils.capitalize("Tony"));
		resetPasswordData.put(ApplicationConstants.LAST_NAME, StringUtils.capitalize("Stark"));
		
		return resetPasswordData;
	}

}
