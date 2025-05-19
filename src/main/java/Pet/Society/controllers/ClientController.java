package Pet.Society.controllers;

import Pet.Society.models.entities.ClientEntity;
import Pet.Society.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<ClientEntity> createClient(@Valid @RequestBody ClientEntity client) {
        try{
            ClientEntity clientEntity = clientService.save(client);
            return new ResponseEntity<>(clientEntity, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update/{id}") ///ERROR AQUI
    public ResponseEntity<ClientEntity> update(@RequestBody ClientEntity client, @PathVariable long id) {
            this.clientService.update(client,id);
            return ResponseEntity.ok(client);
    }


}
