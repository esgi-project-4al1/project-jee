package com.rent.car.rentproperty.repository;

import com.rent.car.rentproperty.entity.PropertyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyTypeEntity, Integer> {

    Optional<PropertyTypeEntity> findByDesignation(String designation);
}
