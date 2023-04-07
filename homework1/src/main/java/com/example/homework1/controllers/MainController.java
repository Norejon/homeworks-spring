package com.example.homework1.controllers;

import com.example.homework1.dao.CarDAO;
import com.example.homework1.models.Car;
import com.example.homework1.models.ClientUser;
import com.example.homework1.models.dto.ClientUserDTO;
import com.example.homework1.models.views.Views;
import com.example.homework1.services.CarService;
import com.example.homework1.services.ClientUserService;
import com.example.homework1.services.MailService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

    private CarService carService;
    private MailService mailService;
    private ClientUserService clientUserService;

    @GetMapping("/cars")
    @JsonView(Views.Level13.class)
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping("/cars/{id}")
    @JsonView(Views.Level11.class)
    public List<Car> getCarById(@PathVariable int id) {
        return carService.getCarById(id);
    }

    @SneakyThrows
    @PostMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    public void saveCar(@RequestBody @Valid Car car) {
        carService.saveCar(car);
        mailService.send(car);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable int id) {
        carService.getCarById(id);
    }

    @GetMapping("/cars/power/{value}")
    @JsonView(Views.Level12.class)
    public List<Car> getCarsByPower(@PathVariable int value) {
        return carService.getCarsByPower(value);
    }

    @GetMapping("/cars/producer/{value}")
    @JsonView(Views.Level12.class)
    public List<Car> getCarsByProducer(@PathVariable String value) {
        return carService.getCarsByProducer(value);
    }

    //clients
    @PostMapping("/clients/save")
    public void saveClient(@RequestBody ClientUserDTO clientUserDTO){
        clientUserService.saveClient(clientUserDTO);
    }

    @PostMapping("/clients/login")
    public void login(@RequestBody ClientUserDTO clientUserDTO){

    }

    @GetMapping("/clients/all")
    @JsonView(Views.Level13.class)
    public List<ClientUser> getAllDefault(){
        return clientUserService.getAllDefault();
    }
    @GetMapping("/admin/all")
    @JsonView(Views.Level12.class)
    public List<ClientUser> getAllClients(){
        return clientUserService.getAllDefault();
    }


}
