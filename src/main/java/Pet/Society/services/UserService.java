package Pet.Society.services;


import Pet.Society.models.entities.ClientEntity;
import Pet.Society.models.entities.CredentialEntity;
import Pet.Society.models.entities.UserEntity;
import Pet.Society.models.enums.Role;
import Pet.Society.models.exceptions.UserExistsException;
import Pet.Society.models.exceptions.UserNotFoundException;
import Pet.Society.repositories.CredentialRepository;
import Pet.Society.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CredentialRepository credentialRepository;


    /**Create*/
    public UserEntity save(UserEntity admin) {
        Optional<UserEntity> existingAdmin = this.userRepository.findByDni(admin.getDni());
        if (existingAdmin.isPresent()) {
            throw new UserExistsException("User already exists");
        }
        this.userRepository.save(admin);
        return admin;
    }


    public void update(UserEntity userToUnsubscribe, long id) {
        Optional<UserEntity> userOpt = this.userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new UserExistsException("User does not exist");
        }
        userToUnsubscribe.setId(id);
        takeAttributes(userToUnsubscribe, userOpt.get());
        userToUnsubscribe.setActive(false);
        this.userRepository.save(userToUnsubscribe);
    }


    public UserEntity takeAttributes(UserEntity origin, UserEntity destination) {
        if(origin.getName() == null){origin.setPhone(destination.getPhone());}
        if(origin.getSurname() == null){origin.setSurname(destination.getSurname());}
        if(origin.getEmail() == null){origin.setEmail(destination.getEmail());}
        if(origin.getDni() == null){origin.setDni(destination.getDni());}
        if(origin.getPhone()==null){origin.setPhone(destination.getPhone());}

        return origin;
    }


    /**unSuscribe*/
    public void unSubscribe(Long id){
        Optional<UserEntity> existingUser = this.userRepository.findById(id);
        if (existingUser.isEmpty()){
            throw new UserNotFoundException("User does not exist");
        }
        UserEntity userToUnsubscribe = existingUser.get();
        userToUnsubscribe.setSubscribed(false);
        this.userRepository.save(userToUnsubscribe);
    }

    /**Suscribe uno ya dado de baja por la funcion de arriba
     * en caso de que un user se vaya y quiera volver*/
    public void reSubscribe(Long id) {
        Optional<UserEntity> existingUser = this.userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User does not exist");
        }
        UserEntity userToResubscribe = existingUser.get();
        userToResubscribe.setActive(true);
        this.userRepository.save(userToResubscribe);
    }


    /**Find by ROLE ADMIN*/
    public List<UserEntity> findByRole() {
        return credentialRepository.findByRole(Role.ADMIN)
                .stream()
                .map(CredentialEntity::getUser)
                .collect(Collectors.toList());
    }
}
