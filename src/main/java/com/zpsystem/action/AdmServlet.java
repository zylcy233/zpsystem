package com.zpsystem.action;
import com.zpsystem.service.AdmService;
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

@Controller
@RequestMapping("/admaction")
public class AdmServlet extends HttpServlet {
    Logger logger=Logger.getLogger(String.valueOf(AdmServlet.class));
    @Autowired
    AdmService admService;
    @GetMapping("/login")
    void login(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        logger.debug(map);
        PrintWriter out=resp.getWriter();
        if (admService.login(map)){
           req.getSession().setAttribute("adm",map);
           out.print("ok!");
       }else {
            req.setAttribute("zy","无效的用户或密码");
           req.getSession().setAttribute("adm",null);
            out.print("fail");
        }
        }

        @GetMapping("/changepwd")
    void changepwd(HttpServletRequest request,HttpServletResponse resp,@RequestParam Map map) throws IOException {
            PrintWriter out=resp.getWriter();
            HashMap adm= (HashMap) request.getSession().getAttribute("adm");
            map.put("aid",adm.get("aid"));
            logger.debug("adm:"+adm);
            logger.debug(map);
            if (!adm.get("apasswd").equals(map.get("oldapasswd"))){
                out.print("olderror");
                return;
            }
            if (admService.changepwd(map)){
                adm.put("apasswd",map.get("apasswd"));
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

