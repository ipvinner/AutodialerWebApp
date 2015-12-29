package com.cartrack.autodialer.asterisk;

import com.cartrack.autodialer.domain.CallResult;
import org.asteriskjava.manager.*;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.event.HangupEvent;
import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.response.ManagerResponse;

import java.io.IOException;
import java.time.ZoneId;

/**
 * Created by vinner on 26.08.2015.
 */
public class HelloEvents2 implements ManagerEventListener {

    private ManagerConnection managerConnection;
    CallResult callResult = new CallResult();

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

        callResult.setDateTime(originateResponse.getDateReceived().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        // and finally log off and disconnect
        managerConnection.logoff();
        System.out.println(callResult);
    }

    @Override
    public void onManagerEvent(ManagerEvent event) {
        if(event instanceof CallDialStatusEvent){
            String dialStatus = ((CallDialStatusEvent) event).getDialStatus();
            if(dialStatus.equals("ANSWERED")){
                callResult.setResult("Success");
                callResult.setReason("Answered");
            }else {
                switch (dialStatus) {
                    case "NOANSWER":
                        callResult.setResult("Failed");
                        callResult.setReason("No answered");
                        break;
                    case "BUSY":
                        callResult.setResult("Failed");
                        callResult.setReason("Busy");
                        break;
                    case "CHANUNAVAIL":
                        callResult.setResult("Failed");
                        callResult.setReason("Channel is anavailable");
                        break;
                    case "CONGESTION":
                        callResult.setResult("Failed");
                        callResult.setReason("Congestion");
                        break;
                    case "CANCEL":
                        callResult.setResult("Failed");
                        callResult.setReason("Congestion");
                        break;
                    default:
                        callResult.setResult("Failed");
                        callResult.setReason("Unknown");
                        break;
                }
            }

        }

    }

    public static void main(String[] args) throws Exception {
        HelloEvents2 helloEvents;

        helloEvents = new HelloEvents2();
        helloEvents.call();
    }
}
