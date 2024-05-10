package com.zpsystem.service;

import java.util.List;
import java.util.Map;
import com.zpsystem.mapper.JrMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JrServiceImpl implements JrService{
	@Autowired
	JrMapper jrMapper;

	org.apache.log4j.Logger logger= Logger.getLogger(String.valueOf(JrServiceImpl.class));
	String oraclesql=" SELECT *, rownum as rn  \n" +
	        "    FROM jobresume  a";
	@Override
	public List getJr(Map map) {
		logger.debug(map);
		List m=jrMapper.select(map);
		return m;
	}

	@Override
	public List getJrone(Map map) {
		logger.debug(map);
		List m=jrMapper.getone(map);
		return m;
	}

	@Override
	public List getNewJr(Map map) {
		logger.debug(map);
		List l=jrMapper.getNew(map);
		return l;
	}

	@Override
	public boolean insert(Map map) {
		logger.debug(map);
		boolean b=jrMapper.insert(map);
		return b;
	}

	@Override
	public boolean shoucang(Map map) {
		logger.debug(map);
		boolean b=jrMapper.shoucang(map);
		return b;
	}

	@Override
	public boolean delshoucang(Map map) {
		logger.debug(map);
		boolean b=jrMapper.delshoucang(map);
		return b;
	}

	@Override
	public boolean update(Map map) {
		logger.debug(map);
		boolean b=jrMapper.update(map);
		return b;
	}

	@Override
	public boolean delete(Map map) {
		logger.debug(map);
		boolean b=jrMapper.delete(map);
		return b;
	}

}
