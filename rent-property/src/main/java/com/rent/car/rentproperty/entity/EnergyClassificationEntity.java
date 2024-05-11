package com.rent.car.rentproperty.entity;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "energy_classification")
public class EnergyClassificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Nonnull
    @Column(length = 1)
    private String designation;

    public EnergyClassificationEntity(@Nonnull String designation) {
        this.designation = designation;
    }

    public EnergyClassificationEntity() {

    }

}
