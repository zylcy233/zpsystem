package com.zpsystem.action;

import com.zpsystem.dao.DDLDML;
import com.zpsystem.service.RecrutierService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Raction")
public class RecruiterServlet extends HttpServlet {
    Logger logger=Logger.getLogger(String.valueOf(RecruiterServlet.class));

    @Autowired
    RecrutierService recrutierService;

    //TODO 前端页面显示报错
    @PostMapping("/getRec")
    List getRec(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map m) {
        logger.debug("m:" + m);
        return recrutierService.getRec(m);
    }


}
//}



//    login.jsp?op=logout;
        //注销之后就算后退界面也无法进行增删改，且跳转登陆界面
