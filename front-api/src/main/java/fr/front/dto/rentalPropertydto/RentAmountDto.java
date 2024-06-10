package fr.front.dto.rentalPropertydto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;


public class RentAmountDto {

    @JsonProperty("rentAmount")
    @Nonnull
    private Double rentAmount;

    public RentAmountDto() {
    }

    @Nonnull
    public Double getRentAmount() {
        return rentAmount;
    }
}
