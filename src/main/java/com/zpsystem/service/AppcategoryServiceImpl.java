package com.zpsystem.service;


import com.zpsystem.mapper.AppcategoryMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AppcategoryServiceImpl implements AppcategoryService {
    Logger logger = Logger.getLogger(String.valueOf(AppcategoryServiceImpl.class));


    @Autowired
    AppcategoryMapper appcategoryMapper;


    @Override
    public List getAppcategory(Map map) {
        List m = appcategoryMapper.select(map);
        return m;
    }

    @Override
    public boolean insert(Map m) {
        logger.debug(m);
        boolean b=appcategoryMapper.insert(m);
        return b;
    }

    @Override
    public boolean update(Map m) {
        logger.debug(m);
        boolean b=appcategoryMapper.update(m);
        return b;
    }

}
