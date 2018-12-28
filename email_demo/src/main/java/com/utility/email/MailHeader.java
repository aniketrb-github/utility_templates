package com.utility.email;

public class MailHeader {
	private static final String SEPARATOR = ",";

	private String[] to;
	private String[] bcc;
	private String[] cc;
	private String subject;
	private String from;

	public String[] getTo() {
		return this.to;
	}

	public void setTo(final String[] to) {
		this.to = to;
	}

	public void setTo(final String to) {
		this.to = to.split(SEPARATOR);
	}

	public String[] getBcc() {
		return this.bcc;
	}

	public void setBcc(final String[] bcc) {
		this.bcc = bcc;
	}

	public void setBcc(final String bcc) {
		this.bcc = bcc.split(SEPARATOR);
	}

	public String[] getCc() {
		return this.cc;
	}

	public void setCc(final String[] cc) {
		this.cc = cc;
	}

	public void setCc(final String cc) {
		this.cc = cc.split(SEPARATOR);
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return this.from;
	}

	public void setFrom(final String from) {
		this.from = from;
	}
}
