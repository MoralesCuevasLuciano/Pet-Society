package Pet.Society.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //REGISTRARSE Y LOGUEARSE
                        .requestMatchers(HttpMethod.POST,"/register/newClient/").permitAll()
                        .requestMatchers(HttpMethod.POST,"/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/register/newAdmin/").hasRole("ADMIN")
                        //ACCESS TO PETS
                        .requestMatchers("/pet/**").hasAnyRole("ADMIN","CLIENT")
                        //ACCESS TO CLIENTS
                        .requestMatchers("/client/**").hasAnyRole("ADMIN","CLIENT")
                        //ACCESS TO APPOINTMENTS
                        .requestMatchers(HttpMethod.PATCH,"/appointment/assign/**").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.DELETE,"/appointment/delete/**").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PATCH,"/appointment/**").hasRole("ADMIN")
                        //ACCESS TO DOCTOR
                        .requestMatchers("/doctor/**").hasRole("ADMIN")
                        // ACCESS TO DIAGNOSES
                        .requestMatchers("/diagnoses/getByPetId/**").hasAnyRole("CLIENT","ADMIN")
                        .requestMatchers("/diagnoses/findById/**",
                                    "/diagnoses/getLastDiagnoses/**",
                                    "/diagnoses/getAll",
                                    "/diagnoses/getByDoctorId/**").hasAnyRole("ADMIN","DOCTOR")
                        .requestMatchers("/diagnoses/create","/diagnoses/update").hasRole("DOCTOR")
                        .anyRequest().authenticated()

                )
                //FILTER WHEN WE IMPLMENT JWT SERVICE
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
