package pl.barwinski.restaurantbackend.core.address;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AddressDto {

    public Long id;
    @NotBlank(message = "Apartment number is mandatory")
    @Min(value = 1, message = "Apartment number should be greater than 0")
    public int aptNumber;

    @NotBlank(message = "City name is mandatory")
    public String cityName;

    @NotBlank(message = "Street name is mandatory")
    public String streetName;

    @NotBlank(message = "Street number is mandatory")
    @Min(value = 1, message = "Street number should be greater than 0")
    public int streetNumber;
}
