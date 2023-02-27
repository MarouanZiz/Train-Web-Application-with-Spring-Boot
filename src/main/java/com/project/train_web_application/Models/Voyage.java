package com.project.train_web_application.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Voyage {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="PASSENG_SEQ")
    @SequenceGenerator(name="PASSENG_SEQ",sequenceName="SEQ_PASSENG_ID", allocationSize = 1)
    private Long voyage_id;
    @ManyToOne
    @JoinColumn(name="ref_train")
    private Train train;
    @ManyToOne
    @JoinColumn(name="ref_origin_station")
    private Station origin_station;
    @ManyToOne
    @JoinColumn(name="ref_desti_station")
    private Station desti_station;

    private Timestamp deparature_date;
    private Timestamp arrival_date;


}
