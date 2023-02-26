package com.project.train_web_application;

import com.project.train_web_application.Models.Passenger;
import com.project.train_web_application.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@SpringBootApplication
public class TrainWebApplication {
PassengerRepository passengerRepository;
	public static void main(String[] args) {
		SpringApplication.run(TrainWebApplication.class, args);

	}

//	@Bean
//	CommandLineRunner commandLineRunner(PassengerRepository passengerRepository){
//		return args -> {
//			passengerRepository.save(
//					new Passenger(null,"Marouan","BAAZIZ","pop@gmail.com",null));
//			passengerRepository.save(
//					new Passenger(null,"Khalid","Aziz","khalid@gmail.com",null));
//			passengerRepository.save(
//					new Passenger(null,"Hassan","Mohssin","hassan@gmail.com",null));
//			passengerRepository.save(
//					new Passenger(null,"Rachid","Mostafa","mostafa@gmail.com",null));
//
//			passengerRepository.findAllByOrderByIdAsc().forEach(p->{
//				System.out.println(p.getFirst_name());
//			});
//
//		};
//	}

}

