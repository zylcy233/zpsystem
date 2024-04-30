package com.zpsystem.entity;

public class Jobresume {
    private int jrid;
    private int jsid;
    private String jsname;
    private String jrlvl;
    private String jradvantage;
    private String jrintention;
    private String jrwork;
    private String jrobject;
    private String jrschool;
    private String jrskill;
    private String jrcert;

    public Jobresume(int jrid, int jsid, String jsname, String jrlvl,
                     String jradvantage, String jrintention,
                     String jrwork, String jrobject,
                     String jrschool, String jrskill, String jrcert) {
        this.jrid = jrid;
        this.jsid = jsid;
        this.jsname = jsname;
        this.jrlvl = jrlvl;
        this.jradvantage = jradvantage;
        this.jrintention = jrintention;
        this.jrwork = jrwork;
        this.jrobject = jrobject;
        this.jrschool = jrschool;
        this.jrskill = jrskill;
        this.jrcert = jrcert;
    }

    public int getJrid() {
        return jrid;
    }

    public void setJrid(int jrid) {
        this.jrid = jrid;
    }

    public int getJsid() {
        return jsid;
    }

    public void setJsid(int jsid) {
        this.jsid = jsid;
    }

    public String getJsname() {
        return jsname;
    }

    public void setJsname(String jsname) {
        this.jsname = jsname;
    }

    public String getJrlvl() {
        return jrlvl;
    }

    public void setJrlvl(String jrlvl) {
        this.jrlvl = jrlvl;
    }

    public String getJradvantage() {
        return jradvantage;
    }

    public void setJradvantage(String jradvantage) {
        this.jradvantage = jradvantage;
    }

    public String getJrintention() {
        return jrintention;
    }

    public void setJrintention(String jrintention) {
        this.jrintention = jrintention;
    }

    public String getJrwork() {
        return jrwork;
    }

    public void setJrwork(String jrwork) {
        this.jrwork = jrwork;
    }

    public String getJrobject() {
        return jrobject;
    }

    public void setJrobject(String jrobject) {
        this.jrobject = jrobject;
    }

    public String getJrschool() {
        return jrschool;
    }

    public void setJrschool(String jrschool) {
        this.jrschool = jrschool;
    }

    public String getJrskill() {
        return jrskill;
    }

    public void setJrskill(String jrskill) {
        this.jrskill = jrskill;
    }

    public String getJrcert() {
        return jrcert;
    }

    public void setJrcert(String jrcert) {
        this.jrcert = jrcert;
    }

    @Override
    public String toString() {
        return "{" +
                "\"jrid\":\"" + jrid +
                "\","+"\"jsid\":\"" + jsid +
                "\","+"\"jsname\":\"" + jsname + 
                "\","+"\"jrlvl\":\"" + jrlvl + 
                "\","+"\"jradvantage\":\"" + jradvantage + 
                "\","+"\"jrintention\":\"" + jrintention + 
                "\","+"\"jrwork\":\"" + jrwork + 
                "\","+"\"jrobject\":\"" + jrobject + 
                "\","+"\"jrschool\":\"" + jrschool + 
                "\","+"\"jrskill\":\"" + jrskill + 
                "\","+"\"jrcert\":\"" + jrcert + "\""+
                '}';
    }

    public  Jobresume(){

    }
}
