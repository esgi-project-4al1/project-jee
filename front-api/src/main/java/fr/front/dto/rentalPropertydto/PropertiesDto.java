package fr.front.dto.rentalPropertydto;


public record PropertiesDto(
        String address,
        Double area,
        String description,
        String propertyTypeDto,
        Double rentAmount,
        Double securityDepositAmount,
        String town
) {
}
