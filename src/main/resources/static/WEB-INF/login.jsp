<%@ page import="com.zpsystem.dao.DDLDML" %><%--
  Created by IntelliJ IDEA.
  User: ZY
  Date: 2024/1/12
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function check(f) {
            console.log(f)  //向浏览器控制台输入信息
            console.log(typeof f);
            //所有的html/xml标签在js世界都是一个个对象
            var ananme=f.aname.value;
            var apasswd=f.apasswd.value;
            if (ananme==""||apasswd==""){
                alert("账号或密码不能位空")
                return false;
            }
        }
    </script>
</head>
<body style="background-image: url('壁纸.jpg')">
<%
//    login.jsp?op=logout;
    //注销之后就算后退界面也无法进行增删改，且跳转登陆界面
    String op=request.getParameter("op");
    if ("logout".equals(op)){
        session.setAttribute("aid",null);
    }

    String aid=request.getParameter("aid");
    String apasswd=request.getParameter("apasswd");
    if (aid!=null  && apasswd!=null){
        String sql="select aid from adm where aid='"+aid+"' and apasswd='"+apasswd+"'";
        if (DDLDML.isExist(sql)){
            session.setAttribute("aid",aid);
            response.sendRedirect("menu.jsp");
        }else {
            request.setAttribute("zy","无效的用户或密码");
        }
    }
%>
<%--<form action="login.jsp" onsubmit="return check(this)"></form>--%>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 style="color: red;"> <%
              String ts= (String) request.getAttribute("zy");
              if (ts!=null){
                  out.print(ts);
              }
            %></h3>
            <form role="form" action="login.jsp" onsubmit="return check(this)">
                <div class="form-group">
                    <label for="aid">账号</label>
                    <input type="text" class="form-control" id="aid" name="aid"/>
                </div>
                <div class="form-group">
                    <label for="apasswd">密码</label>
                    <input type="text" class="form-control" id="apasswd" name="apasswd" />
                </div>
                 <button type="submit" class="btn btn-default">登录</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
