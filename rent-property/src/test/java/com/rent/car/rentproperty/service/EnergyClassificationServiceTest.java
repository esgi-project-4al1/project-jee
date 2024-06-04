package com.rent.car.rentproperty.service;

import com.rent.car.rentproperty.entity.EnergyClassificationEntity;
import com.rent.car.rentproperty.exception.NullEnergyClassificationException;
import com.rent.car.rentproperty.exception.NullRentalPropertyEnumException;
import com.rent.car.rentproperty.repository.EnergyClassificationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnergyClassificationServiceTest {

    @Mock
    private EnergyClassificationRepository energyClassificationRepository;

    @InjectMocks
    private EnergyClassificationService energyClassificationService;


    @Test
    public void saveOrFind_IfStringIsNull() {
        //THEN
        assertThatExceptionOfType(NullEnergyClassificationException.class)
                .isThrownBy(() -> this.energyClassificationService.updateEnergyClassification(null))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("null energyClassification here is error"));
    }

    @Test
    public void saveOrFind_Find() {
        //GiVEN
        String energyClassification = "A";
        Optional<EnergyClassificationEntity> energyClassificationEntity = Optional.of(new EnergyClassificationEntity(energyClassification));

        //WHEN
        when(this.energyClassificationRepository.findByDesignation(energyClassification)).thenReturn(energyClassificationEntity);

        //THEN
        EnergyClassificationEntity energyClassificationEntityResult = this.energyClassificationService.updateEnergyClassification(energyClassification);
        Assertions.assertEquals(energyClassificationEntityResult.getDesignation(), energyClassification);
        verifyNoMoreInteractions(energyClassificationRepository);
    }

    @Test
    public void saveOrFind_NotFind() {
        //GiVEN
        String energyClassification = "A";
        EnergyClassificationEntity energyClassificationEntity = new EnergyClassificationEntity(energyClassification);

        //WHEN
        when(this.energyClassificationRepository.findByDesignation(energyClassification)).thenReturn(Optional.empty());
        when(this.energyClassificationRepository.save(any(EnergyClassificationEntity.class))).thenReturn(energyClassificationEntity);

        //THEN
        EnergyClassificationEntity energyClassificationEntityResult = this.energyClassificationService.updateEnergyClassification(energyClassification);
        Assertions.assertEquals(energyClassificationEntityResult.getDesignation(), energyClassification);
        verifyNoMoreInteractions(energyClassificationRepository);
    }
}