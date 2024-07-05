package com.zpsystem.service;

import java.util.List;
import java.util.Map;

public interface AppcategoryService {
    List getAppcategory(Map map);
    boolean insert(Map m);
    boolean update(Map m);
}
