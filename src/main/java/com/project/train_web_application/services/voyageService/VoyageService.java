package com.project.train_web_application.services.voyageService;

import com.project.train_web_application.Models.Voyage;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface VoyageService {

//    String check_train_voyage(Long train_id ,
//                             Long station_dep_id ,
//                              Long station_des_id ,
//                              String dep_date ,
//                             String arr_date);


    List<Voyage> check_train_voyage(Long train_id,
                                    Long station_dep_id,
                                    Long station_des_id,
                                    LocalDateTime dep_date,
                                    LocalDateTime arr_date);


    List<Voyage> getVoyageByParam(Long station_dep_id,
                                  Long station_des_id,
                                  LocalDateTime dep_date,
                                  LocalDateTime arr_date);



}


