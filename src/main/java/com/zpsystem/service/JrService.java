package com.zpsystem.service;



import com.zpsystem.entity.Jobresume;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface JrService {
    boolean checkJr(HashMap<String, String> m);
    boolean insertJr(HashMap<String, String> m);
    boolean deleteJr(HashMap<String, String> m);
    boolean updateJr(HashMap<String, String> m);
    boolean insertJr(Jobresume jobresume);
    boolean deleterJr(Jobresume jobresume);
    boolean updateJr(Jobresume jobresume);
    ArrayList<Jobresume> getJr(HashMap<String, String> m);
    List getJr(Map map);
    List getJrone(Map map);
}
