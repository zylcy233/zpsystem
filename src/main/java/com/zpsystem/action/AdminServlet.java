package com.zpsystem.action;

import com.zpsystem.entity.Administrator;
import com.zpsystem.service.AdminService;
import com.zpsystem.util.zyUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adminaction")
public class AdminServlet extends HttpServlet {
    @Autowired
    AdminService adminService;
//    AdmService admService=new AdministratorServiceImpl();
    Logger logger=Logger.getLogger(String.valueOf(AdminServlet.class));

    @PostMapping("/selectadm")
    List selectadm(HttpServletRequest request, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        String find=request.getParameter("find");
        map.put("find",find);
        logger.debug(map);
        return  adminService.selectadm(map);

    }

    @GetMapping("/delete")
    void deleteadm(HttpServletRequest request, HttpServletResponse resp,@RequestParam Map map) throws IOException {
        logger.debug("map:"+map);
//        HashMap m=new HashMap<String,String>();
//        zyUtil.getRequestData(request,m);
        String aid=getInitParameter("aid");
        String oldaid=getInitParameter("oldaid");
        PrintWriter out=resp.getWriter();
        HashMap adm= (HashMap) request.getSession().getAttribute("adm");
        logger.debug("adm:"+adm);
        if (adm.get("aid").equals(oldaid)){
            out.print("olderror");
            return;
        }
        if (adminService.deleteAdmin((HashMap<String, String>) map)){
            resp.getWriter().print("OK");}
        else{
            resp.getWriter().print("fail");}
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException
//{
//    	doGet(request, resp);
//    	}
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException
//    {
//
//
//        String method=request.getParameter("method");
//        logger.debug(method);
//        if (method==null) method="";
//        switch (method){
//            case "insert":
//                insert(request,resp);
//                break;
//            case "delete":
//                delete(request,resp);
//                break;
//            case "update":
//                update(request,resp);
//                break;
//            case "check":
//                check(request,resp);
//                break;
//            default:
//                select(request,resp);
//                break;
//        }
//
//    }

    private void check(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        if (adminService.checkAdmin(m)){
            resp.getWriter().print("exist");}
        else{
            resp.getWriter().print("ok");}
    }

//    private void delete(HttpServletRequest request, HttpServletResponse resp) throws IOException {
//        HashMap m=new HashMap<String,String>();
//        zyUtil.getRequestData(request,m);
//        String aid=getInitParameter("aid");
//        String oldaid=getInitParameter("oldaid");
//        PrintWriter out=resp.getWriter();
//        HashMap adm= (HashMap) request.getSession().getAttribute("adm");
//        logger.debug("adm:"+adm);
//        if (adm.get("aid").equals(oldaid)){
//            out.print("olderror");
//            return;
//        }
//        if (adminService.deleteAdmin(m)){
//            resp.getWriter().print("OK");}
//        else{
//            resp.getWriter().print("fail");}
//    }

    private void update(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        logger.debug("m:"+m);
        if (adminService.updateAdmin(m)){
            resp.getWriter().print("OK");}
        else{
            resp.getWriter().print("fail");}
    }

    private void select(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String find=request.getParameter("find");
        resp.setContentType("application/json;charset=UTF-8");
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        logger.debug("m:"+m);
        resp.getWriter().print(adminService.getAdmin(m));
    }

    private void insert(HttpServletRequest request,HttpServletResponse response) throws IOException
//            , JasperException
    {
        Administrator adm=new Administrator();
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
//        org.apache.jasper.runtime.JspRuntimeLibrary.introspect(adm,request);
//        logger.debug("adm:"+adm);
        logger.debug("m:"+m);
        if (adminService.insertAdmin(m)){
                response.getWriter().print("OK");}
            else{
                response.getWriter().print("fail");}

    }
}
//}



//    login.jsp?op=logout;
//注销之后就算后退界面也无法进行增删改，且跳转登陆界面
