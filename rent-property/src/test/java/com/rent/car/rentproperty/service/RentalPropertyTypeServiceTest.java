package com.rent.car.rentproperty.service;

import com.rent.car.rentproperty.dto.RentalPropertyTypeEnumDto;
import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import com.rent.car.rentproperty.mapper.MapperPropertyType;
import com.rent.car.rentproperty.repository.RentalPropertyTypeRepository;
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
class RentalPropertyTypeServiceTest {
    
    @Mock
    private RentalPropertyTypeRepository rentalPropertyTypeRepository;
    @Mock
    private MapperPropertyType mapperPropertyType;
    @InjectMocks
    private RentalPropertyTypeService rentalPropertyTypeService;

    @Test
    public void saveOrFind_IfStringIsNull() {
        //GIVEN
        RentalPropertyTypeEnumDto rentalPropertyTypeEnumDto = null;
        //THEN
        PropertyTypeEntity propertyTypeEntity = this.rentalPropertyTypeService.saveOrFind(rentalPropertyTypeEnumDto);
        Assert.isNull(propertyTypeEntity, "Property Type entity should be null");
    }

    @Test
    public void saveOrFind_Find() {
        //GiVEN
        RentalPropertyTypeEnumDto rentalPropertyTypeEnumDto = RentalPropertyTypeEnumDto.HOUSE;
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity(rentalPropertyTypeEnumDto.name());

        //WHEN
        when(this.rentalPropertyTypeRepository.findByDesignation(rentalPropertyTypeEnumDto.name())).thenReturn(Optional.of(propertyTypeEntity));

        //THEN
        PropertyTypeEntity propertyTypeEntityResult = this.rentalPropertyTypeService.saveOrFind(rentalPropertyTypeEnumDto);
        Assertions.assertEquals(propertyTypeEntityResult.getDesignation(), rentalPropertyTypeEnumDto.name());
        verifyNoMoreInteractions(rentalPropertyTypeRepository);
    }

    @Test
    public void saveOrFind_NotFind() {
        //GiVEN
        RentalPropertyTypeEnumDto rentalPropertyTypeEnumDto = RentalPropertyTypeEnumDto.HOUSE;
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity(rentalPropertyTypeEnumDto.name());

        //WHEN
        when(this.rentalPropertyTypeRepository.findByDesignation(rentalPropertyTypeEnumDto.name())).thenReturn(Optional.empty());
        when(this.rentalPropertyTypeRepository.save(any(PropertyTypeEntity.class))).thenReturn(propertyTypeEntity);
        when(this.mapperPropertyType.toPropertyType(rentalPropertyTypeEnumDto.name())).thenReturn(propertyTypeEntity);

        //THEN
        PropertyTypeEntity propertyType = this.rentalPropertyTypeService.saveOrFind(rentalPropertyTypeEnumDto);
        Assertions.assertEquals(propertyType.getDesignation(), rentalPropertyTypeEnumDto.name());
        verifyNoMoreInteractions(rentalPropertyTypeRepository);
        verifyNoMoreInteractions(mapperPropertyType);
    }

}