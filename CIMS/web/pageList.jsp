<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangergou
  Date: 2017/8/9
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分页查询</title>
    <script type="text/javascript">
        function changePage(obj) {
            if (isNaN(obj.value)) {
                alert("必须是数字");
                obj.value =${page.thispage}
                return;
            } else if (obj.value <=0 || obj.value >${page.countpage}) {
                alert("必须在有效范围内");
                obj.value =${page.thispage}
                return;
            } else {
                window.location.href="${pageContext.request.contextPath}/servlet/pageCustServlet?thispage="+obj.value;
            }


        }
    </script>

</head>
<body>
<div align="center">
    <h4>分页查询客户信息</h4>
    <hr>
    <table border="1" width="100%">
        <tr>
            <th>客户姓名</th>
            <th>客户性别</th>
            <th>出生日期</th>
            <th>手机号码</th>
            <th>电子邮箱</th>
            <th>客户爱好</th>
            <th>客户类型</th>
            <th>描述信息</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
        <c:forEach items="${requestScope.page.list}" var="cust">
            <%--利用c:out标签的自带转义更能，放置xss攻击--%>
            <tr>
                <td><c:out value="${cust.name}"/></td>
                <td><c:out value="${cust.gender}"/></td>
                <td><c:out value="${cust.birthday}"/></td>
                <td><c:out value="${cust.cellphone}"/></td>
                <td><c:out value="${cust.email}"/></td>
                <td><c:out value="${cust.preference}"/></td>
                <td><c:out value="${cust.type}"/></td>
                <td><c:out value="${cust.description}"/></td>
                <td><a href="${pageContext.request.contextPath}/servlet/CustInfServlet?id=${cust.id}">修改</a></td>
                <td><a href="${pageContext.request.contextPath}/servlet/DelCustServlet?id=${cust.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>


    共${requestScope.page.countrow}条记录
    共${requestScope.page.countpage}页
    <a href="${pageContext.request.contextPath}/servlet/pageCustServlet?thispage=${requestScope.page.firstpage}">首页</a>
    <a href="${pageContext.request.contextPath}/servlet/pageCustServlet?thispage=${requestScope.page.prepage}">上一页</a>
    <%--分页逻辑开始--%>
    <c:if test="${page.countpage<=5}">
        <c:set var="begin" value="1" scope="page"></c:set>
        <c:set var="end" value="${page.countpage}" scope="page"></c:set>
    </c:if>
    <c:if test="${page.countpage>5}">
        <c:choose>
            <c:when test="${page.thispage<=3}">
                <c:set var="begin" value="1" scope="page"></c:set>
                <c:set var="end" value="5" scope="page"></c:set>
            </c:when>
            <c:when test="${page.thispage>=page.countpage-2}">
                <c:set var="begin" value="${page.countpage-4}" scope="page"></c:set>
                <c:set var="end" value="${page.countpage}" scope="page"></c:set>
            </c:when>
            <c:otherwise>
                <c:set var="begin" value="${page.thispage-2}" scope="page"></c:set>
                <c:set var="end" value="${page.thispage+2}" scope="page"></c:set>
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:forEach begin="${begin}" end="${end}" var="i" step="1">
        <c:if test="${i == page.thispage}">
            ${i}
        </c:if>
        <c:if test="${page.thispage != i}">
            <a href="${pageContext.request.contextPath}/servlet/pageCustServlet?thispage=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <a href="${pageContext.request.contextPath}/servlet/pageCustServlet?thispage=${requestScope.page.nextpage}">下一页</a>
    <a href="${pageContext.request.contextPath}/servlet/pageCustServlet?thispage=${requestScope.page.lastpage}">尾页</a>
    跳转到第<input type="text" value="${page.thispage}" style="width: 40px" onchange="changePage(this)"/>页

</div>
</body>
</html>

