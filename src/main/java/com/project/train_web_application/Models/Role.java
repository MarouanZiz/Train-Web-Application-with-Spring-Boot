package com.project.train_web_application.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="PASSENG_SEQ")
    @SequenceGenerator(name="PASSENG_SEQ",sequenceName="SEQ_PASSENG_ID", allocationSize = 1)
    @Column(name = "role_id", nullable = false)
    private Long roleId;
    @Column(name = "role_name", nullable = false)
    private String roleName;


    @ManyToMany
    private Collection<User> users = new ArrayList<>();
}
