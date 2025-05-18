package Pet.Society.services;

import Pet.Society.models.entities.ClientEntity;
import Pet.Society.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientEntity save (ClientEntity c){
        return clientRepository.save(c);
    }

    public List<ClientEntity> listAll(){
        return clientRepository.findAll();
    }

    public void delete (Long id){
        clientRepository.deleteById(id);
    }

    public Optional<ClientEntity> findById(Long id){
        return clientRepository.findById(id);
    }
}
