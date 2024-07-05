package com.zpsystem.service;



import com.zpsystem.entity.Jobposting;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface JpService {
    List getJp(Map map);
    List getNewJp(Map map);
    boolean insert(Map map);
    boolean update(Map map);
    boolean delete(Map map);
    boolean insertNewJp(Map map);
    boolean deletNewJp(Map map);

}
