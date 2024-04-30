package com.zpsystem.entity;

public class Zytbl {
    private int zya;
    private String zyb;
    private float zyc;
    private  String zyd;

    @Override
    public String toString() {
        return "{" +
                "\"zya\":\""+ zya +
                "\","+"\"zyb\":\"" + zyb +
                "\","+"\"zyc\":\"" + zyc +
                "\","+"\"zyd\":\""+ zyd +"\""+
                '}';
    }

    public Zytbl(int zya, String zyb, float zyc, String zyd) {
        this.zya = zya;
        this.zyb = zyb;
        this.zyc = zyc;
        this.zyd = zyd;
    }

    public Zytbl() {

    }

    public int getZya() {
        return zya;
    }

    public void setZya(int zya) {
        this.zya = zya;
    }

    public String getZyb() {
        return zyb;
    }

    public void setZyb(String zyb) {
        this.zyb = zyb;
    }

    public float getZyc() {
        return zyc;
    }

    public void setZyc(float zyc) {
        this.zyc = zyc;
    }

    public String getZyd() {
        return zyd;
    }

    public void setZyd(String zyd) {
        this.zyd = zyd;
    }

    public void setZya() {
    }
}
