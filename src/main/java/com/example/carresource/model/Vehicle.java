package com.example.carresource.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String brand;
    @NotEmpty
    private String model;
    @NotEmpty
    private String plateNumber;
    @ManyToOne
    private Customer customer;
    private boolean reserved = false;
}
