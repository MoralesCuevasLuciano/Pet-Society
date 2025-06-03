package Pet.Society.controllers;

import Pet.Society.models.entities.DoctorEntity;
import Pet.Society.models.entities.UserEntity;
import Pet.Society.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**Create*/
    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity savedUser = userService.save(user, false);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    /**Update*/
    @PatchMapping("/update/{id}") //WORKS
    public HttpEntity<UserEntity> updateDoctor(@Valid @RequestBody UserEntity user, @PathVariable Long id) {
        userService.update(user, id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    /**Unsubscribe*/
    @PatchMapping("/delete/{id}")
    public ResponseEntity<UserEntity> unsubscribeUser(@PathVariable Long id) {
        UserEntity user = userService.findById(id);
        user.setActive(false);
        UserEntity updatedUser = userService.save(user, true);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    /**Find by ROL ADMIN*/
    @GetMapping("/admin")
    public ResponseEntity<List<UserEntity>> getAdmins() {
        List<UserEntity> admins = userService.findByRole();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

}
