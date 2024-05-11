package com.rent.car.rentproperty.entity;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "property")
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Nonnull
    @Column(length = 200)
    private String description;
    @Nonnull
    @Column(length = 100)
    private String town;
    @Nonnull
    @Column(length = 200)
    private String address;
    @Nonnull
    @ManyToOne()
    private PropertyTypeEntity propertyType;
    @Nonnull
    private Double rentAmount;
    @Nonnull
    private Double securityDepositAmount;
    @Nonnull
    private Double area;
    private int numberOfBedrooms;
    private int floorNumber;
    private int numberOfFloors;
    @Column(length = 4)
    private String constructionYear;
    @ManyToOne()
    private EnergyClassificationEntity energyClassificationEntity;
    private boolean hasElevator;
    private boolean hasIntercom;
    private boolean hasBalcony;
    private boolean hasParkingSpace;

    public PropertyEntity(int id, @Nonnull String description, @Nonnull String town, @Nonnull String address, @Nonnull PropertyTypeEntity propertyType, @Nonnull Double rentAmount, @Nonnull Double securityDepositAmount, @Nonnull Double area, Integer numberOfBedrooms, Integer floorNumber, Integer numberOfFloors, String constructionYear, EnergyClassificationEntity energyClassificationEntity, Boolean hasElevator, Boolean hasIntercom, Boolean hasBalcony, Boolean hasParkingSpace) {
        this.id = id;
        this.description = description;
        this.town = town;
        this.address = address;
        this.propertyType = propertyType;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.area = area;
        this.numberOfBedrooms = numberOfBedrooms;
        this.floorNumber = floorNumber;
        this.numberOfFloors = numberOfFloors;
        this.constructionYear = constructionYear;
        this.energyClassificationEntity = energyClassificationEntity;
        this.hasElevator = hasElevator;
        this.hasIntercom = hasIntercom;
        this.hasBalcony = hasBalcony;
        this.hasParkingSpace = hasParkingSpace;
    }


    public PropertyEntity( @Nonnull String description, @Nonnull String town, @Nonnull String address, @Nonnull PropertyTypeEntity propertyType, @Nonnull Double rentAmount, @Nonnull Double securityDepositAmount,@Nonnull Double area, Integer numberOfBedrooms, Integer floorNumber, Integer numberOfFloors, String constructionYear, EnergyClassificationEntity energyClassificationEntity, Boolean hasElevator, Boolean hasIntercom, Boolean hasBalcony, Boolean hasParkingSpace) {
        this.description = description;
        this.town = town;
        this.address = address;
        this.propertyType = propertyType;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.area = area;
        this.numberOfBedrooms = numberOfBedrooms;
        this.floorNumber = floorNumber;
        this.numberOfFloors = numberOfFloors;
        this.constructionYear = constructionYear;
        this.energyClassificationEntity = energyClassificationEntity;
        this.hasElevator = hasElevator;
        this.hasIntercom = hasIntercom;
        this.hasBalcony = hasBalcony;
        this.hasParkingSpace = hasParkingSpace;
    }

    public PropertyEntity() {

    }


    public PropertyEntity PropertyEntityWithNewProperTypeAndEnergy(PropertyTypeEntity propertyTypeEntity, EnergyClassificationEntity energyClassificationEntity) {
        return new PropertyEntity(
                this.description,
                this.town,
                this.address,
                propertyTypeEntity,
                this.rentAmount,
                this.securityDepositAmount,
                this.area,
                this.numberOfBedrooms,
                this.floorNumber,
                this.numberOfFloors,
                this.constructionYear,
                energyClassificationEntity,
                this.hasElevator,
                this.hasIntercom,
                this.hasBalcony,
                this.hasParkingSpace
        );
    }

}
