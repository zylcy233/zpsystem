package com.zpsystem.service;

import com.alibaba.fastjson.JSON;
import com.zpsystem.dao.DDLDML;
import com.zpsystem.entity.Jobseekers;
import com.zpsystem.mapper.JsMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

import static com.zpsystem.util.zyUtil.*;

@Service
public class JSServiceImpl implements JsService{
    org.apache.log4j.Logger logger= Logger.getLogger(String.valueOf(JSServiceImpl.class));

//    public static int jsCount;
//    private final static Jobseekers jss[]= new Jobseekers[20] ;
    String oraclesql=" SELECT jsid, jsname, jssex, jsbirth, jstel, jsaddress, jspasswd,jsemail, rownum as rn  \n" +
        "    FROM Jobseekers  a";
    @Autowired
    JsMapper jsMapper;

    @Override
    public boolean login(Map map) {
        if (map.get("jspass")==null) map.put("jspass","");
        Map result=jsMapper.getOne(map);
        logger.debug(result);
        return result!=null;
    }

    @Override
    public boolean zhuce(Map map) {
        logger.debug(map);
        boolean b=jsMapper.zhuce(map);
        return b;
    }

    @Override
    public boolean changepwd(Map map) {
        logger.debug(map);
        boolean b=jsMapper.changepwd(map);
        return b;
    }

    @Override
    public HashMap getJS(HashMap m) {
        HashMap map=new HashMap();
        if (m.get("key")!=null){
            oraclesql=oraclesql+"where a.jsname like '%"+m.get("key")+"%'";
        }
        logger.debug(oraclesql);
        int pageIndex=1;
        try {
            pageIndex=Integer.parseInt((String) m.get("pageIndex"));
        }catch (Exception e){}
        int pageSize=10;
        try {
            pageSize=Integer.parseInt((String) m.get("pageSize"));
        }catch (Exception e){}
        String sql ="select * from ("+oraclesql+") x where x.rn between "
                +((pageIndex-1)*2+1)+" and "+((pageIndex)*2);
        ArrayList data= DDLDML.getDatas(sql);
        logger.debug(data);
        sql="select count(*) from ("+oraclesql+") ";
        int total= DDLDML.getCount(sql);
        logger.debug(total);
        map.put("total",total);
        map.put("data",data);
        return map;
    }

    @Override
    public boolean checkJs(HashMap<String, String> m) {
        String  sql="select jsid from Jobseekers where jsid="+m.get("jsid");
        return DDLDML.isExist(sql);
    }

    @Override
    public boolean insertJS(HashMap<String, String> m) {
        String sql="insert into Jobseekers(jsid,jsname,jssex,jsbirth,jstel,jsaddress,jspasswd,jsemail) values(?,?,?,to_date(?,'yyyy/mm/dd'),?,?,?)";
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
                m.get("jsid"),m.get("jsname"),m.get("jssex"),
                m.get("jsbirth"),m.get("jstel"),m.get("jsaddress"),m.get("jspasswd"),m.get("jsemail")
        });
    }

    @Override
    public boolean deleteJS(HashMap<String, String> m) {
        String sql="delete from Jobseekers where jsid in ("+m.get("jsid")+")";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
    }

    @Override
    public boolean updateJS(HashMap<String, String> m) {
//        String sql="update Jobseekers set jsid="+m.get("jsid")+
//                ",jsname='"+m.get("jsname")
//                +"',"+"jssex='"+m.get("jssex")
//                +"',"+"jsbirth=to_date('"+m.get("jsbirth")+
//                "','YYYY-MM-DD\"T\"HH24:MI:SS')"
//                +","+"jstel='"+m.get("jstel")
//                +"',"+"jsaddress='"+m.get("jsaddress")
//                +"',"+"jspasswd='"+m.get("jspasswd")
//                +"',"+"jsemail='"+m.get("jsemail")+
//                "' where jsid='"
//               +m.get("oldjsid")+"'";
//    
//        logger.debug(sql);
//        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
       String sql="update Jobseekers set jsid=?,jsname=?,jssex=?,jsbirth=(?,'YYYY-MM-DD\"T\"HH24:MI:SS'),jstel=?,jsaddress=?,jspasswd=?,jsemail=? where jsid=?";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
                m.get("jsid"),m.get("jsname"),m.get("jssex"),
                m.get("jsbirth"),
                m.get("jstel"),m.get("jsaddress"),
               m.get("jspasswd"),m.get("jsemail"),m.get("oldjsid")
       });
    }


    @Override
    public boolean insertJs(Jobseekers jobseekers) {
        String sql="insert into Jobseekers(jsid,jsname,jssex,jsbirth,jstel,jsaddress,jspasswd,jsemail)"+" values('"
                +jobseekers.getJsid()+"','"
                +jobseekers.getJsname()+"','"
                +jobseekers.getJssex()+"',to_date('"
                +jobseekers.getJsbirth()+"','YYYY-MM-DD\"T\"HH24:MI:SS'),'"
                +jobseekers.getJstel()+"','"
                +jobseekers.getJsaddress()+"','"
                +jobseekers.getJspasswd()+"','"
                +jobseekers.getJsemail()+
                "')";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
    }

    @Override
    public boolean deleterJS(Jobseekers js) {
        String sql="delete from Jobseekers where jsid='"+js.getJsid()+"'";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
    }

    @Override
    public boolean updateJS(Jobseekers jobseekers) {
        String sql="update Jobseekers set jsname='"+jobseekers.getJsname()
                +"',"+"jssex='"+jobseekers.getJssex()
                +"',"+"jsbirth=to_date('"+jobseekers.getJsbirth()+
                "','YYYY-MM-DD\"T\"HH24:MI:SS')"
                +","+"jstel='"+jobseekers.getJstel()
                +"',"+"jsaddress='"+jobseekers.getJsaddress()
                +"',"+"jspasswd='"+jobseekers.getJspasswd()
                +"',"+"jsemail='"+jobseekers.getJsemail()+
                "' where jsid='"
                +jobseekers.getJsid()+"'";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
    }

    @Override
    public void insert() {
        Jobseekers jobseekers=new Jobseekers();
        Scanner scanner=new Scanner(System.in);
        //求职者编号
        while (true){
            System.out.print("请输入求职者编号：");
            int jsid=getInt(scanner);
            if (jsid<0||jsid>199999999){
                logger.debug("求职者编号无效，应在0~19999999之间");
                continue;}
            else if (isExit(jsid)){
                System.err.println("求职者编号已经存在");
                continue;
            }
            else {
                jobseekers.setJsid(jsid);
                break;
            }
        }

        //求职者姓名
        System.out.print("请输入求职者姓名：");
        String jsname=getStr(scanner);
        jobseekers.setJsname(jsname);

        //求职者性别
        while (true){
            System.out.print("请输入性别（男，女）：");
            String jssex=getStr(scanner);
            if (jssex.equals("男")||jssex.equals("女")){
                jobseekers.setJssex(jssex);
                break;
            }
            System.err.print("性别输入错误");
        }

        //jsbirth
        logger.debug("请输入生日：");
        String jsbirth=getStr(scanner);
        jobseekers.setJsbirth(jsbirth);

        //jstel
        while (true) {
            logger.debug("请输入你的联系方式：");
            long jstel = getLong(scanner);
            if (jstel < 10000000000L || jstel > 99999999999L) {
                System.err.print("无效号码，号码长度为11位");
                continue;
            } else if (isExitjstel(jstel) ) {
                System.out.print("号码重复");
                continue;
            }
            jobseekers.setJstel(jstel);
            break;
        }

        //jsaddress
        System.out.print("请输入地址：");
        String jsaddress=getStr(scanner);
        jobseekers.setJsaddress(jsaddress);

        //jspasswd
        while (true){
            logger.debug("请输入密码：");
            String jspasswd=getStr(scanner);
            if (!isPass(jspasswd)){
                System.err.println("密码长度应在8~12,由字母和数字组合");
                continue;
            }else {
                jobseekers.setJspasswd(jspasswd);
                break;
            }
        }
        logger.debug("添加成功");
//        logger.debug(jobseekers);
//        jss[jsCount++]=jobseekers;

        String sql="insert into Jobseekers(jsid,jsname,jssex,jsbirth,jstel,jsaddress,jspasswd,jsemail)"+" values('"
                +jobseekers.getJsid()+"','"
                +jobseekers.getJsname()+"','"
                +jobseekers.getJssex()+"',to_date('"
                +jobseekers.getJsbirth()+"','yyyy/mm/dd'),'"
                +jobseekers.getJstel()+"','"
                +jobseekers.getJsaddress()+"','"
                +jobseekers.getJspasswd()+"','"
                +jobseekers.getJsemail()+
                "')";
        logger.debug(sql);
        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
            logger.debug("增加到数据库成功："+jobseekers);
        }
    }

    @Override
    public void delete() {
//        if (jsCount==0){
//            System.err.println("还未添加求职者，请先增加求职者信息");
//            return;
//        }

        while (true){
            logger.debug("请输入要删除的求职者编号：");
            Scanner scanner=new Scanner(System.in);
            int jsid=getInt(scanner);

            if (jsid<0||jsid>19999999) {
                logger.debug("求职者编号无效，应在0~19999999之间");
                continue;
            }
            if (!isExit(jsid)){
                System.err.println("求职者编号不存在");
                continue;
            }else {
//                jss[idx]=jss[jsCount-1];
//                jss[jsCount-1]=null;
//                jsCount--;
//                logger.debug("已删除");

                String sql="delete from Jobseekers where jsid='"+jsid+"'";
                logger.debug(sql);
                if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
                    logger.debug("从数据库中删除成功");
                }

                break;
            }
        }
    }

    @Override
    public void update() {
//        if (jsCount==0){
//            System.err.print("还未添加求职者，请先增加求职者信息");
//            return;
//        }
        Scanner scanner = new Scanner(System.in);
        Jobseekers jobseekers=new Jobseekers();
//        int index=0;
        while (true) {
            System.out.print("请输入要修改的求职者的编号：");
            int jsid = getInt(scanner);
            if (jsid < 0 || jsid > 99999999) {
                logger.debug("求职者编号无效，应在0~99999999之间");
                continue;
            }
//            index=isExit(jsid);
            if (!isExit(jsid)) {
                System.err.println("要修改的求职者不存在");
                continue;
            }
            jobseekers.setJsid(jsid);
            break;
        }


        System.out.print("请输入求职者姓名：");
        String jsname = getStr(scanner);
        jobseekers.setJsname(jsname);

        //求职者性别
        while (true) {
            System.out.print("请输入性别（男，女）：");
            String jssex = getStr(scanner);
            if (jssex.equals("男") || jssex.equals("女")) {
                jobseekers.setJssex(jssex);
                break;
            }
            System.err.print("性别输入错误");
        }

        //jsbirth
        logger.debug("请输入生日：");
        String jsbirth = getStr(scanner);
        jobseekers.setJsbirth(jsbirth);

        //jstel
        while (true) {
            logger.debug("请输入你的联系方式：");
            long jstel = getLong(scanner);
            if (jstel < 10000000000L || jstel > 99999999999L) {
                System.err.print("无效号码，号码长度为11位");
                continue;
            }
//            } else if (isExitjstel(jstel)) {
//                System.out.print("号码重复");
//            }
            jobseekers.setJstel(jstel);
            break;
        }

        //jsaddress
        System.out.print("请输入地址：");
        String jsaddress = getStr(scanner);
        jobseekers.setJsaddress(jsaddress);

        //jspasswd
        while (true){
            logger.debug("请输入密码：");
            String jspasswd=getStr(scanner);
            if (!isPass(jspasswd)){
                System.err.println("密码长度应在8~12,由字母和数字组合");
                continue;
            }else {
                jobseekers.setJspasswd(jspasswd);
                break;
            }
        }
//        logger.debug("添加成功");
//        logger.debug("修改后"+jobseekers);

        String sql="update Jobseekers set jsname='"+jobseekers.getJsname()
                +"',"+"jssex='"+jobseekers.getJssex()
                +"',"+"jsbirth=to_date('"+jobseekers.getJsbirth()+
                "','yyyy/mm/dd')"
                +","+"jstel='"+jobseekers.getJstel()
                +"',"+"jsaddress='"+jobseekers.getJsaddress()
                +"',"+"jspasswd='"+jobseekers.getJspasswd()
                +"',"+"jsemail='"+jobseekers.getJsemail()+

                "' where jsid='"
                +jobseekers.getJsid()+"'";
        logger.debug(sql);
        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
            logger.debug("修改数据库成功："+jobseekers);
        }
    }

    @Override
    public void select() {
//        if (jsCount==0){
//            System.err.print("还未添加求职者，请先增加求职者信息");
//            return;
//        }
//        for (Jobseekers js:DDLDML.getJobseekers()){
//            if (js==null)
//                break;
//            logger.debug(js);
//        }

    }


    @Override
    public ArrayList<Jobseekers> getJs(HashMap<String, String> m) {
        String sql=null;
        String find=m.get("find");
        int pageIndex=1;
        int pagesize=2;
        try {
            pageIndex=Integer.parseInt(m.get("pageIndex"));
            pagesize=Integer.parseInt(m.get("pageSize"));
        }catch (Exception e){}
        logger.debug(pageIndex);
        logger.debug(pagesize);
        sql="select a.*,rownum rn from Jobseekers a" ;
        if (find!=null)
            sql=sql+" where jsname like '%"+find+"%'";
        sql="select * from ("
                +sql
                +") b  where b.rn between  "+(pageIndex* pagesize+1)+" and "+
                (pageIndex * pagesize+pagesize);
        logger.debug(sql);
        return DDLDML.getJobseekers(sql);

    }

    @Override
    public List getJS(Map map) {
        logger.debug(map);
        List m=jsMapper.select(map);
        return m;
    }

    @Override
    public List getJsone(Map map) {
        logger.debug(map);
        List m=jsMapper.getone(map);
        return m;
    }

    private static boolean isExit(int jsid) {
//        for (int i=0;i<jsCount;i++){
//            if (jss[i]==null)
//                return -1;
//            if (jss[i].getjsid()==jsid)
//                return i;
//        }
//        return -1;
        return DDLDML.isExist("select * from Jobseekers where jsid='"+jsid+"'");
    }

    private static boolean isExitjstel(long jstel){
//        if(jsCount==0)
//            return -1;
//        for (Jobseekers js:jss){
//            if (js==null)
//                break;
//            if (js.getjstel()==jstel)
//                return 0;
//
//        }
//        return -1;
        return DDLDML.isExist("select * from Jobseekers where jstel='"+jstel+"'");
    }


}
