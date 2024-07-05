package com.zpsystem.service;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public interface UserService {
    boolean login(Map map);
    boolean zhuce(Map map);
    boolean changepwd(Map map);
    boolean insertappcollection(Map map);
    boolean deleteappcollection(Map map);
    boolean delete(Map map);
    boolean update(Map map);
    Map sendemail(Map map);
    List getApp(Map map);
    List getAppCollection(Map map);
    List getJS(Map map);
    List getJsone(Map map);

}
