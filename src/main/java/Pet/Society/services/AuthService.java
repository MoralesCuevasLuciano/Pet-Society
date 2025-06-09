package Pet.Society.services;

import Pet.Society.models.dto.LoginDTO;
import Pet.Society.models.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {

    private AuthenticationManager authenticationManager;

    private JwtService jwtService;

    private UserDetailsService userDetailsService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public LoginResponseDTO login(LoginDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        String token = jwtService.generateToken(userDetails);

        return new LoginResponseDTO(token);
    }
}
