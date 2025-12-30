package com.logistica.client;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    //Listar todos os clientes
    public List<Client> getAllClients(){
        return repository.findAll();
    }

    //Listar clientes por Id
    public ClientDTO getClientById(Long id) {
        Client client = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client not found"));
        return new ClientDTO(client.getId(), client.getName(), client.getEmail(), client.getPhone());
    }

    //Salva novo cliente
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());
        client.setPhone(clientDTO.phone());
        Client savedClient = repository.save(client);
        return new ClientDTO(savedClient.getId(), savedClient.getName(), savedClient.getEmail(), savedClient.getPhone());
    }

    //Atualizar cliente existente
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client client = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client not found"));
        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());
        client.setPhone(clientDTO.phone());
        Client updatedClient = repository.save(client);
        return new ClientDTO(updatedClient.getId(), updatedClient.getName(), updatedClient.getEmail(), updatedClient.getPhone());
    }

    //Deletar cliente
    public void deleteClient(Long id) {
        repository.deleteById(id);
    }
}
