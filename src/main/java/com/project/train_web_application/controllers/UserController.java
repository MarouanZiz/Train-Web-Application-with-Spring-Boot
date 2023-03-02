package com.project.train_web_application.controllers;

import com.project.train_web_application.Models.Role;
import com.project.train_web_application.Models.User;
import com.project.train_web_application.services.UserService.UserService;
import com.project.train_web_application.services.roleService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String test(){
        return "home";
    }

    @GetMapping("/admin/formAddUser")
    public String formAddUser(Model model,
                              RedirectAttributes redirectAttributes,
                              HttpSession session){
        model.addAttribute("user",new User());
        List<Role> roles = roleService.getAllRoles();
//        model.addAttribute("allroles",roles);
        model.addAttribute("allroles",roles);
        session.setAttribute("allroles",roles);
        return "formAddUser";
    }

    @PostMapping(path ="/admin/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           @RequestParam(value = "roles", required = false) Long[] roles,
                           @RequestParam(value = "active", required = false) Long active,
                           RedirectAttributes redirectAttributes,
                           Model model,
                           HttpSession session){



//        model.addAttribute("allroles",roleService.getAllRoles());
        model.addAttribute("allroles",(List)session.getAttribute("allroles"));
        if(bindingResult.hasErrors()) return "formAddUser";

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String username_gene = user.getFirstName()+"_"+user.getLastName();
        System.out.println("active : "+active);

        String pass_gen = passwordEncoder.encode(user.getFirstName()+"_"+user.getLastName());
        user.setActive(active);
        user.setPassword(pass_gen);
        user.setUsername(username_gene);

        if(userService.findByEmailContainingIgnoreCase(user.getEmail()) != null){

            redirectAttributes.addFlashAttribute("msg_add",false);

            return "redirect:/admin/formAddUser";
        }

        userService.saveUser(user);

//        User userSaved = userService.saveUser(user);
        if(roles != null){

            for(int i=0;i<roles.length;i++){
                Role role = roleService.getRoleById(roles[i]);
                userService.addUserToRole(user.getUsername(),role.getRoleName());

            }


        }

        redirectAttributes.addFlashAttribute("msg_add",true);
        return "redirect:/admin/formAddUser";
    }


}
