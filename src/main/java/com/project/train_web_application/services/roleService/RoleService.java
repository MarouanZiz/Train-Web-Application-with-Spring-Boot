package com.project.train_web_application.services.roleService;

import com.project.train_web_application.Models.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    public Role getRoleByRoleName(String roleName);
    public List<Role> getAllRoles();
}
