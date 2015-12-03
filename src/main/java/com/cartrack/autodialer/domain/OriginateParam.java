package com.cartrack.autodialer.domain;

/**
 * Created by vinner on 27.08.2015.
 */
public class OriginateParam extends NamedEntity {

    protected String context;
    protected String exten;
    protected int priority;
    protected boolean async;
    protected long timeout;
    protected String var1;
    protected String var2;
    protected String trunk;

    public OriginateParam(){

    }



    public OriginateParam(Integer id, String name, String context, String exten,
                          int priority, boolean async, long timeout, String var1, String var2, String trunk) {
        this.id = id;
        this.name = name;
        this.context = context;
        this.exten = exten;
        this.priority = priority;
        this.async = async;
        this.timeout = timeout;
        this.var1 = var1;
        this.var2 = var2;
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getExten() {
        return exten;
    }

    public void setExten(String exten) {
        this.exten = exten;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public String getTrunk() {
        return trunk;
    }

    public void setTrunk(String trunk) {
        this.trunk = trunk;
    }

    @Override
    public String toString() {
        return "OriginateParam{" +
                "context='" + context + '\'' +
                ", extension='" + exten + '\'' +
                ", priority=" + priority +
                ", async=" + async +
                ", timeout=" + timeout +
                ", var1='" + var1 + '\'' +
                ", var2='" + var2 + '\'' +
                '}';
    }
}
