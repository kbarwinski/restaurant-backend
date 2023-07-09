package pl.barwinski.restaurantbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.barwinski.restaurantbackend.core.user.UserEntity;
import pl.barwinski.restaurantbackend.security.auth.JwtAuthenticationFilter;
import pl.barwinski.restaurantbackend.security.auth.UserDetailsServiceImpl;
import pl.barwinski.restaurantbackend.utils.EndpointPaths;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String adminRole = "ADMINISTRATOR";
        String employeeRole = "EMPLOYEE";
        String clientRole = "CLIENT";

        http.csrf(csrf -> csrf.disable())
                .cors().and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/swagger-resources/**","/authsocket").permitAll()
                        .requestMatchers(EndpointPaths.BASE + EndpointPaths.ADMIN + "/**").hasRole(adminRole)
                        .requestMatchers(EndpointPaths.BASE + EndpointPaths.EMPLOYEE + "/**").hasAnyRole(employeeRole, adminRole)
                        .requestMatchers(EndpointPaths.BASE + EndpointPaths.CLIENT + "/**").hasAnyRole(clientRole, employeeRole, adminRole)
                        .requestMatchers(EndpointPaths.BASE + "/**").permitAll()
                        .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}