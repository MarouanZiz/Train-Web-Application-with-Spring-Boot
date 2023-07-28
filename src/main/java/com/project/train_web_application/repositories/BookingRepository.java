package com.project.train_web_application.repositories;

import com.project.train_web_application.Models.Booking;
import com.project.train_web_application.Models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {

}
