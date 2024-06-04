package com.rent.car.rentproperty.service;

import com.rent.car.rentproperty.dto.EnergyClassificationEnumDto;
import com.rent.car.rentproperty.dto.RentalPropertiesDto;
import com.rent.car.rentproperty.dto.RentalPropertyDto;
import com.rent.car.rentproperty.dto.RentalPropertyTypeEnumDto;
import com.rent.car.rentproperty.entity.EnergyClassificationEntity;
import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import com.rent.car.rentproperty.entity.RentalPropertyEntity;
import com.rent.car.rentproperty.exception.NotFoundRentalPropertyException;
import com.rent.car.rentproperty.mapper.MapperRentalProperty;
import com.rent.car.rentproperty.repository.RentalPropertyRepository;
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
class RentalPropertyServiceTest {

    @Mock
    private RentalPropertyTypeService rentalPropertyTypeService;
    @Mock
    private EnergyClassificationService energyClassificationService;
    @Mock
    private MapperRentalProperty mapperRentalProperty;
    @Mock
    private RentalPropertyRepository rentalPropertyRepository;

    @InjectMocks
    private RentalPropertyService rentalPropertyService;


    @Test
    public void findRentalProperty_Good() {
        //GIVEN
        int rentalId = 1;
        RentalPropertyEntity rentalPropertyEntity = buildPropertyEntityWithId(1);
        RentalPropertiesDto rentalPropertiesDto = buildPropertiesDto();

        //WHEN
        when(rentalPropertyRepository.findById(rentalId)).thenReturn(Optional.of(rentalPropertyEntity));
        when(this.mapperRentalProperty.toPropertyEntity(rentalPropertyEntity)).thenReturn(rentalPropertiesDto);

        //THEN
        this.rentalPropertyService.findRentalProperty(rentalId);
        verifyNoMoreInteractions(mapperRentalProperty);
        verifyNoMoreInteractions(rentalPropertyRepository);


    }

    @Test
    public void findRentalProperty_Wrong() {
        //GIVEN
        int rentalId = 1;

        //WHEN
        when(rentalPropertyRepository.findById(rentalId)).thenReturn(Optional.empty());

        //THEN
        assertThatExceptionOfType(NotFoundRentalPropertyException.class)
                .isThrownBy(() -> rentalPropertyService.findRentalProperty(rentalId))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("Rental Property Not Found " + rentalId));
        verifyNoMoreInteractions(rentalPropertyRepository);


    }

    @Test
    public void findAllRentalProperties_Empty() {
        //WHEN
        when(rentalPropertyRepository.findAll()).thenReturn(Collections.emptyList());

        //THEN
        List<RentalPropertiesDto> rentalPropertiesDtos = this.rentalPropertyService.findAllRentalProperties();
        Assertions.assertEquals(Collections.emptyList(), rentalPropertiesDtos);
    }

    @Test
    public void findAllRentalProperties_one() {
        //GIVEN
        List<RentalPropertiesDto> rentalPropertiesDtos = List.of(buildPropertiesDto());
        List<RentalPropertyEntity> propertyEntities = List.of(buildPropertyEntity());

        //WHEN
        when(rentalPropertyRepository.findAll()).thenReturn(propertyEntities);
        when(mapperRentalProperty.toPropertyEntity(propertyEntities.get(0))).thenReturn(rentalPropertiesDtos.get(0));

        //THEN
        this.rentalPropertyService.findAllRentalProperties();
        verifyNoMoreInteractions(rentalPropertyRepository);
        verifyNoMoreInteractions(mapperRentalProperty);
    }

    @Test
    public void saveRentalProperty() {
        //GIVEN
        RentalPropertyDto rentalPropertyDto = buildPropertyDto();
        RentalPropertyEntity rentalPropertyEntity = buildPropertyEntityWithId(1);
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity("FLAT");
        EnergyClassificationEntity energyClassificationEntity = new EnergyClassificationEntity("A");

        //WHEN
        when(rentalPropertyTypeService.updatePropertyTypeEnum(rentalPropertyDto.getPropertyType())).thenReturn(propertyTypeEntity);
        when(energyClassificationService.updateEnergyClassification(rentalPropertyDto.getEnergyClassification().name())).thenReturn(energyClassificationEntity);
        when(mapperRentalProperty.toPropertyDto(rentalPropertyDto)).thenReturn(rentalPropertyEntity);
        when(rentalPropertyRepository.save(Mockito.any(RentalPropertyEntity.class))).thenReturn(rentalPropertyEntity);

        //THEN
        this.rentalPropertyService.saveRentalProperty(rentalPropertyDto);
        ArgumentCaptor<RentalPropertyEntity> captor = ArgumentCaptor.forClass(RentalPropertyEntity.class);
        verify(rentalPropertyRepository).save(captor.capture());
        Assertions.assertEquals(rentalPropertyEntity.getAddress(), captor.getValue().getAddress());
        verifyNoMoreInteractions(rentalPropertyTypeService);
        verifyNoMoreInteractions(energyClassificationService);
        verifyNoMoreInteractions(mapperRentalProperty);
        verifyNoMoreInteractions(rentalPropertyRepository);
    }

    @Test
    public void updateRentalProperty_NotFound() {
        //GIVEN
        int rentalId = 2;

        //WHEN
        when(rentalPropertyRepository.findById(rentalId)).thenReturn(Optional.empty());

        //THEN
        assertThatExceptionOfType(NotFoundRentalPropertyException.class)
                .isThrownBy(() -> rentalPropertyService.findRentalProperty(rentalId))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("Rental Property Not Found " + rentalId));

        verifyNoMoreInteractions(rentalPropertyRepository);
    }

    @Test
    public void updateRentalProperty_Good() {
        //GIVEN
        int rentalId = 2;
        RentalPropertyDto rentalPropertyDto = buildPropertyDto();
        RentalPropertyEntity rentalPropertyEntity = buildPropertyEntityWithId(rentalId);
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity("FLAT");
        EnergyClassificationEntity energyClassificationEntity = new EnergyClassificationEntity("A");

        //WHEN
        when(rentalPropertyRepository.findById(rentalId)).thenReturn(Optional.of(rentalPropertyEntity));
        when(rentalPropertyTypeService.updatePropertyTypeEnum(rentalPropertyDto.getPropertyType())).thenReturn(propertyTypeEntity);
        when(energyClassificationService.updateEnergyClassification(rentalPropertyDto.getEnergyClassification().name())).thenReturn(energyClassificationEntity);
        when(mapperRentalProperty.toPropertyDto(rentalPropertyDto, rentalId)).thenReturn(rentalPropertyEntity);
        when(rentalPropertyRepository.save(rentalPropertyEntity)).thenReturn(rentalPropertyEntity);
        when(mapperRentalProperty.mapTo(rentalPropertyEntity, rentalPropertyDto, energyClassificationEntity, propertyTypeEntity)).thenReturn(rentalPropertyEntity);


        //THEN
        this.rentalPropertyService.updateRentalProperty(rentalId, rentalPropertyDto);
        verifyNoMoreInteractions(rentalPropertyRepository);
        verifyNoMoreInteractions(rentalPropertyTypeService);
        verifyNoMoreInteractions(energyClassificationService);
        verifyNoMoreInteractions(mapperRentalProperty);
        verifyNoMoreInteractions(rentalPropertyRepository);
        verifyNoMoreInteractions(mapperRentalProperty);
    }


    @Test
    public void updateRentalAmount_NotFound() {
        //GIVEN
        int rentalId = 2;
        Double rentalAmount = 200.2;

        //WHEN
        when(rentalPropertyRepository.findById(rentalId)).thenReturn(Optional.empty());

        //THEN
        assertThatExceptionOfType(NotFoundRentalPropertyException.class)
                .isThrownBy(() -> rentalPropertyService.updateRentalAmount(rentalId, rentalAmount))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("Rental Property Not Found " + rentalId));

        verifyNoMoreInteractions(rentalPropertyRepository);
    }

    @Test
    public void updateRentalAmount_Good() {
        //GIVEN
        int rentalId = 2;
        Double rentalAmount = 200.2;
        RentalPropertyEntity rentalPropertyEntity = buildPropertyEntityWithId(rentalId);
        RentalPropertyEntity rentalPropertyEntityExpected = buildPropertyEntityWithId(rentalId);
        rentalPropertyEntityExpected.setRentAmount(rentalAmount);

        //WHEN
        when(rentalPropertyRepository.findById(rentalId)).thenReturn(Optional.of(rentalPropertyEntity));
        when(rentalPropertyRepository.save(any(RentalPropertyEntity.class))).thenReturn(rentalPropertyEntityExpected);


        //THEN
        this.rentalPropertyService.updateRentalAmount(rentalId, rentalAmount);
        ArgumentCaptor<RentalPropertyEntity> captor = ArgumentCaptor.forClass(RentalPropertyEntity.class);
        verify(rentalPropertyRepository).save(captor.capture());
        Assertions.assertEquals(rentalPropertyEntityExpected.getRentAmount(), captor.getValue().getRentAmount());
        verifyNoMoreInteractions(rentalPropertyRepository);
    }

    private RentalPropertyEntity buildPropertyEntity() {
        return new RentalPropertyEntity(
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

    private RentalPropertyEntity buildPropertyEntityWithId(int id) {
        return new RentalPropertyEntity(
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

    private RentalPropertyDto buildPropertyDto() {
        return new RentalPropertyDto(
                "hello",
                "hello",
                "test",
                RentalPropertyTypeEnumDto.FLAT,
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

    private RentalPropertiesDto buildPropertiesDto() {
        return new RentalPropertiesDto(
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