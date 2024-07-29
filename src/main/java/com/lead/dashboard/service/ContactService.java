package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Contact;

@Service
public interface ContactService {

	List<Contact> getAllContact();

}
