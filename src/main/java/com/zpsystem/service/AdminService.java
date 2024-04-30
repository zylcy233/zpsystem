package com.zpsystem.service;

import com.zpsystem.entity.Administrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AdminService {
    List selectadm(Map map);
    boolean insertAdmin(HashMap<String ,String> m);
    boolean deleteAdmin(HashMap<String ,String> m);
    boolean updateAdmin(HashMap<String ,String> m);
    boolean checkAdmin(HashMap<String, String> m);
    ArrayList<Administrator> getAdmin(HashMap<String,String> map);
    boolean insertAdm(Administrator adm);
    boolean deleterAdm(Administrator adm);
    boolean updaterAdm(Administrator adm);
    void insert();
    void delete();
    void update();
    ArrayList<Administrator> getAdmin(String aname);

}
