package com.cartrack.autodialer.web.client;

import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.domain.ClientList;
import com.cartrack.autodialer.domain.RequestClientListWrapper;
import com.cartrack.autodialer.service.ClientListService;
import org.asteriskjava.Cli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.List;

/**
 * Created by vinner on 12.11.2015.
 */
@RestController
@RequestMapping("ajax/admin/clients")
public class AjaxClientController extends AbstractClientController {

    @Autowired
    private ClientListService clientListService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Client> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Client get(@PathVariable("id") int id){
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }


    @RequestMapping(method = RequestMethod.POST)
    public void updateOrCreate(@RequestParam("id") int id,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("email") String email){

        ClientList clientList = clientListService.get(1);
        Client client = new Client(id, firstName, lastName, phoneNumber, email, clientList);
        if (id == 0) {
            super.create(client);
        } else {
            super.update(client, id);
        }
    }

    @RequestMapping(value = "/getClientsLists", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ClientList> getLists() {
        return super.getClientsLists();
    }

//    @RequestMapping(value = "/addClientsList", method = RequestMethod.POST, consumes="application/json")
//    public void addClientsList(@RequestParam("name") String name,
//                       @RequestParam("description") String description,
//                       @RequestBody List<Client> clients ) {
////        super.createListAndAddClients(new ClientList(null, name, description), clients);
//        System.out.println("listName" + name);
//        System.out.println("listDesc" + description);
//        for (Object o : clients) {
//            System.out.println(o);
//        }
//
//    }

    @RequestMapping(value = "/addClientsList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestClientListWrapper> addClientsList(@RequestBody RequestClientListWrapper wrapper) {
//        super.createListAndAddClients(new ClientList(null, name, description), clients);
//        System.out.println("listName" + name);
//        System.out.println("listDesc" + description);
        HttpHeaders headers = new HttpHeaders();
        List<Client> clients = wrapper.getClients();
//        for (Object o : clients) {
//            System.out.println(o);
//        }
        return new ResponseEntity<RequestClientListWrapper>(wrapper, HttpStatus.OK);

    }

}
