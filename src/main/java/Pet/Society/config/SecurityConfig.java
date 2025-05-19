package Pet.Society.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // importante: desactiva CSRF para evitar 403
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/client/create").permitAll()
                        .anyRequest().permitAll() // para pruebas, deja todo libre
                );

        return http.build();
    }
}
