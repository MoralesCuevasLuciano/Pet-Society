package Pet.Society.controllers;

import Pet.Society.models.entities.ClientEntity;
import Pet.Society.services.ClientService;
import ch.qos.logback.core.net.server.Client;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<ClientEntity> createClient(@Valid @RequestBody ClientEntity client) {
            ClientEntity clientEntity = clientService.save(client);
            return new ResponseEntity<>(clientEntity, HttpStatus.CREATED);

    }
        
    @PatchMapping("/update/{id}")
    public ResponseEntity<ClientEntity> update(@RequestBody ClientEntity client, @PathVariable long id) {
            this.clientService.update(client,id);
            return ResponseEntity.ok(client);
    }

    @PatchMapping("/unsubscribe/{id}")
    public ResponseEntity<String> unsubscribe(@PathVariable long id) {
        this.clientService.unSubscribe(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client unsubscribed successfully");
    }

    @GetMapping("/findByDni/{dni}")
    public ResponseEntity<ClientEntity> findByDni(@PathVariable String dni){
        return ResponseEntity.ok(this.clientService.findByDNI(dni));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ClientEntity> findById(@PathVariable long id) {
        return ResponseEntity.ok(this.clientService.findById(id));
    }




}
