package com.lead.dashboard.service;


import com.lead.dashboard.domain.User;

import java.util.List;

public interface UserService
{
    User createUser(User user);
    User getUserById(Long id);
    User updateUser(User user);
    void deleteUser(Long id);

    List<User> getAllUsers();
}
