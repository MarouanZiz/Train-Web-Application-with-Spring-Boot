package com.project.train_web_application.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="PASSENG_SEQ")
    @SequenceGenerator(name="PASSENG_SEQ",sequenceName="SEQ_PASSENG_ID", allocationSize = 1)
    private Long user_id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    private String email;
    private String password;
    private String username;
    private Long active;
    @OneToMany(mappedBy = "user")
    private Collection<Booking> bookings = new ArrayList<>();


    @ManyToMany(mappedBy = "users" )
    private Collection<Role> roles = new ArrayList<>();
}
