package com.zpsystem.service;

import com.zpsystem.entity.Company;
import com.zpsystem.mapper.ComMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ComServiceImpl implements ComService{
   org.apache.log4j.Logger logger= Logger.getLogger(String.valueOf(ComServiceImpl.class));

   @Autowired
    ComMapper comMapper;

    @Override
    public boolean update(Map m) {
        logger.debug(m);
        boolean b;
        b = comMapper.update(m);
        return b;
    }

    @Override
    public boolean delete(Map m) {
        logger.debug(m);
        boolean b=comMapper.delete(m);
        return b;
    }

    @Override
    public boolean insert(Map m) {
        logger.debug(m);
        boolean b=comMapper.insert(m);
        return b;
    }

    @Override
    public List getCom(Map map) {
        logger.debug(map);
        List m=comMapper.select(map);
        return m;
    }


}
