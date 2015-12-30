package com.cartrack.autodialer.domain;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by vinner on 27.08.2015.
 */
public class User extends NamedEntity {
    protected String login;
    protected String passwordHash;
    protected String role;

    public User(){

    }

    public User(Integer id, String login, String password, String role){
        this.id = id;
        this.login = login;
        this.passwordHash = password;
        this.role = role;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
