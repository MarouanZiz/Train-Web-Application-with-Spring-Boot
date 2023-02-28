package com.project.train_web_application.services.UserService;

import com.project.train_web_application.Models.Role;
import com.project.train_web_application.Models.User;
import com.project.train_web_application.repositories.RoleRepository;
import com.project.train_web_application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User getUserByUserName(String username) {
       return userRepository.findUserByUsername(username);
    }

    @Override
    public User getUserByFirstName(String firstname) {
        return userRepository.findUserByFirstName(firstname);
    }

    @Override
    public void addUserToRole(String userName, String RoleName) {
        User user = userRepository.findUserByUsername(userName);
        Role role = roleRepository.findByRoleName(RoleName);
        System.out.println(role.getRoleName());
        System.out.println(user.getFirstName());
        role.getUsers().add(user);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
