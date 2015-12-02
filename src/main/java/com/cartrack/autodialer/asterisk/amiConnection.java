package com.cartrack.autodialer.asterisk;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;

/**
 * Created by ipvinner on 02.12.2015.
 */
public class AmiConnection {

    private String host;
    private String username;
    private String password;

    private ManagerConnection managerConnection;

    public AmiConnection(){

    }

    public AmiConnection(String host, String username, String password){
        ManagerConnectionFactory factory = new ManagerConnectionFactory(host, username, password);
        this.managerConnection = factory.createManagerConnection();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
