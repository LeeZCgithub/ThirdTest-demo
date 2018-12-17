<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/10
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品页</title>
</head>
<body>
<table>
<c:forEach items="${productList}" var="productList">
    <tr>
        <td>${productList.pimage}</td>
        <td>${productList.pname}</td>
        <td>${productList.price}</td>
        <td>
            <a href="deleteUser?id=${productList.cid}">删除</a>
            <a href="toUpdateUser?id=${productList.pflag}">修改</a>
        </td>
    </tr>
</c:forEach>
</table>


</body>
</html>
