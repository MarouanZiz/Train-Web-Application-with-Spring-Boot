package com.project.train_web_application.services.roleService;

import com.project.train_web_application.Models.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {

    public Role getRoleByRoleName(String roleName);
    public List<Role> getAllRoles();
    public Role getRoleById(Long roleId);
}
