package com.rent.car.rentproperty.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyDto {

    @JsonProperty("description")
    @Nonnull
    private String description;
    @JsonProperty("town")
    @Nonnull
    private String town;
    @JsonProperty("address")
    @Nonnull
    private String address;
    @JsonProperty("propertyType")
    @Nonnull
    private PropertyTypeEnumDto propertyType;
    @JsonProperty("rentAmount")
    @Nonnull
    private Double rentAmount;
    @JsonProperty("securityDepositAmount")
    @Nonnull
    private Double securityDepositAmount;
    @JsonProperty("area")
    @Nonnull
    private Double area;
    @JsonProperty("numberOfBedrooms")
    private Integer numberOfBedrooms;
    @JsonProperty("floorNumber")
    private Integer floorNumber;
    @JsonProperty("numberOfFloors")
    private Integer numberOfFloors;
    @JsonProperty("constructionYear")
    private String constructionYear;
    @JsonProperty("energyClassification")
    private EnergyClassificationEnumDto energyClassification;
    @JsonProperty("hasElevator")
    private Boolean hasElevator;
    @JsonProperty("hasIntercom")
    private Boolean hasIntercom;
    @JsonProperty("hasBalcony")
    private Boolean hasBalcony;
    @JsonProperty("hasParkingSpace")
    private Boolean hasParkingSpace;

    public PropertyDto() {
    }

    public PropertyDto(@Nonnull String description, @Nonnull String town, @Nonnull String address, @Nonnull PropertyTypeEnumDto propertyType, @Nonnull Double rentAmount, @Nonnull Double securityDepositAmount, @Nonnull Double area, Integer numberOfBedrooms, Integer floorNumber, Integer numberOfFloors, String constructionYear, EnergyClassificationEnumDto energyClassification, Boolean hasElevator, Boolean hasIntercom, Boolean hasBalcony, Boolean hasParkingSpace) {
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
        this.energyClassification = energyClassification;
        this.hasElevator = hasElevator;
        this.hasIntercom = hasIntercom;
        this.hasBalcony = hasBalcony;
        this.hasParkingSpace = hasParkingSpace;
    }

    @Nonnull
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(@Nonnull String description) {
        this.description = description;
    }

    @Nonnull
    @JsonProperty("town")
    public String getTown() {
        return town;
    }

    @JsonProperty("town")
    public void setTown(@Nonnull String town) {
        this.town = town;
    }

    @Nonnull
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(@Nonnull String address) {
        this.address = address;
    }

    @Nonnull
    @JsonProperty("propertyType")
    public PropertyTypeEnumDto getPropertyType() {
        return propertyType;
    }

    @JsonProperty("propertyType")
    public void setPropertyType(@Nonnull PropertyTypeEnumDto propertyType) {
        this.propertyType = propertyType;
    }

    @Nonnull
    @JsonProperty("rentAmount")
    public Double getRentAmount() {
        return rentAmount;
    }

    @JsonProperty("rentAmount")
    public void setRentAmount(@Nonnull Double rentAmount) {
        this.rentAmount = rentAmount;
    }

    @Nonnull
    @JsonProperty("securityDepositAmount")
    public Double getSecurityDepositAmount() {
        return securityDepositAmount;
    }

    @JsonProperty("securityDepositAmount")
    public void setSecurityDepositAmount(@Nonnull Double securityDepositAmount) {
        this.securityDepositAmount = securityDepositAmount;
    }

    @Nonnull
    @JsonProperty("area")
    public Double getArea() {
        return area;
    }

    @JsonProperty("area")
    public void setArea(@Nonnull Double area) {
        this.area = area;
    }

    @JsonProperty("numberOfBedrooms")
    public Integer getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    @JsonProperty("numberOfBedrooms")
    public void setNumberOfBedrooms(Integer numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    @JsonProperty("floorNumber")
    public Integer getFloorNumber() {
        return floorNumber;
    }

    @JsonProperty("floorNumber")
    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    @JsonProperty("numberOfFloors")
    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }

    @JsonProperty("numberOfFloors")
    public void setNumberOfFloors(Integer numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    @JsonProperty("constructionYear")
    public String getConstructionYear() {
        return constructionYear;
    }

    @JsonProperty("constructionYear")
    public void setConstructionYear(String constructionYear) {
        this.constructionYear = constructionYear;
    }

    @JsonProperty("energyClassification")
    public EnergyClassificationEnumDto getEnergyClassification() {
        return energyClassification;
    }

    @JsonProperty("energyClassification")
    public void setEnergyClassification(EnergyClassificationEnumDto energyClassification) {
        this.energyClassification = energyClassification;
    }

    @JsonProperty("hasElevator")
    public Boolean getHasElevator() {
        return hasElevator;
    }

    @JsonProperty("hasElevator")
    public void setHasElevator(Boolean hasElevator) {
        this.hasElevator = hasElevator;
    }

    @JsonProperty("hasIntercom")
    public Boolean getHasIntercom() {
        return hasIntercom;
    }

    @JsonProperty("hasIntercom")
    public void setHasIntercom(Boolean hasIntercom) {
        this.hasIntercom = hasIntercom;
    }

    @JsonProperty("hasBalcony")
    public Boolean getHasBalcony() {
        return hasBalcony;
    }

    @JsonProperty("hasBalcony")
    public void setHasBalcony(Boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    @JsonProperty("hasParkingSpace")
    public Boolean getHasParkingSpace() {
        return hasParkingSpace;
    }

    @JsonProperty("hasParkingSpace")
    public void setHasParkingSpace(Boolean hasParkingSpace) {
        this.hasParkingSpace = hasParkingSpace;
    }


}