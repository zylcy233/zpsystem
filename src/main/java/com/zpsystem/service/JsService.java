package com.zpsystem.service;


import com.zpsystem.entity.Jobseekers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public interface JsService {
    boolean login(Map map);
    boolean zhuce(Map map);
    boolean changepwd(Map map);
    boolean delete(Map map);
    boolean update(Map map);
    Map sendemail(Map map);
    List getJS(Map map);
    List getJsone(Map map);

}
