package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.ClientList;
import com.cartrack.autodialer.repository.ClientListRepository;
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
    public ClientList getById(int id) {
        return repository.getByid(id);
    }
}
