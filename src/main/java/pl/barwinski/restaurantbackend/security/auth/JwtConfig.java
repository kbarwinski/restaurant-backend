package pl.barwinski.restaurantbackend.security.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expirationTime}")
    private int expirationTime;

    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(secretKey, expirationTime, tokenPrefix);
    }
}
