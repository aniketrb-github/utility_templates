package com.utility.email;

public class MailMessage {

	private String content;
	private EMailType mimeType;
	private String templateName;

	public String getContent() {
		return this.content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public EMailType getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(final EMailType mimeType) {
		this.mimeType = mimeType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}
