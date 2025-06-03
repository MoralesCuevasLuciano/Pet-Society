package Pet.Society.services;


import Pet.Society.models.entities.CredentialEntity;
import Pet.Society.models.entities.UserEntity;
import Pet.Society.models.enums.Role;
import Pet.Society.models.exceptions.UserExistsException;
import Pet.Society.repositories.CredentialRepository;
import Pet.Society.repositories.UserRepository;
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
    public UserEntity save(UserEntity admin, boolean isUpdate) {
        Optional<UserEntity> existingAdmin = this.userRepository.findByDni(admin.getDni());
        if (existingAdmin.isPresent()) {
            throw new UserExistsException("User already exists");
        }
        this.userRepository.save(admin);
        return admin;
    }

    /**findById*/
    public UserEntity findById(long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserExistsException("Admin not found"));
    }


    /**delete*/
    public void deleteAdmin(Long id) {
        UserEntity existingAdmin = userRepository.findById(id)
                .orElseThrow(() -> new UserExistsException("El administrador con ID: " + id + " no existe."));
        userRepository.delete(existingAdmin);
    }

    /**Update*/
    public void update(UserEntity userToModify, Long id) {
        Optional<UserEntity> existingUser = this.userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new UserExistsException("User does not exist");
        }
        if (userToModify.getName() != null) existingUser.get().setName(userToModify.getName());
        if (userToModify.getSurname() != null) existingUser.get().setSurname(userToModify.getSurname());
        if (userToModify.getEmail() != null) existingUser.get().setEmail(userToModify.getEmail());
        if (userToModify.getPhone() != null) existingUser.get().setPhone(userToModify.getPhone());
        if (userToModify.getDni() != null) existingUser.get().setDni(userToModify.getDni());

        this.userRepository.save(existingUser.get());
    }

    public List<UserEntity> findByRole() {
        return credentialRepository.findByRole(Role.ADMIN)
                .stream()
                .map(CredentialEntity::getUser)
                .collect(Collectors.toList());
    }
}
