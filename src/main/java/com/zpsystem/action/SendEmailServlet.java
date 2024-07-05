package com.zpsystem.action;


import com.zpsystem.service.UserService;
import com.zpsystem.service.RecrutierService;
import com.zpsystem.util.SendEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@RestController
@RequestMapping("/sendction")
@WebServlet(urlPatterns = "/sendction")
public class SendEmailServlet extends HttpServlet {
    Logger logger=Logger.getLogger(String.valueOf(SendEmailServlet.class));
    @Autowired
    UserService userService;
    @Autowired
    RecrutierService recrutierService;

    @GetMapping("/jssend")
    void jssend(HttpServletRequest request, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        PrintWriter out=resp.getWriter();
        String id=request.getParameter("id");
        logger.debug(id);
//        ArrayList lst= (ArrayList) jsService.sendemail(map);
        Map m=  userService.sendemail(map);
        logger.debug(m);
        if (m !=null){
            request.getSession().setAttribute("adm",m);
            logger.debug("OK");
            out.print("OK");
            String text= (String) m.get("JSPASSWD");
            logger.debug(text);
            String to= (String) m.get("JSEMAIL");
            logger.debug("to:"+to);
            if (m !=null){
                logger.debug("发送成功");
                String title="请查收您的招聘系统密码";
                SendEmail.sendMail(to,text,title);
            }else {
                out.print("fail");
                logger.debug("发送失败");
            }
        }else {
            request.getSession().setAttribute("adm",null);
            logger.debug("fail");
            out.print("fail");
        }
    }

    @GetMapping("/rsend")
    void rsend(HttpServletRequest request, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        PrintWriter out = resp.getWriter();
        String id = request.getParameter("id");
        logger.debug(id);
        Map m = recrutierService.sendemail(map);
        logger.debug(m);
        if (m != null) {
            request.getSession().setAttribute("adm", m);
            logger.debug("OK");
            out.print("OK");
            String text = (String) m.get("RPASSWD");
            logger.debug(text);
            String to = (String) m.get("REMAIL");
            logger.debug("to:" + to);
            if (m != null) {
                logger.debug("发送成功");
                String title = "请查收您的招聘系统密码";
                SendEmail.sendMail(to, text, title);
            } else {
                out.print("fail");
                logger.debug("发送失败");
            }
        } else {
            request.getSession().setAttribute("adm", null);
            logger.debug("fail");
            out.print("fail");
        }
    }
}

