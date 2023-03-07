package com.project.train_web_application.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PASSENG_SEQ")
    @SequenceGenerator(name="PASSENG_SEQ",sequenceName="SEQ_PASSENG_ID", allocationSize = 1)
    private Long passengerId;

    private String firstName;

    private String lastName;
    private String email;
    private Long tele;
    private String gendre;

}
