package com.zpsystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zpsystem.dao.DDLDML;
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

	public boolean checkJp(HashMap<String, String> m) {
		String  sql="select jpid from jobposting where jpid="+m.get("jpid");
        return DDLDML.isExist(sql);
	}

	public boolean insertJp(HashMap<String, String> m) {
		  String sql="insert into jobposting(jpid,cid,jppost,jpsalary,jpaddress,jprequest,rid) values(?,?,?,?,?,?,?)";
	        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
	                m.get("jpid"),m.get("cid"),m.get("jppost"),
	                m.get("jpsalary"),m.get("jpaddress"),m.get("jprequest"),m.get("rid")
	        });
	}

	public boolean deleteJp(HashMap<String, String> m) {
		String sql="delete from jobposting where jpid in ("+m.get("jpid")+")";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
	}

	public boolean updateJp(HashMap<String, String> m) {
	     String sql="update jobposting set jpid=?,cid=?,jppost=?,jpsalary=?,jpaddress=?,jprequest=?,rid=? where jpid=?";
	        logger.debug(sql);
	        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
	                m.get("jpid"),m.get("cid"),m.get("jppost"),
	                m.get("jpsalary"),
	                m.get("jpaddress"),m.get("jprequest"),
	               m.get("rid"),m.get("oldjpid")
	       });
	}

	public boolean insertJp(Jobposting jobposting) {
		 String sql="insert into jobposting(jpid,cid,jppost,jpsalary,jpaddress,jprequest,rid)"+" values('"
	                +jobposting.getJpid()+"','"
	                +jobposting.getCid()+"','"
	                +jobposting.getJppost()+"','"
	                +jobposting.getJpsalary()+"','"
	                +jobposting.getJpaddress()+"','"
	                +jobposting.getJprequest()+"','"
	                +jobposting.getRid()+
	                "')";
	        logger.debug(sql);
	        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
	}

	public boolean deleterJp(Jobposting jobposting) {
		String sql="delete from jobposting where jpid='"+jobposting.getJpid()+"'";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
	}

	public boolean updateJp(Jobposting jobposting) {
		String sql="update jobposting set cid="+jobposting.getCid()
        +","+"jppost='"+jobposting.getJppost()
        +"',"+"jpsalary='"+jobposting.getJpsalary()
        +","+"jpaddress='"+jobposting.getJpaddress()
        +"',"+"jprequest='"+jobposting.getJprequest()
        +"',"+"cid="+jobposting.getCid()+
        " where jpid="
        +jobposting.getJpid();
logger.debug(sql);
return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
	}

	public ArrayList<Jobposting> getJp(HashMap<String, String> m) {
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
        sql="select a.*,rownum rn from jobposting a" ;
        if (find!=null)
            sql=sql+" where jppost like '%"+find+"%'";
        sql="select * from ("
                +sql
                +") b  where b.rn between  "+(pageIndex* pagesize+1)+" and "+
                (pageIndex * pagesize+pagesize);
        logger.debug(sql);
        return DDLDML.getJobposting(sql);
	}

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

}
