<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/12
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>修改页面</title>

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
<h1 align="" class="text-center" >添加页面</h1>
<form class="form-horizontal text-area" role="form" style="width:80%" action="${pageContext.request.contextPath}/product/addProduct" method="post" enctype="multipart/form-data">
    <div class="form-group" >
        <label class="col-sm-2 control-label">商品名</label>
        <div class="col-sm-10">
            <input class="form-control" name="pname" type="text" value="${product.pname}">
        </div>
    </div>
    <div class="form-group" >
        <label class="col-sm-2 control-label">商品价格</label>
        <div class="col-sm-10">
            <input class="form-control" name="price" type="text" value="${product.price}">
        </div>
    </div>
    <div class="form-group" >
        <label class="col-sm-2 control-label">商品图片</label>
        <div class="col-sm-10">
            <input class="form-control" name="pimage1" type="file" value="${product.pimage}">
        </div>
    </div>
    <div class="form-group" >
        <label class="col-sm-2 control-label">是否热门</label>
        <div class="col-sm-10">

            <input type="radio" name="hot" id="ishot" value="是" >是
            <input type="radio" name="hot" id="nothot"  value="否">否
        </div>
    </div>
    <div class="form-group" >
        <label class="col-sm-2 control-label">商品描述</label>
        <div class="col-sm-10">
            <input class="form-control" id="focusedInput" type="text" name="pdesc" value="${product.pdesc}">
        </div>
    </div>
    <div class="form-group" >
        <label class="col-sm-2 control-label">所属分类</label>
        <div class="col-sm-10">
            <input type="text" name="cid" value="${product.cid}"/>
        </div>
    </div>
    <div class="form-group" >
        <label class="col-sm-2 control-label">标记批号</label>
        <div class="col-sm-10">
            <input type="text" name="pflag" value="${product.pflag}" />
        </div>
    </div>
    <div class="form-group" >
        <label class="col-sm-2 control-label">上架时间</label>
        <div class="col-sm-10">
            <input type="date" name="pdate" value="${product.pdate}"/>
        </div>
    </div>

    <div class="text-right" >
        <button type="submit" class="btn btn-success">提交</button>
    </div>
</form>



<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>


</html>
