package com.cartrack.autodialer.repository;

import com.cartrack.autodialer.domain.Client;
import org.asteriskjava.Cli;

import java.util.List;

/**
 * Created by vinner on 29.09.2015.
 */
public interface ClientRepository {

    List<Client> getByList(int listId);

    List<Client> getAll();

    Client save(Client client);

    boolean delete(int id);

    Client get(int id);

    void deleteAllByList(int listId);


}
