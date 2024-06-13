package com.rent.car.rentcars.mapper;

import com.rent.car.rentcars.dto.CarDto;
import com.rent.car.rentcars.dto.UpdateCarDto;
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


    public CarEntity toCarEntity(Integer id , UpdateCarDto updateCarDto, CarEntity carEntity) {
        carEntity.setId(id);
        carEntity.setBrand(carEntity.getBrand());
        carEntity.setModel(carEntity.getModel());
        carEntity.setRentAmount(updateCarDto.getRentAmount());
        carEntity.setSecurityDepositAmount(carEntity.getSecurityDepositAmount());
        carEntity.setNumberOfSeats(carEntity.getNumberOfSeats());
        carEntity.setNumberOfDoors(carEntity.getNumberOfDoors());
        carEntity.setHasAirConditioning(carEntity.getHasAirConditioning());
        return carEntity;
    }


    public CarEntity toCarEntity(int id, CarDto carDto){
        return new CarEntity(
                id,
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getRentAmount(),
                carDto.getSecurityDepositAmount(),
                carDto.getNumberOfSeats(),
                carDto.getNumberOfDoors(),
                carDto.getHasAirConditioning()
        );
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
