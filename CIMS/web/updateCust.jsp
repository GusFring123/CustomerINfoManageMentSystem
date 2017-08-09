<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: wangergou
  Date: 2017/8/8
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新客户信息</title>
</head>
<body>
<div align="center">
    <h4>客户信息管理系统_更新客户</h4>
    <hr>
    <form action="${pageContext.request.contextPath}/servlet/UpdateCustServlet" method="post">
        <%--隐藏的属性的意思是，这是更新，后面需要根据客户的id进行更新，而提交的表单并没有这个id，所以封装到javabean中的时候没有这个id，需要在这里给它一个--%>
        <input type="hidden" name = id value="${cust.id}">
        <table border="1">
            <tr>
                <td>客户姓名</td>
                <%--readonly = readonly表示是只读的，不可更改--%>
                <%--style="background: silver"表示背景颜色是灰色的，提示不能更改--%>
                <td><input type="text" name="name" value="${cust.name}" readonly = readonly style="background: silver" ></td>
            </tr>
            <tr>
                <td>客户性别</td>
                <td>

                    <input type="radio" name="gender" value="男"
                           <c:if test="${'男'== cust.gender}">checked="checked"</c:if>>男
                    <input type="radio" name="gender" value="女"
                           <c:if test="${'女'== cust.gender}">checked="checked"</c:if>>女
                </td>
            </tr>
            <tr>
                <td>出生日期</td>
                <td><input type="text" name="birthday" value="${cust.birthday}"></td>
            </tr>
            <tr>
                <td>手机号码</td>
                <td><input type="text" name="cellphone" value="${cust.cellphone}"></td>
            </tr>
            <tr>
                <td>电子邮箱</td>
                <td><input type="text" name="email" value="${cust.email}"></td>
            </tr>
            <tr>
                <td>客户爱好</td>
                <td>

                    <%--<input type="checkbox" name="preference" value="篮球"--%>
                    <%--<c:forTokens items="${cust.preference}" delims="," var="preference">--%>
                    <%--<c:if test="${'篮球'.equals(preference)}">--%>
                    <%--checked="checked"--%>
                    <%--</c:if>--%>
                    <%--</c:forTokens>>篮球--%>
                    <%--<input type="checkbox" name="preference" value="乒乓球"--%>
                    <%--<c:forTokens items="${cust.preference}" delims="," var="preference">--%>
                    <%--<c:if test="${'乒乓球'.equals(preference)}">--%>
                    <%--checked="checked"--%>
                    <%--</c:if>--%>
                    <%--</c:forTokens>>乒乓球--%>
                    <%--<input type="checkbox" name="preference" value="玻璃球"--%>
                    <%--<c:forTokens items="${cust.preference}" delims="," var="preference">--%>
                    <%--<c:if test="${'玻璃球'.equals(preference)}">--%>
                    <%--checked="checked"--%>
                    <%--</c:if>--%>
                    <%--</c:forTokens>>玻璃球--%>
                    <%--<input type="checkbox" name="preference" value="铅球"--%>
                    <%--<c:forTokens items="${cust.preference}" delims="," var="preference">--%>
                    <%--<c:if test="${'铅球'.equals(preference)}">--%>
                    <%--checked="checked"--%>
                    <%--</c:if>--%>
                    <%--</c:forTokens>>铅球--%>


                    <input type="checkbox" name="preference" value="篮球"
                    <c:if test="${fn:contains(cust.preference, '篮球')}">
                           checked="checked"
                    </c:if>>篮球
                    <input type="checkbox" name="preference" value="乒乓球"
                    <c:if test="${fn:contains(cust.preference, '乒乓球')}">
                           checked="checked"
                    </c:if>>乒乓球
                    <input type="checkbox" name="preference" value="玻璃球"
                    <c:if test="${fn:contains(cust.preference, '玻璃球')}">
                           checked="checked"
                    </c:if>>玻璃球
                    <input type="checkbox" name="preference" value="铅球"
                    <c:if test="${fn:contains(cust.preference, '铅球')}">
                           checked="checked"
                    </c:if>>铅球
                </td>
            </tr>
            <tr>
                <td>客户类型</td>
                <td>
                    <select name="type">

                        <option value="钻石客户"<c:if test="${cust.type == '钻石客户'}">
                            selected = "selected"
                        </c:if>
                        >钻石客户
                        </option>
                        <option value="白金客户"<c:if test="${cust.type == '白金客户'}">
                            selected = "selected"
                        </c:if>
                        >白金客户
                        </option>
                        <option value="黄金客户"<c:if test="${cust.type == '黄金客户'}">
                            selected = "selected"
                        </c:if>>黄金客户
                        </option>
                        <option value="白银客户"<c:if test="${cust.type == '白银客户'}">
                            selected = "selected"
                        </c:if>>白银客户
                        </option>
                        <option value="青铜客户"<c:if test="${cust.type == '青铜客户'}">
                            selected = "selected"
                        </c:if>>青铜客户
                        </option>
                        <option value="黑铁客户"<c:if test="${cust.type == '黑铁客户'}">
                            selected = "selected"
                        </c:if>>黑铁客户
                        </option>
                        <option value="没牌客户"<c:if test="${cust.type == '没牌客户'}">
                            selected = "selected"
                        </c:if>>没牌客户
                        </option>

                    </select>
                </td>
            </tr>
            <tr>
                <td>描述信息</td>
                <td>
                    <textarea name="description" rows="6" cols="40">${cust.description}</textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input value="修改客户" type="submit"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
