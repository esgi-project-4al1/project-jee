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
import java.util.Optional;
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

    public void addCar(CarDto carDto) {
        CarEntity carEntity = mapperCar.toCarEntity(carDto);
        carRepository.save(carEntity);
    }

    public void updateCar(Integer id,  UpdateCarDto updateCarDto) throws ResourceNotFoundException {
        Optional<CarEntity> carEntityOptional = carRepository.findById(id);

        if (carEntityOptional.isPresent()) {
            CarEntity carEntity = carEntityOptional.get();

            if (updateCarDto.getRentAmount() != null) {
                carEntity.setRentAmount(updateCarDto.getRentAmount());
                carRepository.save(carEntity);
            }
        } else {
            throw new ResourceNotFoundException("Car with id " + id + " not found");
        }
    }

    public void updateOrAddCar(Integer id, CarDto carDto){
        if (carRepository.existsById(id)) {
            CarEntity carEntity = mapperCar.toCarEntity(id, carDto);
            carRepository.save(carEntity);
        } else {
            addCar(carDto);
        }
    }

    public List<CarDto> getAllRentalCars() {
        List<CarEntity> rentalCarsEntities = carRepository.findAll();
        return rentalCarsEntities.stream()
                .map(mapperCar::toCarDto)
                .collect(Collectors.toList());
    }

    public CarDto getCarById(Integer id) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
        return mapperCar.toCarDto(carEntity);
    }

       public CarDto deleteCarById(Integer id) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
        carRepository.deleteById(id);
        return mapperCar.toCarDto(carEntity);
    }

}
