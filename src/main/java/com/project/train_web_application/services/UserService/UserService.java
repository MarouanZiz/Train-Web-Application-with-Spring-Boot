package com.project.train_web_application.services.UserService;

import com.project.train_web_application.Models.Role;
import com.project.train_web_application.Models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserByUserName(String username);
    User getUserByFirstName(String firstname);
    public void addUserToRole(String userName,String RoleName);
    public User saveUser(User user);
    public Role saveRole(Role role);


}
