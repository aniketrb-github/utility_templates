package com.utility.email;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @author Aniket Bharsakale
 *
 */
@Component
public class MailManager {

	@Autowired
	private JavaMailSender mailSender;
	
	static final Logger LOGGER = LoggerFactory.getLogger(MailManager.class);
	
	/**
	 * Method is responsible for setting up the {@link MimeMessage} instance
	 * 
	 * @param mail
	 * @param helper
	 * @param processAttachments
	 * @return
	 * @throws MessagingException
	 */
	private MimeMessage setupMail(final Mail mail, final boolean processAttachments) throws MessagingException {
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		if (processAttachments) {
			helper = new MimeMessageHelper(mimeMessage, true);
		} else {
			// as images are sent embedded we need second param true here too
			if ((null != mail.getBinaryContent()) && mail.getBinaryContent().isEmbeddedContentPresent()) {
				helper = new MimeMessageHelper(mimeMessage, true);
			} else {
				helper = new MimeMessageHelper(mimeMessage, false);
			}
		}
		final MailHeader mailHeader = mail.getMailHeader();
		final MailMessage mailMessage = mail.getMailMessage();
		LOGGER.debug("setupMail: setting mailheader -From");
		LOGGER.trace("setupMail: from value set as {}", mailHeader.getFrom());
		helper.setFrom(mailHeader.getFrom());
		
		LOGGER.debug("setupMail: setting mailheader -To");
		LOGGER.trace("setupMail: to value set as {}", mailHeader.getTo());
		helper.setTo(mailHeader.getTo());

		if ((null != mailHeader.getCc()) && (0 != mailHeader.getCc().length)) {
			LOGGER.debug("setupMail: setting mailheader -CC");
			LOGGER.trace("setupMail: cc value set as {}", mailHeader.getCc());
			helper.setCc(mailHeader.getCc());
		}
		if ((null != mailHeader.getBcc()) && (0 != mailHeader.getBcc().length)) {
			LOGGER.debug("setupMail: setting mailheader -BCC");
			LOGGER.trace("setupMail: bcc value set as {}", mailHeader.getBcc());
			helper.setBcc(mailHeader.getBcc());
		}
		LOGGER.debug("setupMail: setting mailheader -subject");
		LOGGER.trace("setupMail: subject value set as {}", mailHeader.getSubject());
		helper.setSubject(mailHeader.getSubject());

		LOGGER.debug("setupMail: setting mail content");
		LOGGER.trace("setupMail: mail content value set as {}", mailMessage.getContent());
		helper.setText(mailMessage.getContent(), mailMessage.getMimeType() == EMailType.HTML);

		/*final InlineBinaryContent binaryContent = mail.getBinaryContent();
		if (null != binaryContent) {
			logger.debug("setupMail: adding inline/embedded content");
			this.addInline(helper, binaryContent);
		}*/

		// attachments
		if (processAttachments) {
			final MailAttachments mailAttachments = mail.getAttachments();
			// helper.addAttachment("File.jpg", new
			// ByteArrayResource(mailAttachments.getAttachment("Chrysanthemum.jpg")));

			if ((null != mailAttachments) && !mailAttachments.isEmpty()) {
				LOGGER.debug("setupMail: setting attachments");
				final Set<String> attachmentKeys = mailAttachments.getAttachmentsNames();
				for (final String attachmentKey : attachmentKeys) {
					final String contentType = mailAttachments.getContentType(attachmentKey);
					if (null == contentType) {
						helper.addAttachment(attachmentKey,
								new ByteArrayResource(mailAttachments.getAttachment(attachmentKey)));
					} else {
						helper.addAttachment(attachmentKey,
								new ByteArrayResource(mailAttachments.getAttachment(attachmentKey)), contentType);
					}
				}
				LOGGER.debug("setupMail: set {} attachment(s)", attachmentKeys.size());
			} else {
				LOGGER.debug("setupMail: no attachments to set");
			}
		}
		return mimeMessage;
	}
	
	/**
	 * Method sends the mail through the system. No attachments processed
	 * 
	 * @param mail
	 * @throws HerdNetException
	 */
	public void sendMail(final Mail mail) {
		MimeMessage message = null;
		try {
			message = this.setupMail(mail, false);
		} catch (final MessagingException e) {
			LOGGER.error("sendMail: Failure creating mail", e);
			throw new RuntimeException("Failure creating mail instance", e);
		}
		try {
			LOGGER.debug("sendMail: Sending mail  ");
			this.mailSender.send(message);
		} catch (final MailException ex) {
			LOGGER.error("sendMail: Failure sending mail", ex);
			throw new RuntimeException("Failure sending mail", ex);
		}
		LOGGER.debug("sendMail:  mail sent successfully ");
	}
}
