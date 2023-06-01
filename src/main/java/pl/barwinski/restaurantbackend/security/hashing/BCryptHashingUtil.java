package pl.barwinski.restaurantbackend.security.hashing;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class BCryptHashingUtil implements IHashingUtil{
    public String hashPassword(String input) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(input);
    }

    public boolean verifyPassword(String input, String hashedInput) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(input, hashedInput);
    }
}
