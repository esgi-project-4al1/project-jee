package com.rent.car.rentproperty.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalPropertyDto {

    @JsonProperty("description")
    @NotNull(message = "Please specify brand")
    @NotBlank(message = "Brand cannot be blank")
    private String description;
    @JsonProperty("town")
    @NotNull(message = "Please specify brand")
    @NotBlank(message = "Brand cannot be blank")
    private String town;
    @JsonProperty("address")
    @NotNull(message = "Please specify brand")
    @NotBlank(message = "Brand cannot be blank")
    private String address;
    @JsonProperty("propertyType")
    @NotNull(message = "Please specify brand")
    private RentalPropertyTypeEnumDto propertyType;
    @JsonProperty("rentAmount")
    @NotNull(message = "Please specify brand")
    private Double rentAmount;
    @JsonProperty("securityDepositAmount")
    @NotNull(message = "Please specify brand")
    private Double securityDepositAmount;
    @JsonProperty("area")
    @NotNull(message = "Please specify brand")
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

    public RentalPropertyDto() {
    }

    public RentalPropertyDto(String description, String town, String address, RentalPropertyTypeEnumDto propertyType, Double rentAmount, Double securityDepositAmount, Double area, Integer numberOfBedrooms, Integer floorNumber, Integer numberOfFloors, String constructionYear, EnergyClassificationEnumDto energyClassification, Boolean hasElevator, Boolean hasIntercom, Boolean hasBalcony, Boolean hasParkingSpace) {
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

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }


    @JsonProperty("town")
    public String getTown() {
        return town;
    }

    @JsonProperty("town")
    public void setTown(String town) {
        this.town = town;
    }


    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }


    @JsonProperty("propertyType")
    public RentalPropertyTypeEnumDto getPropertyType() {
        return propertyType;
    }

    @JsonProperty("propertyType")
    public void setPropertyType(RentalPropertyTypeEnumDto propertyType) {
        this.propertyType = propertyType;
    }


    @JsonProperty("rentAmount")
    public Double getRentAmount() {
        return rentAmount;
    }

    @JsonProperty("rentAmount")
    public void setRentAmount(Double rentAmount) {
        this.rentAmount = rentAmount;
    }


    @JsonProperty("securityDepositAmount")
    public Double getSecurityDepositAmount() {
        return securityDepositAmount;
    }

    @JsonProperty("securityDepositAmount")
    public void setSecurityDepositAmount(Double securityDepositAmount) {
        this.securityDepositAmount = securityDepositAmount;
    }

    @JsonProperty("area")
    public Double getArea() {
        return area;
    }

    @JsonProperty("area")
    public void setArea(Double area) {
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