package com.rent.car.rentproperty.mapper;


import com.rent.car.rentproperty.dto.RentalPropertiesDto;
import com.rent.car.rentproperty.dto.RentalPropertyDto;
import com.rent.car.rentproperty.entity.EnergyClassificationEntity;
import com.rent.car.rentproperty.entity.RentalPropertyEntity;
import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperProperty {

    private final MapperPropertyType mapperPropertyType;

    @Autowired
    public MapperProperty(MapperPropertyType mapperPropertyType) {
        this.mapperPropertyType = mapperPropertyType;
    }


    public RentalPropertyEntity toPropertyDto(RentalPropertyDto rentalPropertyDto, int id) {
        return new RentalPropertyEntity(
                id,
                rentalPropertyDto.getDescription(),
                rentalPropertyDto.getTown(),
                rentalPropertyDto.getAddress(),
                mapperPropertyType.toPropertyType(rentalPropertyDto.getPropertyType().name()),
                rentalPropertyDto.getRentAmount(),
                rentalPropertyDto.getSecurityDepositAmount(),
                rentalPropertyDto.getArea(),
                rentalPropertyDto.getNumberOfBedrooms(),
                rentalPropertyDto.getFloorNumber(),
                rentalPropertyDto.getNumberOfFloors(),
                rentalPropertyDto.getConstructionYear(),
                new EnergyClassificationEntity(rentalPropertyDto.getEnergyClassification().name()),
                rentalPropertyDto.getHasElevator(),
                rentalPropertyDto.getHasIntercom(),
                rentalPropertyDto.getHasBalcony(),
                rentalPropertyDto.getHasParkingSpace()
        );
    }

    public RentalPropertyEntity toPropertyDto(RentalPropertyDto rentalPropertyDto) {
        return new RentalPropertyEntity(
                rentalPropertyDto.getDescription(),
                rentalPropertyDto.getTown(),
                rentalPropertyDto.getAddress(),
                mapperPropertyType.toPropertyType(rentalPropertyDto.getPropertyType().name()),
                rentalPropertyDto.getRentAmount(),
                rentalPropertyDto.getSecurityDepositAmount(),
                rentalPropertyDto.getArea(),
                rentalPropertyDto.getNumberOfBedrooms(),
                rentalPropertyDto.getFloorNumber(),
                rentalPropertyDto.getNumberOfFloors(),
                rentalPropertyDto.getConstructionYear(),
                new EnergyClassificationEntity(rentalPropertyDto.getEnergyClassification().name()),
                rentalPropertyDto.getHasElevator(),
                rentalPropertyDto.getHasIntercom(),
                rentalPropertyDto.getHasBalcony(),
                rentalPropertyDto.getHasParkingSpace()
        );
    }

    public RentalPropertiesDto toPropertyEntity(RentalPropertyEntity rentalPropertyEntity) {
        return new RentalPropertiesDto(
                rentalPropertyEntity.getAddress(),
                rentalPropertyEntity.getArea(),
                rentalPropertyEntity.getDescription(),
                mapperPropertyType.toPropertyTypeEntity(rentalPropertyEntity.getPropertyType()),
                rentalPropertyEntity.getRentAmount(),
                rentalPropertyEntity.getSecurityDepositAmount(),
                rentalPropertyEntity.getTown()
        );
    }

    public RentalPropertyEntity mapTo(RentalPropertyEntity rentalPropertyEntity, RentalPropertyDto rentalPropertyDto, EnergyClassificationEntity energyClassificationEntity, PropertyTypeEntity propertyTypeEntity) {
        rentalPropertyEntity.setDescription(rentalPropertyDto.getDescription());
        rentalPropertyEntity.setTown(rentalPropertyDto.getTown());
        rentalPropertyEntity.setAddress(rentalPropertyDto.getAddress());
        rentalPropertyEntity.setPropertyType(propertyTypeEntity);
        rentalPropertyEntity.setRentAmount(rentalPropertyDto.getRentAmount());
        rentalPropertyEntity.setSecurityDepositAmount(rentalPropertyDto.getSecurityDepositAmount());
        rentalPropertyEntity.setArea(rentalPropertyDto.getArea());
        rentalPropertyEntity.setNumberOfBedrooms(rentalPropertyDto.getNumberOfBedrooms());
        rentalPropertyEntity.setFloorNumber(rentalPropertyDto.getFloorNumber());
        rentalPropertyEntity.setNumberOfFloors(rentalPropertyDto.getNumberOfFloors());
        rentalPropertyEntity.setConstructionYear(rentalPropertyDto.getConstructionYear());
        rentalPropertyEntity.setEnergyClassificationEntity(energyClassificationEntity);
        rentalPropertyEntity.setHasElevator(rentalPropertyDto.getHasElevator());
        rentalPropertyEntity.setHasIntercom(rentalPropertyDto.getHasIntercom());
        rentalPropertyEntity.setHasBalcony(rentalPropertyDto.getHasBalcony());
        rentalPropertyDto.setHasParkingSpace(rentalPropertyDto.getHasParkingSpace());
        return rentalPropertyEntity;
    }

}
