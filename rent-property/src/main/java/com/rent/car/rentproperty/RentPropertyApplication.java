package com.rent.car.rentproperty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.rent.car")
@SpringBootApplication
public class RentPropertyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentPropertyApplication.class, args);
    }

}
