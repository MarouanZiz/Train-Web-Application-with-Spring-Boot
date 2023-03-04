package com.project.train_web_application.repositories;

import com.project.train_web_application.Models.Station;
import com.project.train_web_application.Models.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train,Long> {

public List<Train> findAll();
public Train findByTrainId(Long id);

}
