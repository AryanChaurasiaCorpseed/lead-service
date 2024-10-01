package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Contact;
import com.lead.dashboard.dto.CreateContactDto;

@Service
public interface ContactService {

	List<Contact> getAllContact();


	Contact createContact(CreateContactDto createContactDto);


	Contact getContactById(Long id);

}
