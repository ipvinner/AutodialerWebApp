package com.cartrack.autodialer.asterisk;

import com.cartrack.autodialer.LoggerWrapper;
import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;

import java.io.IOException;

/**
 * Created by ipvinner on 02.12.2015.
 */
public class AsteriskHelper {

    private static final LoggerWrapper LOG = LoggerWrapper.get(AsteriskHelper.class);

    private String host;
    private String username;
    private String password;

    OriginateAction originateAction;
    ManagerResponse originateResponse;



    private ManagerConnection managerConnection;


    public AsteriskHelper(String host, String username, String password){
        ManagerConnectionFactory factory = new ManagerConnectionFactory(host, username, password);
        this.managerConnection = factory.createManagerConnection();
        System.out.println(this.managerConnection);
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

    public ManagerConnection getManagerConnection() {
        return managerConnection;
    }


    public void call(String trunk, String number, String context, String exten, int priority, long timeout, boolean isAsync, String var1, String var2) throws IOException, AuthenticationFailedException, TimeoutException, InterruptedException  {

        // connect to Asterisk and log in
        managerConnection.login();

        originateAction = new OriginateAction();
        originateAction.setChannel(trunk + number);
        originateAction.setContext(context);
        originateAction.setExten(exten);
        originateAction.setPriority(priority);
        originateAction.setAsync(isAsync);
        originateAction.setVariable("TestVar", var1);

        originateResponse = managerConnection.sendAction(originateAction, timeout );

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

        // request channel state
        // managerConnection.sendAction(new StatusAction());

        // wait 10 seconds for events to come in
        //Thread.sleep(10000);

        // and finally log off and disconnect
        managerConnection.logoff();
    }
}
