package com.example.homework1.services;

import com.example.homework1.dao.CarDAO;
import com.example.homework1.dao.ClientUserDAO;
import com.example.homework1.models.Car;
import com.example.homework1.models.dto.ClientUserDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {

    private CarDAO carDAO;

    public void saveCar(@RequestBody @Valid Car car) {
        carDAO.save(car);
    }

    public List<Car> getCars() {
        return carDAO.findAll();
    }

    public List<Car> getCarById(@PathVariable int id) {
        return carDAO.findById(id);
    }

    public void deleteCar(@PathVariable int id) {
        carDAO.deleteById(id);
    }

    public List<Car> getCarsByPower(@PathVariable int value) {
        return carDAO.findByPower(value);
    }

    public List<Car> getCarsByProducer(@PathVariable String value) {
        return carDAO.findByProducer(value);
    }


}
