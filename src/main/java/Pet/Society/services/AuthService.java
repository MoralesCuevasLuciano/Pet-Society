package Pet.Society.services;


import Pet.Society.models.dto.LoginDTO;
import Pet.Society.models.dto.LoginResponseDTO;
import Pet.Society.models.entities.CredentialEntity;
import Pet.Society.models.exceptions.LoginErrorException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final CredentialService credentialService;

    public AuthService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public LoginResponseDTO login(LoginDTO loginDTO) throws LoginErrorException {
        Optional<CredentialEntity> credentialEntityOptional =
                credentialService.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if (credentialEntityOptional.isPresent()) {
            CredentialEntity credentialEntity = credentialEntityOptional.get();
            return new LoginResponseDTO(credentialEntity.getRole(), "Login successful");
        } else {
            throw new LoginErrorException("Invalid username or password");
        }
    }

}
