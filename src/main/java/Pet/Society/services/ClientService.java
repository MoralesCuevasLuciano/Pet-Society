package Pet.Society.services;

import Pet.Society.models.entities.AppointmentEntity;
import Pet.Society.models.entities.ClientEntity;
import Pet.Society.models.entities.PetEntity;
import Pet.Society.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    public Optional<ClientEntity> findById(Long id) {
        return clientRepository.findById(id);
    }

    public ClientEntity save(ClientEntity client) {
        return clientRepository.save(client);
    }

    public Optional<ClientEntity> update(Long id, ClientEntity client) {
        return clientRepository.findById(id).map(existingClient -> {
            existingClient.setName(client.getName());
            existingClient.setEmail(client.getEmail());
            existingClient.setSurname(client.getSurname());
            existingClient.setPhone(client.getPhone());
            existingClient.setPets(client.getPets());
            return clientRepository.save(existingClient);
        });
    }

    public boolean delete(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<AppointmentEntity> getAppointmentsByClientId(Long clientId) {
        return clientRepository.findById(clientId)
                .map(ClientEntity::getAppointments)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }


    public List<ClientEntity> findAllFoundations() {
        return clientRepository.findAllFoundations();
    }

    public List<PetEntity> getPetsByClientId(Long clientId) {
        return clientRepository.findById(clientId)
                .map(ClientEntity::getPets)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public PetEntity addPetToClient(Long clientId, PetEntity pet) {
        return clientRepository.findById(clientId)
                .map(client -> {
                    pet.setClient(client);
                    client.getPets().add(pet);
                    clientRepository.save(client);
                    return pet;
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public ClientEntity save(AppointmentEntity existingAppointment) {
        return clientRepository.save(existingAppointment.getClient());
    }
}