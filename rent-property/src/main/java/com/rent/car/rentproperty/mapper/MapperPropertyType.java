package com.rent.car.rentproperty.mapper;

import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import org.springframework.stereotype.Service;

@Service
public class MapperPropertyType {


    public MapperPropertyType() {
    }

    public String toPropertyTypeEntity(PropertyTypeEntity propertyTypeEntity) {
        return propertyTypeEntity.getDesignation();
    }

    public PropertyTypeEntity toPropertyType(String propertyTypeDto) {
        return new PropertyTypeEntity(
                propertyTypeDto
        );
    }
}
