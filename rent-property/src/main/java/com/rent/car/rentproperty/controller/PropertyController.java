package com.rent.car.rentproperty.controller;

import com.rent.car.rentproperty.dto.PropertiesDto;
import com.rent.car.rentproperty.dto.PropertyDto;
import com.rent.car.rentproperty.dto.RentAmountDto;
import com.rent.car.rentproperty.exception.NotFoundException;
import com.rent.car.rentproperty.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent-properties-api/rental-properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping()
    public List<PropertiesDto> getAllRentalProperties() {
        return this.propertyService.findAllRentalProperties();
    }

    @GetMapping("/{id}")
    public PropertiesDto getRentalPropertiesById(@PathVariable("id") int id) {
        return this.propertyService.findRentalProperty(id);
    }

    @PostMapping()
    public ResponseEntity<Object> postRentalProperties(@RequestBody PropertyDto propertyDto) {
        try {
            this.propertyService.saveRentalProperty(propertyDto);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putRentalProperties(@RequestBody PropertyDto propertyDto, @PathVariable("id") int id) {
        try {
            this.propertyService.updateRentalProperty(id, propertyDto);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchRentalProperties(@RequestBody RentAmountDto rentAmount, @PathVariable("id") int id) {
        try {
            this.propertyService.updateRentalAmount(id, rentAmount.getRentAmount());
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRentalProperties(@PathVariable("id") int id) {
        try {
            this.propertyService.deleteRentalProperty(id);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }


}
