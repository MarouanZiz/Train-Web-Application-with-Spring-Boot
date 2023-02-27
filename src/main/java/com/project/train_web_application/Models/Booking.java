package com.project.train_web_application.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor @Data @NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="PASSENG_SEQ")
    @SequenceGenerator(name="PASSENG_SEQ",sequenceName="SEQ_PASSENG_ID", allocationSize = 1)
    private Long booking_id;
    @ManyToOne
    @JoinColumn(name = "ref_user", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "ref_passenger", nullable = false)
    private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "ref_train", nullable = false)
    private Train train;
    private java.sql.Timestamp booking_date;
    @OneToOne(mappedBy = "booking")
    private Payment payment;

}
