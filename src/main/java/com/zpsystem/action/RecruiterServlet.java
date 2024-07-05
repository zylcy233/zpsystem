package com.zpsystem.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpsystem.service.RecrutierService;
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
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/recaction")

public class RecruiterServlet extends HttpServlet {
    Logger logger=Logger.getLogger(String.valueOf(RecruiterServlet.class));

    @Autowired
    RecrutierService recrutierService;

    @PostMapping("/getRec")
    List getRec(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map m) {
        String find = req.getParameter("find");
        m.put("find",find);
        logger.debug(m);
        List l=recrutierService.getRec(m);
        return l;
    }

    @PostMapping("/getRecOne")
    List getRecOne(HttpServletRequest req, @RequestParam Map m) {
        logger.debug("m:" + m);
        Map adm= (Map) req.getSession().getAttribute("adm");
        logger.debug(adm);
        m.put("rid",adm.get("rid"));
        List l=recrutierService.getRecOne(m);
        return l;
    }

    @PostMapping("/getnewJr")
    List getnewRec(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map m) {
        logger.debug("m:" + m);
        Map adm= (Map) req.getSession().getAttribute("adm");
        logger.debug(adm);
        m.put("rid",adm.get("rid"));
        List l=recrutierService.getRecOne(m);
        return l;
    }

    @GetMapping("/insert")
    void insert(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String data = req.getParameter("data");
        data = data.substring(1, data.length() - 1);
        logger.debug(data);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> map = objectMapper.readValue(data, Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            logger.debug("map:"+map);
            PrintWriter out=resp.getWriter();
            if (recrutierService.insert(map)){
                out.print("OK");
            }else {
                out.print("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/update")
    void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String data = req.getParameter("data");
        data = data.substring(1, data.length() - 1);
        logger.debug(data);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> map = objectMapper.readValue(data, Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            Map adm= (Map) req.getSession().getAttribute("adm");
            map.put("rid", (String) adm.get("rid"));
            logger.debug(map);
            if (recrutierService.update(map)) {
                resp.getWriter().print("OK");
            } else {
                resp.getWriter().print("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/delete")
    String  delete(HttpServletRequest request, HttpServletResponse resp,@RequestParam Map m) throws IOException {
        logger.debug(m);
        if (recrutierService.delete(m)){
//            resp.getWriter().print("OK");
            return "OK";
        }
        else{
//            resp.getWriter().print("fail");
            return "fail";
        }
    }




}

