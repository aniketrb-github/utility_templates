package com.services.email;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utility.constant.ApplicationConstants;
import com.utility.email.EMailType;
import com.utility.email.Mail;
import com.utility.email.MailHeader;
import com.utility.email.MailManager;
import com.utility.email.MailMessage;

@Service
public class EmailServiceImpl implements IEmailService {
	Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	private MailManager mailManager;

	@Autowired
	private VelocityEngine velocityEngine;

	public void setMailManager(MailManager mailManager) {
		this.mailManager = mailManager;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	@Override
	public MailMessage sendEmail(String templatePath, Map<String, Object> paramMap,
			Map<String, Object> paramContentMap) {
		Template template = velocityEngine.getTemplate(templatePath);
		VelocityContext velocityContext = new VelocityContext();
		for (Map.Entry<String, Object> entry : paramContentMap.entrySet()) {
			velocityContext.put(entry.getKey(), entry.getValue());
		}

		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);

		String to = null;
		String[] toList = null;
		if (paramMap.containsKey(ApplicationConstants.MAIL_TO)) {
			to = (String) paramMap.get(ApplicationConstants.MAIL_TO);
			toList = to.split(ApplicationConstants.SEPARATOR_COMMA);
		}

		String bcc = null;
		String[] bccList = null;

		if (paramMap.containsKey(ApplicationConstants.MAIL_BCC))
			bcc = (String) paramMap.get(ApplicationConstants.MAIL_BCC);

		if (null != bcc && !bcc.isEmpty()) {
			bccList = bcc.split(ApplicationConstants.SEPARATOR_COMMA);
		}

		String emailFrom = null;
		if (paramMap.containsKey(ApplicationConstants.MAIL_FROM))
			emailFrom = (String) paramMap.get(ApplicationConstants.MAIL_FROM);

		final MailMessage message = new MailMessage();
		message.setContent(stringWriter.toString());
		message.setMimeType(EMailType.HTML);

		final MailHeader mailHeader = new MailHeader();
		if (null != emailFrom)
			mailHeader.setFrom(emailFrom);

		if (null != toList)
			mailHeader.setTo(toList);

		if (null != bccList) {
			mailHeader.setCc(bccList);
		}

		if (paramMap.containsKey(ApplicationConstants.MAIL_SUBJECT))
			mailHeader.setSubject((String) paramMap.get(ApplicationConstants.MAIL_SUBJECT));

		/*
		 * InlineBinaryContent inlineBinaryContent = new InlineBinaryContent();
		 * inlineBinaryContent.addContent("logo", "logo.png");
		 */

		final Mail mail = new Mail();
		mail.setMailMessage(message);
		mail.setMailHeader(mailHeader);
		/* mail.setBinaryContent(inlineBinaryContent); */
		mailManager.sendMail(mail);
		LOGGER.info("Email sent successfully!!!");
		return message;
	}

}
