package com.zpsystem.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpsystem.service.AdminService;
import com.zpsystem.service.AppcategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appcategoryaction")
public class AppcategoryServlet extends HttpServlet {
    @Autowired
    AppcategoryService appcategoryService;

    Logger logger=Logger.getLogger(String.valueOf(AppcategoryServlet.class));

    @PostMapping("/getAppcategory")
    List getApp(HttpServletRequest req,  @RequestParam Map m) throws IOException {
        logger.debug("m:" + m);
        HashMap adm= (HashMap) req.getSession().getAttribute("adm");
        return appcategoryService.getAppcategory(m);
    }
    @GetMapping("/insert")
    void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String data = req.getParameter("data");
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
            if (appcategoryService.insert(map)) {
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
        String data = req.getParameter("data");
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
            if (appcategoryService.update(map)) {
                resp.getWriter().print("OK");
            } else {
                resp.getWriter().print("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


