package com.zpsystem.service;

import com.zpsystem.mapper.RMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecruiterServiceImpl implements RecrutierService{
    org.apache.log4j.Logger logger= Logger.getLogger(String.valueOf(RecruiterServiceImpl.class));

    @Autowired
    RMapper rMapper;

    @Override
    public boolean login(Map map) {
        if (map.get("rpasswd")==null) map.put("rpasswd","");
        Map result=rMapper.getOne(map);
        logger.debug(result);
        return result!=null;
    }

    @Override
    public boolean zhuce(Map map) {
        logger.debug(map);
        boolean b=rMapper.zhuce(map);
        return b;
    }

    @Override
    public boolean changepwd(Map map) {
        logger.debug(map);
        boolean b=rMapper.changepwd(map);
        return b;
    }

    @Override
    public boolean insert(Map map) {
        logger.debug(map);
        boolean b=rMapper.insert(map);
        return b;
    }

    @Override
    public boolean delete(Map map) {
        logger.debug(map);
        boolean b=rMapper.delete(map);
        return b;
    }

    @Override
    public boolean update(Map map) {
        logger.debug(map);
        boolean b=rMapper.update(map);
        return b;
    }

    @Override
    public Map sendemail(Map map) {
        logger.debug(map);
        Map l=rMapper.check(map);
        return l;
    }

    @Override
    public List getRec(Map map) {
        logger.debug(map);
        List l=rMapper.select(map);
        return l;
    }

    @Override
    public List getRecOne(Map map) {
        logger.debug(map);
        List l=rMapper.getone(map);
        return l;
    }


}
