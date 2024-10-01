package com.lead.dashboard.controller.userController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.CompanyForm;
import com.lead.dashboard.domain.Contact;
import com.lead.dashboard.dto.CreateContactDto;
import com.lead.dashboard.dto.CreateFormDto;
import com.lead.dashboard.service.ContactService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class ContactController {

	@Autowired
	ContactService contactService;
	
	@GetMapping(UrlsMapping.GET_ALL_CONTACT)
	public List<Contact> getAllContact()
	{	
		List<Contact>contactList=contactService.getAllContact();
		return contactList;
	}
	
	@PostMapping(UrlsMapping.CREATE_CONTACT)
	public Contact createContact(@RequestBody CreateContactDto createContactDto)
	{	
		Contact contact=contactService.createContact(createContactDto);
		return contact;
	}
	
	@GetMapping(UrlsMapping.GET_CONTACT_BY_ID)
	public Contact getContactById(@RequestParam Long id)
	{	
		Contact contact=contactService.getContactById(id);
		return contact;
	}
	
}
