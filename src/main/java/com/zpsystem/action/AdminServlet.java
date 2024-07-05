package com.zpsystem.action;

import com.zpsystem.service.AdminService;
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
@RequestMapping("/adminaction")
public class AdminServlet extends HttpServlet {
    @Autowired
    AdminService adminService;

    Logger logger=Logger.getLogger(String.valueOf(AdminServlet.class));

    @PostMapping("/selectadm")
    List selectadm(HttpServletRequest request, HttpServletResponse resp, @RequestParam Map map) throws IOException {
        String find=request.getParameter("find");
        map.put("find",find);
        logger.debug(map);
        return  adminService.selectadm(map);

    }
}


