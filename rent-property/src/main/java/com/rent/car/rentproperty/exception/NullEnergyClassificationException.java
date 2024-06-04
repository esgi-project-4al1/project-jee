package com.rent.car.rentproperty.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "EnergyClassification null is not possible")
public class NullEnergyClassificationException extends RuntimeException{
    public NullEnergyClassificationException(String message){
        super(message);
    }
}
