package com.zpsystem.service;


import com.zpsystem.entity.Jobseekers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service

public interface JsService {
    boolean login(Map map);
    boolean zhuce(Map map);
    boolean changepwd(Map map);
    HashMap getJS(HashMap m);
    boolean checkJs(HashMap<String, String> m);
    boolean insertJS(HashMap<String, String> m);
    boolean deleteJS(HashMap<String, String> m);
    boolean updateJS(HashMap<String, String> m);
    boolean insertJs(Jobseekers jobseekers);
    boolean deleterJS(Jobseekers jobseekers);
    boolean updateJS(Jobseekers jobseekers);
    void insert();
    void delete();
    void update();
    void select();
    ArrayList<Jobseekers> getJs(HashMap<String, String> m);

}
