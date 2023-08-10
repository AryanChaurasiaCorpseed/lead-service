package com.lead.dashboard.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "communication")
@Data
@Getter
@Setter
public class Communication {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	String type ;
	Date SendDate;	
	String mailTo;
	String mailCc;
	String subject;
	String message ; 
	boolean isDeleted ;
	@OneToOne
	Mails mails;
	Date chatTime;
	 
//	@OneToOne
//	Chats chat;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the sendDate
	 */
	public Date getSendDate() {
		return SendDate;
	}
	/**
	 * @param sendDate the sendDate to set
	 */
	public void setSendDate(Date sendDate) {
		SendDate = sendDate;
	}
	/**
	 * @return the mailTo
	 */
	public String getMailTo() {
		return mailTo;
	}
	/**
	 * @param mailTo the mailTo to set
	 */
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	/**
	 * @return the mailCc
	 */
	public String getMailCc() {
		return mailCc;
	}
	/**
	 * @param mailCc the mailCc to set
	 */
	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}
	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@OneToOne
	Mails mails;

	@OneToOne
	Chats chat;

	@OneToOne
	User user;

	@OneToOne
	Sms sms;

	@OneToOne
	Calls call;
	


	

}
