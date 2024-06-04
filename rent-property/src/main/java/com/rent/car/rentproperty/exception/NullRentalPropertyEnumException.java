package com.rent.car.rentproperty.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "RentalPropertyTypeEnum null is not possible")
public class NullRentalPropertyEnumException extends RuntimeException{
    public NullRentalPropertyEnumException(String message) {
        super(message);
    }
}
