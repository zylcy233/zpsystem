package com.zpsystem.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpsystem.service.ComService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comaction")
@MultipartConfig
public class ComServlet extends HttpServlet {
    @Autowired
    ComService comService;

    Logger logger=Logger.getLogger(ComServlet.class);

    @PostMapping("/select")
    List selectcom(HttpServletRequest request, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        String find=request.getParameter("find");
        resp.setContentType("application/json;charset=UTF-8");
        map.put("find",find);
        logger.debug(map);
        return comService.getCom(map);
    }

    @GetMapping("/update")
    void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String data = req.getParameter("Company");
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
            if (comService.update(map)) {
                resp.getWriter().print("OK");
            } else {
                resp.getWriter().print("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/delete")
     String  delete(HttpServletResponse resp,@RequestParam Map m){
        logger.debug(m);
        if (comService.delete(m)){
            return "OK";
        }
        else{
            return "fail";
        }
    }

    @GetMapping("/insert")
     void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String data = req.getParameter("Company");
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
            if (comService.insert(map)) {
                resp.getWriter().print("OK");
            } else {
                resp.getWriter().print("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

