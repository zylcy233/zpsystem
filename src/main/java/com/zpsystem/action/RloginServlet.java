package com.zpsystem.action;

import com.zpsystem.dao.DDLDML;
import com.zpsystem.service.RecrutierService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/rloginaction")
@ResponseBody
public class RloginServlet extends HttpServlet {
    Logger logger=Logger.getLogger(String.valueOf(RloginServlet.class));

    @Autowired
    RecrutierService recrutierService;

    @GetMapping("/login")
    void login(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        logger.debug(map);
        PrintWriter out=resp.getWriter();
        if (recrutierService.login(map)){
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
        if (recrutierService.zhuce(map)){
            out.print("sucess");
        }else {
            out.print("fail");
        }
    }

    @GetMapping("/changepwd")
    void changepwd(HttpServletRequest request,HttpServletResponse resp,@RequestParam Map map) throws IOException {
        PrintWriter out=resp.getWriter();
        Map adm= (Map) request.getSession().getAttribute("adm");
        map.put("rid",adm.get("rid"));
        logger.debug("adm:"+adm);
        logger.debug(map);
        if (!adm.get("rpasswd").equals(map.get("oldrpasswd"))){
            out.print("olderror");
            return;
        }
        if (recrutierService.changepwd(map)){
            adm.put("rpasswd",map.get("rpasswd"));
            out.print("ok!");
        }else {
            out.print("fail");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String method=request.getParameter("method");
        if (method==null) method="";
        logger.debug(method);
        switch (method){
            case "login":
                login(request,resp);
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

    private void login(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String op=request.getParameter("op");
        PrintWriter out=resp.getWriter();
        String rid=request.getParameter("rid");
        String rpasswd=request.getParameter("rpasswd");
        String sql="select * from Recruiter where rid='"+rid+"' and rpasswd='"+rpasswd+"'";
        logger.debug(sql);
        ArrayList lst= DDLDML.getDatas(sql);
        if (lst.size()!=0){
            request.getSession().setAttribute("adm",lst.get(0));
            logger.debug("lsta"+lst);
            logger.debug("OK");
            out.print("ok!");
            logger.debug("lst"+lst);
            logger.debug("get"+lst.get(0));
//                resp.sendRedirect("menu.jsp");
        }else {
//                request.setAttribute("zy","无效的用户或密码");
            request.getSession().setAttribute("adm",null);
            logger.debug("fail");
            out.print("fail");
        }
    }

    private void changepwd(HttpServletRequest request, HttpServletResponse resp) throws IOException {

        String oldrpasswd=request.getParameter("oldrpasswd");
        String rpasswd=request.getParameter("rpasswd");
        PrintWriter out=resp.getWriter();
        HashMap adm= (HashMap) request.getSession().getAttribute("adm");
        logger.debug("adm:"+adm);
        if (!adm.get("rpasswd").equals(oldrpasswd)){
            out.print("olderror");
            return;
        }
        String sql="update Recruiter set rpasswd='"+rpasswd+"' where rid="+adm.get("rid");
        logger.debug(sql);
        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
            adm.put("rpasswd",rpasswd);
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
        String rid=request.getParameter("rid");
        String rname=request.getParameter("rname");
        String rsex=request.getParameter("rsex");
        String cid=request.getParameter("cid");
        String rtel=request.getParameter("rtel");
        String rpasswd=request.getParameter("rpasswd");
        String remail=request.getParameter("remail");
        PrintWriter out=resp.getWriter();

        String sql="insert into Recruiter(rid,rname,rsex,cid,rtel,rpasswd,remail)"+" values('"
                +rid+"','"
                +rname+"','"
                +rsex+"','"
                +cid+"','"
                +rtel+"','"
                +rpasswd+"','"
                +remail+
                "')";
        logger.debug(sql);
        if (DDLDML.我能执行DDL和DML中的insertdeleteupdate(sql)){
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
