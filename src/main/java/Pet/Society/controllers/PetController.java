package Pet.Society.controllers;

import Pet.Society.models.dto.PetDTO;
import Pet.Society.models.entities.ClientEntity;
import Pet.Society.models.entities.PetEntity;
import Pet.Society.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    /**
     * create
     */
    @PostMapping("/create")
    public ResponseEntity<PetEntity> createPet(@Valid @RequestBody PetDTO dto) {
        try {
            PetEntity createdPet = petService.createPet(dto);
            return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * update
    */
    @PatchMapping("/update/{id}")
    public ResponseEntity<PetEntity> updatePet(@PathVariable Long id, @RequestBody PetDTO dto) {
        try {
            PetEntity updatedPet = petService.updatePet(id, dto);
            return new ResponseEntity<>(updatedPet, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * delete
     */
    @PatchMapping("/deleteActive/{id}")
    public ResponseEntity<PetEntity> deleteActive(@PathVariable Long id) {
        try {
            PetEntity pet = petService.getPetById(id);
            PetDTO dto = new PetDTO(
                pet.getName(),
                pet.getAge(),
                false,
                pet.getClient().getId()
            );
            PetEntity petEntity = petService.updatePet(id, dto);
            return ResponseEntity.ok(petEntity);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Buscar por ID y devolver DTO
     */
    @GetMapping("/findForID/{id}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable Long id) {
        try {
            PetEntity pet = petService.getPetById(id);
            PetDTO dto = new PetDTO(
                    pet.getName(),
                    pet.getAge(),
                    pet.isActive(),
                    pet.getClient().getId()
            );
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Listar todos y devolver DTOs
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<PetDTO>> getAllPets() {
        try {
            Iterable<PetEntity> pets = petService.getAllPets();
            List<PetDTO> dtos = new ArrayList<>();
            for (PetEntity pet : pets) {
                dtos.add(new PetDTO(
                        pet.getName(),
                        pet.getAge(),
                        pet.isActive(),
                        pet.getClient().getId()
                ));
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
