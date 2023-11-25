package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

}
