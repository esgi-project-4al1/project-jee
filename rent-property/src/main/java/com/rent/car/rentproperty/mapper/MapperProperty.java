package com.rent.car.rentproperty.mapper;


import com.rent.car.rentproperty.dto.PropertiesDto;
import com.rent.car.rentproperty.dto.PropertyDto;
import com.rent.car.rentproperty.entity.EnergyClassificationEntity;
import com.rent.car.rentproperty.entity.PropertyEntity;
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


    public PropertyEntity toPropertyDto(PropertyDto propertyDto, int id) {
        return new PropertyEntity(
                id,
                propertyDto.getDescription(),
                propertyDto.getTown(),
                propertyDto.getAddress(),
                mapperPropertyType.toPropertyType(propertyDto.getPropertyType().name()),
                propertyDto.getRentAmount(),
                propertyDto.getSecurityDepositAmount(),
                propertyDto.getArea(),
                propertyDto.getNumberOfBedrooms(),
                propertyDto.getFloorNumber(),
                propertyDto.getNumberOfFloors(),
                propertyDto.getConstructionYear(),
                new EnergyClassificationEntity(propertyDto.getEnergyClassification().name()),
                propertyDto.getHasElevator(),
                propertyDto.getHasIntercom(),
                propertyDto.getHasBalcony(),
                propertyDto.getHasParkingSpace()
        );
    }

    public PropertyEntity toPropertyDto(PropertyDto propertyDto) {
        return new PropertyEntity(
                propertyDto.getDescription(),
                propertyDto.getTown(),
                propertyDto.getAddress(),
                mapperPropertyType.toPropertyType(propertyDto.getPropertyType().name()),
                propertyDto.getRentAmount(),
                propertyDto.getSecurityDepositAmount(),
                propertyDto.getArea(),
                propertyDto.getNumberOfBedrooms(),
                propertyDto.getFloorNumber(),
                propertyDto.getNumberOfFloors(),
                propertyDto.getConstructionYear(),
                new EnergyClassificationEntity(propertyDto.getEnergyClassification().name()),
                propertyDto.getHasElevator(),
                propertyDto.getHasIntercom(),
                propertyDto.getHasBalcony(),
                propertyDto.getHasParkingSpace()
        );
    }

    public PropertiesDto toPropertyEntity(PropertyEntity propertyEntity) {
        return new PropertiesDto(
                propertyEntity.getAddress(),
                propertyEntity.getArea(),
                propertyEntity.getDescription(),
                mapperPropertyType.toPropertyTypeEntity(propertyEntity.getPropertyType()),
                propertyEntity.getRentAmount(),
                propertyEntity.getSecurityDepositAmount(),
                propertyEntity.getTown()
        );
    }

    public PropertyEntity mapTo(PropertyEntity propertyEntity, PropertyDto propertyDto, EnergyClassificationEntity energyClassificationEntity, PropertyTypeEntity propertyTypeEntity) {
        propertyEntity.setDescription(propertyDto.getDescription());
        propertyEntity.setTown(propertyDto.getTown());
        propertyEntity.setAddress(propertyDto.getAddress());
        propertyEntity.setPropertyType(propertyTypeEntity);
        propertyEntity.setRentAmount(propertyDto.getRentAmount());
        propertyEntity.setSecurityDepositAmount(propertyDto.getSecurityDepositAmount());
        propertyEntity.setArea(propertyDto.getArea());
        propertyEntity.setNumberOfBedrooms(propertyDto.getNumberOfBedrooms());
        propertyEntity.setFloorNumber(propertyDto.getFloorNumber());
        propertyEntity.setNumberOfFloors(propertyDto.getNumberOfFloors());
        propertyEntity.setConstructionYear(propertyDto.getConstructionYear());
        propertyEntity.setEnergyClassificationEntity(energyClassificationEntity);
        propertyEntity.setHasElevator(propertyDto.getHasElevator());
        propertyEntity.setHasIntercom(propertyDto.getHasIntercom());
        propertyEntity.setHasBalcony(propertyDto.getHasBalcony());
        propertyDto.setHasParkingSpace(propertyDto.getHasParkingSpace());
        return propertyEntity;
    }

}
