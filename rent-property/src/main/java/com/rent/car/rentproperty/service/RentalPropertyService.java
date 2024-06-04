package com.rent.car.rentproperty.service;

import com.rent.car.rentproperty.dto.RentalPropertiesDto;
import com.rent.car.rentproperty.dto.RentalPropertyDto;
import com.rent.car.rentproperty.entity.EnergyClassificationEntity;
import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import com.rent.car.rentproperty.entity.RentalPropertyEntity;
import com.rent.car.rentproperty.exception.NotFoundRentalPropertyException;
import com.rent.car.rentproperty.mapper.MapperRentalProperty;
import com.rent.car.rentproperty.repository.RentalPropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalPropertyService {

    private final RentalPropertyTypeService rentalPropertyTypeService;
    private final EnergyClassificationService energyClassificationService;
    private final MapperRentalProperty mapperRentalProperty;
    private final RentalPropertyRepository rentalPropertyRepository;


    public RentalPropertyService(MapperRentalProperty mapperRentalProperty, RentalPropertyRepository rentalPropertyRepository, RentalPropertyTypeService rentalPropertyTypeService, EnergyClassificationService energyClassificationService) {
        this.mapperRentalProperty = mapperRentalProperty;
        this.rentalPropertyRepository = rentalPropertyRepository;
        this.rentalPropertyTypeService = rentalPropertyTypeService;
        this.energyClassificationService = energyClassificationService;
    }

    public RentalPropertiesDto findRentalProperty(int rentalId) {
        return this.rentalPropertyRepository
                .findById(rentalId)
                .map(this.mapperRentalProperty::toPropertyEntity)
                .orElseThrow(() -> new NotFoundRentalPropertyException("Rental Property Not Found " + rentalId));
    }

    public List<RentalPropertiesDto> findAllRentalProperties() {
        return this.rentalPropertyRepository
                .findAll()
                .stream()
                .map(this.mapperRentalProperty::toPropertyEntity)
                .toList();
    }

    public void deleteRentalProperty(int rentalId) {
        this.rentalPropertyRepository.deleteById(rentalId);
    }

    public void updateRentalAmount(int rentalId, Double amount) {
        RentalPropertyEntity rentalPropertyEntity = this.rentalPropertyRepository.findById(rentalId)
                .orElseThrow(() -> new NotFoundRentalPropertyException("Rental Property Not Found " + rentalId));
        rentalPropertyEntity.setRentAmount(amount);
        this.rentalPropertyRepository.save(
                rentalPropertyEntity
        );
    }

    public void updateRentalProperty(int rentalId, RentalPropertyDto rentalPropertyDto) {
        this.rentalPropertyRepository.findById(rentalId)
                .orElseThrow(() -> new NotFoundRentalPropertyException("Rental Property Not Found " + rentalId));
        PropertyTypeEntity propertyTypeEntity = this.rentalPropertyTypeService.updatePropertyTypeEnum(rentalPropertyDto.getPropertyType());
        EnergyClassificationEntity energyClassificationEntity = this.energyClassificationService.updateEnergyClassification(rentalPropertyDto.getEnergyClassification().name());
        RentalPropertyEntity rentalPropertyEntity = this.mapperRentalProperty.toPropertyDto(rentalPropertyDto, rentalId);
        this.rentalPropertyRepository.save(
                this.mapperRentalProperty.mapTo(rentalPropertyEntity, rentalPropertyDto, energyClassificationEntity, propertyTypeEntity)
        );

    }

    public void saveRentalProperty(RentalPropertyDto rentalPropertyDto) {
        PropertyTypeEntity propertyTypeEntity = this.rentalPropertyTypeService.updatePropertyTypeEnum(rentalPropertyDto.getPropertyType());
        EnergyClassificationEntity energyClassificationEntity = this.energyClassificationService.updateEnergyClassification(rentalPropertyDto.getEnergyClassification().name());
        RentalPropertyEntity rentalPropertyEntity = this.mapperRentalProperty.toPropertyDto(rentalPropertyDto);
        this.rentalPropertyRepository.save(rentalPropertyEntity.PropertyEntityWithNewProperTypeAndEnergy(propertyTypeEntity, energyClassificationEntity));
    }
}
