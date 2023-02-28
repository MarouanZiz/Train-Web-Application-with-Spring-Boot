package com.project.train_web_application.repositories;

import com.project.train_web_application.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    public User findUserByUsername(String username);

    User findUserByFirstName(String firstname);
}
