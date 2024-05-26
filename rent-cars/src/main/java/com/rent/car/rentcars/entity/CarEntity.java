package com.rent.car.rentcars.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;



@Setter
@Getter
@Entity(name = "car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    @NotEmpty(message = "Please specify brand")
    @Column(length = 100)
    private String brand;

    @NotEmpty(message = "Please specify model")
    @Column(length = 100)
    private String model;

    @NotNull(message = "Please specify rent amount")
    private Double rentAmount;

    @NotNull(message = "Please specify security deposit amount")
    private Double securityDepositAmount;

    private Integer numberOfSeats;

    private Integer numberOfDoors;


    private Boolean hasAirConditioning;

    public CarEntity(Integer id, @NotNull String brand, @NotNull String model, @NotNull Double rentAmount, @NotNull Double securityDepositAmount, Integer numberOfSeats, Integer numberOfDoors, Boolean hasAirConditioning) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.numberOfSeats = numberOfSeats;
        this.numberOfDoors = numberOfDoors;
        this.hasAirConditioning = hasAirConditioning;
    }

    public CarEntity(@NotNull String brand, @NotNull String model, @NotNull Double rentAmount, @NotNull Double securityDepositAmount, Integer numberOfSeats, Integer numberOfDoors, Boolean hasAirConditioning) {
        this.brand = brand;
        this.model = model;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.numberOfSeats = numberOfSeats;
        this.numberOfDoors = numberOfDoors;
        this.hasAirConditioning = hasAirConditioning;
    }

    public CarEntity() {
    }

    public CarEntity carEntityWithNewDetails(Integer id, Integer numberOfSeats, Integer numberOfDoors, Boolean hasAirConditioning) {
        return new CarEntity(
                id,
                this.brand,
                this.model,
                this.rentAmount,
                this.securityDepositAmount,
                numberOfSeats,
                numberOfDoors,
                hasAirConditioning
        );
    }
}
