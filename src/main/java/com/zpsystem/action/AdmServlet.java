package com.zpsystem.action;
import com.zpsystem.dao.DDLDML;
import com.zpsystem.service.AdmService;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admaction")
public class AdmServlet extends HttpServlet {
    Logger logger=Logger.getLogger(String.valueOf(AdmServlet.class));
    @Autowired
    AdmService admService;
    @GetMapping("/login")
    void login(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        logger.debug(map);
        PrintWriter out=resp.getWriter();
        if (admService.login(map)){
           req.getSession().setAttribute("adm",map);
           out.print("ok!");
       }else {
            req.setAttribute("zy","无效的用户或密码");
           req.getSession().setAttribute("adm",null);
            out.print("fail");
        }
        }

        @GetMapping("/changepwd")
    void changepwd(HttpServletRequest request,HttpServletResponse resp,@RequestParam Map map) throws IOException {
            PrintWriter out=resp.getWriter();
            HashMap adm= (HashMap) request.getSession().getAttribute("adm");
            map.put("aid",adm.get("aid"));
            logger.debug("adm:"+adm);
            logger.debug(map);
            if (!adm.get("apasswd").equals(map.get("oldapasswd"))){
                out.print("olderror");
                return;
            }
            if (admService.changepwd(map)){
                adm.put("apasswd",map.get("apasswd"));
                out.print("ok!");
            }else {
                out.print("fail");
            }
        }

        @PostMapping("/logout")
    void logout(HttpServletRequest request, HttpServletResponse resp) throws IOException {
            request.getSession().setAttribute("adm",null);
            //TODO 跳转有问题
            resp.sendRedirect("exit");
        }
    }




//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
//
//
//        String method=request.getParameter("method");
//        if (method==null) method="";
//        switch (method){
//            case "login":
//                login(request,resp);
//                break;
//            case "changepwd":
//                changepwd(request,resp);
//                break;
//            case "logout":
//                logout(request,resp);
//            case "zhuce":
//                zhuce(request,resp);
//                break;
//        }
//
//
//        logger.debug("" instanceof String);
//        logger.debug("对象的地址"+this);
//        logger.debug("处理客户端请求的当前线程标识："+Thread.currentThread().getId());
//        logger.debug("当前会话的id:"+request.getSession().getId());
//        logger.debug("1");
//
//
//        }
//
//    private void zhuce(HttpServletRequest request, HttpServletResponse resp) throws IOException {
//        String aid=request.getParameter("aid");
//        String aname=request.getParameter("aname");
//        String apasswd=request.getParameter("apasswd");
//        PrintWriter out=resp.getWriter();
//
//        String sql="insert into adm(aid,aname,apasswd) values("+aid+",'"+aname+"','"+apasswd+"')";
//        logger.debug(sql);
//        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
////            adm.put("apasswd",apasswd);
//            logger.debug("OK");
//            out.print("ok!");
//        }else {
//            logger.debug("fail");
//            out.print("fail");
//        }
//    }
//
//    private void login(HttpServletRequest request, HttpServletResponse resp) throws IOException {
//        String op=request.getParameter("op");
//        PrintWriter out=resp.getWriter();
//        String aid=request.getParameter("aid");
//        String apasswd=request.getParameter("apasswd");
////        PrintWriter out=
////        if (aid!=null  && apasswd!=null){
//        String sql="select * from adm where aid='"+aid+"' and apasswd='"+apasswd+"'";
//        logger.debug(sql);
//            ArrayList lst=DDLDML.getDatas(sql);
////        if (DDLDML.isExist(sql)){
//        if (lst.size()!=0){
//            request.getSession().setAttribute("adm",lst.get(0));
//            logger.debug("lsta"+lst);
//            logger.debug("OK");
//            out.print("ok!");
//            logger.debug("lst"+lst);
//            logger.debug("get"+lst.get(0));
////                resp.sendRedirect("menu.jsp");
//        }else {
////                request.setAttribute("zy","无效的用户或密码");
//            request.getSession().setAttribute("adm",null);
//            logger.debug("fail");
//            out.print("fail");
//        }
////        if (!DDLDML.isExist(sql)){
////            request.getSession().setAttribute("aid",null);
////            out.print("fail");
//////                resp.sendRedirect("menu.jsp");
////        }else {
//////                request.setAttribute("zy","无效的用户或密码");
////            request.getSession().setAttribute("aid",aid);
////            out.print("ok!");
////
////        }
//    }
//
//    private void changepwd(HttpServletRequest request, HttpServletResponse resp) throws IOException {
//        String oldapasswd=request.getParameter("oldapasswd");
//        String apasswd=request.getParameter("apasswd");
//        PrintWriter out=resp.getWriter();
//        HashMap adm= (HashMap) request.getSession().getAttribute("adm");
//
//        logger.debug("adm:"+adm);
//        if (!adm.get("apasswd").equals(oldapasswd)){
//            out.print("olderror");
//            return;
//        }
//        String sql="update adm set apasswd='"+apasswd+"' where aid="+adm.get("aid");
//        logger.debug(sql);
//        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
//            adm.put("apasswd",apasswd);
//            logger.debug("OK");
//            out.print("ok!");
//        }else {
//            logger.debug("fail");
//            out.print("fail");
//        }
//        logger.debug(sql);
//    }
//
//    private void logout(HttpServletRequest request, HttpServletResponse resp) throws IOException {
//        request.getSession().setAttribute("adm",null);
//        resp.sendRedirect("exit");
//
//        String op=request.getParameter("op");
//        PrintWriter out=resp.getWriter();
//        if ("logout".equals(op)){
//            request.setAttribute("aid",null);
//        }
//
//}
//}
