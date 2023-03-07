package pl.barwinski.restaurantbackend.core.user;

import pl.barwinski.restaurantbackend.core.address.AddressDto;
import pl.barwinski.restaurantbackend.core.contact.ContactDto;

import java.util.ArrayList;

public class UserDto {
    public String email;

    public String userRole;

    public ContactDto contact;

    public ArrayList<AddressDto> addresses;
}
