package com.project.train_web_application.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="PASSENG_SEQ")
    @SequenceGenerator(name="PASSENG_SEQ",sequenceName="SEQ_PASSENG_ID", allocationSize = 1)
    private Long gare_id;
    private String gare_name;
    private String city_name;
    @OneToMany(mappedBy = "origin_station", fetch = FetchType.EAGER)
    private Collection<Voyage> origin_stations = new ArrayList<>();
    @OneToMany(mappedBy = "desti_station", fetch = FetchType.EAGER)
    private Collection<Voyage> desti_stations = new ArrayList<>();
}
