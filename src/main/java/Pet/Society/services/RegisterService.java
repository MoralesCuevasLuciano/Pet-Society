package Pet.Society.services;

import Pet.Society.models.dto.RegisterDTO;
import Pet.Society.models.entities.ClientEntity;
import Pet.Society.models.entities.CredentialEntity;
import Pet.Society.models.entities.UserEntity;
import Pet.Society.models.enums.Role;
import Pet.Society.models.exceptions.UserAttributeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private ClientService clientService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private UserService userService;



    public void registerNewClient(RegisterDTO registerDTO) {


        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(registerDTO.getName());
        clientEntity.setSurname(registerDTO.getSurname());
        clientEntity.setFoundation(false);
        clientEntity.setDni(registerDTO.getDni());
        clientEntity.setEmail(registerDTO.getEmail());
        clientEntity.setPhone(registerDTO.getPhone());

        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setUsername(registerDTO.getUsername());
        credentialEntity.setPassword(registerDTO.getPassword());
        credentialEntity.setRole(Role.CLIENT);
        credentialEntity.setUser(clientService.save(clientEntity));


        credentialService.save(credentialEntity);
    }

    public void registerNewAdmin(RegisterDTO registerDTO) {

        UserEntity userEntity = new UserEntity();
        userEntity.setName(registerDTO.getName());
        userEntity.setSurname(registerDTO.getSurname());
        userEntity.setDni(registerDTO.getDni());
        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setPhone(registerDTO.getPhone());

        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setUsername(registerDTO.getUsername());
        credentialEntity.setPassword(registerDTO.getPassword());
        credentialEntity.setRole(Role.ADMIN);
        credentialEntity.setUser(userService.save(userEntity, false));

        credentialService.save(credentialEntity);
    }


}
