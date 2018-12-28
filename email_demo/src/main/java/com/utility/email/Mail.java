package com.utility.email;

/**
 * @author Aniket Bharsakale
 *
 */
public class Mail {

	private MailHeader mailHeader;
	private MailMessage mailMessage;
	private MailAttachments attachments;

	private InlineBinaryContent binaryContent;

	public MailHeader getMailHeader() {
		return this.mailHeader;
	}

	public void setMailHeader(final MailHeader mailHeader) {
		this.mailHeader = mailHeader;
	}

	public MailMessage getMailMessage() {
		return this.mailMessage;
	}

	public void setMailMessage(final MailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public MailAttachments getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final MailAttachments attachments) {
		this.attachments = attachments;
	}

	public InlineBinaryContent getBinaryContent() {
		return this.binaryContent;
	}

	public void setBinaryContent(final InlineBinaryContent binaryContent) {
		this.binaryContent = binaryContent;
	}

}