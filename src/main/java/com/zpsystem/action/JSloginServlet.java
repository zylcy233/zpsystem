package com.zpsystem.action;

import com.zpsystem.service.JsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//@Controller
@RestController
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

    @GetMapping("/logout")
    void logout(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        request.getSession().setAttribute("adm",null);
        resp.sendRedirect("http://localhost:8080/zpSystem/flogin.html");
    }


}
