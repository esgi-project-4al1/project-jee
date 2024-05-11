package com.rent.car.rentproperty.service;

import com.rent.car.rentproperty.dto.PropertiesDto;
import com.rent.car.rentproperty.dto.PropertyDto;
import com.rent.car.rentproperty.entity.EnergyClassificationEntity;
import com.rent.car.rentproperty.entity.PropertyEntity;
import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import com.rent.car.rentproperty.exception.NotFoundException;
import com.rent.car.rentproperty.mapper.MapperProperty;
import com.rent.car.rentproperty.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private final PropertyTypeService propertyTypeService;
    private final EnergyClassificationService energyClassificationService;
    private final MapperProperty mapperProperty;
    private final PropertyRepository propertyRepository;


    @Autowired()
    public PropertyService(MapperProperty mapperProperty, PropertyRepository propertyRepository, PropertyTypeService propertyTypeService, EnergyClassificationService energyClassificationService) {
        this.mapperProperty = mapperProperty;
        this.propertyRepository = propertyRepository;
        this.propertyTypeService = propertyTypeService;
        this.energyClassificationService = energyClassificationService;
    }

    public PropertiesDto findRentalProperty(int rentalId) {
        return this.propertyRepository
                .findById(rentalId)
                .map(this.mapperProperty::toPropertyEntity)
                .orElseThrow(() -> new NotFoundException("Rental Property Not Found " + rentalId));
    }

    public List<PropertiesDto> findAllRentalProperties() {
        return this.propertyRepository
                .findAll()
                .stream()
                .map(this.mapperProperty::toPropertyEntity)
                .toList();
    }

    public void deleteRentalProperty(int rentalId) {
        this.propertyRepository.deleteById(rentalId);
    }

    public void updateRentalAmount(int rentalId, Double amount) {
        PropertyEntity propertyEntity = this.propertyRepository.findById(rentalId)
                .orElseThrow(() -> new NotFoundException("Rental Property Not Found " + rentalId));
        propertyEntity.setRentAmount(amount);
        this.propertyRepository.save(
                propertyEntity
        );
    }

    public void updateRentalProperty(int rentalId, PropertyDto propertyDto) {
        this.propertyRepository.findById(rentalId)
                .orElseThrow(() -> new NotFoundException("Rental Property Not Found " + rentalId));
        PropertyTypeEntity propertyTypeEntity = this.propertyTypeService.saveOrFind(propertyDto.getPropertyType());
        EnergyClassificationEntity energyClassificationEntity = this.energyClassificationService.saveOrFind(propertyDto.getEnergyClassification().name());
        PropertyEntity propertyEntity = this.mapperProperty.toPropertyDto(propertyDto, rentalId);
        this.propertyRepository.save(
                this.mapperProperty.mapTo(propertyEntity, propertyDto, energyClassificationEntity, propertyTypeEntity)
        );

    }

    public void saveRentalProperty(PropertyDto propertyDto) {
        PropertyTypeEntity propertyTypeEntity = this.propertyTypeService.saveOrFind(propertyDto.getPropertyType());
        EnergyClassificationEntity energyClassificationEntity = this.energyClassificationService.saveOrFind(propertyDto.getEnergyClassification().name());
        PropertyEntity propertyEntity = this.mapperProperty.toPropertyDto(propertyDto);
        this.propertyRepository.save(propertyEntity.PropertyEntityWithNewProperTypeAndEnergy(propertyTypeEntity, energyClassificationEntity));
    }
}
