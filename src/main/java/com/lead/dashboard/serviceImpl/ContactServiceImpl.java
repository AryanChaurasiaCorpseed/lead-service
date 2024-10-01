package com.lead.dashboard.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Contact;
import com.lead.dashboard.dto.CreateContactDto;
import com.lead.dashboard.repository.ContactRepo;
import com.lead.dashboard.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	ContactRepo contactRepo;
	@Override
	public List<Contact> getAllContact() {
		// TODO Auto-generated method stub
		List<Contact> contactList = contactRepo.findAll();
		return contactList;
	}
	@Override
	public Contact createContact(CreateContactDto createContactDto) {
		Contact contact = new Contact();
		contact.setName(createContactDto.getName());
		contact.setEmails(createContactDto.getEmails());
		contact.setWhatsappNo(createContactDto.getWhatsappNo());
		contact.setContactNo(createContactDto.getContactNo());
		contactRepo.save(contact);
		return contact;
	}
	@Override
	public Contact getContactById(Long id) {
		Contact contact = contactRepo.findById(id).get();
		return contact;
	}

}
