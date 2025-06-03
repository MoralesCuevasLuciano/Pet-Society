package Pet.Society.services;


import Pet.Society.models.entities.UserEntity;
import Pet.Society.models.exceptions.UserExistsException;
import Pet.Society.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    /**save*/
    public UserEntity save(UserEntity admin, boolean isUpdate) {
        Optional<UserEntity> existingAdmin = this.userRepository.findByDni(admin.getDni());
        if (existingAdmin.isPresent() && (!isUpdate || existingAdmin.get().getId() != admin.getId())) {
            throw new UserExistsException("User already exists");
        }
        this.userRepository.save(admin);
        return admin;
    }

    /**findById*/
    public UserEntity findById(long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserExistsException("Admin not found"));
    }

    /**Update*/
    public UserEntity updateAdmin(Long id, UserEntity adminDetails) {
        UserEntity existingAdmin = userRepository.findById(id)
                .orElseThrow(() -> new UserExistsException("El administrador con ID: " + id + " no existe."));

        /** Validar y actualizar cada campo */
        if (adminDetails.getName() != null) {
            existingAdmin.setName(adminDetails.getName());
        }
        if (adminDetails.getSurname() != null) {
            existingAdmin.setSurname(adminDetails.getSurname());
        }
        if (adminDetails.getPhone() != null) {
            existingAdmin.setPhone(adminDetails.getPhone());
        }
        if (adminDetails.getDni() != null) {
            existingAdmin.setDni(adminDetails.getDni());
        }
        if (adminDetails.getEmail() != null) {
            existingAdmin.setEmail(adminDetails.getEmail());
        }

        return userRepository.save(existingAdmin);
    }


    /**delete*/
    public void deleteAdmin(Long id) {
        UserEntity existingAdmin = userRepository.findById(id)
                .orElseThrow(() -> new UserExistsException("El administrador con ID: " + id + " no existe."));
        userRepository.delete(existingAdmin);
    }


}
