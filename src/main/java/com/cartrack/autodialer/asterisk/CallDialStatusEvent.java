package com.cartrack.autodialer.asterisk;

import org.asteriskjava.manager.event.UserEvent;

/**
 * Created by ipvinner on 09.12.2015.
 */
public class CallDialStatusEvent extends UserEvent {
    protected String dialStatus;

    public CallDialStatusEvent(Object source) {
        super(source);
    }

    public String getDialStatus() {
        return dialStatus;
    }

    public void setDialStatus(String dialStatus) {
        this.dialStatus = dialStatus;
    }


}
