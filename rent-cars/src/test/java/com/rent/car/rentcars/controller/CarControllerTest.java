package com.rent.car.rentcars.controller;

import com.rent.car.rentcars.dto.CarDto;
import com.rent.car.rentcars.dto.UpdateCarDto;
import com.rent.car.rentcars.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    @InjectMocks
    private CarController carController;

    @Mock
    private CarService carService;


    @Test
    void testAddCar() {
        CarDto carDto = new CarDto();
        doNothing().when(carService).saveCar(any(CarDto.class));

        ResponseEntity<Void> response = carController.addCar(carDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(carService, times(1)).saveCar(any(CarDto.class));
        verifyNoMoreInteractions(carService);
    }



    @Test
    void testUpdateCarRentalAmount_Success() {
        UpdateCarDto updateCarDto = new UpdateCarDto();

        ResponseEntity<Void> response = carController.updateCarRentalAmount(1, updateCarDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(carService, times(1)).updateRentalAmount(anyInt(), any(UpdateCarDto.class));
        verifyNoMoreInteractions(carService);
    }


    @Test
    void testUpdateOrAddCar() {
        CarDto carDto = new CarDto();
        doNothing().when(carService).updateCar(anyInt(), any(CarDto.class));

        ResponseEntity<Void> response = carController.updateCar(1, carDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(carService, times(1)).updateCar(anyInt(), any(CarDto.class));
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
    @Test
    void testDeleteCarById() {
        doNothing().when(carService).deleteCarById(anyInt());

        ResponseEntity<Object> response = carController.deleteCarById(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(carService, times(1)).deleteCarById(anyInt());
        verifyNoMoreInteractions(carService);
    }


}
