package com.lead.dashboard.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Data
@Getter
@Setter
public class Client {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	String emails;
	String contactNo;
//	String type ;
//	Date SendDate;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="client_communication",joinColumns = {@JoinColumn(name="client_communication_id",referencedColumnName="id",nullable=true)},
	inverseJoinColumns = {@JoinColumn(name="communication_id"
			+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Communication>communication;
//	
//	@OneToOne
//	Mails mails;
//	
//	@OneToOne
//	Chats chat;
//	
//	@OneToOne
//	Sms sms;
//	
//	@OneToOne
//	Calls call;
//	
}
