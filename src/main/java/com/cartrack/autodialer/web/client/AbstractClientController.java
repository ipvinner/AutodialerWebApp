package com.cartrack.autodialer.web.client;

import com.cartrack.autodialer.LoggerWrapper;
import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.List;

/**
 * Created by vinner on 12.11.2015.
 */

public class AbstractClientController {
    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    @Autowired
    private ClientService clientService;


    public Client get(int id){
        LOG.info("get client by id: " + id);
        return clientService.get(id);
    }

    public Collection<Client> getAll(){
        LOG.info("getAll");
        return clientService.getAll();
    }


    public List<Client> getByList(int listId){
        LOG.info("getByList");
        return clientService.getByList(listId);
    }


    public void delete( int id){
        LOG.info("delete + " + id);
        clientService.delete(id);
    }

    public void deleteAllByList(int listId){
        LOG.info("delete all clients by listId + " + listId);
        clientService.deleteAllByList(listId);
    }


    public Client create(Client client) {
        client.setId(null);
        LOG.info("create " + client);
        return clientService.save(client);
    }


    public Client update(Client client, int id) {
        client.setId(id);
        LOG.info("update Client + " + client);
        return clientService.update(client);
    }


}
