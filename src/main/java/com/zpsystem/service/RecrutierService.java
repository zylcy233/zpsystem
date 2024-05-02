package com.zpsystem.service;

import com.zpsystem.entity.Recruiter;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public interface RecrutierService {
    boolean login(Map map);
    boolean zhuce(Map map);
    boolean insertRec(Recruiter recruiter);
    boolean deleterRec(Recruiter recruiter);
    boolean updateRec(Recruiter recruiter);
    void insert();
    void delete();
    void update();
    void select();
    ArrayList<Recruiter> getRec(String rname);
    List getRec(Map map);

}
