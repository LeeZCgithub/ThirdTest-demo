<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>后台管理</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<h1>后台管理系統</h1>
<!--导航条 -->

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">后台</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="#">Link <span class="sr-only">(current)</span></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/product/toAddProduct">add</a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">下拉菜单<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/product/showProductsByCid?cid=1">手机数码</a>
                        </li>
                        <li role="separator" class="divider"></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/product/showProductsByCid?cid=2">精美女妆</a>
                        </li>
                        <li role="separator" class="divider"></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/product/showProductsByCid?cid=3">鞋帽箱包</a>
                        </li>
                        <li role="separator" class="divider"></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/product/showProductsByCid?cid=4">逗趣水果</a>
                        </li>
                        <li role="separator" class="divider"></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/product/showProductsByCid?cid=5">家用电器</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left" action="fuzzyQuery">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search" name="pname">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/ThirdTest/product/showProductsByPage">home</a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#">Action</a>
                        </li>
                        <li>
                            <a href="#">Another action</a>
                        </li>
                        <li>
                            <a href="#">Something else here</a>
                        </li>
                        <li role="separator" class="divider"></li>
                        <li>
                            <a href="#">Separated link</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
    <table width="80%" class="table table-striped table-bordered table-hover table-condensed">
        <caption><h3>该表格用于展示商品列表</h3><caption>
        <tr class="active">
            <td class="tab-content-area">
                序号
            </td>
            <td class="tab-content-area">
                名称
            </td>
            <td class="tab-content-area">
                单价
            </td>
            <td class="tab-content-area">
                图片
            </td>
            <td class="tab-content-area">
                是否热门
            </td>
            <td class="tab-content-area">
                上架日期
            </td>
            <td class="tab-content-area">
                商品描述
            </td>
            <td class="tab-content-area">
                删除
            </td>
            <td class="tab-content-area">
                修改
            </td>
        </tr>
        <c:forEach items="${productList}" var="product" varStatus="i">
            <tr class="active">
                <td class="tab-content-area">
                    ${i.count+number}
                </td>
                <td class="tab-content-area">
                    ${product.pname}
                 </td>
                <td class="tab-content-area">
                    ${product.price}
                </td>
                <td class="tab-content-area">
                    <img style="width: 50px;height: 50px" src="${pageContext.request.contextPath}/${product.pimage}" alt="暂无">
                </td>
                <td class="tab-content-area">
                    ${product.hot}
                </td>
                <td class="tab-content-area">
                    ${product.pdate}
                </td>
                <td class="tab-content-area">
                    ${product.pdesc}
                </td>
                <td class="tab-content-area">
                    <button class="btn btn-default" type="button" onclick="location.href='${pageContext.request.contextPath}/product/deleteProduct?pid=${product.pid}&currentPage=${currentPage}&pageSize=${pageSize}'">删除</button>
                </td>
                <td class="tab-content-area">
                    <button class="btn btn-default" type="button" onclick="location.href='${pageContext.request.contextPath}/product/toUpdateProduct?pid=${product.pid}&currentPage=${currentPage}&pageSize=${pageSize}'">修改</button>
                </td>

            </tr>
        </c:forEach>
    </table>
    <div align="center">
    <nav  style=" align-content: center;align-items: center" aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li >
            <c:forEach begin="1" end="${pageNumber}" var="i">
                <li ><a href="${pageContext.request.contextPath}/product/showProductsByPage?currentPage=${i}">${i}</a></li>
            </c:forEach>

            <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
    </div>
    <%--<div class="row">--%>
        <%--<div class="col-xs-6 col-md-3">--%>
            <%--<a href="#" class="thumbnail">--%>
                <%--<img src="${pageContext.request.contextPath}/img/avatar04.png" alt="666">--%>
            <%--</a>--%>
        <%--</div>--%>
        <%--<div class="col-xs-6 col-md-3">--%>
            <%--<a href="#" class="thumbnail">--%>
                <%--<img src="${pageContext.request.contextPath}/img/avatar04.png" alt="666">--%>
            <%--</a>--%>
        <%--</div>--%>
        <%--<div class="col-xs-6 col-md-3">--%>
            <%--<a href="#" class="thumbnail">--%>
                <%--<img src="${pageContext.request.contextPath}/img/avatar04.png" alt="666">--%>
            <%--</a>--%>
        <%--</div>--%>
        <%--<div class="col-xs-6 col-md-3">--%>
            <%--<a href="#" class="thumbnail">--%>
                <%--<img src="${pageContext.request.contextPath}/img/avatar04.png" alt="666">--%>
            <%--</a>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="row">--%>
        <%--<div class="col-xs-6 col-md-3">--%>
            <%--<a href="#" class="thumbnail">--%>
                <%--<img src="${pageContext.request.contextPath}/img/avatar04.png" alt="666">--%>
            <%--</a>--%>
        <%--</div>--%>
        <%--<div class="col-xs-6 col-md-3">--%>
            <%--<a href="#" class="thumbnail">--%>
                <%--<img src="${pageContext.request.contextPath}/img/avatar04.png" alt="666">--%>
            <%--</a>--%>
        <%--</div>--%>
        <%--<div class="col-xs-6 col-md-3">--%>
            <%--<a href="#" class="thumbnail">--%>
                <%--<img src="${pageContext.request.contextPath}/img/avatar04.png" alt="666">--%>
            <%--</a>--%>
        <%--</div>--%>
        <%--<div class="col-xs-6 col-md-3">--%>
            <%--<a href="#" class="thumbnail">--%>
                <%--<img src="${pageContext.request.contextPath}/img/avatar04.png" alt="666">--%>
            <%--</a>--%>
        <%--</div>--%>

    <%--</div>--%>
</nav>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>