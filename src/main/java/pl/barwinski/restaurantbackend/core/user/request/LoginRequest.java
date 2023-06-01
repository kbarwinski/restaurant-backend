package pl.barwinski.restaurantbackend.core.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
