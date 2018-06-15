<%--
  Created by IntelliJ IDEA.
  User: ZHAOKANG
  Date: 2018/6/13
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="width:100%;height:80px;background-color:#C0C0C0;float:top;">
    <h4 align="right">欢迎</h4>
    <h4 align="right">${username}</h4>
</div>
<div style="width:100%;height:10px;background-color:#FFFFFF;"></div>
<div style="width:40%;height:300px;background-color:#FFFFFF;float:left;">
    <div style="width:25%;height:300px;background-color:#FFFFFF;float:left;"></div>
    <div style="width:75%;height:200px;background-color:#DCDCDC;float:right;">
         <h3> </h3>
         <h2> 流程管理</h2>
         <h3>   图纸发放管理</h3>
         <h3>   到图登记管理</h3>
    </div>
</div>
<div style="width:60%;height:400px;background-color:#FFFFFF;float:right;">
    <div style="width:5%;height:400px;background-color:#FFFFFF;float:left;"></div>
    <div style="width:95%;height:400px;background-color:#FFFFFF;float:right;">
        <form action="/record/upload" method="post" enctype="multipart/form-data">
            <label >标题：</label><input type="text" name="title" /> <br>
            <label >描述：</label><input type="text" name="description" /><br>
            <input type="file" name="imgFile" />
            <input type="submit" id="submit">
        </form>
    </div>
</div>
<div style="clear:both;"></div>
</body>
</html>
