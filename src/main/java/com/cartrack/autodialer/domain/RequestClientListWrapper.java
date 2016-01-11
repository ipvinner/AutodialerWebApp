package com.cartrack.autodialer.domain;

import java.util.List;

/**
 * Created by vinner on 11.01.2016.
 */
public class RequestClientListWrapper {
    protected List<Client> clients;
    protected String name;
    protected String description;

    public RequestClientListWrapper(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "RequestClientListWrapper{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
