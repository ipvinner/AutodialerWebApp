package com.cartrack.autodialer.repository;

import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.domain.ClientList;

import java.util.List;

/**
 * Created by vinner on 05.11.2015.
 */
public interface ClientListRepository {

    List<ClientList> getAll();

    ClientList get(int id);

    void createWithListOfClients(ClientList clientList, List<Client> clientsList);

    boolean delete(int id);

}
