package com.zpsystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zpsystem.entity.Jobposting;
import com.zpsystem.mapper.JpMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public  class JpServiceImpl implements JpService{
	@Autowired
	JpMapper jpMapper;

	org.apache.log4j.Logger logger= Logger.getLogger(String.valueOf(JpServiceImpl.class));
	 String oraclesql=" SELECT jpid, cid, jppost, jpsalary, jpaddress, jprequest, rid, rownum as rn  \n" +
		        "    FROM jobposting  a";

	@Override
	public List getJp(Map map) {
		logger.debug(map);
		List l=jpMapper.get(map);
		return l;
	}
	@Override
	public List getNewJp(Map map) {
		logger.debug(map);
		List l=jpMapper.getone(map);
		return l;
	}

	@Override
	public boolean insertNewJp(Map map) {
		logger.debug(map);
		boolean b=jpMapper.insert(map);
		return b;
	}

	@Override
	public boolean deletNewJp(Map map) {
		logger.debug(map);
		boolean b=jpMapper.delete(map);
		return b;
	}

}
