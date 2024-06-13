package com.rent.car.rentcars.service;

import com.rent.car.rentcars.dto.CarDto;
import com.rent.car.rentcars.dto.UpdateCarDto;
import com.rent.car.rentcars.entity.CarEntity;
import com.rent.car.rentcars.exception.ResourceNotFoundException;
import com.rent.car.rentcars.mapper.CarMapper;
import com.rent.car.rentcars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarMapper mapperCar;
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarMapper mapperCar, CarRepository carRepository) {
        this.mapperCar = mapperCar;
        this.carRepository = carRepository;
    }


    public void updateRentalAmount(int id, UpdateCarDto updateCarDto) {
        CarEntity carEntitySave = this.carRepository.findById(id)
                .map(carEntity -> this.mapperCar.toCarEntity(id, updateCarDto, carEntity))
                .orElseThrow(() -> new ResourceNotFoundException("not found car by this id " + id));
        this.carRepository.save(carEntitySave);
    }

    public void saveCar(CarDto carDto) {
        CarEntity carEntity = this.mapperCar.toCarEntity(carDto);
        this.carRepository.save(carEntity);
    }

    public List<CarDto> getAllRentalCars() {
        List<CarEntity> rentalCarsEntities = carRepository.findAll();
        return rentalCarsEntities.stream()
                .map(mapperCar::toCarDto)
                .collect(Collectors.toList());
    }

    public CarDto getCarById(int id) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
        return mapperCar.toCarDto(carEntity);
    }

    public void updateCar(int id, CarDto carDto){
        this.carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        CarEntity carEntity = this.mapperCar.toCarEntity(id, carDto);
        this.carRepository.save(carEntity);
    }

    public void deleteCarById(Integer id) {
        carRepository.deleteById(id);
    }

}
