package com.cartrack.autodialer;

import com.cartrack.autodialer.domain.Role;

import java.util.Set;

/**
 * Created by vinner on 27.08.2015.
 */
public class LoggedUser {
    protected int id;
    protected Set<Role> roles;
    protected boolean enabled;

    public int getId(){
        return id;
    }

}
