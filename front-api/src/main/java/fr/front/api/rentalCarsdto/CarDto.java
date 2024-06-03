package fr.front.api.rentalCarsdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CarDto {

    @NotNull(message = "Please specify brand")
    @NotBlank(message = "Brand cannot be blank")
    private String brand;

    @NotNull(message = "Please specify model")
    @NotBlank(message = "Model cannot be blank")
    private String model;

    @NotNull(message = "Please specify rent amount")
    private Double rentAmount;

    @NotNull(message = "Please specify security deposit amount")
    private Double securityDepositAmount;

    private Integer numberOfSeats;

    private Integer numberOfDoors;

    @NotNull(message = "Please specify if it has air conditioning")
    private Boolean hasAirConditioning;
    @NotNull
    public String getBrand() {
        return brand;
    }

    @NotNull
    public String getModel() {
        return model;
    }

    @NotNull
    public Double getRentAmount() {
        return rentAmount;
    }

    @NotNull
    public Double getSecurityDepositAmount() {
        return securityDepositAmount;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public Boolean getHasAirConditioning() {
        return hasAirConditioning;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRentAmount(Double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public void setSecurityDepositAmount(Double securityDepositAmount) {
        this.securityDepositAmount = securityDepositAmount;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public void setHasAirConditioning(Boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }
}

