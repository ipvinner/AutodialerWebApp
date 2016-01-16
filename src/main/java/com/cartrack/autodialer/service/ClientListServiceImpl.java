package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.domain.ClientList;
import com.cartrack.autodialer.repository.ClientListRepository;
import com.cartrack.autodialer.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vinner on 05.11.2015.
 */
@Service
public class ClientListServiceImpl implements ClientListService {

    @Autowired
    private ClientListRepository repository;


    @Override
    public List<ClientList> getAll() {
        return repository.getAll();
    }

    @Override
    public ClientList get(int id) {
        return repository.get(id);
    }

    @Override
    public void createWithListOfClients(ClientList clientList, List<Client> clientsList) {
        repository.createWithListOfClients(clientList, clientsList);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.check(repository.delete(id), id);
    }
}
