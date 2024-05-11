package com.rent.car.rentproperty.service;


import com.rent.car.rentproperty.entity.EnergyClassificationEntity;
import com.rent.car.rentproperty.repository.EnergyClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnergyClassificationService {

    private final EnergyClassificationRepository energyClassificationRepository;

    @Autowired
    public EnergyClassificationService(EnergyClassificationRepository energyClassificationRepository) {
        this.energyClassificationRepository = energyClassificationRepository;
    }

    public EnergyClassificationEntity saveOrFind(String energyClassification){
        if (energyClassification== null) return null;
        Optional<EnergyClassificationEntity> energyClassificationEntity = this.energyClassificationRepository.findByDesignation(energyClassification);
        return energyClassificationEntity.orElseGet(() -> this.energyClassificationRepository.save(
                new EnergyClassificationEntity(energyClassification)
        ));
    }
}
