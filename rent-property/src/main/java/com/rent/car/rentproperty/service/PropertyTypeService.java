package com.rent.car.rentproperty.service;

import com.rent.car.rentproperty.dto.PropertyTypeEnumDto;
import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import com.rent.car.rentproperty.mapper.MapperPropertyType;
import com.rent.car.rentproperty.repository.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyTypeService {

    private final PropertyTypeRepository propertyTypeRepository;
    private final MapperPropertyType mapperPropertyType;

    @Autowired
    public PropertyTypeService(PropertyTypeRepository propertyTypeRepository, MapperPropertyType mapperPropertyType) {
        this.propertyTypeRepository = propertyTypeRepository;
        this.mapperPropertyType = mapperPropertyType;
    }


    public PropertyTypeEntity saveOrFind(PropertyTypeEnumDto propertyTypeEnumDto){
        if (propertyTypeEnumDto == null) return null;
        Optional<PropertyTypeEntity> propertyTypeEntity = this.propertyTypeRepository.findByDesignation(propertyTypeEnumDto.name());
        return propertyTypeEntity.orElseGet(() -> this.propertyTypeRepository.save(
                mapperPropertyType.toPropertyType(propertyTypeEnumDto.name())
        ));
    }
}
