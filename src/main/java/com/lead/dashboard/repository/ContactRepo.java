package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {

}
