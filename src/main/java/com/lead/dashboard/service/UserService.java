package com.lead.dashboard.service;


import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.UpdateUser;
import com.lead.dashboard.dto.UserDto;

import java.util.List;

public interface UserService
{
    User getUserById(Long id);
    User updateUser(User existingUser,UpdateUser user);
    void deleteUser(Long id);

    List<User> getAllUsers();
	User createUser(UserDto user);
	boolean isUserExistOrNot(Long userId) throws Exception;
//	User createUserByEmail(String email, String role, Long userId);
	
	
	public User createUserByEmail(String userName, String email, String role, Long userId, String designation);
}
