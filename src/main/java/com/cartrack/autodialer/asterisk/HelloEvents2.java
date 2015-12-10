package com.cartrack.autodialer.asterisk;

import org.asteriskjava.manager.*;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.event.HangupEvent;
import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.response.ManagerResponse;

import java.io.IOException;

/**
 * Created by vinner on 26.08.2015.
 */
public class HelloEvents2 implements ManagerEventListener {

    private ManagerConnection managerConnection;

    public HelloEvents2() throws IOException {
        ManagerConnectionFactory factory = new ManagerConnectionFactory("31.131.16.59", "autodialer", "gieB7Due6eit");
        this.managerConnection = factory.createManagerConnection();

    }

    public void call() throws IOException, AuthenticationFailedException, TimeoutException, InterruptedException  {

        OriginateAction originateAction;
        ManagerResponse originateResponse;
        // register for events
        managerConnection.addEventListener(this);
        managerConnection.registerUserEventClass(CallDialStatusEvent.class);
        // connect to Asterisk and log in
        managerConnection.login();

        originateAction = new OriginateAction();
        originateAction.setChannel("Local/DIAL@my_context");
        originateAction.setContext("my_context");
        originateAction.setExten("s-ANSWERED");
        originateAction.setPriority(1);
        originateAction.setAsync(false);
        originateAction.setVariable("dial_string","SIP/zadarma/14168");

        originateResponse = managerConnection.sendAction(originateAction, 300000);

        // print out whether the originate succeeded or not
        System.out.println("SOUT Response getResponse:  " + originateResponse.getResponse());
        System.out.println("SOUT Response Message:   " + originateResponse.getMessage());
        System.out.println("SOUT Response getServer: " + originateResponse.getServer());
        System.out.println("SOUT Response getActionId: " + originateResponse.getActionId());
        System.out.println("SOUT Response getEventList: " + originateResponse.getEventList());
        System.out.println("SOUT Response getDateReceived: " + originateResponse.getDateReceived());
        System.out.println("SOUT Response getAttributes: " + originateResponse.getAttributes());
        System.out.println("SOUT Response getArrribute actionid: " + originateResponse.getAttribute("actionid"));
        System.out.println("SOUT Response getUniqueID " + originateResponse.getUniqueId());

        // and finally log off and disconnect
        managerConnection.logoff();
    }

    @Override
    public void onManagerEvent(ManagerEvent event) {
        if(event instanceof CallDialStatusEvent){
            System.out.println("Ураааааааааааааааааааа!!!!!!!!!!!!!");
        }




    }

    public static void main(String[] args) throws Exception {
        HelloEvents2 helloEvents;

        helloEvents = new HelloEvents2();
        helloEvents.call();
    }
}
