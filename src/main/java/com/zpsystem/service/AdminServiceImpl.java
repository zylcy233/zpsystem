package com.zpsystem.service;



import com.zpsystem.mapper.AdminMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class AdminServiceImpl implements AdminService {
    org.apache.log4j.Logger logger = Logger.getLogger(String.valueOf(AdminServiceImpl.class));


    @Autowired
    AdminMapper adminMapper;

    @Override
    public boolean login(Map map) {
        if (map.get("apasswd")==null) map.put("apasswd","");
        Map result=adminMapper.getOne(map);
        logger.debug(result);
        return result!=null;
    }

    @Override
    public List selectadm(Map map) {
        List m = adminMapper.selectadm(map);
        return m;
    }

    @Override
    public boolean insert(Map m) {
        logger.debug(m);
        boolean b=adminMapper.insert(m);
        return b;
    }

    @Override
    public boolean update(Map m) {
        logger.debug(m);
        boolean b=adminMapper.update(m);
        return b;
    }

}
