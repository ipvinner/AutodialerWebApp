package com.cartrack.autodialer.asterisk;


import org.asteriskjava.manager.event.UserEvent;

/**
 * Created by vinner on 26.08.2015.
 */
public class VIPCallEvent extends UserEvent
{
    private String firstName;


    public VIPCallEvent(Object source)
    {
        super(source);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
}
