package com.cartrack.autodialer.asterisk;

import com.cartrack.autodialer.LoggerWrapper;
import org.asteriskjava.manager.*;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;

import java.io.IOException;


public class HelloManager  {

    private ManagerConnection managerConnection;
    
    private static final LoggerWrapper LOG = LoggerWrapper.get(HelloManager.class);


    public HelloManager() throws IOException {
        ManagerConnectionFactory factory = new ManagerConnectionFactory("31.131.16.59", "autodialer", "gieB7Due6eit");
        this.managerConnection = factory.createManagerConnection();
    }

    public void call(final String user, final String phoneNumber) throws IOException, AuthenticationFailedException, TimeoutException {
        OriginateAction originateAction;
        ManagerResponse originateResponse;
        originateAction = new OriginateAction();
        originateAction.setChannel("SIP/zadarma/14168");
        originateAction.setContext("from-ami");
        originateAction.setExten("s");
        originateAction.setPriority(1);
        originateAction.setAsync(false);
        originateAction.setVariable("dial_string","SIP/zadarma/14168");
        originateAction.setTimeout(new Integer(30000));
//        originateAction.setContext("directdial");
//        originateAction.setExten("1");
//        originateAction.setPriority(new Integer(1));
//        originateAction.setTimeout(new Integer(30000));
//        originateAction.setVariable("customernum", phoneNumber);

        // connect to Asterisk and log in
        managerConnection.login();


        // send the originate action and wait for a maximum of 30 seconds for Asterisk
        // to send a reply

        originateResponse = managerConnection.sendAction(originateAction, 30000);
//        ResponseEvents responseEvents1 = managerConnection.sendEventGeneratingAction(originateAction);


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

        //System.out.println("SOUT RespontEVEnt" + responseEvents.getEvents());
        // and finally log off and disconnect
        managerConnection.logoff();
    }


    public static void main(String[] args) throws Exception {

        String[] phoneNumbers = {"14168", "14168", "14168"};
        String trunk = "SIP/zadarma/";


        HelloManager helloManager;

        helloManager = new HelloManager();
        helloManager.call("88001", "14168");
    }

}
