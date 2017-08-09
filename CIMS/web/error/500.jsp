<%--
  Created by IntelliJ IDEA.
  User: wangergou
  Date: 2017/8/8
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>错误提示</title>
</head>
<body>
<hr>
<h3>服务器出错了哦~~~~~原因：${pageContext.exception.message}</h3>
<hr>
</body>
</html>
