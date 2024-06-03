package com.rent.car.rentproperty.service;

import com.rent.car.rentproperty.dto.RentalPropertyTypeEnumDto;
import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import com.rent.car.rentproperty.mapper.MapperPropertyType;
import com.rent.car.rentproperty.repository.RentalPropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalPropertyTypeService {

    private final RentalPropertyTypeRepository rentalPropertyTypeRepository;
    private final MapperPropertyType mapperPropertyType;

    @Autowired
    public RentalPropertyTypeService(RentalPropertyTypeRepository rentalPropertyTypeRepository, MapperPropertyType mapperPropertyType) {
        this.rentalPropertyTypeRepository = rentalPropertyTypeRepository;
        this.mapperPropertyType = mapperPropertyType;
    }


    public PropertyTypeEntity saveOrFind(RentalPropertyTypeEnumDto rentalPropertyTypeEnumDto){
        if (rentalPropertyTypeEnumDto == null) return null;
        Optional<PropertyTypeEntity> propertyTypeEntity = this.rentalPropertyTypeRepository.findByDesignation(rentalPropertyTypeEnumDto.name());
        return propertyTypeEntity.orElseGet(() -> this.rentalPropertyTypeRepository.save(
                mapperPropertyType.toPropertyType(rentalPropertyTypeEnumDto.name())
        ));
    }
}
