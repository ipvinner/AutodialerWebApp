package com.cartrack.autodialer.web;

import com.cartrack.autodialer.LoggerWrapper;
import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.service.ClientService;
import org.asteriskjava.Cli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by vinner on 12.11.2015.
 */
@RestController
@RequestMapping("rest/admin/clients")
public class ClientRestController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(ClientRestController.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getAll(){
        LOG.info("getAll");
        return clientService.getAll();
    }

    @RequestMapping(value = "/{listId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getByList(@PathVariable("listId") int listId){
        LOG.info("getByList");
        return clientService.getByList(listId);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Client get(@PathVariable("id") int id) {
//        LOG.info("get Client + " + id);
//        return clientService.get(id);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        LOG.info("delete + " + id);
        clientService.delete(id);
    }
    @RequestMapping(value = "/{listId}", method = RequestMethod.DELETE)
    public void deleteAllByList(@PathVariable("listID") int listId){
        LOG.info("delete all clients by listId + " + listId);
        clientService.deleteAllByList(listId);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> create(@RequestBody Client client) {
        client.setId(null);
        Client created = clientService.save(client);
        LOG.info("create + " + client);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("rest/admin/clients" + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriOfNewResource);

        return new ResponseEntity<>(created, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Client update(@RequestBody Client client, @PathVariable("id") int id) {
        client.setId(id);
        LOG.info("update Client + " + client);
        return clientService.update(client);
    }


}
