package com.project.train_web_application.repositories;

import com.project.train_web_application.Models.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station,Long> {

public List<Station> findAll();
public Station findAllByStationId(Long id);

}
