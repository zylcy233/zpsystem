package com.zpsystem.entity;

public class Recruiter {
    private int rid;
    private String rname;
    private String rsex;
    private int cid;
    private long rtel;
    private String rpasswd;
    private String remail;

    public Recruiter(int rid, String rname,
                     String rsex, int cid,
                     long rtel, String rpasswd,String remail) {
        this.rid = rid;
        this.rname = rname;
        this.rsex = rsex;
        this.cid = cid;
        this.rtel = rtel;
        this.rpasswd = rpasswd;
        this.remail=remail;
    }

    public Recruiter(){

    }

    @Override
    public String toString() {
        return "Recruiter{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", rsex='" + rsex + '\'' +
                ", cid=" + cid +
                ", rtel=" + rtel +
                ", rpasswd='" + rpasswd + '\'' +
                '}';
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRsex() {
        return rsex;
    }

    public void setRsex(String rsex) {
        this.rsex = rsex;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public long getRtel() {
        return rtel;
    }

    public void setRtel(long rtel) {
        this.rtel = rtel;
    }

    public String getRpasswd() {
        return rpasswd;
    }

    public void setRpasswd(String rpasswd) {
        this.rpasswd = rpasswd;
    }
    public String getRemail() {
        return remail;
    }

    public void setRemail(String remail) {
        this.remail = remail;
    }
}
