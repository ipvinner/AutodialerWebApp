package com.cartrack.autodialer.asterisk;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;

import java.io.IOException;

public class HelloManager {

    private ManagerConnection managerConnection;

    public HelloManager() throws IOException {
        ManagerConnectionFactory factory = new ManagerConnectionFactory("31.131.16.59", "autodialer", "gieB7Due6eit");

        this.managerConnection = factory.createManagerConnection();
    }

    public void call(final String user, final String phoneNumber) throws IOException, AuthenticationFailedException, TimeoutException {
        OriginateAction originateAction;
        ManagerResponse originateResponse;

        originateAction = new OriginateAction();
        originateAction.setChannel("Local/1310@from-ami");
        originateAction.setContext("from-ami");
        originateAction.setExten("1310");
        originateAction.setPriority(1);
        originateAction.setAsync(true);
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

        // print out whether the originate succeeded or not
        System.out.println("Response FROM JAVA SOUTPRINTLN : " + originateResponse.getResponse());

        // and finally log off and disconnect
        managerConnection.logoff();
    }


    public static void main(String[] args) throws Exception
    {
        HelloManager helloManager;

        helloManager = new HelloManager();
        helloManager.call("88001", "14168");
    }

}
