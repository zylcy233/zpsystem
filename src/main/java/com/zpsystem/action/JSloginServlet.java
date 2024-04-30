package com.zpsystem.action;

import com.zpsystem.dao.DDLDML;
import com.zpsystem.service.JsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping( "/jsloginaction")
public class JSloginServlet extends HttpServlet {
    Logger logger=Logger.getLogger(String.valueOf(JSloginServlet.class));
    @Autowired
    JsService jsService;
    @GetMapping("/login")
    void login(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        logger.debug(map);
        PrintWriter out=resp.getWriter();
        if (jsService.login(map)){
            req.getSession().setAttribute("adm",map);
            out.print("ok!");
        }else {
            req.setAttribute("zy","无效的用户或密码");
            req.getSession().setAttribute("adm",null);
            out.print("fail");
        }
    }
    @GetMapping("/zhuce")
    void zhuce(HttpServletResponse resq,@RequestParam Map map) throws IOException {
        logger.debug("map:"+map);
        PrintWriter out=resq.getWriter();
        if (jsService.zhuce(map)){
            out.print("sucess");
        }else {
            out.print("fail");
        }
    }
    @GetMapping("/changepwd")
    void changepwd(HttpServletRequest request,HttpServletResponse resp,@RequestParam Map map) throws IOException {
        PrintWriter out=resp.getWriter();
        HashMap adm= (HashMap) request.getSession().getAttribute("adm");
        map.put("jsid",adm.get("jsid"));
        logger.debug("adm:"+adm);
        logger.debug(map);
        if (!adm.get("jspasswd").equals(map.get("oldjspasswd"))){
            out.print("olderror");
            return;
        }
        if (jsService.changepwd(map)){
            adm.put("jspasswd",map.get("jspasswd"));
            out.print("ok!");
        }else {
            out.print("fail");
        }
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String method=request.getParameter("method");
        if (method==null) method="";
        switch (method){
            case "login":
//                login(request,resp);
                break;
            case "changepwd":
                changepwd(request,resp);
                break;
            case "logout":
                logout(request,resp);
            case "zhuce":
                zhuce(request,resp);
                break;
        }



        }

//    private void login(HttpServletRequest request, HttpServletResponse resp) throws IOException {
//        String op=request.getParameter("op");
//        PrintWriter out=resp.getWriter();
//        String jsid=request.getParameter("jsid");
//        String jspasswd=request.getParameter("jspasswd");
////        if (jsid!=null  && jspasswd!=null){
//        String sql="select * from Jobseekers where jsid='"+jsid+"' and jspasswd='"+jspasswd+"'";
//        logger.debug(sql);
//        ArrayList lst= DDLDML.getDatas(sql);
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
//    }

    private void changepwd(HttpServletRequest request, HttpServletResponse resp) throws IOException {

        String oldjspasswd=request.getParameter("oldjspasswd");
        String jspasswd=request.getParameter("jspasswd");
        PrintWriter out=resp.getWriter();
        HashMap adm= (HashMap) request.getSession().getAttribute("adm");
        logger.debug("adm:"+adm);
        if (!adm.get("jspasswd").equals(oldjspasswd)){
            out.print("olderror");
            return;
        }
        String sql="update Jobseekers set jspasswd='"+jspasswd+"' where jsid="+adm.get("jsid");
        logger.debug(sql);
        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
            adm.put("jspasswd",jspasswd);
            logger.debug("OK");
            out.print("ok!");
        }else {
            logger.debug("fail");
            out.print("fail");
        }
        logger.debug(sql);
    }

    private void logout(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        request.getSession().setAttribute("adm",null);
        resp.sendRedirect("exit");
    }
    private void zhuce(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String jsid=request.getParameter("jsid");
        String jsname=request.getParameter("jsname");
        String jssex=request.getParameter("jssex");
        String jsbirth=request.getParameter("jsbirth");
        String jstel=request.getParameter("jstel");
        String jsaddress = request.getParameter("jsaddress");
        String jspasswd=request.getParameter("jspasswd");
        String jsemail=request.getParameter("jsemail");
        PrintWriter out=resp.getWriter();

        String sql="insert into Jobseekers(jsid,jsname,jssex,jsbirth,jstel,jsaddress,jspasswd,jsemail)"+" values('"
                +jsid+"','"
                +jsname+"','"
                +jssex+"',to_date('"
                +jsbirth+"','YYYY-MM-DD\"T\"HH24:MI:SS'),'"
                +jstel+"','"
                +jsaddress+"','"
                +jspasswd+"','"
                +jsemail+
                "')";
        logger.debug(sql);
        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
//            adm.put("apasswd",apasswd);
            logger.debug("OK");
            out.print("ok!");
        }else {
            logger.debug("fail");
            out.print("fail");
        }

    }

}
//}



//    login.jsp?op=logout;
        //注销之后就算后退界面也无法进行增删改，且跳转登陆界面
