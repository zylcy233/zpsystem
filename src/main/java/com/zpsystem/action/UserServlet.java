package com.zpsystem.action;

import com.zpsystem.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping( "/userloginaction")
public class UserServlet extends HttpServlet {
    Logger logger=Logger.getLogger(String.valueOf(UserServlet.class));
    @Autowired
    UserService userService;

    @GetMapping("/login")
    void login(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        logger.debug(map);
        PrintWriter out=resp.getWriter();
        if (userService.login(map)){
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
        if (userService.zhuce(map)){
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
        if (userService.changepwd(map)){
            adm.put("jspasswd",map.get("jspasswd"));
            out.print("ok!");
        }else {
            out.print("fail");
        }
    }

    @GetMapping("/shoucang")
    void   shoucang(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map) throws IOException {
        Map adm= (Map) req.getSession().getAttribute("adm");
        map.put("userid",adm.get("userid"));
        logger.debug(map);
        String ids= (String) map.get("appid");
        logger.debug(ids);
        String[] numbersArray = ids.split(",");
        List<Integer> numbersList = new ArrayList<>();
        for (String numberStr : numbersArray) {
            // 去除可能存在的空格（如果有的话）
            numberStr = numberStr.trim();
            // 将子字符串转换为整数并添加到列表中
            numbersList.add(Integer.parseInt(numberStr));
        }
        for (int number : numbersList) {
            System.out.println(number);
            map.put("appid",number);
            if (userService.insertappcollection(map)){
                resp.getWriter().print("OK");
            }else {
                resp.getWriter().print("fail");
            }
        }
    }

    @GetMapping("/delshoucang")
    void   delshoucang(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map) throws IOException {
        Map adm= (Map) req.getSession().getAttribute("adm");
        map.put("userid",adm.get("userid"));
        logger.debug(map);
        String ids= (String) map.get("appid");
        logger.debug(ids);
        String[] numbersArray = ids.split(",");
        List<Integer> numbersList = new ArrayList<>();
        for (String numberStr : numbersArray) {
            // 去除可能存在的空格（如果有的话）
            numberStr = numberStr.trim();
            // 将子字符串转换为整数并添加到列表中
            numbersList.add(Integer.parseInt(numberStr));
        }
        for (int number : numbersList) {
            System.out.println(number);
            map.put("appid",number);
            if (userService.deleteappcollection(map)){
                resp.getWriter().print("OK");
            }else {
                resp.getWriter().print("fail");
            }
        }
    }

    @PostMapping("/getApp")
    List getApp(HttpServletRequest req,  @RequestParam Map m) throws IOException {
        logger.debug("m:" + m);
        HashMap adm= (HashMap) req.getSession().getAttribute("adm");
        return userService.getApp(m);
    }

    @PostMapping("/getAppCollection")
    List getnewJP(HttpServletRequest req,  @RequestParam Map m) throws IOException {
        String find = req.getParameter("find");
        m.put("find",find);
        logger.debug("m:" + m);
        HashMap adm= (HashMap) req.getSession().getAttribute("adm");
        m.put("userid",adm.get("userid"));
        return userService.getAppCollection(m);
    }

    @GetMapping("/logout")
    void logout(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        request.getSession().setAttribute("adm",null);
        resp.sendRedirect("http://localhost:8080/zpSystem/flogin.html");
    }


}
