package com.rent.car.rentproperty.repository;

import com.rent.car.rentproperty.entity.EnergyClassificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnergyClassificationRepository extends JpaRepository<EnergyClassificationEntity, Integer> {

    Optional<EnergyClassificationEntity> findByDesignation(String designation);

}
