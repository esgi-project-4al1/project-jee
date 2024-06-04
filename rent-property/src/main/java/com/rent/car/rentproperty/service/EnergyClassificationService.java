package com.rent.car.rentproperty.service;


import com.rent.car.rentproperty.entity.EnergyClassificationEntity;
import com.rent.car.rentproperty.exception.NullEnergyClassificationException;
import com.rent.car.rentproperty.exception.NullRentalPropertyEnumException;
import com.rent.car.rentproperty.repository.EnergyClassificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnergyClassificationService {

    private final EnergyClassificationRepository energyClassificationRepository;

    public EnergyClassificationService(EnergyClassificationRepository energyClassificationRepository) {
        this.energyClassificationRepository = energyClassificationRepository;
    }

    public EnergyClassificationEntity updateEnergyClassification(String energyClassification){
        if (energyClassification== null) throw  new NullEnergyClassificationException("null energyClassification here is error");
        Optional<EnergyClassificationEntity> energyClassificationEntity = this.energyClassificationRepository.findByDesignation(energyClassification);
        return energyClassificationEntity.orElseGet(() -> this.energyClassificationRepository.save(
                new EnergyClassificationEntity(energyClassification)
        ));
    }
}
