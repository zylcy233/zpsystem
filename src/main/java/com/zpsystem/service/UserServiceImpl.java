package com.zpsystem.service;

import com.zpsystem.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;



@Service
public class UserServiceImpl implements UserService {
    org.apache.log4j.Logger logger= Logger.getLogger(String.valueOf(UserServiceImpl.class));

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean login(Map map) {
        if (map.get("passwd")==null)
            map.put("passwd","");
        Map result= userMapper.getOne(map);
        logger.debug(result);
        return result!=null;
    }

    @Override
    public boolean zhuce(Map map) {
        logger.debug(map);
        boolean b= userMapper.zhuce(map);
        return b;
    }

    @Override
    public boolean changepwd(Map map) {
        logger.debug(map);
        boolean b= userMapper.changepwd(map);
        return b;
    }

    @Override
    public boolean insertappcollection(Map map) {
        logger.debug(map);
        boolean b=userMapper.shoucang(map);
        return b;
    }

    @Override
    public boolean deleteappcollection(Map map) {
        logger.debug(map);
        boolean b=userMapper.delshoucang(map);
        return b;
    }

    @Override
    public boolean delete(Map map) {
        logger.debug(map);
        boolean b= userMapper.delete(map);
        return b;
    }

    @Override
    public boolean update(Map map) {
        logger.debug(map);
        boolean b= userMapper.update(map);
        return b;
    }

    @Override
    public Map sendemail(Map map) {
        logger.debug(map);
        Map l= userMapper.check(map);
        return l;
    }

    @Override
    public List getApp(Map map) {
        logger.debug(map);
        List l= userMapper.get(map);
        return l;
    }

    @Override
    public List getAppCollection(Map map) {
        logger.debug(map);
        List m= userMapper.select(map);
        return m;
    }


    @Override
    public List getJS(Map map) {
        logger.debug(map);
        List m= userMapper.select(map);
        return m;
    }

    @Override
    public List getJsone(Map map) {
        logger.debug(map);
        List m= userMapper.getone(map);
        return m;
    }


}
