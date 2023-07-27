package com.lead.dashboard.repository;

import com.lead.dashboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long>
{

}
