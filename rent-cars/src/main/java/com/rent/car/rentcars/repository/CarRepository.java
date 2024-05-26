package com.rent.car.rentcars.repository;

import com.rent.car.rentcars.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {
}
