package com.rent.car.rentproperty.service;

import com.rent.car.rentproperty.dto.PropertyTypeEnumDto;
import com.rent.car.rentproperty.entity.EnergyClassificationEntity;
import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import com.rent.car.rentproperty.mapper.MapperPropertyType;
import com.rent.car.rentproperty.repository.PropertyTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PropertyTypeServiceTest {
    
    @Mock
    private PropertyTypeRepository propertyTypeRepository;
    @Mock
    private MapperPropertyType mapperPropertyType;
    @InjectMocks
    private PropertyTypeService propertyTypeService;

    @Test
    public void saveOrFind_IfStringIsNull() {
        //GIVEN
        PropertyTypeEnumDto propertyTypeEnumDto = null;
        //THEN
        PropertyTypeEntity propertyTypeEntity = this.propertyTypeService.saveOrFind(propertyTypeEnumDto);
        Assert.isNull(propertyTypeEntity, "Property Type entity should be null");
    }

    @Test
    public void saveOrFind_Find() {
        //GiVEN
        PropertyTypeEnumDto propertyTypeEnumDto = PropertyTypeEnumDto.HOUSE;
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity(propertyTypeEnumDto.name());

        //WHEN
        when(this.propertyTypeRepository.findByDesignation(propertyTypeEnumDto.name())).thenReturn(Optional.of(propertyTypeEntity));

        //THEN
        PropertyTypeEntity propertyTypeEntityResult = this.propertyTypeService.saveOrFind(propertyTypeEnumDto);
        Assertions.assertEquals(propertyTypeEntityResult.getDesignation(), propertyTypeEnumDto.name());
        verifyNoMoreInteractions(propertyTypeRepository);
    }

    @Test
    public void saveOrFind_NotFind() {
        //GiVEN
        PropertyTypeEnumDto propertyTypeEnumDto = PropertyTypeEnumDto.HOUSE;
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity(propertyTypeEnumDto.name());

        //WHEN
        when(this.propertyTypeRepository.findByDesignation(propertyTypeEnumDto.name())).thenReturn(Optional.empty());
        when(this.propertyTypeRepository.save(any(PropertyTypeEntity.class))).thenReturn(propertyTypeEntity);
        when(this.mapperPropertyType.toPropertyType(propertyTypeEnumDto.name())).thenReturn(propertyTypeEntity);

        //THEN
        PropertyTypeEntity propertyType = this.propertyTypeService.saveOrFind(propertyTypeEnumDto);
        Assertions.assertEquals(propertyType.getDesignation(), propertyTypeEnumDto.name());
        verifyNoMoreInteractions(propertyTypeRepository);
        verifyNoMoreInteractions(mapperPropertyType);
    }

}