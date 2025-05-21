package Pet.Society.controllers;

import Pet.Society.models.entities.ClientEntity;
import Pet.Society.models.entities.PetEntity;
import Pet.Society.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    /**
     * create
     */
    @PostMapping("/create")
    public ResponseEntity<PetEntity> createPet(@Valid @RequestBody PetEntity pet) {
        try {
            pet.setActive(true);
            PetEntity petEntity = petService.createPet(pet);
            return new ResponseEntity<>(petEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * update
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<PetEntity> updatePet(@PathVariable Long id, @Valid @RequestBody PetEntity pet) {
        try {
            PetEntity updatedPet = petService.updatePet(id, pet);
            return new ResponseEntity<>(updatedPet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/deleteActive/{id}")
    public ResponseEntity<Void> deleteActive(@PathVariable Long id) {
        try {
            PetEntity pet = petService.getPetById(id);
            pet.setActive(false);
            petService.updatePet(id, pet);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
