package com.rent.car.rentproperty.repository;

import com.rent.car.rentproperty.entity.RentalPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalPropertyRepository extends JpaRepository<RentalPropertyEntity, Integer> {

}
