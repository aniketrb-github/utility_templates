package com.services.email;

import java.util.Map;

import com.utility.email.MailMessage;

/**
 * @author Aniket Bharsakale
 *
 */
public interface IEmailService {

	MailMessage sendEmail(String templatePath, Map<String, Object> paramMap, Map<String, Object> paramContentMap);

}
