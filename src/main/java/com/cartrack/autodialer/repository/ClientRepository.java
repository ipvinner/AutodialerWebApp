package com.cartrack.autodialer.repository;

import com.cartrack.autodialer.domain.Client;

import java.util.List;

/**
 * Created by vinner on 29.09.2015.
 */
public interface ClientRepository {

    List<Client> getByList(int listId);

    Client save(Client client);

    boolean delete(int id);

    Client get(int id);

    void deleteAllByList(int listId);


}
