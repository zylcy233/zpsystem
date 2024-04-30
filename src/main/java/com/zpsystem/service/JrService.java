package com.zpsystem.service;



import com.zpsystem.entity.Jobresume;

import java.util.ArrayList;
import java.util.HashMap;

public interface JrService {
    boolean checkJr(HashMap<String, String> m);
    boolean insertJr(HashMap<String, String> m);
    boolean deleteJr(HashMap<String, String> m);
    boolean updateJr(HashMap<String, String> m);
    boolean insertJr(Jobresume jobresume);
    boolean deleterJr(Jobresume jobresume);
    boolean updateJr(Jobresume jobresume);
    ArrayList<Jobresume> getJr(HashMap<String, String> m);
}
