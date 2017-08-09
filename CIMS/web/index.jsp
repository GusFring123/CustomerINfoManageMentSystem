<%--
  Created by IntelliJ IDEA.
  User: wangergou
  Date: 2017/8/8
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户信息管理系统</title>
</head>
<body>
<div align="center">
    <h4>客户信息管理系统</h4>
    <hr>
    <a href="${pageContext.request.contextPath}/addCust.jsp">添加客户</a>
    <a href="${pageContext.request.contextPath}/servlet/ListCustomerServlet">客户列表</a>
    <a href="${pageContext.request.contextPath}/servlet/pageCustServlet?thispage=1">分页查询</a>
</div>
</body>
</html>
