package Pet.Society.controllers;

import Pet.Society.models.entities.UserEntity;
import Pet.Society.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**Create*/
    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        // Validate the user entity
        if (user.getName() == null || user.getSurname() == null || user.getPhone() == null ||
                user.getDni() == null || user.getEmail() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserEntity savedUser = userService.save(user, false);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    /**Update*/
    @PatchMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateAdmin(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        UserEntity existingUser = userService.findById(id);

        updates.forEach((field, value) -> {
            switch (field) {
                case "name":
                    existingUser.setName((String) value);
                    break;
                case "surname":
                    existingUser.setSurname((String) value);
                    break;
                case "phone":
                    existingUser.setPhone((String) value);
                    break;
                case "dni":
                    existingUser.setDni((String) value);
                    break;
                case "email":
                    existingUser.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Campo no válido: " + field);
            }
        });

        UserEntity updatedUser = userService.updateAdmin(id, existingUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    /**Delete*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**See user by ID*/
    @GetMapping("/seeById/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        UserEntity user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
