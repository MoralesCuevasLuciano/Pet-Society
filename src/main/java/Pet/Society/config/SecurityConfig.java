package Pet.Society.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"/register/newClient/").permitAll()
                        .requestMatchers(HttpMethod.POST,"/login").hasAnyRole("ADMIN,CLIENT,DOCTOR")
                        .requestMatchers(HttpMethod.POST,"/register/newAdmin/").hasRole("ADMIN")
                        .requestMatchers("/pet/**").hasAnyRole("ADMIN,CLIENT")
                        .requestMatchers("/client/**").hasAnyRole("ADMIN,CLIENT")

                        .anyRequest().authenticated()
                ).formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
