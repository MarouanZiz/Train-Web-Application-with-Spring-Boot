package com.project.train_web_application.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="PASSENG_SEQ")
    @SequenceGenerator(name="PASSENG_SEQ",sequenceName="SEQ_PASSENG_ID", allocationSize = 1)
    private Long user_id;
    @NotEmpty
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)

    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    private String password;

    private String username;
    @NotNull
    private Long active;


    @OneToMany(mappedBy = "user")
    private Collection<Booking> bookings = new ArrayList<>();


    @ManyToMany(mappedBy = "users",cascade=CascadeType.ALL )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Collection<Role> roles = new ArrayList<>();

}
