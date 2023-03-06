package com.project.train_web_application.services.voyageService;

import com.project.train_web_application.Models.PriceByDistance;
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

    @Override
    public List<Voyage> getVoyageByParam(Long station_dep_id, Long station_des_id, LocalDateTime dep_date, LocalDateTime arr_date) {
        if(dep_date == null && arr_date != null){
            return voyageRepository.getVoyageByParam(station_dep_id, station_des_id,arr_date);
        }else if (arr_date == null && dep_date != null){
            return voyageRepository.getVoyageByParam2(station_dep_id, station_des_id,dep_date);
        } else if (dep_date == null && arr_date == null) {
            return voyageRepository.getVoyageByParam(station_dep_id,station_des_id);
        }
        return voyageRepository.getVoyageByParam(station_dep_id,station_des_id,dep_date,arr_date);
    }
    @Override
    public PriceByDistance getPriceByDistance(Long idDes, Long idOrigin){
        return voyageRepository.getPriceByDistance(idDes,idOrigin);
    }

//    @Override
//    public double getPriceVoyage(PriceByDistance priceByDistance, Long trainId) {
//        return getPriceV(priceByDistance,trainId);
//    }

    public double getPriceV(PriceByDistance priceByDistance, Long trainId,int nbPassagers){
        double price = 10;
        if (priceByDistance != null){
            price = priceByDistance.getPriceDis();
        }

        if (trainId == 1){
            price = price*1.2*nbPassagers;
        } else if (trainId == 2) {
            price = price*1.3*nbPassagers;
        }else {
            price = price*1.5*nbPassagers;
        }

        return price;
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
