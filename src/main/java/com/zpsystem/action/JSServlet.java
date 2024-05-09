package com.zpsystem.action;


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
    List getJS( @RequestParam Map m){
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
    void insert(HttpServletResponse resp, @RequestParam Map map) throws IOException {
        logger.debug(map);
        if (jsService.zhuce(map)) {
            resp.getWriter().print("OK");
        } else {
            resp.getWriter().print("fail");
        }
    }

    @GetMapping("/update")
    String update(HttpServletRequest request, HttpServletResponse resp,@RequestParam Map map) throws IOException {
        logger.debug(map);
        if (jsService.update(map)) {
            resp.getWriter().print("OK");
            return "OK";
        } else {
            resp.getWriter().print("fail");
            return "fail";
        }
    }
}
