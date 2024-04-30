package com.zpsystem.action;


import com.alibaba.fastjson.JSON;
import com.zpsystem.dao.DDLDML;
import com.zpsystem.entity.Jobresume;
import com.zpsystem.service.JrService;
import com.zpsystem.service.JrServiceImpl;
import com.zpsystem.util.zyUtil;
import org.apache.log4j.Logger;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(urlPatterns = "/Jraction")
public class JrServlet extends HttpServlet {
    com.zpsystem.service.JrService JrService = new JrServiceImpl();
    Logger logger=Logger.getLogger(String.valueOf(JSServlet.class));

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if (method == null) method = "";
        switch (method) {
            case "insert":
                insert(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "updateone":
                updateone(req, resp);
                break;
            case "getJrone":
                getJrone(req, resp);
                break;
            case "check":
                check(req,resp);
                break;
            default:
                getJr(req, resp);
                break;
        }
    }



	private void updateone(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(req,m);
        logger.debug("m:"+m);
        if (JrService.updateJr(m)){
            resp.getWriter().print("OK");}
        else{
            resp.getWriter().print("fail");}
    }

    private void getJr(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String find = req.getParameter("find");
        resp.setContentType("application/json;charset=UTF-8");
        HashMap m = new HashMap<String, String>();
        zyUtil.getRequestData(req, m);
        logger.debug("m:" + m);
        resp.getWriter().print(JrService.getJr(m));
    }

    private void getJrone(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String find=req.getParameter("find");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        HashMap adm= (HashMap) req.getSession().getAttribute("adm");
        logger.debug("adm:"+adm);
        String sql="select * from Jobresume where jsid='"+adm.get("jsid")+"'";
        logger.debug(sql);
        String a=JSON.toJSONString(DDLDML.getDatas(sql));
        resp.getWriter().print(a);

    }
    private void check(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        if (JrService.checkJr(m)){
            resp.getWriter().print("exist");}
        else{
            resp.getWriter().print("ok");}
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Jobresume Jobresume = new Jobresume();
        HashMap m = new HashMap<String, String>();
        zyUtil.getRequestData(request, m);
        logger.debug("Jobresume:"+Jobresume);
        String data = request.getParameter("Jobresume");
        logger.debug(data);
        data = data.substring(1, data.length() - 1);
        logger.debug("data:" + data);
        Jobresume j = JSON.parseObject(data, Jobresume.class);
        logger.debug(j);

        if (JrService.insertJr(j)) {
            response.getWriter().print("OK");
        } else {
            response.getWriter().print("fail");
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HashMap m = new HashMap<String, String>();
        zyUtil.getRequestData(request, m);
        if (JrService.deleteJr(m)) {
            resp.getWriter().print("OK");
        } else {
            resp.getWriter().print("fail");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse resp) throws IOException {
    	String data = request.getParameter("Jobresume");
        logger.debug("data1:"+data);
        data = data.substring(1, data.length() - 1);
        logger.debug("data2:" + data);
        Jobresume j = JSON.parseObject(data, Jobresume.class);
        logger.debug("j:"+j);
        if (JrService.updateJr(j)) {
            resp.getWriter().print("OK");
        } else {
            resp.getWriter().print("fail");
        }
    }
}
