package com.rent.car.rentcars.mapper;

import com.rent.car.rentcars.dto.CarDto;
import com.rent.car.rentcars.entity.CarEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarMapperTest {

    private CarMapper carMapper;

    @BeforeEach
    void setUp() {
        carMapper = new CarMapper();
    }

    @Test
    void testToCarEntity() {
        CarDto carDto = new CarDto();
        carDto.setBrand("Toyota");
        carDto.setModel("Corolla");
        carDto.setRentAmount(50.0);
        carDto.setSecurityDepositAmount(100.0);
        carDto.setNumberOfSeats(5);
        carDto.setNumberOfDoors(4);
        carDto.setHasAirConditioning(true);

        CarEntity carEntity = carMapper.toCarEntity(carDto);

        assertEquals(carDto.getBrand(), carEntity.getBrand());
        assertEquals(carDto.getModel(), carEntity.getModel());
        assertEquals(carDto.getRentAmount(), carEntity.getRentAmount());
        assertEquals(carDto.getSecurityDepositAmount(), carEntity.getSecurityDepositAmount());
        assertEquals(carDto.getNumberOfSeats(), carEntity.getNumberOfSeats());
        assertEquals(carDto.getNumberOfDoors(), carEntity.getNumberOfDoors());
        assertEquals(carDto.getHasAirConditioning(), carEntity.getHasAirConditioning());
    }

    @Test
    void testToCarEntityWithId() {
        CarDto carDto = new CarDto();
        carDto.setBrand("Toyota");
        carDto.setModel("Corolla");
        carDto.setRentAmount(50.0);
        carDto.setSecurityDepositAmount(100.0);
        carDto.setNumberOfSeats(5);
        carDto.setNumberOfDoors(4);
        carDto.setHasAirConditioning(true);

        Integer id = 1;
        CarEntity carEntity = carMapper.toCarEntity(id, carDto);

        assertEquals(id, carEntity.getId());
        assertEquals(carDto.getBrand(), carEntity.getBrand());
        assertEquals(carDto.getModel(), carEntity.getModel());
        assertEquals(carDto.getRentAmount(), carEntity.getRentAmount());
        assertEquals(carDto.getSecurityDepositAmount(), carEntity.getSecurityDepositAmount());
        assertEquals(carDto.getNumberOfSeats(), carEntity.getNumberOfSeats());
        assertEquals(carDto.getNumberOfDoors(), carEntity.getNumberOfDoors());
        assertEquals(carDto.getHasAirConditioning(), carEntity.getHasAirConditioning());
    }

    @Test
    void testToCarDto() {
        CarEntity carEntity = new CarEntity();
        carEntity.setBrand("Toyota");
        carEntity.setModel("Corolla");
        carEntity.setRentAmount(50.0);
        carEntity.setSecurityDepositAmount(100.0);
        carEntity.setNumberOfSeats(5);
        carEntity.setNumberOfDoors(4);
        carEntity.setHasAirConditioning(true);

        CarDto carDto = carMapper.toCarDto(carEntity);

        assertEquals(carEntity.getBrand(), carDto.getBrand());
        assertEquals(carEntity.getModel(), carDto.getModel());
        assertEquals(carEntity.getRentAmount(), carDto.getRentAmount());
        assertEquals(carEntity.getSecurityDepositAmount(), carDto.getSecurityDepositAmount());
        assertEquals(carEntity.getNumberOfSeats(), carDto.getNumberOfSeats());
        assertEquals(carEntity.getNumberOfDoors(), carDto.getNumberOfDoors());
        assertEquals(carEntity.getHasAirConditioning(), carDto.getHasAirConditioning());
    }
}
