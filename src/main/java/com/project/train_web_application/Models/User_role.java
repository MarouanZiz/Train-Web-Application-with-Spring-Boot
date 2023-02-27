package com.project.train_web_application.Models;

import com.project.train_web_application.Models.compositeKey.User_role_composite;

import javax.persistence.*;

@Entity
@IdClass(User_role_composite.class)
public class User_role {
    @Id
    @ManyToOne
    @JoinColumn(name = "ref_user", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name="ref_role",nullable = false)
    private Role role;
}
