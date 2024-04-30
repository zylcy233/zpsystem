package com.zpsystem.entity;

public class Jobposting {
    private int jpid;
    private int cid;
    private String jppost;
    private String jpsalary;
    private String jpaddress;
    private String jprequest;
    private int rid;

    public Jobposting(int jpid, int cid, String jppost,
                      String jpsalary, String jpaddress,
                      String jprequest, int rid) {
        this.jpid = jpid;
        this.cid = cid;
        this.jppost = jppost;
        this.jpsalary = jpsalary;
        this.jpaddress = jpaddress;
        this.jprequest = jprequest;
        this.rid = rid;
    }

    public Jobposting() {
		// TODO Auto-generated constructor stub
	}

	public int getJpid() {
        return jpid;
    }

    public void setJpid(int jpid) {
        this.jpid = jpid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getJppost() {
        return jppost;
    }

    public void setJppost(String jppost) {
        this.jppost = jppost;
    }

    public String getJpsalary() {
        return jpsalary;
    }

    public void setJpsalary(String jpsalary) {
        this.jpsalary = jpsalary;
    }

    public String getJpaddress() {
        return jpaddress;
    }

    public void setJpaddress(String jpaddress) {
        this.jpaddress = jpaddress;
    }

    public String getJprequest() {
        return jprequest;
    }

    public void setJprequest(String jprequest) {
        this.jprequest = jprequest;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "{" +
                "\"jpid\":\"" + jpid +
                "\","+ "\"cid\":\"" + cid +
                "\","+"\"jppost\":\"" + jppost + 
                "\","+"\"jpsalary\":\"" + jpsalary + 
                "\","+"\"jpaddress\":\"" + jpaddress + 
                "\","+"\"jprequest\":\"" + jprequest + 
                "\","+"\"rid\":\"" + rid +"\""+
                '}';
    }
}
