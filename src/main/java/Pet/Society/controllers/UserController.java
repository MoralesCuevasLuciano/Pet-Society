package Pet.Society.controllers;

import Pet.Society.models.entities.ClientEntity;
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
        UserEntity savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    /**Update*/
    @PatchMapping("/update/{id}")
    public ResponseEntity<UserEntity> update(@Valid @RequestBody UserEntity user, @PathVariable long id) {
        this.userService.update(user,id);
        return ResponseEntity.ok(user);

    }


    /**delete false active*/
    @PatchMapping("/delete/{id}")
    public ResponseEntity<String> unsubscribe(@PathVariable long id) {
        this.userService.unSubscribe(id);
        return ResponseEntity.status(HttpStatus.OK).body("User unsubscribed successfully");

    }

    /**volver a suscribir*/
    @PatchMapping("/resubscribe/{id}")
    public ResponseEntity<String> resubscribe(@PathVariable long id) {
        this.userService.reSubscribe(id);
        return ResponseEntity.status(HttpStatus.OK).body("User resubscribed successfully");
    }



    /**Find by ROL ADMIN*/
    @GetMapping("/admin")
    public ResponseEntity<List<UserEntity>> getAdmins() {
        List<UserEntity> admins = userService.findByRole();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }


    @GetMapping("/randomsAdmins")
    public ResponseEntity<?> addClients() {
        userService.addRandomAdmins();
        return ResponseEntity.status(HttpStatus.CREATED).body("Admins aleatorios agregados correctamente");
    }
}
