package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
