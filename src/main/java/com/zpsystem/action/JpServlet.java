package com.zpsystem.action;

import com.alibaba.fastjson.JSON;
import com.zpsystem.dao.DDLDML;
import com.zpsystem.entity.Jobposting;
import com.zpsystem.service.JpService;
import com.zpsystem.util.zyUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSOutput;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Jpaction")
public class JpServlet extends HttpServlet {
    @Autowired
    JpService jpService;
    Logger logger=Logger.getLogger(String.valueOf(JpServlet.class));

    //TODO No mapping for POST /zpSystem/Jpaction/getJp
    @PostMapping("/getJp")
    List getJp(HttpServletRequest req,  @RequestParam Map m) throws IOException {
        logger.debug("m:" + m);
        HashMap adm= (HashMap) req.getSession().getAttribute("adm");
        m.put("jsid",adm.get("jsid"));
        return jpService.getNewJp(m);
    }

    //TODO POST "/zpSystem/Jpaction/getnewJP", parameters={masked}
    @PostMapping("/getnewJP")
    List getnewJP(HttpServletRequest req,  @RequestParam Map m) throws IOException {
        logger.debug("m:" + m);
        HashMap adm= (HashMap) req.getSession().getAttribute("adm");
        m.put("jsid",adm.get("jsid"));
        return jpService.getJp(m);
    }

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
            case "get":
                getJpone(req, resp);
                break;
            case "check":
                check(req,resp);
                break;
             case "shoucang":
                	shoucang(req,resp);
                	break;
             case "delshoucang":
                	delshoucang(req,resp);
                	break;
             case "getnewJP":
                	getnewJP(req,resp);
                	break;
            default:
                getjp(req, resp);
                break;
        }
    }

    private void getnewJP(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        HashMap adm= (HashMap) req.getSession().getAttribute("adm");
        logger.debug("adm:"+adm);
        String sql="select * from newjobposting where jsid='"+adm.get("jsid")+"'";
        logger.debug(sql);
        String a=JSON.toJSONString(DDLDML.getDatas(sql));
        resp.getWriter().print(a);
		
	}


	private void delshoucang(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	 resp.setContentType("application/json;charset=UTF-8");
         PrintWriter out=resp.getWriter();
         HashMap m=new HashMap<String,String>();
         zyUtil.getRequestData(req,m);
         logger.debug("m:"+m);
         HashMap adm= (HashMap) req.getSession().getAttribute("adm");
         logger.debug("adm:"+adm);
         String sql="delete from newjobposting where jpid='"+m.get("jpid")+
        		 "' and jsid='"+adm.get("jsid")+"'";
         logger.debug(sql);
         String a=JSON.toJSONString(DDLDML.getDatas(sql));
         resp.getWriter().print(a);
		
	}


	private void shoucang(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	 resp.setContentType("application/json;charset=UTF-8");
         PrintWriter out=resp.getWriter();
         HashMap m=new HashMap<String,String>();
         zyUtil.getRequestData(req,m);
         logger.debug("m:"+m);
         HashMap adm= (HashMap) req.getSession().getAttribute("adm");
         logger.debug("adm:"+adm);
         String sql="insert into newjobposting values('"+m.get("jpid")+"','"+adm.get("jsid")+"')";
         logger.debug(sql);
         String a=JSON.toJSONString(DDLDML.getDatas(sql));
         resp.getWriter().print(a);
	}


	private void updateone(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(req,m);
        logger.debug("m:"+m);
        if (jpService.updateJp(m)){
            resp.getWriter().print("OK");}
        else{
            resp.getWriter().print("fail");}
    }

    private void getjp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String find = req.getParameter("find");
        resp.setContentType("application/json;charset=UTF-8");
        HashMap m = new HashMap<String, String>();
        zyUtil.getRequestData(req, m);
        logger.debug("m:" + m);
        resp.getWriter().print(jpService.getJp(m));
    }

    private void getJpone(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String find=req.getParameter("find");
        resp.setContentType("application/json;charset=UTF-8");
        String jsid=req.getParameter("jsid");
        String jspasswd=req.getParameter("jspasswd");
        PrintWriter out=resp.getWriter();
        HashMap adm= (HashMap) req.getSession().getAttribute("adm");
        logger.debug("adm:"+adm);
        String sql="select * from Jobposting where jsid='"+adm.get("jsid")+"' and jspasswd='"+adm.get("jspasswd")+"'";
        logger.debug(sql);
        String a=JSON.toJSONString(DDLDML.getDatas(sql));
        resp.getWriter().print(a);

    }
    private void check(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        if (jpService.checkJp(m)){
            resp.getWriter().print("exist");}
        else{
            resp.getWriter().print("ok");}
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Jobposting jobposting = new Jobposting();
        HashMap m = new HashMap<String, String>();
        zyUtil.getRequestData(request, m);
        logger.debug("jobposting:"+jobposting);
        String data = request.getParameter("Jobposting");
        logger.debug(data);
        data = data.substring(1, data.length() - 1);
        logger.debug("data:" + data);
        Jobposting j = JSON.parseObject(data, Jobposting.class);
        logger.debug(j);

        if (jpService.insertJp(j)) {
            response.getWriter().print("OK");
        } else {
            response.getWriter().print("fail");
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HashMap m = new HashMap<String, String>();
        zyUtil.getRequestData(request, m);
        if (jpService.deleteJp(m)) {
            resp.getWriter().print("OK");
        } else {
            resp.getWriter().print("fail");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse resp) throws IOException {
    	String data = request.getParameter("Jobposting");
        logger.debug("data1:"+data);
        data = data.substring(1, data.length() - 1);
        logger.debug("data2:" + data);
        Jobposting j = JSON.parseObject(data, Jobposting.class);
        logger.debug("j:"+j);
        if (jpService.updateJp(j)) {
            resp.getWriter().print("OK");
        } else {
            resp.getWriter().print("fail");
        }
    }
}
