package com.project.train_web_application.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class Train {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="PASSENG_SEQ")
    @SequenceGenerator(name="PASSENG_SEQ",sequenceName="SEQ_PASSENG_ID", allocationSize = 1)
    @JoinColumn(name="train_id")
    private Long trainId;
    @JoinColumn(name="train_name")
    private String trainName;
    @JoinColumn(name="sets_1_max")
    private Long sets1Max;
    @JoinColumn(name="sets_2_max")
    private Long sets2Max;
    @OneToMany(mappedBy = "train", fetch = FetchType.EAGER)
    private Collection<Set> sets = new ArrayList<>();

}
