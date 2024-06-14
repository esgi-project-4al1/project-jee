package com.rent.car.rentproperty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RentAmountDto {

    @JsonProperty("rentAmount")
    @NotNull(message = "Please specify rentAmount")
    @Min(value=5)
    private Double rentAmount;

    public RentAmountDto() {
    }


    public Double getRentAmount() {
        return rentAmount;
    }
}
