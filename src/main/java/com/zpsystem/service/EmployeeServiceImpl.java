package com.zpsystem.service;


import com.zpsystem.dao.DDLDML;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeServiceImpl implements EmployeeService{
    String oraclesql="select a.*,b.name dept_name,c.name position_name,d.name educational_name,rownum rn \n" +
            "from t_employee a\n" +
            "left join t_department b\n" +
            "on a.dept_id = b.id\n" +
            "left join t_position c\n" +
            "on a.position = c.id\n" +
            "left join t_educational d\n" +
            "on a.educational = d.id\n" ;


    @Override
    public HashMap getEmployee(HashMap m) {
        HashMap map=new HashMap();
        if (m.get("key")!=null){
            oraclesql=oraclesql+"where a.name like '%"+m.get("key")+"%'";
        }
        System.out.println(oraclesql);
        int pageIndex=0;
        try {
            pageIndex=Integer.parseInt((String) m.get("pageIndex"));
        }catch (Exception e){}
        int pageSize=10;
        try {
            pageSize=Integer.parseInt((String) m.get("pageSize"));
        }catch (Exception e){}
        String sql ="select * from ("+oraclesql+") x where x.rn between "
                +(pageIndex*pageSize+1)+" and "+((pageIndex+1)*pageSize);
        ArrayList data= DDLDML.getDatas(sql);
        System.out.println(data);
        sql="select count(*) from ("+oraclesql+") ";
        int total=DDLDML.getCount(sql);
        System.out.println(total);
        map.put("total",total);
        map.put("data",data);
        return map;
    }
}
