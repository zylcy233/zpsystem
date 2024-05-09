package com.zpsystem.action;

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

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/recaction")

public class RecruiterServlet extends HttpServlet {
    Logger logger=Logger.getLogger(String.valueOf(RecruiterServlet.class));

    @Autowired
    RecrutierService recrutierService;

    @PostMapping("/getRec")
    List getRec(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map m) {
        logger.debug("m:" + m);
        Map adm= (Map) req.getSession().getAttribute("adm");
        m.put("rid",adm.get("rid"));
        List l=recrutierService.getRec(m);
        return l;
    }

    @PostMapping("/getRecOne")
    List getRecOne(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map m) {
        logger.debug("m:" + m);
        Map adm= (Map) req.getSession().getAttribute("adm");
        logger.debug(adm);
        m.put("rid",adm.get("rid"));
        List l=recrutierService.getRecOne(m);
        return l;
    }

    @GetMapping("/insert")
    void insert(HttpServletResponse resq,@RequestParam Map map) throws IOException {
        logger.debug("map:"+map);
        PrintWriter out=resq.getWriter();
        if (recrutierService.insert(map)){
            out.print("sucess");
        }else {
            out.print("fail");
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

