package com.zpsystem.action;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpsystem.service.JsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/JSaction")
public class JSServlet extends HttpServlet {
    @Autowired
    JsService jsService;

    Logger logger=Logger.getLogger(String.valueOf(JSServlet.class));

    @PostMapping("/getJS")
    List getJS(HttpServletRequest req, @RequestParam Map m){
        String find = req.getParameter("find");
        m.put("find",find);
        logger.debug("m:" + m);
        return jsService.getJS(m);
    }
    @PostMapping("/getJsone")
    List getJSone(HttpServletRequest req, @RequestParam Map m){
        logger.debug("m:" + m);
        Map adm= (Map) req.getSession().getAttribute("adm");
        logger.debug("adm:"+adm);
        return jsService.getJsone(adm);
    }

    @GetMapping("/delete")
    String delete(@RequestParam Map m) {
        logger.debug(m);
        if (jsService.delete(m)){
            return "OK";
        }
        else{
            return "fail";
        }
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
            logger.debug(map);
            String jsbirth=map.get("jsbirth");
            String date = jsbirth.split("T")[0];
            map.put("jsbirth",date);
            logger.debug(map);
            if (jsService.zhuce(map)) {
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
            }
            logger.debug(map);
            String jsbirth=map.get("jsbirth");
            String date = jsbirth.split("T")[0];
            map.put("jsbirth",date);
            if (jsService.update(map)) {
                resp.getWriter().print("OK");
            } else {
                resp.getWriter().print("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
