package Pet.Society.services;

import Pet.Society.models.entities.PetEntity;
import Pet.Society.models.exceptions.PetAlreadyExistsException;
import Pet.Society.models.exceptions.PetNotFoundException;
import Pet.Society.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;


    public PetEntity createPet(PetEntity pet) {
        if (petRepository.existsById(pet.getId())) {
            throw new PetAlreadyExistsException("La mascota con ID: " + pet.getId() + " ya existe.");
        }
        return petRepository.save(pet);
    }


    public PetEntity updatePet(Long id, PetEntity pet) {
        if (!petRepository.existsById(id)) {
         throw new PetNotFoundException("La mascota con ID: " + pet.getId() + " no existe.");
        }
        pet.setId(id);
        return petRepository.save(pet);
    }

    public void deletePet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException("La mascota con el ID: " + id + " no existe.");
        }
        petRepository.deleteById(id);
    }

    public PetEntity getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("La mascota con el ID: " + id + " no existe."));

    }






}
