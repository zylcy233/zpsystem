package com.zpsystem.entity;

public class Jobseekers {
    private int jsid;
    private String jsname;
    private String jssex;
    private String jsbirth;
    private long jstel;
    private String jsaddress;
    private String jspasswd;
    private String jsemail;



    public Jobseekers() {

    }

    public Jobseekers(int jsid, String jsname, String jssex, String jsbirth, long jstel, String jsaddress, String jspasswd,String jsemail) {
        this.jsid = jsid;
        this.jsname = jsname;
        this.jssex = jssex;
        this.jsbirth = jsbirth;
        this.jstel = jstel;
        this.jsaddress = jsaddress;
        this.jspasswd = jspasswd;
        this.jsemail = jsemail;
    }

    public String getJsemail() {
        return jsemail;
    }

    public void setJsemail(String jsemail) {
        this.jsemail = jsemail;
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

    public String getJssex() {
        return jssex;
    }

    public void setJssex(String jssex) {
        this.jssex = jssex;
    }

    public String getJsbirth() {
        return jsbirth;
    }

    public void setJsbirth(String jsbirth) {
        this.jsbirth = jsbirth;
    }

    public long getJstel() {
        return jstel;
    }

    public void setJstel(long jstel) {
        this.jstel = jstel;
    }

    public String getJsaddress() {
        return jsaddress;
    }

    public void setJsaddress(String jsaddress) {
        this.jsaddress = jsaddress;
    }

    public String getJspasswd() {
        return jspasswd;
    }

    public void setJspasswd(String jspasswd) {
        this.jspasswd = jspasswd;
    }

    @Override
    public String toString() {
        return "{" +
                "\"jsid\":\"" + jsid +
                "\","+ "\"jsname\":\"" + jsname +
                "\"," +"\"jssex\":\"" + jssex +
                "\","+ "\"jsbirth\":\"" + jsbirth +
                "\","+ "\"jstel\":\"" + jstel +
                "\","+ "\"jsaddress\":\"" + jsaddress +
                "\","+ "\"jspasswd\":\"" + jspasswd +
                "\"," +"\"jsemail\":\""+jsemail+"\""+
                '}';
    }
}
