package com.project.train_web_application.Models.compositeKey;

import com.project.train_web_application.Models.Role;
import com.project.train_web_application.Models.User;

import java.io.Serializable;

public class User_role_composite implements Serializable {
    private User user;
    private Role role;

}
