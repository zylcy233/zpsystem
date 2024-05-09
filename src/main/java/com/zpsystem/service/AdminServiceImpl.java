package com.zpsystem.service;


import com.zpsystem.entity.Administrator;
import com.zpsystem.mapper.AdminMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

import static com.zpsystem.util.zyUtil.*;

@Service
public class AdminServiceImpl implements AdminService {
    org.apache.log4j.Logger logger= Logger.getLogger(String.valueOf(AdminServiceImpl.class));


@Autowired
    AdminMapper adminMapper;

    @Override
    public List selectadm(Map map) {
        List m= adminMapper.selectadm(map);
        return m;
    }

}
