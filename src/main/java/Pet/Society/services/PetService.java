package Pet.Society.services;

import Pet.Society.models.dto.PetDTO;
import Pet.Society.models.entities.ClientEntity;
import Pet.Society.models.entities.PetEntity;
import Pet.Society.models.exceptions.PetNotFoundException;
import Pet.Society.repositories.ClientRepository;
import Pet.Society.repositories.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClientRepository clientRepository;


    public PetEntity createPet(PetDTO dto) {
        ClientEntity client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente con ID " + dto.getClientId() + " no encontrado."));

        PetEntity pet = new PetEntity();
        pet.setName(dto.getName());
        pet.setAge(dto.getAge());
        pet.setActive(dto.isActive());
        pet.setClient(client);

        return petRepository.save(pet);
    }



    public PetEntity updatePet(Long id,PetDTO pet) {
        PetEntity existingPet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("La mascota con ID: " + id + " no existe."));

        /**Validar y actualizar cada campo*/
        if (pet.getName() != null) {
            existingPet.setName(pet.getName());
        }
        if (pet.getAge() != 0) {
            existingPet.setAge(pet.getAge());
        }

        if (pet.getClientId() != null){
            ClientEntity client = clientRepository.findById(pet.getClientId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente con ID " + pet.getClientId() + " no encontrado."));
            existingPet.setClient(client);
        }

        if (pet.isActive() != existingPet.isActive()) {
            existingPet.setActive(pet.isActive());
        }
        return petRepository.save(existingPet);
    }



    public PetEntity getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("La mascota con el ID: " + id + " no existe."));

    }


    public Iterable<PetEntity> getAllPets() {
        return petRepository.findAll();
    }

    public Iterable<PetEntity> getAllPetsByClientId(Long clientId) {
        ClientEntity client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente con ID " + clientId + " no encontrado."));

        return petRepository.findAllByClient(client);
    }
}
