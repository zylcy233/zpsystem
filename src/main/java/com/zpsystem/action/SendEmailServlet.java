package com.zpsystem.action;

import com.zpsystem.dao.DDLDML;
import com.zpsystem.util.SendEmail;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(urlPatterns = "/sendction")
public class SendEmailServlet extends HttpServlet {
    Logger logger=Logger.getLogger(String.valueOf(SendEmailServlet.class));

    @Override
    public   void init() throws  ServletException{
        logger.debug("这个函数只执行一次（不管发送多少次请求）");
        try {
            String path= getServletContext().getRealPath("/");
            path = path + "WEB-INF\\classes\\log4j.properties";
            logger.debug(path);
            PropertyConfigurator.configure(path);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {



        String method=request.getParameter("method");
        if (method==null) method="";
        switch (method){
            case "rsend":
                rsend(request,resp);
                break;
            case "jssend":
                jssend(request,resp);
                break;
        }

        }

    private void jssend(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        PrintWriter out=resp.getWriter();
        String id=request.getParameter("id");
        logger.debug(id);
        String sqlb="select * from Jobseekers where jsid="+id;
        ArrayList lst= DDLDML.getDatas(sqlb);
        HashMap hashmap = (HashMap) lst.get(0);
        if (lst.size()!=0){
            request.getSession().setAttribute("adm",lst.get(0));
            logger.debug("lsta"+lst);
            logger.debug("OK");
            out.print("OK");
            logger.debug("lst"+lst);
            logger.debug("get"+lst.get(0));
            String text= (String) hashmap.get("jspasswd");
            logger.debug(text);
            String to= (String) hashmap.get("jsemail");
            logger.debug("to:"+to);
            if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sqlb)){
                logger.debug("发送成功");
                String title="请查收您的招聘系统密码";
                SendEmail.sendMail(to,text,title);
            }else {
            	out.print("fail");
                logger.debug("发送失败");
            }
        }else {
            request.getSession().setAttribute("adm",null);
            logger.debug("fail");
            out.print("fail");
        }
    }


    private void rsend(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        PrintWriter out=resp.getWriter();
        String id=request.getParameter("id");
        logger.debug(id);
        String sql="select * from recruiter where rid="+id;
        logger.debug(sql);
        ArrayList lst=DDLDML.getDatas(sql);
        HashMap hashmap = (HashMap) lst.get(0);
        if (lst.size()!=0){
            request.getSession().setAttribute("adm",lst.get(0));
            logger.debug("lsta"+lst);
            logger.debug("OK");
            out.print("OK");
            logger.debug("lst"+lst);
            logger.debug("get"+lst.get(0));
            String text= (String) hashmap.get("rpasswd");
            String to=(String) hashmap.get("remail");
            if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
                logger.debug("发送成功");
                String title="请查收您的招聘系统密码";
                logger.debug("to:"+to);
                logger.debug("title:"+title);
                logger.debug("text:"+text);
                SendEmail.sendMail(to,text,title);
            }else {
                logger.debug("发送失败");
            }
        }else {
            request.getSession().setAttribute("adm",null);
            logger.debug("fail");
            out.print("fail");
        }
    }
}
//}



//    login.jsp?op=logout;
        //注销之后就算后退界面也无法进行增删改，且跳转登陆界面
