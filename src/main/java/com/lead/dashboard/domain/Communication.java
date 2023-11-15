package com.lead.dashboard.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "communication")
//@Data
//@Getter
//@Setter
public class Communication {
	// Type : - mail,Remark,Chat
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    private Long id ;
	private String type ;
	private Date SendDate;	
	private String mailTo;
	private String mailCc;
	private String subject;
	private String message ; 
	private Boolean isDeleted ;
	private Boolean isSendBy;
	private Date chatTime;
	private Boolean isView=false;
	@ManyToOne
	private User createdBy;
    public Date getChatTime() {
		return chatTime;
	}
        
	public Boolean getIsView() {
		return isView;
	}

	public void setIsView(Boolean isView) {
		this.isView = isView;
	}

	public void setChatTime(Date chatTime) {
		this.chatTime = chatTime;
	}
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
	public Boolean isDeleted() {
		return isDeleted;
	}
	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isSendBy() {
		return isSendBy;
	}

	public void setSendBy(boolean isSendBy) {
		this.isSendBy = isSendBy;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}


	
	
//	@OneToOne
//	Mails mails;
//
//	@OneToOne
//	Chats chat;
//
//	@OneToOne
//	User user;
//
//	@OneToOne
//	Sms sms;
//
//	@OneToOne
//	Calls call;
	


	

}
