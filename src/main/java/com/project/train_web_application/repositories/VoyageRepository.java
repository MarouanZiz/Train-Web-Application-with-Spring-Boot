package com.project.train_web_application.repositories;

import com.project.train_web_application.Models.Passenger;
import com.project.train_web_application.Models.PriceByDistance;
import com.project.train_web_application.Models.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VoyageRepository extends JpaRepository<Voyage,Long> {

//    @Query(value = "SELECT check_train_voyage(:train_id,:station_dep_id,:station_des_id,:dep_date,:arr_date) FROM Dual")
//    String check_train_voyage(@Param("train_id") Long train_id ,
//                              @Param("station_dep_id") Long station_dep_id ,
//                              @Param("station_des_id") Long station_des_id ,
//                              @Param("dep_date")  String dep_date ,
//                              @Param("arr_date") String arr_date );


//    @Query(nativeQuery = true,value = "SELECT v FROM Voyage v WHERE train_id= :train_id")
//    public Voyage check_train_voyage(@Param("train_id") Long train_id ,
//                              @Param("station_dep_id") Long station_dep_id ,
//                              @Param("station_des_id") Long station_des_id ,
//                              @Param("dep_date")  LocalDateTime dep_date ,
//                              @Param("arr_date") LocalDateTime arr_date);


    @Query(value = "SELECT v FROM Voyage v WHERE v.train.trainId = :train_id and" +
            " v.origin_station.stationId=:station_dep_id and " +
            "v.desti_station.stationId=:station_des_id and" +
            " v.deparature_date=:dep_date and" +
            " v.arrival_date=:arr_date")
    public List<Voyage> check_train_voyage(@Param("train_id") Long train_id,
                              @Param("station_dep_id") Long station_dep_id ,
                              @Param("station_des_id") Long station_des_id ,
                              @Param("dep_date")  LocalDateTime dep_date ,
                              @Param("arr_date") LocalDateTime arr_date);

    @Query(value = "SELECT v FROM Voyage v WHERE v.origin_station.stationId=:station_dep_id and " +
            "v.desti_station.stationId=:station_des_id and" +
            " v.deparature_date=:dep_date and" +
            " v.arrival_date=:arr_date")
    public List<Voyage> getVoyageByParam(
                                           @Param("station_dep_id") Long station_dep_id ,
                                           @Param("station_des_id") Long station_des_id ,
                                           @Param("dep_date")  LocalDateTime dep_date ,
                                           @Param("arr_date") LocalDateTime arr_date);



    @Query(value = "SELECT v FROM Voyage v WHERE v.origin_station.stationId=:station_dep_id and " +
            "v.desti_station.stationId=:station_des_id and" +
            " v.arrival_date=:arr_date")
    public List<Voyage> getVoyageByParam(
            @Param("station_dep_id") Long station_dep_id ,
            @Param("station_des_id") Long station_des_id ,
            @Param("arr_date") LocalDateTime arr_date);


    @Query(value = "SELECT v FROM Voyage v WHERE v.origin_station.stationId=:station_dep_id and " +
            "v.desti_station.stationId=:station_des_id and" +
            " v.deparature_date=:dep_date")
    public List<Voyage> getVoyageByParam2(
            @Param("station_dep_id") Long station_dep_id ,
            @Param("station_des_id") Long station_des_id ,
            @Param("dep_date")  LocalDateTime dep_date);


    @Query(value = "SELECT v FROM Voyage v WHERE v.origin_station.stationId=:station_dep_id and " +
            "v.desti_station.stationId=:station_des_id")
    public List<Voyage> getVoyageByParam(
            @Param("station_dep_id") Long station_dep_id ,
            @Param("station_des_id") Long station_des_id);

@Query(value = "select p from PriceByDistance p where p.ref_desti_station=:idDes and p.ref_origin_station=:idOrigin")
    public PriceByDistance getPriceByDistance(@Param("idDes")Long idDes,@Param("idOrigin") Long idOrigin);
}





