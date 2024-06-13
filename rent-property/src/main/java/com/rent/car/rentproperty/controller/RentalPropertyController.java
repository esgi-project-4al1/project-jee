package com.rent.car.rentproperty.controller;

import com.rent.car.rentproperty.dto.RentAmountDto;
import com.rent.car.rentproperty.dto.RentalPropertiesDto;
import com.rent.car.rentproperty.dto.RentalPropertyDto;
import com.rent.car.rentproperty.service.RentalPropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent-properties-api/rental-properties")
public class RentalPropertyController {

    private final RentalPropertyService rentalPropertyService;


    public RentalPropertyController(RentalPropertyService rentalPropertyService) {
        this.rentalPropertyService = rentalPropertyService;
    }

    @GetMapping()
    public List<RentalPropertiesDto> getAllRentalProperties() {
        return this.rentalPropertyService.findAllRentalProperties();
    }

    @GetMapping("/{id}")
    public RentalPropertiesDto getRentalPropertiesById(@PathVariable("id") int id) {
        return this.rentalPropertyService.findRentalProperty(id);
    }

    @PostMapping()
    public ResponseEntity<?> postRentalProperties(@Valid @RequestBody RentalPropertyDto rentalPropertyDto) {
        this.rentalPropertyService.saveRentalProperty(rentalPropertyDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putRentalProperties(@Valid @RequestBody RentalPropertyDto rentalPropertyDto, @PathVariable("id") int id) {
        this.rentalPropertyService.updateRentalProperty(id, rentalPropertyDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchRentalProperties(@Valid @RequestBody RentAmountDto rentAmount, @PathVariable("id") int id) {
        this.rentalPropertyService.updateRentalAmount(id, rentAmount.getRentAmount());
        return new ResponseEntity<>(null, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRentalProperties(@PathVariable("id") int id) {
        this.rentalPropertyService.deleteRentalProperty(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }


}
