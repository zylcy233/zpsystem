package com.zpsystem.service;


import com.zpsystem.entity.Company;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface ComService {
     boolean insertCom(HashMap<String, String> m);
    boolean deleteCom(HashMap<String ,String> m);
    boolean updateCom(HashMap<String ,String> m);
    boolean checkCom(HashMap<String, String> m);

    List getCom(Map map);

    boolean insertCom(Company company);
    boolean deleterCom(Company company);
    boolean updateCom(Company company);
//    void insert();
//    void delete();
//    void update();
    ArrayList<Company> getCom(String cid);

}
