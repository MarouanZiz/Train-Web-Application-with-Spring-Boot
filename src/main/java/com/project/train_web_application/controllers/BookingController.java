package com.project.train_web_application.controllers;

import com.project.train_web_application.Models.User;
import com.project.train_web_application.Models.Voyage;
import com.project.train_web_application.repositories.StationRepository;
import com.project.train_web_application.repositories.TrainRepository;
import com.project.train_web_application.services.UserService.UserService;
import com.project.train_web_application.services.roleService.RoleService;
import com.project.train_web_application.services.voyageService.VoyageService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.CallableStatement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private StationRepository stationRepository;

//    @Autowired
//    private EntityManager entityManager;

    @Autowired
    private VoyageService voyageService;

    @GetMapping("/Responsable_reservation/formReservation")
    public String formAddUser(Model model,
                              RedirectAttributes redirectAttributes,
                              HttpSession session){
        model.addAttribute("allStations", stationRepository.findAll());

        return "formBooking";
    }

    @PostMapping(path ="/Responsable_voyage/resultats-disponibilites")
    public String resultatsDispo(
            @RequestParam("station_depart") Long station_depart
            ,@RequestParam("station_arrivee") Long station_arrivee
            ,@RequestParam("depart_date") String departDate
            ,@RequestParam("arrival_date") String arrivalDate){
        if (!departDate.equals("") && !arrivalDate.equals("")){


        LocalDateTime arr_date = LocalDateTime.parse(arrivalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        LocalDateTime dep_date = LocalDateTime.parse(departDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        List<Voyage> voyages =  voyageService.getVoyageByParam(station_depart,station_arrivee,dep_date,arr_date);
            System.out.println("size de voyage :"+voyages.size());
        }else if(!departDate.equals(""))
        {

            LocalDateTime dep_date = LocalDateTime.parse(departDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

            List<Voyage> voyages =  voyageService.getVoyageByParam(station_depart,station_arrivee,dep_date,null);
            System.out.println("size de voyage :"+voyages.get(0).getDeparature_date());
        }
        else if(!arrivalDate.equals("")){

            LocalDateTime arr_date = LocalDateTime.parse(arrivalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

            List<Voyage> voyages =  voyageService.getVoyageByParam(station_depart,station_arrivee,null,arr_date);
            System.out.println("size de voyage :"+voyages.size());

        }else{

            List<Voyage> voyages =  voyageService.getVoyageByParam(station_depart,station_arrivee,null,null);
        System.out.println("size de voyage :"+voyages.size());
        }




//        Session session = entityManager.unwrap( Session.class );
//
//        Integer commentCount = session.doReturningWork(
//                connection -> {
//                    try (CallableStatement function = connection
//                            .prepareCall(
//                                    "{ ? = call fn_count_voyage(?) }" )) {
//                        function.registerOutParameter( 1, Types.INTEGER );
//                        function.setInt( 2, 1 );
//                        function.execute();
//                        return function.getInt( 1 );
//                    }
//                } );
//        System.out.println(commentCount);

        return "formBooking";
    }




}
