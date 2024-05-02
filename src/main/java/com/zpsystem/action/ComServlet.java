package com.zpsystem.action;

import com.alibaba.fastjson.JSON;
import com.zpsystem.entity.Company;
import com.zpsystem.service.ComService;
import com.zpsystem.service.ComServiceImpl;
import com.zpsystem.util.zyUtil;
import org.apache.jasper.runtime.JspRuntimeLibrary;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comaction")
@MultipartConfig
public class ComServlet extends HttpServlet {
    @Autowired
    ComService comService;
//    ComService comService=new ComServiceImpl();
    Logger logger=Logger.getLogger(ComServlet.class);

    @PostMapping("/select")
    List selectcom(HttpServletRequest request, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        String find=request.getParameter("find");
        resp.setContentType("application/json;charset=UTF-8");
//        HashMap m=new HashMap<String,String>();
//        zyUtil.getRequestData(request,m);
        map.put("find",find);
        logger.debug(map);
//        resp.getWriter().print(comService.getCom((HashMap<String, String>) map));
        return comService.getCom(map);
    }
    @PostMapping("/update")
    String edit(HttpServletRequest request, HttpServletResponse resp,@RequestParam Map map) throws IOException {
        logger.debug(map);
//        String data = request.getParameter("Company");
//        logger.debug("data1:"+data);
//        data = data.substring(1, data.length() - 1);
//        logger.debug("data2:" + data);
//        Company c = JSON.parseObject(data, Company.class);
//        logger.debug("c:"+c);
        if (comService.update(map)) {
            resp.getWriter().print("OK");
            return "OK";
        } else {
            resp.getWriter().print("fail");
            return "fail";
        }
    }

    @GetMapping("/delete")
    String delet(HttpServletRequest request, HttpServletResponse resp,@RequestParam Map m) throws IOException {
        logger.debug(m);
        if (comService.delete(m)){
            resp.getWriter().print("OK");
            return "OK";
        }
        else{
            resp.getWriter().print("fail");
            return "fail";
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException
//            , JasperException
    {

        String method=request.getParameter("method");
        logger.debug(method);
        if (method==null) method="";
        switch (method){
            case "insert":
                inserta(request,resp);
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
//                select(request,resp);
                break;
        }

    }

    private void update(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
        String data = request.getParameter("Company");
        logger.debug("data1:"+data);
        data = data.substring(1, data.length() - 1);
        logger.debug("data2:" + data);
        Company c = JSON.parseObject(data, Company.class);
        logger.debug("c:"+c);
        if (comService.updateCom(c)) {
            resp.getWriter().print("OK");
        } else {
            resp.getWriter().print("fail");
        }
		
	}

	private void inserta(HttpServletRequest request, HttpServletResponse resp) throws IOException {
    	Company company = new Company();
        HashMap m = new HashMap<String, String>();
        zyUtil.getRequestData(request, m);
        logger.debug("m:"+m);
        String data = request.getParameter("Company");
        logger.debug("data1:"+data);
        data = data.substring(1, data.length() - 1);
        logger.debug("data2:" + data);
        Company c = JSON.parseObject(data, Company.class);
        logger.debug("c:"+c);
        if (comService.insertCom(c)) {
            resp.getWriter().print("OK");
        } else {
            resp.getWriter().print("fail");
        }
		
	}

	private void check(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        if (comService.checkCom(m)){
            resp.getWriter().print("exist");}
        else{
            resp.getWriter().print("ok");}
    }

    private void delete(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        logger.debug(m);
        if (comService.deleteCom(m)){
            resp.getWriter().print("OK");}
        else{
            resp.getWriter().print("fail");}
    }


    private void select(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String find=request.getParameter("find");
        resp.setContentType("application/json;charset=UTF-8");
        HashMap m=new HashMap<String,String>();
        zyUtil.getRequestData(request,m);
        logger.debug(m);
        resp.getWriter().print(comService.getCom(m));
    }

    private void insert(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
//            , JasperException
    {
//        response.setContentType("application/json;charset=UTF-8");
        HashMap m=new HashMap<String,String>();
        Company company=new Company();
        zyUtil.getRequestData(request,m);
        logger.debug(m);
//        JspRuntimeLibrary.introspect(company,request);
        org.apache.jasper.runtime.JspRuntimeLibrary.introspect(company,request);
        logger.debug(company);
        Part filePart = request.getPart("pic");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String sext=fileName.substring(fileName.lastIndexOf(".")+1);
        logger.debug(sext);
        InputStream fileContent=filePart.getInputStream();
        String path=request.getRealPath("/")+"/uploads/"+company.getCid()+"_."+sext;
        logger.debug(path);
//        logger.debug(Paths.get("/uploads/"+fileName));
        Files.copy(fileContent,Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        logger.debug(fileName);
        response.getWriter().print(fileName);
        String data=request.getParameter("Company");
        logger.debug("company:"+company);
        logger.debug("data:"+data);
        Company j= JSON.parseObject(data,Company.class);
        logger.debug("j:"+j);
//        org.apache.jasper.runtime.JspRuntimeLibrary.introspect(company,request);
        logger.debug(company);
        if (comService.insertCom(m)){
                response.getWriter().print("OK");}
            else{
                response.getWriter().print("fail");}

    }
}
//}



//    login.jsp?op=logout;
//注销之后就算后退界面也无法进行增删改，且跳转登陆界面
