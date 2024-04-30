package com.zpsystem.service;

import com.zpsystem.dao.DDLDML;
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
//    @Override
//    public boolean insertAdmin(Administrator adm) {
//        String sql="insert into adm(Aid,Aname,Apasswd)"+" values('"+adm.getAid()+"','"
//                +adm.getAname()+"','"+adm.getApasswd()+"')";
//        logger.debug(sql);
//        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
//    }

@Autowired
    AdminMapper adminMapper;

    @Override
    public List selectadm(Map map) {
        List m= adminMapper.selectadm(map);
        return m;
    }

    @Override
    public boolean insertAdmin(HashMap<String, String> m) {
        String sql="insert into adm(aid,aname,apasswd) values(?,?,?)";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
                m.get("aid"),m.get("aname"),m.get("apasswd")
        });
    }

    @Override
    public boolean deleteAdmin(HashMap<String, String> m) {
        String sql="delete from adm where aid=?";
        logger.debug(m);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
                m.get("aid")
        });

    }

    @Override
    public boolean updateAdmin(HashMap<String, String> m) {
        String sql="update adm set aid=?,aname=?,apasswd=? where aid=?";
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
                m.get("aid"),m.get("aname"),m.get("apasswd"),m.get("oldaid")
        });
    }

    @Override
    public boolean checkAdmin(HashMap<String, String> m) {
        String  sql="select aid from adm where aid="+m.get("aid");
        return DDLDML.isExist(sql);
    }

    @Override
    public ArrayList<Administrator> getAdmin(HashMap<String, String> map) {
        String sql=null;
        String find=map.get("find");
        logger.debug(find);
        int pageIndex=1;
        int pagesize=2;
        try {
            pageIndex=Integer.parseInt(map.get("pageIndex"));
            pagesize=Integer.parseInt(map.get("pageSize"));
        }catch (Exception e){}
        logger.debug(pageIndex);
        logger.debug(pagesize);
        sql="select a.*,rownum rn from adm a" ;
        if (find!=null)
            sql=sql+" where aname like '%"+find+"%'";
        sql="select * from ("
                +sql
                +") b  where b.rn between  "+(pageIndex* pagesize+1)+" and "+
                (pageIndex * pagesize+pagesize);
        logger.debug(sql);
        return DDLDML.getAdmin(sql);
    }

    @Override
    public boolean insertAdm(Administrator adm) {
        String sql="insert into adm(aid,aname,apasswd)"+" values('"+adm.getAid()+"','"
                +adm.getAname()+"','"+adm.getApasswd()+"')";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
    }

    @Override
    public boolean deleterAdm(Administrator adm) {
        String sql="delete from adm where aid='"+adm.getAid()+"'";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
    }

    @Override
    public boolean updaterAdm(Administrator adm) {
        String sql="update adm set aname='"+adm.getAname()+"',"+"apasswd='"
                +adm.getApasswd()+"' where aid='"+adm.getAid()+"'";
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
    }

    @Override
    public void insert() {
        Administrator administrator=new Administrator();
        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.print("请输入管理员账号：");
            int Aid = getInt(scanner);
            if (Aid < 30000000 || Aid > 39999999) {
                logger.debug("管理员账号无效，应在30000000~39999999之间");
                continue;
            } else if (isExitAid(Aid) ) {
                System.err.println("管理员账号已经存在");
                continue;
            } else {
                administrator.setAid(Aid);
                break;
            }
        }

        System.out.print("请输入管理员姓名：");
        String Aname=getStr(scanner);
        administrator.setAname(Aname);

        while (true){
            logger.debug("请输入密码：");
            String Apasswd=getStr(scanner);
            if (!isPass(Apasswd)){
                System.err.println("密码长度应在8~12,由字母和数字组合");
                continue;
            }else {
                administrator.setApasswd(Apasswd);
                break;
            }
        }
        logger.debug("添加成功");
//        logger.debug(administrator);
//        a[aCount++]=administrator;

        String sql="insert into adm(aid,aname,apasswd)"+" values('"+administrator.getAid()+"','"
                +administrator.getAname()+"','"+administrator.getApasswd()+"')";
        logger.debug(sql);
        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
            logger.debug("增加到数据库成功："+administrator);
        }
    }

    @Override
    public void delete() {
//        if (aCount==0){
//            System.err.println("还未添加管理员，请先增加管理员");
//            return;
//        }
        while (true){
            logger.debug("请输入要删除的管理员账号：");
            Scanner scanner=new Scanner(System.in);
            int Aid=getInt(scanner);
//            int idx=isExit(Aid);
            if (Aid < 30000000 || Aid > 39999999) {
                logger.debug("管理员账号无效，应在30000000~39999999之间");
                continue;
            }
            if (!isExitAid(Aid)){
                System.err.println("管理员不存在");
                continue;
            }else {
//                a[idx]=a[aCount-1];
//                a[aCount-1]=null;
//                aCount--;
//                logger.debug("已删除");

                String sql="delete from adm where Aid='"+Aid+"'";
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
//        if (aCount==0){
//            System.err.print("还未添加管理员，请先增加管理员信息");
//            return;
//        }
        Scanner scanner=new Scanner(System.in);
        Administrator administrator=new Administrator();
//        int index=0;
        while (true) {
            System.out.print("请输入管理员账号：");
            int Aid = getInt(scanner);
            if (Aid < 30000000 || Aid > 39999999) {
                logger.debug("管理员账号无效，应在30000000~39999999之间");
                continue;
            }
//            index=isExit(Aid);
            if (!isExitAid(Aid) ) {
                System.err.println("要删除的管理员不存在");
                continue;
            }
            break;
        }

        System.out.print("请输入管理员姓名：");
        String Aname=getStr(scanner);
        administrator.setAname(Aname);

        while (true){
            logger.debug("请输入密码：");
            String Apasswd=getStr(scanner);
            if (!isPass(Apasswd)){
                System.err.println("密码长度应在8~12,由字母和数字组合");
                continue;
            }else {
                administrator.setApasswd(Apasswd);
                break;
            }
        }
//        logger.debug("修改成功");
//        logger.debug(a[index]);

        String sql="update adm set aname='"+administrator.getAname()+"',"+"apasswd='"
                +administrator.getApasswd()+"' where aid='"+administrator.getAid()+"'";
        logger.debug(sql);
        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
            logger.debug("修改数据库成功："+administrator);
        }
    }



 //    public void select() {
//        if (aCount==0){
//            System.err.print("还未添加管理员，请先增加管理员信息");
//            return;
//        }
//        for (Administrator A:DDLDML.getAdm("select * from adm")){
 //           if (A==null)
//                break;
 //           logger.debug(A);
 //       }
 //   }



    public ArrayList<Administrator> getAdmin(String aname) {
        String sql="select * from adm where aname like '%"+aname+"%'";
        return DDLDML.getAdmin(sql);
    }

    private static boolean isExitAid(int Aid) {
//        for (int i=0;i<aCount;i++){
//            if (a[i]==null)
//                return -1;
//            if (a[i].getAid()==id)
//                return i;
//        }
//        return -1;
//    }
        return DDLDML.isExist("select * from adm where Aid='"
                +Aid+"'");
    }
}
