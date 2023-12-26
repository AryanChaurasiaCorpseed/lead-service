package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Organization.UserManagment;

@Repository
public interface UserManagmentRepo extends JpaRepository<UserManagment,Long>{

}