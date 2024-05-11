package com.rent.car.rentproperty.service;

import com.rent.car.rentproperty.dto.EnergyClassificationEnumDto;
import com.rent.car.rentproperty.dto.PropertiesDto;
import com.rent.car.rentproperty.dto.PropertyDto;
import com.rent.car.rentproperty.dto.PropertyTypeEnumDto;
import com.rent.car.rentproperty.entity.EnergyClassificationEntity;
import com.rent.car.rentproperty.entity.PropertyEntity;
import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import com.rent.car.rentproperty.exception.NotFoundException;
import com.rent.car.rentproperty.mapper.MapperProperty;
import com.rent.car.rentproperty.repository.PropertyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PropertyServiceTest {

    @Mock
    private PropertyTypeService propertyTypeService;
    @Mock
    private EnergyClassificationService energyClassificationService;
    @Mock
    private MapperProperty mapperProperty;
    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyService propertyService;


    @Test
    public void findRentalProperty_Good(){
        //GIVEN
        int rentalId = 1;
        PropertyEntity propertyEntity = buildPropertyEntityWithId(1);
        PropertiesDto propertiesDto = buildPropertiesDto();

        //WHEN
        when(propertyRepository.findById(rentalId)).thenReturn(Optional.of(propertyEntity));
        when(this.mapperProperty.toPropertyEntity(propertyEntity)).thenReturn(propertiesDto);

        //THEN
        this.propertyService.findRentalProperty(rentalId);
        verifyNoMoreInteractions(mapperProperty);
        verifyNoMoreInteractions(propertyRepository);


    }

    @Test
    public void findRentalProperty_Wrong(){
        //GIVEN
        int rentalId = 1;

        //WHEN
        when(propertyRepository.findById(rentalId)).thenReturn(Optional.empty());

        //THEN
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() ->  propertyService.findRentalProperty(rentalId))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("Rental Property Not Found " + rentalId));
        verifyNoMoreInteractions(propertyRepository);


    }

    @Test
    public void findAllRentalProperties_Empty(){
        //WHEN
        when(propertyRepository.findAll()).thenReturn(Collections.emptyList());

        //THEN
        List<PropertiesDto> propertiesDtos = this.propertyService.findAllRentalProperties();
        Assertions.assertEquals(Collections.emptyList(), propertiesDtos);
    }

    @Test
    public void findAllRentalProperties_one(){
        //GIVEN
        List<PropertiesDto> propertiesDtos = List.of(buildPropertiesDto());
        List<PropertyEntity> propertyEntities = List.of(buildPropertyEntity());

        //WHEN
        when(propertyRepository.findAll()).thenReturn(propertyEntities);
        when(mapperProperty.toPropertyEntity(propertyEntities.get(0))).thenReturn(propertiesDtos.get(0));

        //THEN
        List<PropertiesDto> propertiesDtoList = this.propertyService.findAllRentalProperties();
        verifyNoMoreInteractions(propertyRepository);
        verifyNoMoreInteractions(mapperProperty);
    }

    @Test
    public void saveRentalProperty(){
        //GIVEN
        PropertyDto propertyDto = buildPropertyDto();
        PropertyEntity propertyEntity = buildPropertyEntityWithId(1);
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity("FLAT");
        EnergyClassificationEntity energyClassificationEntity = new EnergyClassificationEntity("A");

        //WHEN
        when(propertyTypeService.saveOrFind(propertyDto.getPropertyType())).thenReturn(propertyTypeEntity);
        when(energyClassificationService.saveOrFind(propertyDto.getEnergyClassification().name())).thenReturn(energyClassificationEntity);
        when(mapperProperty.toPropertyDto(propertyDto)).thenReturn(propertyEntity);
        when(propertyRepository.save(Mockito.any(PropertyEntity.class))).thenReturn(propertyEntity);

        //THEN
        this.propertyService.saveRentalProperty(propertyDto);
        ArgumentCaptor<PropertyEntity> captor = ArgumentCaptor.forClass(PropertyEntity.class);
        verify(propertyRepository).save(captor.capture());
        Assertions.assertEquals(propertyEntity.getAddress(), captor.getValue().getAddress());
        verifyNoMoreInteractions(propertyTypeService);
        verifyNoMoreInteractions(energyClassificationService);
        verifyNoMoreInteractions(mapperProperty);
        verifyNoMoreInteractions(propertyRepository);
    }

    @Test
    public void updateRentalProperty_NotFound(){
        //GIVEN
        int rentalId = 2;

        //WHEN
        when(propertyRepository.findById(rentalId)).thenReturn(Optional.empty());

        //THEN
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() ->  propertyService.findRentalProperty(rentalId))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("Rental Property Not Found " + rentalId));

        verifyNoMoreInteractions(propertyRepository);
    }

    @Test
    public void updateRentalProperty_Good(){
        //GIVEN
        int rentalId = 2;
        PropertyDto propertyDto = buildPropertyDto();
        PropertyEntity propertyEntity = buildPropertyEntityWithId(rentalId);
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity("FLAT");
        EnergyClassificationEntity energyClassificationEntity = new EnergyClassificationEntity("A");

        //WHEN
        when(propertyRepository.findById(rentalId)).thenReturn(Optional.of(propertyEntity));
        when(propertyTypeService.saveOrFind(propertyDto.getPropertyType())).thenReturn(propertyTypeEntity);
        when(energyClassificationService.saveOrFind(propertyDto.getEnergyClassification().name())).thenReturn(energyClassificationEntity);
        when(mapperProperty.toPropertyDto(propertyDto, rentalId)).thenReturn(propertyEntity);
        when(propertyRepository.save(propertyEntity)).thenReturn(propertyEntity);
        when(mapperProperty.mapTo(propertyEntity, propertyDto, energyClassificationEntity, propertyTypeEntity)).thenReturn(propertyEntity);


        //THEN
        this.propertyService.updateRentalProperty(rentalId, propertyDto);
        verifyNoMoreInteractions(propertyRepository);
        verifyNoMoreInteractions(propertyTypeService);
        verifyNoMoreInteractions(energyClassificationService);
        verifyNoMoreInteractions(mapperProperty);
        verifyNoMoreInteractions(propertyRepository);
        verifyNoMoreInteractions(mapperProperty);
    }


    @Test
    public void updateRentalAmount_NotFound(){
        //GIVEN
        int rentalId = 2;
        Double rentalAmount = 200.2;

        //WHEN
        when(propertyRepository.findById(rentalId)).thenReturn(Optional.empty());

        //THEN
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() ->  propertyService.updateRentalAmount(rentalId, rentalAmount))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("Rental Property Not Found " + rentalId));

        verifyNoMoreInteractions(propertyRepository);
    }

    @Test
    public void updateRentalAmount_Good(){
        //GIVEN
        int rentalId = 2;
        Double rentalAmount = 200.2;
        PropertyEntity propertyEntity = buildPropertyEntityWithId(rentalId);
        PropertyEntity propertyEntityExpected = buildPropertyEntityWithId(rentalId);
        propertyEntityExpected.setRentAmount(rentalAmount);

        //WHEN
        when(propertyRepository.findById(rentalId)).thenReturn(Optional.of(propertyEntity));
        when(propertyRepository.save(any(PropertyEntity.class))).thenReturn(propertyEntityExpected);


        //THEN
        this.propertyService.updateRentalAmount(rentalId, rentalAmount);
        ArgumentCaptor<PropertyEntity> captor = ArgumentCaptor.forClass(PropertyEntity.class);
        verify(propertyRepository).save(captor.capture());
        Assertions.assertEquals(propertyEntityExpected.getRentAmount(), captor.getValue().getRentAmount());
        verifyNoMoreInteractions(propertyRepository);
    }

    private PropertyEntity buildPropertyEntity(){
        return new PropertyEntity(
                "hello",
                "hello",
                "test",
                new PropertyTypeEntity("FLAT"),
                280.10,
                120.0,
                145.0,
                10,
                10,
                10,
                "2024",
                new EnergyClassificationEntity("A"),
                true,
                true,
                true,
                true
        );
    }

    private PropertyEntity buildPropertyEntityWithId(int id){
        return new PropertyEntity(
                id,
                "hello",
                "hello",
                "test",
                new PropertyTypeEntity("FLAT"),
                280.10,
                120.0,
                145.0,
                10,
                10,
                10,
                "2024",
                new EnergyClassificationEntity("A"),
                true,
                true,
                true,
                true
        );
    }

    private PropertyDto buildPropertyDto(){
        return new PropertyDto(
                "hello",
                "hello",
                "test",
                PropertyTypeEnumDto.FLAT,
                280.10,
                120.0,
                145.0,
                10,
                10,
                10,
                "2024",
                EnergyClassificationEnumDto.A,
                true,
                true,
                true,
                true
        );
    }

    private PropertiesDto buildPropertiesDto(){
        return new PropertiesDto(
                "hello",
                145.0,
                "hello",
                "test",
                280.10,
                120.0,
                "hello"
        );
    }


}