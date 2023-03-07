package com.project.train_web_application.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Voyage {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="PASSENG_SEQ")
    @SequenceGenerator(name="PASSENG_SEQ",sequenceName="SEQ_PASSENG_ID", allocationSize = 1)
    @JoinColumn(name="voyage_id")
    private Long voyageId;
    @ManyToOne
    @JoinColumn(name="ref_train")
    private Train train;
    @ManyToOne
    @JoinColumn(name="ref_origin_station")
    private Station origin_station;
    @ManyToOne
    @JoinColumn(name="ref_desti_station")
    private Station desti_station;

    private LocalDateTime deparature_date;

    private LocalDateTime arrival_date;



}
