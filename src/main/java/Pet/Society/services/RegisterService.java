package Pet.Society.services;

import Pet.Society.models.dto.RegisterDTO;
import Pet.Society.models.entities.ClientEntity;
import Pet.Society.models.entities.CredentialEntity;
import Pet.Society.models.enums.Role;
import Pet.Society.repositories.ClientRepository;

public class RegisterService {
    private final ClientService clientService;
    private final CredentialService credentialService;

    public RegisterService(ClientService clientService, CredentialService credentialService) {
        this.clientService = clientService;
        this.credentialService = credentialService;
    }

    public void registerNewUsuario(RegisterDTO registerDTO){
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(registerDTO.getName());
        clientEntity.setSurname(registerDTO.getSurname());
        clientEntity.setFoundation(registerDTO.isFoundation());
        clientEntity.setDNI(registerDTO.getDNI());
        clientEntity.setEmail(registerDTO.getEmail());
        clientEntity.setPhone(registerDTO.getPhone());

        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setClient(clientEntity);
        credentialEntity.setUsername(registerDTO.getUsername());
        credentialEntity.setPassword(registerDTO.getPassword());
        credentialEntity.setRole(Role.CLIENT);

        ///clientService.save(clientEntity);
        ///  credentialService.save(credentialEntity);
    }
}
