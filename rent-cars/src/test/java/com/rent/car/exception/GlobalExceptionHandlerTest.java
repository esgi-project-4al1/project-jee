package com.rent.car.exception;

import com.rent.car.rentcars.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleValidationExceptions() {
        BindingResult bindingResult = mock(BindingResult.class);
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        FieldError fieldError1 = new FieldError("objectA", "rentAmount", "RentAmount is invalid");
        FieldError fieldError2 = new FieldError("objectB", "brand", "Brand is invalid");
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError1, fieldError2));

        ResponseEntity<Map<String, String>> responseEntity = globalExceptionHandler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("rentAmount", "RentAmount is invalid");
        expectedErrors.put("brand", "Brand is invalid");

        assertEquals(expectedErrors, responseEntity.getBody());
    }
}
