package Pet.Society.services;

import Pet.Society.models.dto.RegisterDTO;
import Pet.Society.models.entities.ClientEntity;
import Pet.Society.models.entities.CredentialEntity;
import Pet.Society.models.enums.Role;
import Pet.Society.models.exceptions.UserAttributeException;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final ClientService clientService;
    private final CredentialService credentialService;

    public RegisterService(ClientService clientService, CredentialService credentialService) {
        this.clientService = clientService;
        this.credentialService = credentialService;
    }

    public void registerNewClient(RegisterDTO registerDTO) {
        validateRegisterDTO(registerDTO);

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(registerDTO.getName());
        clientEntity.setSurname(registerDTO.getSurname());
        clientEntity.setFoundation(registerDTO.isFoundation());
        clientEntity.setDNI(registerDTO.getDNI());
        clientEntity.setEmail(registerDTO.getEmail());
        clientEntity.setPhone(registerDTO.getPhone());

        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setUsername(registerDTO.getUsername());
        credentialEntity.setPassword(registerDTO.getPassword());
        credentialEntity.setRole(Role.CLIENT);

        clientService.save(clientEntity);
        credentialService.save(credentialEntity);
    }

    private void validateRegisterDTO(RegisterDTO dto) {
        if (dto == null) {
            throw new UserAttributeException("No field can be null");
        }

        if (isBlank(dto.getName()) || !dto.getName().matches("^[a-zA-Z]+$")) {
            throw new UserAttributeException("The name field can only contain letters");
        }

        if (isBlank(dto.getSurname()) || !dto.getSurname().matches("^[a-zA-Z ]+$")) {
            throw new UserAttributeException("The surnaname field can only contain letters");
        }

        if (isBlank(dto.getUsername()) || !dto.getUsername().matches("^[a-zA-Z0-9]+$")) {
            throw new UserAttributeException("The username field can only contain letters and numbers");
        }

        if (isBlank(dto.getPassword()) || dto.getPassword().length() < 8) {
            throw new UserAttributeException("The password must contain at least 8 characters");
        }

        if (isBlank(dto.getDNI()) || !dto.getDNI().matches("^\\d{8}$")) {
            throw new UserAttributeException("The DNI field must contain 8 numeric digits");
        }

        if (isBlank(dto.getEmail()) || !dto.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$")) {
            throw new UserAttributeException("The email field must have a valid format (example@domain.com)");
        }

        if (isBlank(dto.getPhone()) || !dto.getPhone().matches("^\\d{10}$")) {
            throw new UserAttributeException("The phone number must contain 8 numeric digits");
        }
    }

    private boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}
