package pl.barwinski.restaurantbackend.core.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import pl.barwinski.restaurantbackend.core.address.AddressDto;
import pl.barwinski.restaurantbackend.core.contact.ContactDto;

public class RegistrationRequest {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    public String email;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 6, message = "Password should be at least 6 characters")
    public String password;
    public ContactDto contact;
    public AddressDto address;
}
