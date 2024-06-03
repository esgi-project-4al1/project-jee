package com.rent.car.rentproperty.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "property not found")
public class NotFoundRentalPropertyException extends RuntimeException {
    public NotFoundRentalPropertyException(String message) {
        super(message);
    }
}
