package com.project.train_web_application;

import com.project.train_web_application.Models.Role;
import com.project.train_web_application.Models.User;
import com.project.train_web_application.repositories.*;

import com.project.train_web_application.services.UserService.UserService;
import com.project.train_web_application.services.roleService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrainWebApplication {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(TrainWebApplication.class, args);

	}

		@Bean
		CommandLineRunner commandLineRunner(){
		return args -> {
//			userRepository.save(new User(null,"Marouan","Baaziz","pop@gmail.com","123","mr_ziz",1L,null,null));
//			System.out.println(userRepository.findById(1L).get().getFirst_name()+" "+userRepository.findById(1L).get().getLast_name());
//			userRepository.deleteById(2L);
//			roleRepository.save(new Role(null,"ADMIN",null));
//			roleRepository.save(new Role(null,"USER",null));

//			User u1 = userRepository.findUserByUsername("Marouan");
//			Role r1 = roleService.getRoleByRoleName("ADMIN");
//			User u1 = userService.getUserByFirstName("Marouan");
//			userService.addUserToRole("mr_ziz","ADMIN");
//			System.out.println(userRepository.findUserByRolesEquals(new Role(1L,"ADMIN",null)).getFirstName());
//			System.out.println(r1.getRoleName());

		};
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

