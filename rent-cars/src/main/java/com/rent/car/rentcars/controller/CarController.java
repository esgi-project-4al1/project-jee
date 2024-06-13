package com.rent.car.rentcars.controller;

import com.rent.car.rentcars.dto.CarDto;
import com.rent.car.rentcars.dto.UpdateCarDto;
import com.rent.car.rentcars.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rent-cars-api/rental-cars")
@Validated
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Void> addCar(@Valid @RequestBody CarDto carDto) {

        carService.saveCar(carDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateCar(@PathVariable Integer id, @Valid @RequestBody UpdateCarDto updateCarDto) {
        carService.updateRentalAmount(id, updateCarDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrAddCar(@PathVariable Integer id, @Valid @RequestBody CarDto carDto) {
        carService.updateCar(id, carDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CarDto>> getAllRentalCars() {
        List<CarDto> rentalCars = carService.getAllRentalCars();
        return new ResponseEntity<>(rentalCars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Integer id) {
        CarDto carDto = carService.getCarById(id);

        return new ResponseEntity<>(carDto, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCarById(@PathVariable Integer id) {
        carService.deleteCarById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }
}