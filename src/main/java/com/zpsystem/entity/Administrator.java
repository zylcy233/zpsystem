package com.zpsystem.entity;

public class Administrator {
    private int aid;
    private String aname;
    private String apasswd;



    public Administrator() {

    }

    public Administrator(int aid, String aname, String apasswd) {
        this.aid = aid;
        this.aname = aname;
        this.apasswd = apasswd;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApasswd() {
        return apasswd;
    }

    public void setApasswd(String apasswd) {
        this.apasswd = apasswd;
    }

    @Override
    public String toString() {
        return "{" +
                "\"aid\":\"" + aid +
                "\","+"\"aname\":\"" + aname +
                "\","+"\"apasswd\":\"" + apasswd + "\""+
                '}';
    }
}
