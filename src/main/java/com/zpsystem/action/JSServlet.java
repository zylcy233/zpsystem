package com.zpsystem.action;


import com.alibaba.fastjson.JSON;
import com.zpsystem.dao.DDLDML;
import com.zpsystem.entity.Jobseekers;
import com.zpsystem.service.JSServiceImpl;
import com.zpsystem.service.JsService;
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

@WebServlet(urlPatterns = "/JSaction")
public class JSServlet extends HttpServlet {
    JsService jsService = new JSServiceImpl();
    Logger logger=Logger.getLogger(String.valueOf(JSServlet.class));

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        logger.debug("method:"+method);
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
            case "getJsone":
                getJsone(req, resp);
                break;
            case "check":
                check(req,resp);
                break;
            case "jsshoucang":
                jsshoucang(req, resp);
                break;
            default:
                getjs(req, resp);
                break;
        }
    }
    
	private void jsshoucang(HttpServletRequest req, HttpServletResponse resp) throws IOException {
   	 resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(req,m);
        logger.debug("m:"+m);
        HashMap adm= (HashMap) req.getSession().getAttribute("adm");
        logger.debug("adm:"+adm);
        String sql="insert into newjobseekers values('"+m.get("jsid")+"','"+adm.get("rid")+"')";
        logger.debug(sql);
        String a=JSON.toJSONString(DDLDML.getDatas(sql));
        resp.getWriter().print(a);
	}

    private void updateone(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(req,m);
        logger.debug("m:"+m);
       
        if (jsService.updateJS(m)){
            resp.getWriter().print("OK");}
        else{
            resp.getWriter().print("fail");}
    }

    private void getjs(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String find = req.getParameter("find");
        resp.setContentType("application/json;charset=UTF-8");
        HashMap m = new HashMap<String, String>();
        zyUtil.getRequestData(req, m);
        logger.debug("m:" + m);
        resp.getWriter().print(jsService.getJs(m));
    }

    private void getJsone(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String find=req.getParameter("find");
        resp.setContentType("application/json;charset=UTF-8");
        String jsid=req.getParameter("jsid");
        String jspasswd=req.getParameter("jspasswd");
        PrintWriter out=resp.getWriter();
        HashMap adm= (HashMap) req.getSession().getAttribute("adm");
        logger.debug("adm:"+adm);
        String sql="select * from Jobseekers where jsid='"+adm.get("jsid")+"'";
        logger.debug(sql);
        String a=JSON.toJSONString(DDLDML.getDatas(sql));
        resp.getWriter().print(a);

    }
    private void check(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        if (jsService.checkJs(m)){
            resp.getWriter().print("exist");}
        else{
            resp.getWriter().print("ok");}
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Jobseekers jobseekers = new Jobseekers();
        HashMap m = new HashMap<String, String>();
        zyUtil.getRequestData(request, m);
//        org.apache.jasper.runtime.JspRuntimeLibrary.introspect(zytbl,request);
        logger.debug(jobseekers);
        String data = request.getParameter("Jobseekers");
        logger.debug(data);
        data = data.substring(1, data.length() - 1);
        logger.debug("data:" + data);
        Jobseekers j = JSON.parseObject(data, Jobseekers.class);
        logger.debug(j);

        if (jsService.insertJs(j)) {
            response.getWriter().print("OK");
        } else {
            response.getWriter().print("fail");
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HashMap m = new HashMap<String, String>();
        zyUtil.getRequestData(request, m);
        if (jsService.deleteJS(m)) {
            resp.getWriter().print("OK");
        } else {
            resp.getWriter().print("fail");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse resp) throws IOException {
    	String data = request.getParameter("Jobseekers");
        logger.debug("data1:"+data);
        data = data.substring(1, data.length() - 1);
        logger.debug("data2:" + data);
        Jobseekers j = JSON.parseObject(data, Jobseekers.class);
        logger.debug("j:"+j);
        if (jsService.updateJS(j)) {
            resp.getWriter().print("OK");
        } else {
            resp.getWriter().print("fail");
        }
    }
}
