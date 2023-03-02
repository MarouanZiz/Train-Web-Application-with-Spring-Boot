package com.project.train_web_application.repositories;

import com.project.train_web_application.Models.Role;
import com.project.train_web_application.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByRoleName(String name);
    public Role findByRoleId(Long roleId);

}
