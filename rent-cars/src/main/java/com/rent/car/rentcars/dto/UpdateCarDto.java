package com.rent.car.rentcars.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarDto {



    @NotNull(message = "please specify the amount")
    private Double rentAmount;

    public Double getRentAmount() {
        return rentAmount;
    }
}
