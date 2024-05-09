package com.zpsystem.action;

import com.alibaba.fastjson.JSON;
import com.zpsystem.entity.Jobposting;
import com.zpsystem.service.JpService;
import com.zpsystem.util.zyUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
@RequestMapping("/Jpaction")
public class JpServlet extends HttpServlet {
    @Autowired
    JpService jpService;
    Logger logger=Logger.getLogger(String.valueOf(JpServlet.class));

    @PostMapping("/getJp")
    List getJp(HttpServletRequest req,  @RequestParam Map m) throws IOException {
        logger.debug("m:" + m);
        HashMap adm= (HashMap) req.getSession().getAttribute("adm");
        m.put("jsid",adm.get("jsid"));
        return jpService.getJp(m);
    }

    @PostMapping("/getnewJP")
    List getnewJP(HttpServletRequest req,  @RequestParam Map m) throws IOException {
        String find = req.getParameter("find");
        m.put("find",find);
        logger.debug("m:" + m);
        HashMap adm= (HashMap) req.getSession().getAttribute("adm");
        m.put("jsid",adm.get("jsid"));
        return jpService.getNewJp(m);
    }

    @GetMapping("/shoucang")
    String  shoucang(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map){
        Map adm= (Map) req.getSession().getAttribute("adm");
        map.put("jsid",adm.get("jsid"));
        logger.debug(map);
        if (jpService.insertNewJp(map)){
            return "OK";
        }else {
            return "fail";
        }
    }

    @GetMapping("/delshoucang")
    String  delshoucang(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map){
        Map adm= (Map) req.getSession().getAttribute("adm");
        map.put("jsid",adm.get("jsid"));
        logger.debug(map);
        if (jpService.deletNewJp(map)){
            return "OK";
        }else {
            return "fail";
        }
    }
}
