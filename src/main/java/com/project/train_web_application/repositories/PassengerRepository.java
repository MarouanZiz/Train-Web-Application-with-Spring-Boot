package com.project.train_web_application.repositories;

import com.project.train_web_application.Models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {

//    public List<Passenger> findAllByOrderByIdAsc();

}
