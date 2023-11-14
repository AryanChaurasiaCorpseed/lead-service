package com.lead.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Date;


@Entity
@Table(name = "mails")
@Data
public class Mails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;

	@Nullable
	private String emailFrom;

	@Nullable
	private String replyTo;

	@Nullable
	private String[] emailTo;

	@Nullable
	private String[] ccPersons;

	@Nullable
	private String[] bccPersons;

	@Nullable
	private Date sentDate;

	@Nullable
	private String emailSubject;

	@Nullable
	private String emailText;

	private boolean isDeleted;


// ==============================================Getter+++++++++++++++++++++++++++++++++++++++++++

	public Long getId() {
		return id;
	}

	@Nullable
	public String getEmailFrom() {
		return emailFrom;
	}

	@Nullable
	public String getReplyTo() {
		return replyTo;
	}

	@Nullable
	public String[] getEmailTo() {
		return emailTo;
	}

	@Nullable
	public String[] getCcPersons() {
		return ccPersons;
	}

	@Nullable
	public String[] getBccPersons() {
		return bccPersons;
	}

	@Nullable
	public Date getSentDate() {
		return sentDate;
	}

	@Nullable
	public String getEmailSubject() {
		return emailSubject;
	}

	@Nullable
	public String getEmailText() {
		return emailText;
	}

	public boolean isDeleted() {
		return isDeleted;
	}


	// ==============================================Setter+++++++++++++++++++++++++++++++++++++++++++


	public void setId(Long id) {
		this.id = id;
	}

	public void setEmailFrom(@Nullable String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public void setReplyTo(@Nullable String replyTo) {
		this.replyTo = replyTo;
	}

	public void setEmailTo(@Nullable String[] emailTo) {
		this.emailTo = emailTo;
	}

	public void setCcPersons(@Nullable String[] ccPersons) {
		this.ccPersons = ccPersons;
	}

	public void setBccPersons(@Nullable String[] bccPersons) {
		this.bccPersons = bccPersons;
	}

	public void setSentDate(@Nullable Date sentDate) {
		this.sentDate = sentDate;
	}

	public void setEmailSubject(@Nullable String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public void setEmailText(@Nullable String emailText) {
		this.emailText = emailText;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}
}
