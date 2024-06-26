package com.zpsystem.action;


import com.zpsystem.service.JsService;
import com.zpsystem.service.RecrutierService;
import com.zpsystem.util.SendEmail;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/sendction")
@WebServlet(urlPatterns = "/sendction")
public class SendEmailServlet extends HttpServlet {
    Logger logger=Logger.getLogger(String.valueOf(SendEmailServlet.class));
    @Autowired
    JsService jsService;
    @Autowired
    RecrutierService recrutierService;

    @GetMapping("/jssend")
    void jssend(HttpServletRequest request, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        PrintWriter out=resp.getWriter();
        String id=request.getParameter("id");
        logger.debug(id);
//        ArrayList lst= (ArrayList) jsService.sendemail(map);
        Map m=  jsService.sendemail(map);
        logger.debug(m);
        if (m !=null){
            request.getSession().setAttribute("adm",m);
            logger.debug("OK");
            out.print("OK");
            String text= (String) m.get("JSPASSWD");
            logger.debug(text);
            String to= (String) m.get("JSEMAIL");
            logger.debug("to:"+to);
            if (m !=null){
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

    @GetMapping("/rsend")
    void rsend(HttpServletRequest request, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        PrintWriter out=resp.getWriter();
        String id=request.getParameter("id");
        logger.debug(id);
        Map m=  recrutierService.sendemail(map);
        logger.debug(m);
        if (m !=null){
            request.getSession().setAttribute("adm",m);
            logger.debug("OK");
            out.print("OK");
            String text= (String) m.get("RPASSWD");
            logger.debug(text);
            String to= (String) m.get("REMAIL");
            logger.debug("to:"+to);
            if (m !=null){
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


//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
//
//        String method=request.getParameter("method");
//        if (method==null) method="";
//        switch (method){
//            case "rsend":
//                rsend(request,resp);
//                break;
//            case "jssend":
//                jssend(request,resp);
//                break;
//        }
//
//        }
//
//    private void jssend(HttpServletRequest request, HttpServletResponse resp) throws IOException {
//        PrintWriter out=resp.getWriter();
//        String id=request.getParameter("id");
//        logger.debug(id);
//        String sqlb="select * from Jobseekers where jsid="+id;
//        ArrayList lst= DDLDML.getDatas(sqlb);
//        HashMap hashmap = (HashMap) lst.get(0);
//        if (lst.size()!=0){
//            request.getSession().setAttribute("adm",lst.get(0));
//            logger.debug("lsta"+lst);
//            logger.debug("OK");
//            out.print("OK");
//            logger.debug("lst"+lst);
//            logger.debug("get"+lst.get(0));
//            String text= (String) hashmap.get("jspasswd");
//            logger.debug(text);
//            String to= (String) hashmap.get("jsemail");
//            logger.debug("to:"+to);
//            if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sqlb)){
//                logger.debug("发送成功");
//                String title="请查收您的招聘系统密码";
//                SendEmail.sendMail(to,text,title);
//            }else {
//            	out.print("fail");
//                logger.debug("发送失败");
//            }
//        }else {
//            request.getSession().setAttribute("adm",null);
//            logger.debug("fail");
//            out.print("fail");
//        }
//    }
//
//
//    private void rsend(HttpServletRequest request, HttpServletResponse resp) throws IOException {
//        PrintWriter out=resp.getWriter();
//        String id=request.getParameter("id");
//        logger.debug(id);
//        String sql="select * from recruiter where rid="+id;
//        logger.debug(sql);
//        ArrayList lst=DDLDML.getDatas(sql);
//        HashMap hashmap = (HashMap) lst.get(0);
//        if (lst.size()!=0){
//            request.getSession().setAttribute("adm",lst.get(0));
//            logger.debug("lsta"+lst);
//            logger.debug("OK");
//            out.print("OK");
//            logger.debug("lst"+lst);
//            logger.debug("get"+lst.get(0));
//            String text= (String) hashmap.get("rpasswd");
//            String to=(String) hashmap.get("remail");
//            if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
//                logger.debug("发送成功");
//                String title="请查收您的招聘系统密码";
//                logger.debug("to:"+to);
//                logger.debug("title:"+title);
//                logger.debug("text:"+text);
//                SendEmail.sendMail(to,text,title);
//            }else {
//                logger.debug("发送失败");
//            }
//        }else {
//            request.getSession().setAttribute("adm",null);
//            logger.debug("fail");
//            out.print("fail");
//        }
//    }
}
//}



//    login.jsp?op=logout;
        //注销之后就算后退界面也无法进行增删改，且跳转登陆界面
