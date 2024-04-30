package com.zpsystem.entity;

public class Emptbl {
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private String hiredate;
    private float sal;
    private float comm;
    private int deptno;

    public Emptbl(int empno, String ename, String job, int mgr, String hiredate, float sal, float comm, int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Emptbl() {

    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public float getSal() {
        return sal;
    }

    public void setSal(float sal) {
        this.sal = sal;
    }

    public float getComm() {
        return comm;
    }

    public void setComm(float comm) {
        this.comm = comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "{" +
                "\"empno\":\"" + empno +
                "\","+ "\"ename\":\"" + ename +
                "\", "+"\"job\":\"" + job  +
                "\", "+"\"mgr\":\"" + mgr +
                "\","+"\"hiredate\":\"" + hiredate  +
                "\", "+"\"sal\":\"" + sal +
                "\", "+"\"comm\":\"" + comm +
                "\", "+"\"deptno\":\"" + deptno +"\""+
                '}';
    }
}
