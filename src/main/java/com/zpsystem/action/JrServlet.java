package com.zpsystem.action;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpsystem.entity.Jobresume;
import com.zpsystem.service.JrService;
import com.zpsystem.util.zyUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Jraction")
public class JrServlet extends HttpServlet {
    @Autowired
    JrService jrService;
    Logger logger = Logger.getLogger(String.valueOf(JSServlet.class));

    @PostMapping("/getJr")
    List getJr(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map m) throws IOException {
        logger.debug("m:" + m);
        return jrService.getJr(m);
    }


    @PostMapping("/getJrone")
    List getJrone(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map m) throws IOException {
        logger.debug("m:" + m);
        HashMap adm = (HashMap) req.getSession().getAttribute("adm");
        m.put("jsid", adm.get("jsid"));
        return jrService.getJrone(m);
    }

    @PostMapping("/getnewJr")
    List getnewJr(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map m) throws IOException {
        logger.debug("m:" + m);
        HashMap adm = (HashMap) req.getSession().getAttribute("adm");
        m.put("rid", adm.get("rid"));
        return jrService.getNewJr(m);
    }
//TODO 多个复选框，要分开
    @GetMapping("/insert")
    void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String data = req.getParameter("Jobresume");
        data = data.substring(1, data.length() - 1);
        logger.debug(data);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> map1 = objectMapper.readValue(data, Map.class);
            for (Map.Entry<String, String> entry : map1.entrySet()) {
                map1.put(entry.getKey(), entry.getValue());
            }
            HashMap adm = (HashMap) req.getSession().getAttribute("adm");
            map1.put("jsid", (String) adm.get("jsid"));
            logger.debug(map1);
            if (jrService.insert(map1)) {
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
        String data = req.getParameter("Jobresume");
        data = data.substring(1, data.length() - 1);
        logger.debug(data);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> map1 = objectMapper.readValue(data, Map.class);
            for (Map.Entry<String, String> entry : map1.entrySet()) {
                map1.put(entry.getKey(), entry.getValue());
            }
            logger.debug(map1);
            HashMap adm = (HashMap) req.getSession().getAttribute("adm");
            map1.put("jsid", (String) adm.get("jsid"));
            logger.debug(map1);
            if (jrService.update(map1)) {
                resp.getWriter().print("OK");
            } else {
                resp.getWriter().print("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/delete")
    String delete(@RequestParam Map m) {
        logger.debug(m);
        if (jrService.delete(m)){
            return "OK";
        }
        else{
            return "fail";
        }
    }

    @GetMapping("/shoucang")
    void shoucang(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map) throws IOException {
        logger.debug(map);
        HashMap adm = (HashMap) req.getSession().getAttribute("adm");
        map.put("rid", adm.get("rid"));
        if (jrService.shoucang(map)) {
            resp.getWriter().print("OK");
        } else {
            resp.getWriter().print("fail");
        }
    }
    @GetMapping("/delshoucang")
    void delshoucang(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map) throws IOException {
        logger.debug(map);
        HashMap adm = (HashMap) req.getSession().getAttribute("adm");
        map.put("rid", adm.get("rid"));
        if (jrService.delshoucang(map)) {
            resp.getWriter().print("OK");
        } else {
            resp.getWriter().print("fail");
        }
    }


}
