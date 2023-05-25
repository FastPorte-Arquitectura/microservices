package com.fastporte.ClientService.service;

import com.fastporte.ClientService.entities.Client;

public interface IClientService extends CrudService<Client> {

    Client findByEmailAndPassword(String email, String password) throws Exception;
}