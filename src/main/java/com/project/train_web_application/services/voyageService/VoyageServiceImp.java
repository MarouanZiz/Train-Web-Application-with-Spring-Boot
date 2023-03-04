package com.project.train_web_application.services.voyageService;

import com.project.train_web_application.Models.Voyage;
import com.project.train_web_application.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Repository
public class VoyageServiceImp implements VoyageService {
    @Autowired
    private VoyageRepository voyageRepository;
//    @Override
//    public List<Voyage> check_train_voyage(Long train_id) {
//
//        return voyageRepository.check_train_voyage(train_id,
//                                    Long station_dep_id,
//                                    Long station_des_id ,
//                                    LocalDateTime dep_date ,
//                                    LocalDateTime arr_date);
//
//    }

    @Override
    public List<Voyage> check_train_voyage(Long train_id,
                                           Long station_dep_id,
                                           Long station_des_id,
                                           LocalDateTime dep_date,
                                           LocalDateTime arr_date) {

        return voyageRepository.check_train_voyage(train_id,station_dep_id,station_des_id,dep_date,arr_date);

    }


//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    @Override
//    public String check_train_voyage(Long train_id ,
//                                     Long station_dep_id ,  Long station_des_id ,
//                                    String dep_date , String arr_date ) {
//
//        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
//                .withFunctionName("check_train_voyage");
//        SqlParameterSource paramMap = new MapSqlParameterSource()
//                .addValue("train_id",  train_id )
//                .addValue("station_dep_id", station_dep_id)
//                .addValue("station_des_id", station_des_id)
//                .addValue("dep_date", dep_date)
//                .addValue("arr_date", arr_date);
//        return jdbcCall.executeFunction(String.class, paramMap);
//
//
//    }


}
