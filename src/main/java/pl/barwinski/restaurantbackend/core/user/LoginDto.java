package pl.barwinski.restaurantbackend.core.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginDto {
    public String email;
    public String userRole;
    public String token;
}
