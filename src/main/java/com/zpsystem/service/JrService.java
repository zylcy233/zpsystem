package com.zpsystem.service;



import com.zpsystem.entity.Jobresume;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface JrService {
    List getJr(Map map);
    List getJrone(Map map);
    List getNewJr(Map map);
    boolean insert(Map map);
    boolean shoucang(Map map);
    boolean delshoucang(Map map);
    boolean update(Map map);
    boolean delete(Map map);
}
