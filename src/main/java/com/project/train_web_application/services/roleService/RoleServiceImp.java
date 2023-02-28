package com.project.train_web_application.services.roleService;

import com.project.train_web_application.Models.Role;
import com.project.train_web_application.Models.User;
import com.project.train_web_application.repositories.RoleRepository;
import com.project.train_web_application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImp implements RoleService{
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;


    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
