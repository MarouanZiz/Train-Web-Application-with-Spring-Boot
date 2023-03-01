package com.project.train_web_application.controllers;

import com.project.train_web_application.Models.Role;
import com.project.train_web_application.Models.User;
import com.project.train_web_application.services.roleService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/")
    public String test(){
        return "home";
    }

    @GetMapping("/admin/formAddUser")
    public String formAddUser(Model model){
        model.addAttribute("user",new User());
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("allroles",roles);
        return "formAddUser";
    }


}
