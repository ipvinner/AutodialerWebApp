package com.cartrack.autodialer.domain;



import java.time.LocalDateTime;

/**
 * Created by vinner on 09.12.2015.
 */
public class CallResult extends BaseEntity {
    protected LocalDateTime dateTime;
    protected String result;
    protected String reason;
    protected Task task;
    protected Client client;

    public CallResult(Integer id, LocalDateTime datetime, String result, String reason, Task task, Client client) {
        this.id = id;
        this.dateTime = datetime;
        this.result = result;
        this.reason = reason;
        this.task = task;
        this.client = client;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "CallResult{" +
                "dateTime=" + dateTime +
                ", result='" + result + '\'' +
                ", reason='" + reason + '\'' +
                ", client=" + client +
                ", task=" + task +
                '}';
    }
}
