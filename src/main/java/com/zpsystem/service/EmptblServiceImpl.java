package com.zpsystem.service;


import com.zpsystem.dao.DDLDML;
import com.zpsystem.entity.Emptbl;

import java.util.ArrayList;
import java.util.HashMap;

public class EmptblServiceImpl implements EmptblService {
    @Override
    public boolean insertEmptbl(Emptbl emptbl) {
        String sql="insert into emp values(?,?,?,?,to_date(?,'yyyy/mm/dd'),?,?,?)";
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
                emptbl.getEmpno(), emptbl.getEname(), emptbl.getJob(), emptbl.getMgr()
                , emptbl.getHiredate(), emptbl.getSal(), emptbl.getComm(), emptbl.getDeptno()
        });
    }

    @Override
    public boolean deleteEmptbl(HashMap<String, String> m) {
        String sql="delete from emp where empno=?";
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
                m.get("empno")
        });
    }

    @Override
    public boolean updateEmptbl(HashMap<String, String> m) {
        String  sql="update emp set empno=?,ename=?,job=?,mgr=?,hiredate=to_date(?,'yyyy/mm/dd'),sal=?,comm=?,deptno=? where empno=?";
        System.out.println(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
                m.get("empno"), m.get("ename"),m.get("job"),m.get("mgr"),
                m.get("hiredate"),m.get("sal"),m.get("comm"),m.get("deptno"),
                m.get("oldempno")
        });
    }

    @Override
    public boolean selectEmptbl(Emptbl emptbl) {
        return false;
    }

    @Override
    public boolean checkEmptbl(HashMap<String, String> m) {
        String sql="select empno from emp where empno=?";
        return DDLDML.isExist(sql,new Object[]{
                m.get("empno")
        });
    }

    @Override
    public ArrayList<Emptbl> getEmptbls(HashMap<String, String> map) {
        String sql=null;
        String find=map.get("find");
        int pageIndex=1;
        try {
            pageIndex=Integer.parseInt(map.get("pageIndex"));
        }catch (Exception e){}
        sql="select a.*,rownum rn from emp a" ;
        if (find!=null)
            sql=sql+" where ename like '%"+find+"%'";
        sql="select * from ("
                +sql
                +") b  where b.rn between  "+((pageIndex-1)*2+1)+" and "+
                (pageIndex*2);
        return DDLDML.getemp(sql);

    }
}
