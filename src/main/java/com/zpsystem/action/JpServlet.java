package com.zpsystem.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpsystem.service.JpService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
        m.put("userid",adm.get("userid"));
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
    void   shoucang(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map) throws IOException {
        Map adm= (Map) req.getSession().getAttribute("adm");
        map.put("jsid",adm.get("jsid"));
        String ids= (String) map.get("jpid");
        logger.debug(ids);
        String[] numbersArray = ids.split(",");
        List<Integer> numbersList = new ArrayList<>();
        for (String numberStr : numbersArray) {
            // 去除可能存在的空格（如果有的话）
            numberStr = numberStr.trim();
            // 将子字符串转换为整数并添加到列表中
            numbersList.add(Integer.parseInt(numberStr));
        }
        for (int number : numbersList) {
            System.out.println(number);
            map.put("jpid",number);
            if (jpService.insertNewJp(map)){
                resp.getWriter().print("OK");
            }else {
                resp.getWriter().print("fail");
            }
        }
    }

    @GetMapping("/delshoucang")
    void   delshoucang(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map) throws IOException {
        Map adm= (Map) req.getSession().getAttribute("adm");
        map.put("jsid",adm.get("jsid"));
        String ids= (String) map.get("jpid");
        logger.debug(ids);
        String[] numbersArray = ids.split(",");
        List<Integer> numbersList = new ArrayList<>();
        for (String numberStr : numbersArray) {
            // 去除可能存在的空格（如果有的话）
            numberStr = numberStr.trim();
            // 将子字符串转换为整数并添加到列表中
            numbersList.add(Integer.parseInt(numberStr));
        }
        for (int number : numbersList) {
            System.out.println(number);
            map.put("jpid",number);
            if (jpService.deletNewJp(map)){
                resp.getWriter().print("OK");
            }else {
                resp.getWriter().print("fail");
            }
        }
    }

    @GetMapping("/insert")
    void insert(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Map adm= (Map) req.getSession().getAttribute("adm");
        logger.debug(adm);
        String data = req.getParameter("data");
        data = data.substring(1, data.length() - 1);
        logger.debug(data);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> map = objectMapper.readValue(data, Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            map.put("rid", (String) adm.get("rid"));
            logger.debug(map);
            if (jpService.insert(map)){
                resp.getWriter().print("OK");
            }else {
                resp.getWriter().print("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/update")
    void update(HttpServletRequest req,HttpServletResponse resp) throws IOException {
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
            if (jpService.update(map)){
                resp.getWriter().print("OK");
            }else {
                resp.getWriter().print("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/delete")
    void   delete(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map) throws IOException {
        String ids= (String) map.get("jpid");
        logger.debug(ids);
        String[] numbersArray = ids.split(",");
        List<Integer> numbersList = new ArrayList<>();
        for (String numberStr : numbersArray) {
            // 去除可能存在的空格（如果有的话）
            numberStr = numberStr.trim();
            // 将子字符串转换为整数并添加到列表中
            numbersList.add(Integer.parseInt(numberStr));
        }
        for (int number : numbersList) {
            System.out.println(number);
            map.put("jpid",number);
            if (jpService.delete(map)){
                resp.getWriter().print("OK");
            }else {
                resp.getWriter().print("fail");
            }
        }
    }
}
