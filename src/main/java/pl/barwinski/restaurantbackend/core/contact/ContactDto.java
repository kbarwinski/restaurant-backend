package pl.barwinski.restaurantbackend.core.contact;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ContactDto {

    public Long id;

    @NotBlank(message = "Name is mandatory")
    @Length(max = 255, message = "Name should be less than 255 characters")
    @Length(min = 2, message = "Name should be at least 2 characters")
    public String name;

    @NotBlank(message = "Surname is mandatory")
    @Length(max = 255, message = "Surname should be less than 255 characters")
    @Length(min = 2, message = "Surname should be at least 2 characters")
    public String surname;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^[0-9]{9}$", message = "Phone number should be 9 digits long")
    public String phoneNumber;
}
