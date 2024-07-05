package com.zpsystem.service;

import com.zpsystem.mapper.JsMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;



@Service
public class JSServiceImpl implements JsService{
    org.apache.log4j.Logger logger= Logger.getLogger(String.valueOf(JSServiceImpl.class));

    @Autowired
    JsMapper jsMapper;

    @Override
    public boolean login(Map map) {
        if (map.get("jspass")==null) map.put("jspass","");
        Map result=jsMapper.getOne(map);
        logger.debug(result);
        return result!=null;
    }

    @Override
    public boolean zhuce(Map map) {
        logger.debug(map);
        boolean b=jsMapper.zhuce(map);
        return b;
    }

    @Override
    public boolean changepwd(Map map) {
        logger.debug(map);
        boolean b=jsMapper.changepwd(map);
        return b;
    }

    @Override
    public boolean delete(Map map) {
        logger.debug(map);
        boolean b=jsMapper.delete(map);
        return b;
    }

    @Override
    public boolean update(Map map) {
        logger.debug(map);
        boolean b=jsMapper.update(map);
        return b;
    }

    @Override
    public Map sendemail(Map map) {
        logger.debug(map);
        Map l=jsMapper.check(map);
        return l;
    }


    @Override
    public List getJS(Map map) {
        logger.debug(map);
        List m=jsMapper.select(map);
        return m;
    }

    @Override
    public List getJsone(Map map) {
        logger.debug(map);
        List m=jsMapper.getone(map);
        return m;
    }


}
