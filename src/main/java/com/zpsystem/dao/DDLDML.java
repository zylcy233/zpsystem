package com.zpsystem.dao;

import com.zpsystem.action.AdmServlet;
import com.zpsystem.entity.*;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;


public class DDLDML {
    Logger logger=Logger.getLogger(String.valueOf(AdmServlet.class));
    public static void main(String[] args) {

    }
    //定义四个类变量，变量名
public static String driver;

public static String url;

public static String user;

public static String passwd;

//static {
//    Properties properties=new Properties();
//    String path=DDLDML.class.getProtectionDomain().getCodeSource().getLocation().getPath()
//            +"/zpSystem/zyzpxt/dao/db.properties";
//    logger.debug(path);
//    try(FileReader fileReader=new FileReader(path);) {
////        fileReader=new FileReader("db.properties");
//        properties.load(fileReader);
//        logger.debug(properties.getProperty("driver"));
//        logger.debug(properties.getProperty("url"));
//        logger.debug(properties.getProperty("user"));
//        logger.debug(properties.getProperty("passwd"));
//    }catch (Exception e){
//        System.err.println(e.getMessage());
//    }
//}

    public static boolean isExist(String sql){
//        logger.debug(sql);
        try (Connection con=和数据建立连接()){
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
//            logger.debug(e.getMessage());
        }
        return false;
    }

    public static boolean isExist(String sql,Object params[]){
//        logger.debug(sql);
        try (Connection con=和数据建立连接()){
            PreparedStatement pst=con.prepareStatement(sql);
            for (int i=0;i<params.length;i++){
                pst.setObject(i+1,params[i]);
            }
            ResultSet rs= pst.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
//            logger.debug(e.getMessage());
        }
        return false;
    }

    public static ArrayList<Jobseekers> getJobseekers(String sql){
        ArrayList<Jobseekers> js=new ArrayList<>(20);
            try (Connection con=和数据建立连接()){
                Statement st=con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                int i=0;
                while (rs.next()){
                    Jobseekers j=new Jobseekers();
                    j.setJsid(rs.getInt("jsid"));
                    j.setJsname(rs.getString("jsname"));
                    j.setJssex(rs.getString("jssex"));
                    j.setJsbirth(rs.getString("jsbirth"));
                    j.setJstel(rs.getLong("jstel"));
                    j.setJsaddress(rs.getString("jsaddress"));
                    j.setJspasswd(rs.getString("jspasswd"));
                    j.setJsemail(rs.getString("jsemail"));
                    js.add(j);
                }
            } catch (Exception e) {
//                logger.debug(e.getMessage());
            }
            return js;
    }
    
    public static ArrayList<Jobposting> getJobposting(String sql){
        ArrayList<Jobposting> jP=new ArrayList<>(20);
            try (Connection con=和数据建立连接()){
                Statement st=con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                int i=0;
                while (rs.next()){
                    Jobposting jp=new Jobposting();
                    jp.setJpid(rs.getInt("jpid"));
                    jp.setCid(rs.getInt("cid"));
                    jp.setJppost(rs.getString("jppost"));
                    jp.setJpsalary(rs.getString("jpsalary"));
                    jp.setJpaddress(rs.getString("jpaddress"));
                    jp.setJprequest(rs.getString("jprequest"));
                    jp.setRid(rs.getInt("rid"));
                    jP.add(jp);
                }
            } catch (Exception e) {
//                logger.debug(e.getMessage());
            }
            return jP;
    }
    
    public static ArrayList<Jobresume> getJobresume(String sql){
        ArrayList<Jobresume> JR=new ArrayList<>(20);
            try (Connection con=和数据建立连接()){
                Statement st=con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                int i=0;
                while (rs.next()){
                	Jobresume jr=new Jobresume();
                	jr.setJrid(rs.getInt("jrid"));
                	jr.setJsid(rs.getInt("jsid"));
                	jr.setJsname(rs.getString("jsname"));
                	jr.setJrlvl(rs.getString("jrlvl"));
                	jr.setJradvantage(rs.getString("jradvantage"));
                	jr.setJrintention(rs.getString("jrintention"));
                	jr.setJrwork(rs.getString("jrwork"));
                	jr.setJrobject(rs.getString("jrobject"));
                	jr.setJrschool(rs.getString("jrschool"));
                	jr.setJrskill(rs.getString("jrskill"));
                	jr.setJrcert(rs.getString("jrcert"));
                	JR.add(jr);
                }
            } catch (Exception e) {
//                logger.debug(e.getMessage());
            }
            return JR;
    }

    public static ArrayList<Company> getCompany(String sql){
        System.out.print(sql);
        ArrayList<Company> com=new ArrayList<Company>(20);
        try (Connection con=和数据建立连接()){
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int i=0;
            while (rs.next()){
                Company c=new Company();
                c.setCid(rs.getInt("Cid"));
                c.setCname(rs.getString("Cname"));
                c.setCdescription(rs.getString("Cdescription"));
                c.setCaddress(rs.getString("Caddress"));
                c.setCtel(rs.getLong("Ctel"));
                c.setCpasswd(rs.getString("Cpasswd"));
                com.add(c);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        //logger.debug(com);
        return com;
    }


    //函数重载
    public static ArrayList<Administrator> getAdmin(String sql){
        ArrayList<Administrator> admin=new ArrayList<Administrator>(20);
        //logger.debug(sql);
        try (Connection con=和数据建立连接()){
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Administrator a=new Administrator();
                a.setAid(rs.getInt("Aid"));
                a.setAname(rs.getString("Aname"));
                a.setApasswd(rs.getString("Apasswd"));
                admin.add(a);

            }
        } catch (Exception e) {
            //logger.debug(e.getMessage());
        }
        //logger.debug(admin);
        return admin;
    }

    public static ArrayList<Zytbl> getZytbls(String sql){
//        logger.debug(sql);
        ArrayList<Zytbl> zytbls=new ArrayList<>(20);
//        logger.debug(sql);
        try (Connection con=和数据建立连接()){
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Zytbl z=new Zytbl();
                z.setZya(rs.getInt("zya"));
                z.setZyb(rs.getString("zyb"));
                z.setZyc(rs.getFloat("zyc"));
                z.setZyd(rs.getString("zyd"));
                zytbls.add(z);

            }
        } catch (Exception e) {
//            logger.debug(e.getMessage());
        }
//        logger.debug(zytbls);
        return zytbls;
    }

    public static ArrayList<Emptbl> getemp(String sql){
//        logger.debug(sql);
        ArrayList<Emptbl> lstEmptbls=new ArrayList<>();
        try(Connection con=和数据建立连接()) {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()){//数据库游标概念？指向某一个查询结果
                Emptbl p=new Emptbl();
                p.setEmpno(rs.getInt("empno"));
                p.setEname(rs.getString("ename"));
                p.setJob(rs.getString("job"));
                p.setMgr(rs.getInt("mgr"));
                p.setHiredate(rs.getString("hiredate"));
                p.setSal(rs.getFloat("sal"));
                p.setComm(rs.getFloat("comm"));
                p.setDeptno(rs.getInt("deptno"));
                lstEmptbls.add(p);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
//        logger.debug(lstEmptbls);
        return lstEmptbls;
    }



    public static ArrayList<Recruiter> getRec(String sql){
        ArrayList<Recruiter> rec=new ArrayList<>(20);
        try (Connection con=和数据建立连接()){
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int i=0;
            while (rs.next()){
                Recruiter r=new Recruiter();
                r.setRid(rs.getInt("Rid"));
                r.setRname(rs.getString("Rname"));
                r.setRsex(rs.getString("Rsex"));
                r.setCid(rs.getInt("Cid"));
                r.setRtel(rs.getLong("Rtel"));
                r.setRpasswd(rs.getString("Rpasswd"));
                rec.add(r);
            }
        }catch (Exception e){
//            logger.debug(e.getMessage());
        }
        return rec;
    }

    public static ArrayList getDatas(String sql){
//        logger.debug(sql);
        ArrayList lst=new ArrayList();
        try(Connection con=和数据建立连接()) {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            ResultSetMetaData md=rs.getMetaData();
            while (rs.next()){
                HashMap m=new HashMap();
                for (int i = 1; i <=md.getColumnCount() ; i++) {
                    String fldName=md.getColumnName(i);
                    String fldValue=rs.getString(i);
                    m.put(fldName.toLowerCase(),fldValue);
                }
                lst.add(m);
            }

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return lst;
    }

    public static ArrayList getDatas(String sql,Object params[]){
//        logger.debug(sql);
        ArrayList lst=new ArrayList();
        try(Connection con=和数据建立连接()) {
            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery(sql);
            ResultSetMetaData md=rs.getMetaData();
            while (rs.next()){
                HashMap m=new HashMap();
                for (int i = 1; i <md.getColumnCount() ; i++) {
                    String fldName=md.getColumnName(i);
                    String fldValue=rs.getString(i);
                    m.put(fldName.toLowerCase(),fldValue);
                }
                lst.add(m);
            }

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return lst;
    }


    public static boolean 我能执行DDL和DML中的insertdeleteupdate
            (String sql){
        try (Connection con=和数据建立连接()){
            Statement st=con.createStatement();
            return st.executeUpdate(sql)>0;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public static boolean 我能执行DDL和DML中的insertdeleteupdate
            (String sql,Object params[]){
        try (Connection con=和数据建立连接()){
            PreparedStatement pst=con.prepareStatement(sql);
            for (int i=0;i<params.length;i++)
                pst.setObject(i+1,params[i]);
            return pst.executeUpdate()>0;    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(sql);
        return false;
    }


    private static Connection 和数据建立连接(){
        Connection connection=null;
        try {
            InitialContext context=new InitialContext();
            DataSource ds=(DataSource) context.lookup("java:comp/env/jdbc/mysqlDataSource");
            connection =ds.getConnection();
//            Class.forName("oracle.jdbc.driver.OracleDriver");//反射
//            connection= DriverManager.getConnection(
//                    "jdbc:oracle:thin:@192.168.13.192:1521:ORACLE","scott","123456"
//                    );
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return connection;
    }

    public static int getCount(String sql) {
//        logger.debug(sql);
        try (Connection con=和数据建立连接()){
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while (rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }
}
