package com.zpsystem.service;


import com.zpsystem.entity.Company;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface ComService {
    boolean update(Map m);
    boolean delete(Map m);
    boolean insert(Map m);
    List getCom(Map map);

}
