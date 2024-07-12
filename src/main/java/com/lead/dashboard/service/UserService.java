package com.lead.dashboard.service;


import com.lead.dashboard.domain.CreateUserDto;
import com.lead.dashboard.domain.UpdateUserByHr;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.NewSignupRequest;
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
	User updateUserData(User existingUser, UpdateUser user);
	User editUserByHr(User existingUser, UpdateUserByHr user);
	User createUserByHr(CreateUserDto createUserDto);
	List<User> getUserForManager(Long id);
	Boolean approvedUserByManager(Long currentUserId, Long userId, String status);
	Boolean autoActive(Long userId, Long currentUser);
	List<Long> getUserManager(Long id);
	Boolean updateLockerCount(Long id, int count, Long currentUserId);
	Boolean updateProfile(Long userId, String profilePic);
	String getProfile(Long userId);
	Boolean isManagerApproved(Long userId);

}
