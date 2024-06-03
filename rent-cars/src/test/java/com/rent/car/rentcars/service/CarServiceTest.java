package com.rent.car.rentcars.service;

import com.rent.car.rentcars.dto.CarDto;
import com.rent.car.rentcars.dto.UpdateCarDto;
import com.rent.car.rentcars.entity.CarEntity;
import com.rent.car.rentcars.exception.ResourceNotFoundException;
import com.rent.car.rentcars.mapper.CarMapper;
import com.rent.car.rentcars.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCar() {
        CarDto carDto = new CarDto();
        CarEntity carEntity = new CarEntity();
        when(carMapper.toCarEntity(carDto)).thenReturn(carEntity);

        carService.addCar(carDto);

        verify(carRepository, times(1)).save(carEntity);
    }

    @Test
    void testUpdateCar_Success() {
        Integer id = 1;
        UpdateCarDto updateCarDto = new UpdateCarDto();
        updateCarDto.setRentAmount(1000.0);
        CarEntity carEntity = new CarEntity();
        when(carRepository.findById(id)).thenReturn(Optional.of(carEntity));

        carService.updateCar(id, updateCarDto);

        assertEquals(1000.0, carEntity.getRentAmount());
        verify(carRepository, times(1)).save(carEntity);
    }

    @Test
    void testUpdateCar_NotFound() {
        Integer id = 1;
        UpdateCarDto updateCarDto = new UpdateCarDto();
        when(carRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> carService.updateCar(id, updateCarDto));
    }

    @Test
    void testUpdateOrAddCar_NewCar() {
        Integer id = 1;
        CarDto carDto = new CarDto();
        when(carRepository.existsById(id)).thenReturn(false);

        carService.updateOrAddCar(id, carDto);

        verify(carMapper, times(1)).toCarEntity( carDto);
        verify(carRepository, times(1)).save(any());
    }

    @Test
    void testGetAllRentalCars() {
        List<CarEntity> carEntities = new ArrayList<>();
        when(carRepository.findAll()).thenReturn(carEntities);

        List<CarDto> result = carService.getAllRentalCars();

        assertEquals(carEntities.size(), result.size());
        verify(carMapper, times(carEntities.size())).toCarDto(any());
    }

    @Test
    void testGetCarById_Success() {
        Integer id = 1;
        CarEntity carEntity = new CarEntity();
        when(carRepository.findById(id)).thenReturn(Optional.of(carEntity));
        CarDto expectedCarDto = new CarDto(); // create expected DTO here
        when(carMapper.toCarDto(carEntity)).thenReturn(expectedCarDto);

        CarDto result = carService.getCarById(id);

        assertEquals(expectedCarDto, result);
    }

    @Test
    void testGetCarById_NotFound() {
        Integer id = 1;
        when(carRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> carService.getCarById(id));
    }

}