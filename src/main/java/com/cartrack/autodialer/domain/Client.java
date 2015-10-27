package com.cartrack.autodialer.domain;

/**
 * Created by vinner on 27.08.2015.
 */
public class Client extends BaseEntity{
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;
    protected ClientList clientList;

    public Client(Integer id, String firstName, String lastName, String phoneNumber, String email, ClientList clientList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.clientList = clientList;
    }



    public Client(){


    }

    public Client(Client client1) {
        this(client1.getId(), client1.getFirstName(), client1.getLastName(), client1.getPhoneNumber(), client1.getEmail(), client1.getClientList());
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientList getClientList() {
        return clientList;
    }

    public void setClientList(ClientList clientList) {
        this.clientList = clientList;
    }

    @Override
    public String toString() {
        return "Client (" +
                "id=" + id +
                ", firstname=" + firstName +
                ", lastname=" + lastName +
                ", phonenumber=" + phoneNumber +
                ", email=" + email +
                ')';
    }
}
