package com.rent.car.rentcars.controller;

import com.rent.car.rentcars.dto.CarDto;
import com.rent.car.rentcars.dto.UpdateCarDto;
import com.rent.car.rentcars.exception.ResourceNotFoundException;
import com.rent.car.rentcars.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CarControllerTest {

    @InjectMocks
    private CarController carController;

    @Mock
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCar() {
        CarDto carDto = new CarDto();
        doNothing().when(carService).addCar(any(CarDto.class));

        ResponseEntity<Void> response = carController.addCar(carDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(carService, times(1)).addCar(any(CarDto.class));
        verifyNoMoreInteractions(carService);
    }

    @Test
    void testUpdateCar_Success() {
        UpdateCarDto updateCarDto = new UpdateCarDto();
        doNothing().when(carService).updateCar(anyInt(), any(UpdateCarDto.class));

        ResponseEntity<Void> response = carController.updateCar(1, updateCarDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(carService, times(1)).updateCar(anyInt(), any(UpdateCarDto.class));
        verifyNoMoreInteractions(carService);
    }

    @Test
    void testUpdateCar_NotFound() {
        UpdateCarDto updateCarDto = new UpdateCarDto();
        doThrow(ResourceNotFoundException.class).when(carService).updateCar(anyInt(), any(UpdateCarDto.class));

        ResponseEntity<Void> response = carController.updateCar(1, updateCarDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(carService, times(1)).updateCar(anyInt(), any(UpdateCarDto.class));
        verifyNoMoreInteractions(carService);
    }

    @Test
    void testUpdateOrAddCar() {
        CarDto carDto = new CarDto();
        doNothing().when(carService).updateOrAddCar(anyInt(), any(CarDto.class));

        ResponseEntity<Void> response = carController.updateOrAddCar(1, carDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(carService, times(1)).updateOrAddCar(anyInt(), any(CarDto.class));
        verifyNoMoreInteractions(carService);

    }

    @Test
    void testGetAllRentalCars() {
        List<CarDto> carList = Collections.singletonList(new CarDto());
        when(carService.getAllRentalCars()).thenReturn(carList);

        ResponseEntity<List<CarDto>> response = carController.getAllRentalCars();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carList, response.getBody());
        verify(carService, times(1)).getAllRentalCars();
        verifyNoMoreInteractions(carService);

    }

    @Test
    void testGetCarById() {
        CarDto carDto = new CarDto();
        when(carService.getCarById(anyInt())).thenReturn(carDto);

        ResponseEntity<CarDto> response = carController.getCarById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carDto, response.getBody());
        verify(carService, times(1)).getCarById(anyInt());
        verifyNoMoreInteractions(carService);

    }


}
