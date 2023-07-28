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
	
	@OneToOne
	Mails mails;
	
	@OneToOne
	Chats chat;
	
	@OneToOne
	Sms sms;
	
	@OneToOne
	Calls call;

}
