package com.rent.car.rentcars.service;

import com.rent.car.rentcars.dto.CarDto;
import com.rent.car.rentcars.dto.UpdateCarDto;
import com.rent.car.rentcars.entity.CarEntity;
import com.rent.car.rentcars.exception.ResourceNotFoundException;
import com.rent.car.rentcars.mapper.CarMapper;
import com.rent.car.rentcars.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @Test
    void testSaveCar() {
        CarDto carDto = new CarDto();
        CarEntity carEntity = new CarEntity();
        when(carMapper.toCarEntity(carDto)).thenReturn(carEntity);

        carService.saveCar(carDto);

        verify(carRepository, times(1)).save(carEntity);
        verify(carMapper, times(1)).toCarEntity(carDto);
        verifyNoMoreInteractions(carRepository, carMapper);
    }

    @Test
    void testUpdateCarRentalAmount_Success() {
        UpdateCarDto updateCarDto = new UpdateCarDto();
        updateCarDto.setRentAmount(100.0);
        CarEntity carEntity = new CarEntity();
        carEntity.setRentAmount(100.0);
        when(carRepository.findById(eq(1))).thenReturn(Optional.of(carEntity));
        when(carMapper.toCarEntity(eq(1), any(), any() )).thenReturn(carEntity);
        carService.updateRentalAmount(1, updateCarDto);

        assertEquals(100.0, carEntity.getRentAmount());
        verify(carRepository).findById(eq(1));
        verify(carRepository).save(carEntity);
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    void testUpdateRentalAmount_NotFound() {
        UpdateCarDto updateCarDto = new UpdateCarDto();
        when(carRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> carService.updateRentalAmount(1, updateCarDto));

        verify(carRepository, times(1)).findById(anyInt());
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    void testUpdateCar_Update() {
        CarDto carDto = new CarDto();
        CarEntity carEntity = new CarEntity();
        when(carRepository.findById(anyInt())).thenReturn(Optional.of(carEntity));
        when(carMapper.toCarEntity(anyInt(), any(CarDto.class))).thenReturn(carEntity);

        carService.updateCar(1, carDto);

        verify(carRepository, times(1)).findById(anyInt());
        verify(carRepository, times(1)).save(carEntity);
        verify(carMapper, times(1)).toCarEntity(anyInt(), any(CarDto.class));
        verifyNoMoreInteractions(carRepository, carMapper);
    }

    @Test
    void testGetAllRentalCars() {
        CarEntity carEntity = new CarEntity();
        CarDto carDto = new CarDto();
        when(carRepository.findAll()).thenReturn(Collections.singletonList(carEntity));
        when(carMapper.toCarDto(carEntity)).thenReturn(carDto);

        List<CarDto> rentalCars = carService.getAllRentalCars();

        assertEquals(1, rentalCars.size());
        assertEquals(carDto, rentalCars.get(0));
        verify(carRepository, times(1)).findAll();
        verify(carMapper, times(1)).toCarDto(carEntity);
        verifyNoMoreInteractions(carRepository, carMapper);
    }

    @Test
    void testGetCarById_Success() {
        CarEntity carEntity = new CarEntity();
        CarDto carDto = new CarDto();
        when(carRepository.findById(anyInt())).thenReturn(Optional.of(carEntity));
        when(carMapper.toCarDto(carEntity)).thenReturn(carDto);

        CarDto result = carService.getCarById(1);

        assertEquals(carDto, result);
        verify(carRepository, times(1)).findById(anyInt());
        verify(carMapper, times(1)).toCarDto(carEntity);
        verifyNoMoreInteractions(carRepository, carMapper);
    }

    @Test
    void testGetCarById_NotFound() {
        when(carRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> carService.getCarById(1));

        verify(carRepository, times(1)).findById(anyInt());
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    void testDeleteCarById_Success() {

        carService.deleteCarById(1);
        verify(carRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(carRepository);
    }
}
