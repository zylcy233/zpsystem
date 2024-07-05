package com.zpsystem.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpsystem.service.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adminaction")
public class AdminServlet extends HttpServlet {
    @Autowired
    AdminService adminService;

    Logger logger=Logger.getLogger(String.valueOf(AdminServlet.class));

    @PostMapping("/selectadm")
    List selectadm(HttpServletRequest request, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        String find=request.getParameter("find");
        map.put("find",find);
        logger.debug(map);
        return  adminService.selectadm(map);
    }

    @GetMapping("/login")
    void login(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        logger.debug(map);
        PrintWriter out=resp.getWriter();
        if (adminService.login(map)){
            req.getSession().setAttribute("adm",map);
            out.print("ok!");
        }else {
            req.setAttribute("zy","无效的用户或密码");
            req.getSession().setAttribute("adm",null);
            out.print("fail");
        }
    }

    @GetMapping("/logout")
    void logout(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        request.getSession().setAttribute("adm",null);
        resp.sendRedirect("http://localhost:8080/zpSystem/flogin.html");
    }


    @GetMapping("/insert")
    void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String data = req.getParameter("App");
        data = data.substring(1, data.length() - 1);
        logger.debug(data);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> map = objectMapper.readValue(data, Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
            logger.debug(map);
            if (adminService.insert(map)) {
                resp.getWriter().print("OK");
            } else {
                resp.getWriter().print("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/update")
    void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String data = req.getParameter("App");
        data = data.substring(1, data.length() - 1);
        logger.debug(data);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> map = objectMapper.readValue(data, Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
            logger.debug(map);
            if (adminService.update(map)) {
                resp.getWriter().print("OK");
            } else {
                resp.getWriter().print("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


