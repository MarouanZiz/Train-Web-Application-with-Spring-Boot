package com.project.train_web_application.controllers;

import com.project.train_web_application.Models.Station;
import com.project.train_web_application.Models.Train;
import com.project.train_web_application.Models.Voyage;
import com.project.train_web_application.repositories.StationRepository;
import com.project.train_web_application.repositories.TrainRepository;
import com.project.train_web_application.repositories.VoyageRepository;
import com.project.train_web_application.services.voyageService.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class VoyageController {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private VoyageRepository voyageRepository;
    @Autowired
    private VoyageService voyageService;

    @GetMapping("/admin/formAddVoyage")
    public String formAddVoyage(Model model){
        model.addAttribute("allStations", stationRepository.findAll());
        model.addAttribute("allTrains", trainRepository.findAll());
        model.addAttribute("voyage", new Voyage());

        return "formAddVoyage";
    }

    @PostMapping("/admin/saveVoyage")
    public String saveVoyage(Model model
            ,@RequestParam("station_depart") Long station_depart
            ,@RequestParam("station_arrivee") Long station_arrivee
            ,@RequestParam("train_depart") Long train_depart
            ,@RequestParam("depart_date") String departDate
            ,@RequestParam("arrival_date") String arrivalDate,
                             RedirectAttributes redirectAttributes
                             ){


//        model.addAttribute("allGares", gareRepository.findAll());
            Station stationDep = stationRepository.findAllByStationId(station_depart);
            Station stationArr = stationRepository.findAllByStationId(station_arrivee);
            Train trainDep = trainRepository.findByTrainId(train_depart);

        LocalDateTime arr_date = LocalDateTime.parse(arrivalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        LocalDateTime dep_date = LocalDateTime.parse(departDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        Voyage voyage = new Voyage(null,trainDep,stationDep,stationArr,dep_date,arr_date);

        // check if train already has a voyage

        if(voyageService.check_train_voyage(train_depart,station_depart,station_arrivee,dep_date,arr_date).size() == 0){
            voyageRepository.save(voyage);
            redirectAttributes.addFlashAttribute("msg_add",true);
            return "redirect:/admin/formAddVoyage";
        }else{
            redirectAttributes.addFlashAttribute("msg_add",false);
            return "redirect:/admin/formAddVoyage";
        }




    }



}
