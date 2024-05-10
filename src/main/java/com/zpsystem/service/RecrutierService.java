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
    boolean changepwd(Map map);
    boolean insert(Map map);
    boolean delete(Map map);
    boolean update(Map map);
    Map sendemail(Map map);
    List getRec(Map map);
    List getRecOne(Map map);

}
