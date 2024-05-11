package com.rent.car.rentproperty.repository;

import com.rent.car.rentproperty.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Integer> {

}
