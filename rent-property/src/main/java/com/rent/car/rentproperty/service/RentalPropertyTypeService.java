package com.rent.car.rentproperty.service;

import com.rent.car.rentproperty.dto.RentalPropertyTypeEnumDto;
import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import com.rent.car.rentproperty.exception.NullEnergyClassificationException;
import com.rent.car.rentproperty.exception.NullRentalPropertyEnumException;
import com.rent.car.rentproperty.mapper.MapperRentalPropertyType;
import com.rent.car.rentproperty.repository.RentalPropertyTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalPropertyTypeService {

    private final RentalPropertyTypeRepository rentalPropertyTypeRepository;
    private final MapperRentalPropertyType mapperRentalPropertyType;


    public RentalPropertyTypeService(RentalPropertyTypeRepository rentalPropertyTypeRepository, MapperRentalPropertyType mapperRentalPropertyType) {
        this.rentalPropertyTypeRepository = rentalPropertyTypeRepository;
        this.mapperRentalPropertyType = mapperRentalPropertyType;
    }


    public PropertyTypeEntity updatePropertyTypeEnum(RentalPropertyTypeEnumDto rentalPropertyTypeEnumDto){
        if (rentalPropertyTypeEnumDto == null) throw  new NullRentalPropertyEnumException("error RentalPropertyEnum is null");
        Optional<PropertyTypeEntity> propertyTypeEntity = this.rentalPropertyTypeRepository.findByDesignation(rentalPropertyTypeEnumDto.name());
        return propertyTypeEntity.orElseGet(() -> this.rentalPropertyTypeRepository.save(
                mapperRentalPropertyType.toPropertyType(rentalPropertyTypeEnumDto.name())
        ));
    }
}
