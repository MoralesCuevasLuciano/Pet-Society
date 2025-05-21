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
    PetEntity existingPet = petRepository.findById(id)
            .orElseThrow(() -> new PetNotFoundException("La mascota con ID: " + id + " no existe."));

    /**Validar y actualizar cada campo*/
    if (pet.getName() != null) {
        if (pet.getName().equals(existingPet.getName())) {
            throw new IllegalArgumentException("El nombre proporcionado ya está en uso. Por favor, elija otro.");
        }
        existingPet.setName(pet.getName());
    } else {
        throw new IllegalArgumentException("El campo 'nombre' no puede ser nulo. Por favor, complételo.");
    }

    if (pet.getAge() != 0) {
        existingPet.setAge(pet.getAge());
    } else {
        throw new IllegalArgumentException("El campo 'edad' no puede ser nulo. Por favor, complételo.");
    }




    return petRepository.save(existingPet);
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
