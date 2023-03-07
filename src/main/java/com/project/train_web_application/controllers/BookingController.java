package com.project.train_web_application.controllers;

import com.project.train_web_application.Models.Passenger;
import com.project.train_web_application.Models.PriceByDistance;
import com.project.train_web_application.Models.User;
import com.project.train_web_application.Models.Voyage;
import com.project.train_web_application.repositories.StationRepository;
import com.project.train_web_application.repositories.TrainRepository;
import com.project.train_web_application.repositories.VoyageRepository;
import com.project.train_web_application.services.UserService.UserService;
import com.project.train_web_application.services.roleService.RoleService;
import com.project.train_web_application.services.voyageService.VoyageService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.CallableStatement;
import java.sql.Types;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private VoyageRepository voyageRepository;


    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private VoyageService voyageService;

    @GetMapping("/Responsable_reservation/formReservation")
    public String formAddUser(Model model,
                              RedirectAttributes redirectAttributes,
                              HttpSession session){
        model.addAttribute("allStations", stationRepository.findAll());

        return "formBooking";
    }

    @PostMapping(path ="/Responsable_reservation/resultats-disponibilites")
    public String resultatsDispo(Model model,
            @RequestParam("station_depart") Long station_depart
            ,@RequestParam("station_arrivee") Long station_arrivee
            ,@RequestParam("depart_date") String departDate
            ,@RequestParam("arrival_date") String arrivalDate
            ,@RequestParam("nb_passagers") int nb_passagers
            ,RedirectAttributes redirectAttributes){



        model.addAttribute("allStations", stationRepository.findAll());

        List<Voyage> voyages = null;
        List<Voyage> ResultVoyages = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterForHour = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatterForDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String now = LocalDateTime.now().format(formatter);

        String nowDate = LocalDateTime.now().format(formatterForDate);
        String dateTimeVoyage = "";
        String dateVoyage = "";

        if (!departDate.equals("") && !arrivalDate.equals("")){


        LocalDateTime arr_date = LocalDateTime.parse(arrivalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        LocalDateTime dep_date = LocalDateTime.parse(departDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                if (voyageService.getVoyageByParam(station_depart,station_arrivee,dep_date,arr_date).isEmpty()){

                    redirectAttributes.addFlashAttribute("msg_add",false);
                    return "redirect:/Responsable_reservation/formReservation";

                }else{

                    voyages =  voyageService.getVoyageByParam(station_depart,station_arrivee,dep_date,arr_date);


                    return getString(nb_passagers, redirectAttributes, voyages, ResultVoyages, formatter, formatterForHour, formatterForDate, now, nowDate);
                    }

                }else if(!departDate.equals("")) {

            LocalDateTime dep_date = LocalDateTime.parse(departDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

            if (voyageService.getVoyageByParam(station_depart,station_arrivee,dep_date,null).isEmpty()){

                redirectAttributes.addFlashAttribute("msg_add",false);
                return "redirect:/Responsable_reservation/formReservation";

            }else{

            voyages =  voyageService.getVoyageByParam(station_depart,station_arrivee,dep_date,null);


                return getString(nb_passagers, redirectAttributes, voyages, ResultVoyages, formatter, formatterForHour, formatterForDate, now, nowDate);

            }

        }
        else if(!arrivalDate.equals("")){

            LocalDateTime arr_date = LocalDateTime.parse(arrivalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

            if (voyageService.getVoyageByParam(station_depart,station_arrivee,null,arr_date).isEmpty()){

                redirectAttributes.addFlashAttribute("msg_add",false);
                return "redirect:/Responsable_reservation/formReservation";

            }else {

                voyages = voyageService.getVoyageByParam(station_depart, station_arrivee, null, arr_date);



                return getString(nb_passagers, redirectAttributes, voyages, ResultVoyages, formatter, formatterForHour, formatterForDate, now, nowDate);


            }

//          ****  if the user not chose the datetime  ****

        }else{

            if (voyageService.getVoyageByParam(station_depart,station_arrivee,null,null).isEmpty()){
                //          ****  if there is not voyages  ****
                redirectAttributes.addFlashAttribute("msg_add",false);

                return "redirect:/Responsable_reservation/formReservation";
            }else {

                voyages =  voyageService.getVoyageByParam(station_depart,station_arrivee,null,null);

                //        si le client n'est pas precis date depart et arrivee on l'affiche les train d'aujourd'hui
                return getString(nb_passagers, redirectAttributes, voyages, ResultVoyages, formatter, formatterForHour, formatterForDate, now, nowDate);

            }


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

//        return "formBooking";
    }

    private String getString(@RequestParam("nb_passagers") int nb_passagers, RedirectAttributes redirectAttributes, List<Voyage> voyages, List<Voyage> resultVoyages, DateTimeFormatter formatter, DateTimeFormatter formatterForHour, DateTimeFormatter formatterForDate, String now, String nowDate) {
        String dateTimeVoyage;
        String dateVoyage;
        for (Voyage v:voyages) {
            dateTimeVoyage = v.getDeparature_date().format(formatter);
            dateVoyage = v.getDeparature_date().format(formatterForDate);



            if(dateTimeVoyage.compareTo(now)>0 && dateVoyage.compareTo(nowDate)==0){
                resultVoyages.add(v);
            }


        }

        if(resultVoyages.size() == 0){

            redirectAttributes.addFlashAttribute("msg_add",false);
            return "redirect:/Responsable_reservation/formReservation";

        }else{


            redirectAttributes.addFlashAttribute("ResultVoyages", resultVoyages);
            redirectAttributes.addFlashAttribute("formatterForHour",formatterForHour);
            redirectAttributes.addFlashAttribute("trainRepository",trainRepository);
            redirectAttributes.addFlashAttribute("stationRepository",stationRepository);
            redirectAttributes.addFlashAttribute("voyageService",voyageService);
            redirectAttributes.addFlashAttribute("nombrePassagers",nb_passagers);


            return "redirect:/Responsable_reservation/formReservation";

        }
    }


    @GetMapping("/resultats-disponibilites/mes-coordonnees")
    public String contactDetails(Model model,
                                 @RequestParam("id")Long idVoyage,
                                 @RequestParam("nbPers")int nbPers,
                                 HttpSession httpSession){

        Voyage voyage = voyageRepository.findVoyageByVoyageId(idVoyage);
        double prixVoyage = voyageService.getPriceV(voyageService.getPriceByDistance(voyage.getDesti_station().getStationId(),voyage.getOrigin_station().getStationId()),voyage.getTrain().getTrainId(),nbPers);
        httpSession.setAttribute("voyageur",voyage);
        httpSession.setAttribute("prix_voyage",prixVoyage);

            model.addAttribute("passenger",new Passenger());
            model.addAttribute("nbr_passengers",nbPers);

        httpSession.setAttribute("nbr_passengers",nbPers);
        return "coordonees";
    }

    @PostMapping("/resultats-disponibilites/savePassenger")
    public String contactDetails(@Valid @ModelAttribute("passenger") Passenger passenger,
                                 BindingResult bindingResult,
                                 @RequestParam("firstName2") String firstName2,
                                 @RequestParam("lastName2") String lastName2,
                                 @RequestParam("email2") String email2,
                                 @RequestParam("tele2") Long tele2,
                                 @RequestParam("gendre2") String gendre2,
                                 HttpSession httpSession){

        httpSession.setAttribute("passenger1",passenger);
        httpSession.setAttribute("passenger1",new Passenger(null,firstName2,lastName2,email2,tele2,gendre2));


//        if(bindingResult.hasErrors()) return "coordonees";

        return "coordonees";
    }

//    public PriceByDistance getPriceByDistance(Long idDes,Long idOrigin) {
//        PriceByDistance priceByDistance = entityManager.createQuery("select p from PriceByDistance p", PriceByDistance.class).getSingleResult();
//        return priceByDistance;
//    }


//    public double getPriceVoyage(PriceByDistance priceByDistance, Long trainId){
//        double price = priceByDistance.getPriceDis();
//        if (trainId == 1){
//            price = price*1.2;
//        } else if (trainId == 2) {
//            price = price*1.3;
//        }else {
//            price = price*1.5;
//        }
//
//        return price;
//    }

}
