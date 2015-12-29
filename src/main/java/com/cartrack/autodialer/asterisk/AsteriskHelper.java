package com.cartrack.autodialer.asterisk;

import com.cartrack.autodialer.LoggerWrapper;
import com.cartrack.autodialer.domain.CallResult;
import com.cartrack.autodialer.util.TimeUtil;
import org.asteriskjava.manager.*;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.response.ManagerResponse;

import java.io.IOException;
import java.time.ZoneId;

/**
 * Created by ipvinner on 02.12.2015.
 */
public class AsteriskHelper implements ManagerEventListener {

    private static final LoggerWrapper LOG = LoggerWrapper.get(AsteriskHelper.class);
    CallResult callResult = new CallResult();

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


    public CallResult call(String trunk, String number, String context, String exten, int priority, long timeout, boolean isAsync, String var1, String var2)  {

        OriginateAction originateAction;
        ManagerResponse originateResponse;

        // register for events
        managerConnection.addEventListener(this);
        managerConnection.registerUserEventClass(CallDialStatusEvent.class);
        // connect to Asterisk and log in
        try {
            managerConnection.login();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        originateAction = new OriginateAction();
        originateAction.setChannel("Local/DIAL@my_context");
        originateAction.setContext(context);
        originateAction.setExten(exten);
        originateAction.setPriority(priority);
        originateAction.setAsync(isAsync);
        originateAction.setVariable("dial_string",trunk + number);

        try {
            originateResponse = managerConnection.sendAction(originateAction, 300000);
            callResult.setDateTime(originateResponse.getDateReceived().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().withNano(0));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }



        // and finally log off and disconnect
        managerConnection.logoff();
        System.out.println(callResult);
        return callResult;
    }

    @Override
    public void onManagerEvent(ManagerEvent event) {
        if(event instanceof CallDialStatusEvent){
            // get dialStatus
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
                        callResult.setReason("Unknown reason");
                        break;
                }
            }

        }

    }
}
