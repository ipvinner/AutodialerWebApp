package com.cartrack.autodialer.domain;

/**
 * Created by vinner on 27.08.2015.
 */
public class Task extends NamedEntity {
    private ClientList clientList;
    private boolean active;
    private OriginateParam originateParam;

    public Task() {

    }

    public Task(Integer id, String name, ClientList clientList, boolean active, OriginateParam originateParam) {
        this.id = id;
        this.name = name;
        this.clientList = clientList;
        this.active = active;
        this.originateParam = originateParam;
    }


    public ClientList getClientList() {
        return clientList;
    }

    public void setClientList(ClientList clientList) {
        this.clientList = clientList;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public OriginateParam getOriginateParam() {
        return originateParam;
    }

    public void setOriginateParam(OriginateParam originateParam) {
        this.originateParam = originateParam;
    }
}
