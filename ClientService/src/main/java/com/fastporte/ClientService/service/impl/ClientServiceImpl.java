package com.fastporte.ClientService.service.impl;

import com.fastporte.ClientService.entities.Client;
import com.fastporte.ClientService.repository.IClientRepository;
import com.fastporte.ClientService.service.IClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    public ClientServiceImpl(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public Client save(Client client) throws Exception {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> getAll() throws Exception {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getById(Long id) throws Exception {
        return clientRepository.findById(id);
    }

    @Override
    public Client findByEmailAndPassword(String email, String password) throws Exception {
        return clientRepository.findByEmailAndPassword(email, password);
    }
}