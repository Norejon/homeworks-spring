package com.example.homework1.dao;

import com.example.homework1.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarDAO extends JpaRepository<Car, Integer> {

    List<Car> findById(int id);

    List<Car> findByPower(int value);

    //не працювало
    //    @Query("select c from Car c where c.producer =:value")
    //    List<Car> getCarsByProducer(@Param("producer") String value);
    List<Car> findByProducer(String value);
}
