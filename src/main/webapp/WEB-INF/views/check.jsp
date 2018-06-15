<%--
  Created by IntelliJ IDEA.
  User: ZHAOKANG
  Date: 2018/6/13
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="width:100%;height:80px;background-color:#C0C0C0;float:top;">
    <h4 align="right">欢迎</h4>
    <h4 align="right">${user}</h4>
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
        <table border="1" cellpadding="10" cellspacing="0">
            <tr>
                <th>标题</th>
                <th>用户名</th>
                <th>描述</th>
                <th>文件</th>
                <th>同意</th>
                <th>不同意</th>
                <th>描述</th>
                <th>提交</th>
            </tr>
            <form action="/record/doCheck" method="post">
                <c:forEach items="${records}" var="record">
                    <tr>
                        <td>${record.title }</td>
                        <td>${record.user1 }</td>
                        <td>${record.discription1}</td>
                        <td><a href="downloadPic/${record.pic}">${record.pic}</a></td>
                        <td><input type="radio" name="radio" value=${"1"}${record.pic}> </td>
                        <td><input type="radio" name="radio" value=${"2"}${record.pic}> </td>
                        <td><input type="text" name="description"> </td>
                        <td><input type="submit" value="提交"></td>
                    </tr>
                </c:forEach>
            </form>
        </table>

        </form>
    </div>
</div>
<div style="clear:both;"></div>
</body>



</html>
