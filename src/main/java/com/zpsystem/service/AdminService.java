package com.zpsystem.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AdminService {
    List selectadm(Map map);
    boolean insert(Map m);
    boolean update(Map m);
    boolean login(Map map);
}
