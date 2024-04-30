package com.zpsystem.service;



import com.zpsystem.entity.Jobposting;

import java.util.ArrayList;
import java.util.HashMap;

public interface JpService {
    
    boolean checkJp(HashMap<String, String> m);
    boolean insertJp(HashMap<String, String> m);
    boolean deleteJp(HashMap<String, String> m);
    boolean updateJp(HashMap<String, String> m);
    boolean insertJp(Jobposting jobposting);
    boolean deleterJp(Jobposting jobposting);
    boolean updateJp(Jobposting jobposting);
    ArrayList<Jobposting> getJp(HashMap<String, String> m);

}
