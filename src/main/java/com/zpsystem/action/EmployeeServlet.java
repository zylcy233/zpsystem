package com.zpsystem.action;


import com.alibaba.fastjson.JSON;
import com.zpsystem.service.EmployeeService;
import com.zpsystem.service.EmployeeServiceImpl;
import com.zpsystem.util.zyUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = "/employeeaction")
public class EmployeeServlet extends HttpServlet {
    EmployeeService employeeService= new EmployeeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if(method==null)method="";
        switch(method){
            case "getEmployees":
                getEmployees(req,resp);
                break;
        }
    }

    private void getEmployees(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap m=new HashMap();
        zyUtil.getRequestData(req,m);
        System.out.println(m);
        HashMap hashMap=employeeService.getEmployee(m);
        System.out.println(hashMap);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().print(JSON.toJSONString(hashMap));
    }
}
