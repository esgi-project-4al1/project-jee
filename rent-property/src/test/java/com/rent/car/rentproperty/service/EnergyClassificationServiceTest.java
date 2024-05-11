package com.rent.car.rentproperty.service;

import com.rent.car.rentproperty.entity.EnergyClassificationEntity;
import com.rent.car.rentproperty.repository.EnergyClassificationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnergyClassificationServiceTest {

    @Mock
    private EnergyClassificationRepository energyClassificationRepository;

    @InjectMocks
    private EnergyClassificationService energyClassificationService;


    @Test
    public void saveOrFind_IfStringIsNull() {
        //GIVEN
        String energyClassification = null;
        //THEN
        EnergyClassificationEntity energyClassificationEntity = this.energyClassificationService.saveOrFind(energyClassification);
        Assert.isNull(energyClassificationEntity, "Energy classification entity should be null");
    }

    @Test
    public void saveOrFind_Find() {
        //GiVEN
        String energyClassification = "A";
        Optional<EnergyClassificationEntity> energyClassificationEntity = Optional.of(new EnergyClassificationEntity(energyClassification));

        //WHEN
        when(this.energyClassificationRepository.findByDesignation(energyClassification)).thenReturn(energyClassificationEntity);

        //THEN
        EnergyClassificationEntity energyClassificationEntityResult = this.energyClassificationService.saveOrFind(energyClassification);
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
        EnergyClassificationEntity energyClassificationEntityResult = this.energyClassificationService.saveOrFind(energyClassification);
        Assertions.assertEquals(energyClassificationEntityResult.getDesignation(), energyClassification);
        verifyNoMoreInteractions(energyClassificationRepository);
    }
}