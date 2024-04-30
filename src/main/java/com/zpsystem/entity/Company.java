package com.zpsystem.entity;

public class Company {
    private int cid;
    private String cname;
    private String cdescription;
    private String caddress;
    private long ctel;
    private String cpasswd;


    public Company() {

    }

    public Company(int cid, String cname, String cdescription, String caddress, long ctel, String cpasswd) {
        this.cid = cid;
        this.cname = cname;
        this.cdescription = cdescription;
        this.caddress = caddress;
        this.ctel = ctel;
        this.cpasswd = cpasswd;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdescription() {
        return cdescription;
    }

    public void setCdescription(String cdescription) {
        this.cdescription = cdescription;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public long getCtel() {
        return ctel;
    }

    public void setCtel(long ctel) {
        this.ctel = ctel;
    }

    public String getCpasswd() {
        return cpasswd;
    }

    public void setCpasswd(String cpasswd) {
        this.cpasswd = cpasswd;
    }

    @Override
    public String toString() {
        return "{" +
                "\"cid\":\"" + cid +
                "\","+"\"cname\":\"" + cname +
                "\","+ "\"cdescription\":\"" + cdescription +
                "\","+"\"caddress\":\"" + caddress +
                "\", "+"\"ctel\":\"" + ctel +
                "\", "+"\"cpasswd\":\"" + cpasswd + "\""+
                '}';
    }
}
