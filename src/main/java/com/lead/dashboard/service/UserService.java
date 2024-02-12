package com.lead.dashboard.service;


import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.UpdateUser;
import com.lead.dashboard.dto.UserDto;

import java.util.List;

public interface UserService
{
    User getUserById(Long id);
    User updateUser(User existingUser,UpdateUser user);
    Boolean deleteUser(Long id);

    List<User> getAllUsers();
	User createUser(UserDto user);
	boolean isUserExistOrNot(Long userId) throws Exception;
//	User createUserByEmail(String email, String role, Long userId);
	
	
	public User createUserByEmail(String userName, String email, List<String> role, Long userId, String designation,String department);
	List<User> getAllUserByHierarchy(Long userId);
//	public User createUserByEmail(String userName, String email, String role, Long userId, String designation);
	Boolean createUser(Long id);

}
