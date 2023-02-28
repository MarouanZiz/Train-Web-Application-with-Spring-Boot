package com.project.train_web_application.services.roleService;

import com.project.train_web_application.Models.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    public Role getRoleByRoleName(String roleName);
}
