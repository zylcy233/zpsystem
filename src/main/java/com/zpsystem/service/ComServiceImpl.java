package com.zpsystem.service;

import com.zpsystem.dao.DDLDML;
import com.zpsystem.entity.Company;
import com.zpsystem.mapper.ComMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ComServiceImpl implements ComService{
   org.apache.log4j.Logger logger= Logger.getLogger(String.valueOf(ComServiceImpl.class));

   @Autowired
    ComMapper comMapper;

    public boolean insertCom(HashMap<String, String> m) {
        String sql="insert into company(cid,cname,cdescription,caddress,ctel,cpasswd) values(?,?,?,?,?,?)";
        logger.debug(sql);
        logger.debug("cid:"+m.get("cid"));
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
                m.get("cid"),m.get("cname"),m.get("cdescription"),
                m.get("caddress"),m.get("ctel"),m.get("cpasswd")
        });
    }

    @Override
    public boolean deleteCom(HashMap<String, String> m) {
        String  sql="delete from company where cid=?";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
                m.get("cid")
        });
    }

    @Override
    public boolean updateCom(HashMap<String, String> m) {
        String  sql="update company set cid=?,cname=?,cdescription=?,caddress=?,ctel=?,cpasswd=? where cid=?";
        logger.debug(sql);
        logger.debug("cid:"+m.get("cid"));
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql,new Object[]{
                    m.get("cid"),m.get("cname"), m.get("cdescription"),m.get("caddress"),
                    m.get("ctel"),m.get("cpasswd"),m.get("oldcid")});
         
    }

    @Override
    public boolean checkCom(HashMap<String, String> m) {
        String  sql="select cid from Company where cid="+m.get("cid");
        return DDLDML.isExist(sql);
    }

    @Override
    public boolean update(Map m) {
        //  先查  有就更新  没有就保存
        logger.debug(m);
        boolean b;
        b = comMapper.update(m);
        return b;
    }

    @Override
    public boolean delete(Map m) {
        logger.debug(m);
        boolean b=comMapper.delete(m);
        return b;
    }

    @Override
    public List getCom(Map map) {
//        String sql=null;
//        String find= (String) map.get("find");
//        int pageIndex=1;
//        int pagesize=2;
//        try {
//            pageIndex=Integer.parseInt((String) map.get("pageIndex"));
//            pagesize=Integer.parseInt((String) map.get("pageSize"));
//        }catch (Exception e){}
//        logger.debug(pageIndex);
//        logger.debug(pagesize);
//        sql="select a.*,rownum rn from company a " ;
//        if (find!=null)
//            sql=sql+" where Cname like '%"+find+"%'";
//        sql="select * from ("
//                +sql
//                +") b  where b.rn between  "+(pageIndex* pagesize+1)+" and "+
//                (pageIndex * pagesize+pagesize);
//        logger.debug(sql);
//        return DDLDML.getCompany(sql);
        logger.debug(map);
        List m=comMapper.select(map);
        return m;
    }


    @Override
    public boolean insertCom(Company company) {
        String sql="insert into company"+" values('"+company.getCid()+"','"
                +company.getCname()+"','"+company.getCdescription()
                +"','"+company.getCaddress()
                +"','"+company.getCtel()
                +"','"+company.getCpasswd()+ "')";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);

    }

    @Override
    public boolean deleterCom(Company company) {
        String sql="delete from company where cid='"+company.getCid()+"'";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
    }

    @Override
    public boolean updateCom(Company company) {
        String sql="update company set Cname='"+company.getCname()+
                "',"+ "Cdescription='" +company.getCdescription()+
                "',"+ "Caddress='" +company.getCaddress()+
                "',"+ "Ctel='" +company.getCtel()+
                "',"+ "Cpasswd='" +company.getCpasswd()+
                "' where Cid='"+company.getCid()+"'";
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
    }

    //    public static int CpCount;
//    private final static Company cp[]= new Company[20] ;
//    @Override
//    public void insert() {
//        Company company=new Company();
//        Scanner scanner=new Scanner(System.in);
//        while (true){
//            logger.debug("请输入公司编号：");
//            int cid=getInt(scanner);
//            if (cid<20000000||cid>29999999){
//                logger.debug("公司编号无效，应在20000000~29999999之间");
//                continue;}
//            else if (isExitCid(cid)){
//                System.err.println("公司编号已经存在");
//                continue;
//            }
//            else {
//                company.setCid(cid);
//                break;
//            }
//        }
//
//        System.out.print("请输入公司名称：");
//        String Cname=getStr(scanner);
//        company.setCname(Cname);
//
//        System.out.print("请输入公司描述：");
//        String Cdescription=getStr(scanner);
//        company.setCdescription(Cdescription);
//
//        System.out.print("请输入公司地址：");
//        String Caddress=getStr(scanner);
//        company.setCaddress(Caddress);
//
//        while (true) {
//            logger.debug("请输入公司联系方式：");
//            long Ctel = getLong(scanner);
//            if (Ctel < 10000000000L || Ctel > 99999999999L) {
//                System.err.print("无效号码，号码长度为11位");
//                continue;
//            } else if (isExitCtel(Ctel) ) {
//                System.out.print("号码重复");
//            }
//            company.setCtel(Ctel);
//            break;
//        }
//        while (true){
//            logger.debug("请输入密码：");
//            String Cpasswd=getStr(scanner);
//            if (!isPass(Cpasswd)){
//                System.err.println("密码长度应在8~12,由字母和数字组合");
//                continue;
//            }else {
//                company.setCpasswd(Cpasswd);
//                break;
//            }
//        }
//        logger.debug("添加成功");
////        logger.debug(company);
////        cp[CpCount++]=company;
//
//        String sql="insert into company"+" values('"+company.getCid()+"','"
//                +company.getCname()+"','"+company.getCdescription()
//                +"','"+company.getCaddress()
//                +"','"+company.getCtel()
//                +"','"+company.getCpasswd()+ "')";
//        logger.debug(sql);
//        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
//            logger.debug("增加到数据库成功："+company);
//        }
//    }
//
//    @Override
//    public void delete() {
////        if (CpCount==0){
////            System.err.println("还未添加公司，请先增加公司信息");
////            return;
////        }
//
//        while (true){
//            logger.debug("请输入要删除的公司编号：");
//            Scanner scanner=new Scanner(System.in);
//            int Cid=getInt(scanner);
//            if (Cid<20000000||Cid>29999999){
//                logger.debug("公司编号无效，应在20000000~29999999之间");
//                continue;
//            }
//            if (!isExitCid(Cid)){
//                System.err.println("公司编号不存在");
//                continue;
//            }else {
////                cp[idx]=cp[CpCount-1];
////                cp[CpCount-1]=null;
////                CpCount--;
////                logger.debug("已删除");
//
//                String sql="delete from company where Cid='"+Cid+"'";
//                logger.debug(sql);
//                if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
//                    logger.debug("从数据库中删除成功");
//                }
//
//                break;
//            }
//        }
//    }
//
//    @Override
//    public void     update() {
////        if (CpCount==0){
////            System.err.print("还未添加求职者，请先增加求职者信息");
////            return;
////        }
//        Scanner scanner=new Scanner(System.in);
//        Company company=new Company();
////        int index=0;
//        while (true){
//            logger.debug("请输入公司编号：");
//            int Cid=getInt(scanner);
//            if (Cid<20000000||Cid>29999999){
//                logger.debug("公司编号无效，应在20000000~29999999之间");
//                continue;}
////            index=isExit(cid);
//            if (!isExitCid(Cid)){
//                System.err.println("要修改的公司编号不存在");
//                continue;
//            }
//            company.setCid(Cid);
//            break;
//        }
//
//        System.out.print("请输入公司名称：");
//        String Cname=getStr(scanner);
//        company.setCname(Cname);
//
//        System.out.print("请输入公司描述：");
//        String Cdescription=getStr(scanner);
//        company.setCdescription(Cdescription);
//
//        System.out.print("请输入公司地址：");
//        String Caddress=getStr(scanner);
//        company.setCaddress(Caddress);
//
//        while (true) {
//            logger.debug("请输入公司联系方式：");
//            long Ctel = getLong(scanner);
//            if (Ctel < 10000000000L || Ctel > 99999999999L) {
//                System.err.print("无效号码，号码长度为11位");
//                continue;
//            }
////            } else if (isExit(Ctel) != -1) {
////                System.out.print("号码重复");
////            }
//            company.setCtel(Ctel);
//            break;
//        }
//        while (true){
//            logger.debug("请输入密码：");
//            String Cpasswd=getStr(scanner);
//            if (!isPass(Cpasswd)){
//                System.err.println("密码长度应在8~12,由字母和数字组合");
//                continue;
//            }else {
//                company.setCpasswd(Cpasswd);
//                break;
//            }
//        }
////        logger.debug("修改成功");
////        logger.debug(cp[index]);
//
//        String sql="update company set Cname='"+company.getCname()+
//                "',"+ "Cdescription='" +company.getCdescription()+
//                "',"+ "Caddress='" +company.getCaddress()+
//                "',"+ "Ctel='" +company.getCtel()+
//                "',"+ "Cpasswd='" +company.getCpasswd()+
//                "' where Cid='"+company.getCid()+"'";
//        logger.debug(sql);
//        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
//            logger.debug("修改数据库成功："+company);
//        }
//    }

    @Override
    public ArrayList<Company> getCom(String cid) {
        String sql="select * from company where cid like '%"+cid+"%'";
        return DDLDML.getCompany(sql);
    }


    private static boolean isExitCid(int Cid) {
        return DDLDML.isExist("select * from company where Cid='"+Cid+"'");
    }

    private static boolean isExitCtel(long Ctel) {
        return DDLDML.isExist("select * from company where Ctel='"+Ctel+"'");
    }


}
