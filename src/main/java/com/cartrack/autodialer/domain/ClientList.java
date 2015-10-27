package com.cartrack.autodialer.domain;

/**
 * Created by vinner on 27.08.2015.
 */
public class ClientList extends NamedEntity {

    protected String description;

    public ClientList(){

    }

    public ClientList(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
