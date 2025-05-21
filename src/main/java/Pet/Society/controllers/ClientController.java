package Pet.Society.controllers;

import Pet.Society.models.entities.ClientEntity;
import Pet.Society.services.ClientService;
import ch.qos.logback.core.net.server.Client;
import jakarta.validation.Valid;
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
        try{
            ClientEntity clientEntity = clientService.save(client);
            return new ResponseEntity<>(clientEntity, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
        
    @PatchMapping("/update/{id}") ///SOLO FUNCIONA CON JSON COMPLETO
    public ResponseEntity<ClientEntity> update(@RequestBody ClientEntity client, @PathVariable long id) {
            this.clientService.update(client,id);
            return ResponseEntity.ok(client);
    }

    /*@PatchMapping("/unsubscribe") ///SOLO FUNCIONA CON JSON COMPLETO
    public ResponseEntity<ClientEntity> unsubscribe(@PathVariable long id) {
        this.clientService.unSubscribe(id);
        return ResponseEntity.ok(clientService.);
    }*/ // fue comentada porque el return estaba inclompleto y no me dejaba compliar, solucionar para descomentar




}
