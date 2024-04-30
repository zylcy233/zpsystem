package com.zpsystem.service;

import com.zpsystem.mapper.AdminMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdmServiceImpl implements AdmService {
    Logger logger=Logger.getLogger(AdmServiceImpl.class);
    @Autowired
    AdminMapper adminMapper;
    @Override
    public boolean login(Map map) {
        if (map.get("jspasswd")==null) map.put("jspasswd","");
        Map result=adminMapper.getOne(map);
        logger.debug(result);
        return result!=null;
    }

    @Override
    public boolean changepwd(Map map) {
        logger.debug(map);
        boolean b=adminMapper.changepwd(map);
        return b;
    }
}
