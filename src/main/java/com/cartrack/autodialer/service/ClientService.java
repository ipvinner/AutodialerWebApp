package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.Client;

import java.util.List;

/**
 * Created by vinner on 29.09.2015.
 */
public interface ClientService {

    List<Client> getByList(int listId);

    List<Client> getAll();

    Client save(Client client);

    Client update(Client client);

    void delete(int id);

    Client get(int id);

    void deleteAllByList(int listId);
}
