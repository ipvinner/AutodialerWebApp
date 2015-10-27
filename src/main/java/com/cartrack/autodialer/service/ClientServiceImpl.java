package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.repository.ClientRepository;
import com.cartrack.autodialer.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vinner on 29.09.2015.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;


    @Override
    public List<Client> getByList(int listId) {
        return repository.getByList(listId);
    }

    @Override
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    public Client update(Client client) {
        return ExceptionUtil.check(repository.save(client), client.getId());
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.check(repository.delete(id), id);
    }

    @Override
    public Client get(int id) {
        return ExceptionUtil.check(repository.get(id), id);
    }

    @Override
    public void deleteAllByList(int listId) {
        repository.deleteAllByList(listId);
    }
}
