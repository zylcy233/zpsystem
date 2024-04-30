package com.zpsystem.action;

import com.zpsystem.entity.Emptbl;
import com.zpsystem.service.EmptblService;
import com.zpsystem.service.EmptblServiceImpl;
import com.zpsystem.util.zyUtil;
import org.apache.jasper.JasperException;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@WebServlet(urlPatterns = "/emptblaction")
public class EmptblServlet extends HttpServlet {
    EmptblService emptblService=new EmptblServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException
//           , JasperException
    {

        String method=request.getParameter("method");
        if (method==null) method="";
        switch (method){
            case "insert":
                insert(request,resp);
                break;
            case "delete":
                delete(request,resp);
                break;
            case "update":
                update(request,resp);
                break;
            case "check":
                check(request,resp);
                break;
            default:
                select(request,resp);
                break;
        }

    }

    private void check(HttpServletRequest request, HttpServletResponse resp) throws
//            JasperException,
            IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        if (emptblService.checkEmptbl(m)){
            resp.getWriter().print("exist");}
        else{
            resp.getWriter().print("ok");}

    }

    private void delete(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        if (emptblService.deleteEmptbl(m)){
            resp.getWriter().print("OK");}
        else{
            resp.getWriter().print("fail");}
    }

    private void update(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        if (emptblService.updateEmptbl(m)){
            resp.getWriter().print("OK");}
        else{
            resp.getWriter().print("fail");}
    }

    private void select(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String find=request.getParameter("find");
        resp.setContentType("application/json;charset=UTF-8");
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        resp.getWriter().print(emptblService.getEmptbls(m));
    }

    private void insert(HttpServletRequest request,HttpServletResponse response) throws IOException
//            , JasperException
    {
        Emptbl emptbl =new Emptbl();
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
//        org.apache.jasper.runtime.JspRuntimeLibrary.introspect(emptbl,request);
        System.out.println(emptbl);
        if (emptblService.insertEmptbl(emptbl)){
                response.getWriter().print("OK");}
            else{
                response.getWriter().print("fail");}

    }
}
//}



//    login.jsp?op=logout;
//注销之后就算后退界面也无法进行增删改，且跳转登陆界面
