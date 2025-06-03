package Pet.Society.controllers;

import Pet.Society.models.dto.RegisterDTO;
import Pet.Society.services.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/new/client")
    public ResponseEntity<String> registerClient(@Valid @RequestBody RegisterDTO dto) {
        registerService.registerNewClient(dto);
        return ResponseEntity.ok("Successfully registered user");
    }

    @PostMapping("/new/admin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody RegisterDTO dto) {
        registerService.registerNewAdmin(dto);
        return ResponseEntity.ok("Successfully registered admin");
    }
}

