package com.rent.car.rentcars.mapper;

import com.rent.car.rentcars.dto.CarDto;
import com.rent.car.rentcars.entity.CarEntity;
import org.springframework.stereotype.Component;

@Component

public class CarMapper {
    public CarEntity toCarEntity(CarDto carDto) {

        CarEntity carEntity = new CarEntity();
        carEntity.setBrand(carDto.getBrand());
        carEntity.setModel(carDto.getModel());
        carEntity.setRentAmount(carDto.getRentAmount());
        carEntity.setSecurityDepositAmount(carDto.getSecurityDepositAmount());
        carEntity.setNumberOfSeats(carDto.getNumberOfSeats());
        carEntity.setNumberOfDoors(carDto.getNumberOfDoors());
        carEntity.setHasAirConditioning(carDto.getHasAirConditioning());
        return carEntity;
    }
    public CarEntity toCarEntity(Integer id ,CarDto carDto) {

        CarEntity carEntity = new CarEntity();
        carEntity.setId(id);
        carEntity.setBrand(carDto.getBrand());
        carEntity.setModel(carDto.getModel());
        carEntity.setRentAmount(carDto.getRentAmount());
        carEntity.setSecurityDepositAmount(carDto.getSecurityDepositAmount());
        carEntity.setNumberOfSeats(carDto.getNumberOfSeats());
        carEntity.setNumberOfDoors(carDto.getNumberOfDoors());
        carEntity.setHasAirConditioning(carDto.getHasAirConditioning());
        return carEntity;
    }


    public CarDto toCarDto(CarEntity carEntity) {
        CarDto carDto = new CarDto();
        carDto.setBrand(carEntity.getBrand());
        carDto.setModel(carEntity.getModel());
        carDto.setRentAmount(carEntity.getRentAmount());
        carDto.setSecurityDepositAmount(carEntity.getSecurityDepositAmount());
        carDto.setNumberOfSeats(carEntity.getNumberOfSeats());
        carDto.setNumberOfDoors(carEntity.getNumberOfDoors());
        carDto.setHasAirConditioning(carEntity.getHasAirConditioning());
        return carDto;
    }
}
