package com.example.homework1.controllers;

import com.example.homework1.dao.CarDAO;
import com.example.homework1.models.Car;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

    private CarDAO carDAO;

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carDAO.findAll();
    }

    @GetMapping("/cars/{id}")
    public List<Car> getCarById(@PathVariable int id) {
        return carDAO.findById(id);
    }

    @PostMapping("/cars")
    public void saveCar(@RequestBody Car car) {
        carDAO.save(car);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable int id) {
        carDAO.deleteById(id);
    }

    @GetMapping("/cars/power/{value}")
    public List<Car> getCarsByPower(@PathVariable int value) {
        return carDAO.findByPower(value);
    }

    @GetMapping("/cars/producer/{value}")
    public List<Car> getCarsByProducer(@PathVariable String value) {
        return carDAO.findByProducer(value);
    }

}
