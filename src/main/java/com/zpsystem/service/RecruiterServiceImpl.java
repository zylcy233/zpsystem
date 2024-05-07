package com.zpsystem.service;

import com.zpsystem.dao.DDLDML;
import com.zpsystem.entity.Recruiter;
import com.zpsystem.mapper.RMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.zpsystem.util.zyUtil.*;

@Service
public class RecruiterServiceImpl implements RecrutierService{
    org.apache.log4j.Logger logger= Logger.getLogger(String.valueOf(RecruiterServiceImpl.class));

    @Autowired
    RMapper rMapper;

    @Override
    public boolean login(Map map) {
        if (map.get("rpasswd")==null) map.put("rpasswd","");
        Map result=rMapper.getOne(map);
        logger.debug(result);
        return result!=null;
    }

    @Override
    public boolean zhuce(Map map) {
        logger.debug(map);
        boolean b=rMapper.zhuce(map);
        return b;
    }

    @Override
    public boolean changepwd(Map map) {
        logger.debug(map);
        boolean b=rMapper.changepwd(map);
        return b;
    }

    @Override
    public boolean insertRec(Recruiter recruiter) {
        String sql="insert into Recruiter(rid,rname,rsex,cid,rtel,rpasswd)"
                +"values ('"
                +recruiter.getRid()+"','"
                +recruiter.getRname()+"','"
                +recruiter.getRsex()+"','"
                +recruiter.getCid()+"','"
                +recruiter.getRtel()+"','"
                +recruiter.getRpasswd()+"')";
        logger.debug(sql);
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
    }

    @Override
    public boolean deleterRec(Recruiter recruiter) {
        String sql="delete from Recruiter where rid='"+recruiter.getRid()+"'";
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
    }

    @Override
    public boolean updateRec(Recruiter recruiter) {
        String sql="update Recruiter set rname='"+recruiter.getRname()+
                "',"+"rsex='"+recruiter.getRsex()+
                "',"+"cid='"+recruiter.getCid()+
                "',"+"rtel='"+recruiter.getRtel()+
                "',"+"rpasswd='"+recruiter.getRpasswd()+"' where rid='"+recruiter.getRid()+"'";
        return DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql);
    }

    @Override
    public void insert() {
        Recruiter recruiter=new Recruiter();
        Scanner scanner=new Scanner(System.in);
        while (true){
            logger.debug("请输入招聘者账号：");
            int Rid= getInt(scanner);
            if (Rid<40000000||Rid>49999999){
                logger.debug("无效编号，招聘者账号应在40000000~49999999之间");
                continue;
            }else if(isExistRid(Rid)){
                System.err.println("输入的招聘者账号已存在");
                continue;
            }else {
                recruiter.setRid(Rid);
                break;
            }
        }

        logger.debug("请输入招聘者姓名：");
        String Rname=getStr(scanner);
        recruiter.setRname(Rname);

        while (true){
            logger.debug("请输入招聘者性别（男，女）：");
            String Rsex=getStr(scanner);
            if (Rsex.equals("男")||Rsex.equals("女")){
                recruiter.setRsex(Rsex);
                break;
            }
            System.err.println("性别输入错误");
        }

        while (true){
            logger.debug("请输入招聘者公司账号：");
            int Cid=getInt(scanner);
            if (Cid<10000||Cid>19999){
                logger.debug("公司编号输入错误，范围应在10000~19999");
                continue;
            }else if(!isExistCid(Cid)){
                logger.debug("无效公司编号");
                continue;
            }else {
                recruiter.setCid(Cid);
                break;
            }
        }

        while (true){
            logger.debug("请输入招聘者联系方式：");
            long Rtel= getLong(scanner);
            if (Rtel<10000000000L||Rtel>99999999999L){
                logger.debug("无效号码，号码长度11位");
                continue;
            }else if(isExistRtel(Rtel)){
                logger.debug("号码已存在");
                continue;
            }
            else {
                recruiter.setRtel(Rtel);
                break;
            }
        }

        while (true){
            logger.debug("请输入招聘者密码：");
            String Rpasswd=getStr(scanner);
            if (!isPass(Rpasswd)){
                logger.debug("无效密码，密码由8~12数字和字母组成");
                continue;
            }else {
                recruiter.setRpasswd(Rpasswd);
                break;
            }
        }

        String sql="insert into Recruiter(Rid,Rname,Rsex,Cid,Rtel,Rpasswd)"
                +"values ('"
                +recruiter.getRid()+"','"
                +recruiter.getRname()+"','"
                +recruiter.getRsex()+"','"
                +recruiter.getCid()+"','"
                +recruiter.getRtel()+"','"
                +recruiter.getRpasswd()+"')";
        logger.debug(sql);
        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
            logger.debug("在数据库中添加成功");
        }


    }

    @Override
    public void delete() {
        while (true){
            logger.debug("请输入要删除的招聘者账号：");
            Scanner scanner=new Scanner(System.in);
            int rid=getInt(scanner);
            if (rid<40000000||rid>49999999){
                logger.debug("无效编号，招聘者账号应在40000000~49999999之间");
                continue;
            }
            if (!isExistRid(rid)){
                System.err.println("输入的招聘者账号不存在");
                continue;
            }else {
                String sql="delete from Recruiter where Rid='"+rid+"'";
                if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
                    logger.debug("在数据库中成功删除");
                }
                break;
            }

        }


    }

    @Override
    public void update() {
        Scanner scanner=new Scanner(System.in);
        Recruiter recruiter=new Recruiter();
        while (true){
            logger.debug("请输入要更改的招聘者账号：");
            int Rid= getInt(scanner);
            if (Rid<40000000||Rid>49999999){
                logger.debug("无效编号，招聘者账号应在40000000~49999999之间");
                continue;
            }else if(!isExistRid(Rid)){
                System.err.println("输入的招聘者账号已存在");
                continue;
            }else {
                recruiter.setRid(Rid);
                break;
            }
        }
        logger.debug("请输入招聘者姓名：");
        String Rname=getStr(scanner);
        recruiter.setRname(Rname);

        while (true){
            logger.debug("请输入招聘者性别（男，女）：");
            String Rsex=getStr(scanner);
            if (Rsex.equals("男")||Rsex.equals("女")){
                recruiter.setRsex(Rsex);
                break;
            }
            System.err.println("性别输入错误");
        }

        while (true){
            logger.debug("请输入招聘者公司账号：");
            int Cid=getInt(scanner);
            if (Cid<10000||Cid>19999){
                logger.debug("公司账号输入错误，范围应在10000~19999");
                continue;
            }else if(!isExistCid(Cid)){
                logger.debug("无效公司账号");
                continue;
            }else {
                recruiter.setCid(Cid);
                break;
            }
        }

        while (true){
            logger.debug("请输入招聘者联系方式：");
            long Rtel= getLong(scanner);
            if (Rtel<10000000000L||Rtel>99999999999L){
                logger.debug("无效号码，号码长度11位");
                continue;
            }
                recruiter.setRtel(Rtel);
                break;

        }

        while (true){
            logger.debug("请输入招聘者密码：");
            String Rpasswd=getStr(scanner);
            if (!isPass(Rpasswd)){
                logger.debug("无效密码，密码由8~12数字和字母组成");
                continue;
            }else {
                recruiter.setRpasswd(Rpasswd);
                break;
            }
        }

        String sql="update Recruiter set Rname='"+recruiter.getRname()+
                "',"+"Rsex='"+recruiter.getRsex()+
                "',"+"Cid='"+recruiter.getCid()+
                "',"+"Rtel='"+recruiter.getRtel()+
                "',"+"Rpasswd='"+recruiter.getRpasswd()+"' where Rid='"+recruiter.getRid()+"'";
        logger.debug(sql);
        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
            logger.debug("在数据库中更改成功");
        }

    }

    @Override
    public void select() {

    }

    @Override
    public ArrayList<Recruiter> getRec(String rname) {
        String  sql=null;
        if (rname==null)
            sql="select * from Recruiter";
        else  sql="select * from Recruiter where rname like '%"+rname+"%'";
        return DDLDML.getRec(sql);
    }

    @Override
    public List getRec(Map map) {
        logger.debug(map);
        List l=rMapper.select(map);
        return l;
    }

    @Override
    public List getRecOne(Map map) {
        logger.debug(map);
        List l=rMapper.getone(map);
        return l;
    }

//    @Override
//    public void select() {
//        for (Recruiter r:DDLDML.getRec()){
//            if (r==null)
//                break;
//            logger.debug(r);
//        }
//    }

    public boolean isExistRid(int Rid){
        return DDLDML.isExist("select * from Recruiter where Rid='"
                +Rid+ "'");
    }

    public boolean isExistCid(int Cid){
        return DDLDML.isExist("select * from company where Cid='"
                +Cid+ "'");
    }

    public boolean isExistRtel(long Rtel){
        return DDLDML.isExist("select * from Recruiter where Rtel='"
                +Rtel+ "'");
    }

}
