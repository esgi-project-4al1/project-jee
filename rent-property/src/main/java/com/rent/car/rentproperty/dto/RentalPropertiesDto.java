package com.rent.car.rentproperty.dto;


public record RentalPropertiesDto(
        String address,
        Double area,
        String description,
        String propertyTypeDto,
        Double rentAmount,
        Double securityDepositAmount,
        String town
) {
}
