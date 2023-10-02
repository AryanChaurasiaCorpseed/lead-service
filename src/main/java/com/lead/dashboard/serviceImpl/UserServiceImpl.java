package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.UpdateUser;
import com.lead.dashboard.dto.UserDto;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(UserDto user) {
    	User u =new User();
//    	u.setId(user.getId());
    	u.setFirstName(user.getFirstName());
    	u.setLastName(user.getLastName());
    	u.setFullName(user.getFullName());
    	u.setEmail(user.getEmail());
    	u.setDesignation(user.getDesignation());
    	u.setDepartment(user.getDepartment());
    	u.setRole(user.getRole());
        return userRepo.save(u);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User existingUser,UpdateUser user) {
    	existingUser.setFirstName(user.getFirstName());
    	existingUser.setLastName(user.getLastName());
    	existingUser.setEmail(user.getEmail());
    	existingUser.setDesignation(user.getDesignation());
    	existingUser.setDepartment(user.getDepartment());
    	existingUser.setRole(user.getRole());
        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

	@Override
	public boolean isUserExistOrNot(Long userId) throws Exception {
		boolean flag=false;
		try {
		Optional<User> user = userRepo.findById(userId);
		if(user!=null && user.get()!=null) {
			flag=true;
		}
		}catch(Exception e) {
			throw new Exception("User Does not exist");
		}
		return flag;
	}
}
