package com.rent.car.rentproperty.service;

import com.rent.car.rentproperty.dto.RentalPropertyTypeEnumDto;
import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import com.rent.car.rentproperty.exception.NullRentalPropertyEnumException;
import com.rent.car.rentproperty.mapper.MapperRentalPropertyType;
import com.rent.car.rentproperty.repository.RentalPropertyTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RentalPropertyTypeServiceTest {

    @Mock
    private RentalPropertyTypeRepository rentalPropertyTypeRepository;
    @Mock
    private MapperRentalPropertyType mapperRentalPropertyType;
    @InjectMocks
    private RentalPropertyTypeService rentalPropertyTypeService;

    @Test
    public void updatePropertyTypeEnum_IfStringIsNull() {
        //THEN
        assertThatExceptionOfType(NullRentalPropertyEnumException.class)
                .isThrownBy(() -> this.rentalPropertyTypeService.updatePropertyTypeEnum(null))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("error RentalPropertyEnum is null"));
    }

    @Test
    public void updatePropertyTypeEnum_Find() {
        //GiVEN
        RentalPropertyTypeEnumDto rentalPropertyTypeEnumDto = RentalPropertyTypeEnumDto.HOUSE;
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity(rentalPropertyTypeEnumDto.name());

        //WHEN
        when(this.rentalPropertyTypeRepository.findByDesignation(rentalPropertyTypeEnumDto.name())).thenReturn(Optional.of(propertyTypeEntity));

        //THEN
        PropertyTypeEntity propertyTypeEntityResult = this.rentalPropertyTypeService.updatePropertyTypeEnum(rentalPropertyTypeEnumDto);
        Assertions.assertEquals(propertyTypeEntityResult.getDesignation(), rentalPropertyTypeEnumDto.name());
        verifyNoMoreInteractions(rentalPropertyTypeRepository);
    }

    @Test
    public void updatePropertyTypeEnum_Save() {
        //GiVEN
        RentalPropertyTypeEnumDto rentalPropertyTypeEnumDto = RentalPropertyTypeEnumDto.HOUSE;
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity(rentalPropertyTypeEnumDto.name());

        //WHEN
        when(this.rentalPropertyTypeRepository.findByDesignation(rentalPropertyTypeEnumDto.name())).thenReturn(Optional.empty());
        when(this.rentalPropertyTypeRepository.save(any(PropertyTypeEntity.class))).thenReturn(propertyTypeEntity);
        when(this.mapperRentalPropertyType.toPropertyType(rentalPropertyTypeEnumDto.name())).thenReturn(propertyTypeEntity);

        //THEN
        PropertyTypeEntity propertyType = this.rentalPropertyTypeService.updatePropertyTypeEnum(rentalPropertyTypeEnumDto);
        Assertions.assertEquals(propertyType.getDesignation(), rentalPropertyTypeEnumDto.name());
        verifyNoMoreInteractions(rentalPropertyTypeRepository);
        verifyNoMoreInteractions(mapperRentalPropertyType);
    }

}