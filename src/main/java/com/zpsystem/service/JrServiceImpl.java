package com.zpsystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zpsystem.dao.DDLDML;
import com.zpsystem.entity.Jobresume;
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
	public boolean checkJr(HashMap<String, String> m) {
		String  sql="select jrid from jobresume where jrid="+m.get("jrid");
        return DDLDML.isExist(sql);
	}

	@Override
	public boolean insertJr(HashMap<String, String> m) {
		 String sql="insert into jobresume values(?,?,?,?,?,?,?,?,?,?,?)";
	        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
	                m.get("jrid"),m.get("jsid"),m.get("jsname"),
	                m.get("jrlvl"),m.get("jradvantage"),
	                m.get("jrintention"),m.get("jrwork"),
	                m.get("jrobject"),m.get("jrschool"),
	                m.get("jrskill"),m.get("jrcert")
	        });
	}

	@Override
	public boolean deleteJr(HashMap<String, String> m) {
		String sql="delete from jobresume where jrid in ("+m.get("jrid")+")";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
	}

	@Override
	public boolean updateJr(HashMap<String, String> m) {
		String sql="update jobresume set jrid=?,jsid=?,jppost=?,"
				+ "jsname=?,jrlvl=?,jradvantage=?,"
				+ "jrintention=?,jrwork=?,jrobject=?,"
				+ "jrschool=?,jrskill=?,jrcert=?"
				+ " where jrid=?";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
                m.get("jrid"),m.get("jsid"),m.get("jsname"),
                m.get("jrlvl"),m.get("jradvantage"),
                m.get("jrintention"),m.get("jrwork"),
                m.get("jrobject"),m.get("jrschool"),
                m.get("jrskill"),m.get("jrcert"),m.get("oldjrid")
       });
	}

	@Override
	public boolean insertJr(Jobresume jobresume) {
		String sql="insert into jobresume"+" values('"
                +jobresume.getJrid()+"','"
                +jobresume.getJsid()+"','"
                +jobresume.getJsname()+"','"
                +jobresume.getJrlvl()+"','"
                +jobresume.getJradvantage()+"','"
                +jobresume.getJrintention()+"','"
                +jobresume.getJrwork()+"','"
                +jobresume.getJrobject()+"','"
                +jobresume.getJrschool()+"','"
                +jobresume.getJrskill()+"','"
                +jobresume.getJrcert()+
                "')";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
	}

	@Override
	public boolean deleterJr(Jobresume jobresume) {
		String sql="delete from jobresume where jrid='"+jobresume.getJrid()+"'";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
	}

	@Override
	public boolean updateJr(Jobresume jobresume) {
		String sql="update jobresume set jsid='"+jobresume.getJsid()
        +"',"+"jsname='"+jobresume.getJsname()
        +"',"+"jrlvl='"+jobresume.getJrlvl()
        +"',"+"jradvantage='"+jobresume.getJradvantage()
        +"',"+"jrintention='"+jobresume.getJrintention()
        +"',"+"jrwork='"+jobresume.getJrwork()
        +"',"+"jrobject='"+jobresume.getJrobject()
        +"',"+"jrschool='"+jobresume.getJrschool()
        +"',"+"jrskill='"+jobresume.getJrskill()
        +"',"+"jrcert='"+jobresume.getJrcert()+
        "' where jrid='"
        +jobresume.getJrid()+"'";
logger.debug(sql);
return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
	}

	@Override
	public ArrayList<Jobresume> getJr(HashMap<String, String> m) {
		String sql=null;
        String find=m.get("find");
        int pageIndex=1;
        int pagesize=2;
        try {
            pageIndex=Integer.parseInt(m.get("pageIndex"));
            pagesize=Integer.parseInt(m.get("pageSize"));
        }catch (Exception e){}
        logger.debug("pageIndex:"+pageIndex);
        logger.debug("pageSize:"+pagesize);
        sql="select a.*,rownum rn from jobresume a" ;
        if (find!=null)
            sql=sql+" where jrid like '%"+find+"%'";
        sql="select * from ("
                +sql
                +") b  where b.rn between  "+(pageIndex* pagesize+1)+" and "+
                (pageIndex * pagesize+pagesize);
        logger.debug(sql);
        return DDLDML.getJobresume(sql);
	}

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

}
