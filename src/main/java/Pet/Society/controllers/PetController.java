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
            PetEntity createdPet = petService.createPet(dto);
            return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
    }

    /**
     * update
     */
    @PatchMapping("/update/{id}")
    public ResponseEntity<PetEntity> updatePet(@PathVariable Long id, @RequestBody PetDTO dto) {
            PetEntity updatedPet = petService.updatePet(id, dto);
            return new ResponseEntity<>(updatedPet, HttpStatus.OK);

    }

    /**
     * delete
     */
    @PatchMapping("/deleteActive/{id}")
    public ResponseEntity<PetEntity> deleteActive(@PathVariable Long id) {

            PetEntity pet = petService.getPetById(id);
            PetDTO dto = new PetDTO(
                    pet.getName(),
                    pet.getAge(),
                    false,
                    pet.getClient().getId()
            );
            PetEntity petEntity = petService.updatePet(id, dto);
            return ResponseEntity.ok(petEntity);

    }

    /**
     * Buscar por ID y devolver DTO
     */
    @GetMapping("/findForID/{id}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable Long id) {

            PetEntity pet = petService.getPetById(id);
            PetDTO dto = new PetDTO(
                    pet.getName(),
                    pet.getAge(),
                    pet.isActive(),
                    pet.getClient().getId()
            );
            return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    /**
     * Listar todos los registros de mascotas
     */
    @GetMapping("/findAllByClientId/{id}")
    public ResponseEntity<List<PetDTO>> getAllPetsByClientId(@PathVariable("id") Long id) {

            Iterable<PetEntity> pets = petService.getAllPetsByClientId(id);
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

    }


    @GetMapping("/assignPets")
    public ResponseEntity<String> assignSamplePets() {
        petService.assignPetsToClients();
        return ResponseEntity.ok("Se asignaron 2 mascotas a cada cliente.");
    }

}
