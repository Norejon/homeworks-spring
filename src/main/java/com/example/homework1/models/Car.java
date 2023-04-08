package com.example.homework1.models;

import com.example.homework1.models.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Level11.class)
    private int id;
    @JsonView({Views.Level11.class, Views.Level12.class, Views.Level13.class})
    private String model;
    @JsonView({Views.Level11.class, Views.Level12.class})
    private String producer;
    @JsonView({Views.Level11.class, Views.Level12.class})
    @Min(value = 0,message = "power can't be less than 1")
    @Max(value = 1100,message = "power can't be more than 1100")
    private int power;
}
