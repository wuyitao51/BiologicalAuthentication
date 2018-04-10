<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加用户</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="bootstrapFileInput/css/fileinput.min.css">

    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">

        /*顶部*/
        
        .carousel {
            height: 550px;
            margin-bottom: 60px;
        }

        .carousel .item {
            height: 550px;
            background-color: #000;
        }

        .carousel .item img {
            width: 100%;
        }

        .carousel-caption {
            z-index: 10;
        }

        .carousel-caption p {
            margin-bottom: 20px;
            font-size: 20px;
            line-height: 1.8;
        }

        .listtop{
            margin-top: 20px;
            margin-right: 0px;
            margin-left: 0px;
            padding-top: 20px;
            background-image: url("images/standard16.jpeg");
            
        }

        .listword{
            text-align: center;
            font-size: 150%;
            font-weight:700;
            color: #F0F0F0 ;
        }

        .list{
            margin-right: 0px;
            margin-left: 0px;
            padding-top: 20px;
            margin-top: 20px;
            margin-bottom: 40px;
  
        }

        .buttun{
          text-align: center;
        }
    </style>
</head>


<body>
<!--顶部-->
<div id="ad-carousel" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="item active">
            <img src="images/standard18.jpeg" >
            <div class="container">
                <div class="carousel-caption">
                    <h1>生物识别云平台</h1>
                    <h1>BIOLOGICAL AUTHENTICATION SYSTEM</h1>
                </div>
            </div>
        </div>
    </div>
</div>

<hr class="feature-divider">

<div class="container main">
  <!--用户列表栏-->
  <div class="row listtop">
      <p class="listword">用户列表</p>
  </div>
  <form  class="form-horizontal" method="post" action="${pageContext.request.contextPath }/addUsers" enctype="multipart/form-data">
  <!--用户列表-->
  <div class="row list">
    <table class="table table-striped table-bordered table-hover" >
      <thead>
        <tr>
          <th>选择</th>
          <th>序号</th>
          <th>姓名</th>
          <th>性别</th>
          <th>年龄</th>
          <th>电话号码</th>
          <th>邮箱地址</th>
          <th>身份证号码</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th><input type="checkbox" name="choose" value="1"></th>
          <th>1</th>
          <th><input type="text" class="form-control" placeholder="请输入姓名" name="name1"></th>
          <th><label>男</label> <input type="checkbox" name="male1"> <br>
          		   <label>女</label> <input type="checkbox" name="female1"></th>
          <th><input type="number" class="form-control" placeholder="请输入年龄" name="age1"></th>
          <th><input type="text" class="form-control" placeholder="请输入电话号码" name="tel1"></th>
          <th><input type="email" class="form-control" placeholder="请输入邮箱地址" name="email1"></th>
          <th><input type="text" class="form-control" placeholder="请输入身份证号码" name="id1"></th>
        </tr>
        <tr>
          <th><input type="checkbox" name="choose"  value="2"></th>
          <th>2</th>
          <th><input type="text" class="form-control" placeholder="请输入姓名" name="name2"></th>
          <th><label>男</label> <input type="checkbox" name="male2"><br>
                   <label>女</label> <input type="checkbox" name="female2"></th>
          <th><input type="number" class="form-control" placeholder="请输入年龄" name="age2"></th>
          <th><input type="text" class="form-control" placeholder="请输入电话号码" name="tel2"></th>
          <th><input type="email" class="form-control" placeholder="请输入邮箱地址" name="email2"></th>
          <th><input type="text" class="form-control" placeholder="请输入身份证号码" name="id2"></th>
        </tr>
        <tr>
          <th><input type="checkbox" name="choose" value="3"></th>
          <th>3</th>
          <th><input type="text" class="form-control" placeholder="请输入姓名" name="name3"></th>
          <th><label>男</label> <input type="checkbox" name="male3"><br>
                   <label>女</label> <input type="checkbox" name="female3"></th>
          <th><input type="number" class="form-control" placeholder="请输入年龄" name="age3"></th>
          <th><input type="text" class="form-control" placeholder="请输入电话号码" name="tel3"></th>
          <th><input type="email" class="form-control" placeholder="请输入邮箱地址" name="email3"></th>
          <th><input type="text" class="form-control" placeholder="请输入身份证号码" name="id3"></th>
        </tr>
        <tr>
          <th><input type="checkbox" name="choose" value="4"></th>
          <th>4</th>
          <th><input type="text" class="form-control" placeholder="请输入姓名" name="name4"></th>
          <th><label>男</label> <input type="checkbox" name="male4"><br>
                  <label>女</label> <input type="checkbox" name="female4"></th>
          <th><input type="number" class="form-control" placeholder="请输入年龄" name="age4"></th>
          <th><input type="text" class="form-control" placeholder="请输入电话号码" name="tel4"></th>
          <th><input type="email" class="form-control" placeholder="请输入邮箱地址" name="email4"></th>
          <th><input type="text" class="form-control" placeholder="请输入身份证号码" name="id4"></th>
        </tr>
        <tr>
          <th><input type="checkbox" name="choose" value="5"></th>
          <th>5</th>
          <th><input type="text" class="form-control" placeholder="请输入姓名" name="name5"></th>
          <th><label>男</label> <input type="checkbox" name="male5"><br>
                  <label>女</label> <input type="checkbox" name="female5"></th>
          <th><input type="number" class="form-control" placeholder="请输入年龄" name="age5"></th>
          <th><input type="text" class="form-control" placeholder="请输入电话号码" name="tel5"></th>
          <th><input type="email" class="form-control" placeholder="请输入邮箱地址" name="email5"></th>
          <th><input type="text" class="form-control" placeholder="请输入身份证号码" name="id5"></th>
        </tr>
        <tr>
          <th><input type="checkbox" name="choose" value="6"></th>
          <th>6</th>
          <th><input type="text" class="form-control" placeholder="请输入姓名" name="name6"></th>
          <th><label>男</label> <input type="checkbox" name="male6"><br>
                  <label>女</label> <input type="checkbox" name="female6"></th>
          <th><input type="number" class="form-control" placeholder="请输入年龄" name="age6"></th>
          <th><input type="text" class="form-control" placeholder="请输入电话号码" name="tel6"></th>
          <th><input type="email" class="form-control" placeholder="请输入邮箱地址" name="email6"></th>
          <th><input type="text" class="form-control" placeholder="请输入身份证号码" name="id6"></th>
        </tr>
        <tr>
          <th><input type="checkbox" name="choose" value="7"></th>
          <th>7</th>
          <th><input type="text" class="form-control" placeholder="请输入姓名" name="name7"></th>
          <th><label>男</label> <input type="checkbox" name="male7"> <br>
                 <label>女</label> <input type="checkbox" name="female7"></th>
          <th><input type="number" class="form-control" placeholder="请输入年龄" name="age7"></th>
          <th><input type="text" class="form-control" placeholder="请输入电话号码" name="tel7"></th>
          <th><input type="email" class="form-control" placeholder="请输入邮箱地址" name="email7"></th>
          <th><input type="text" class="form-control" placeholder="请输入身份证号码" name="id7"></th>
        </tr>
        <tr>
          <th><input type="checkbox" name="choose" value="8"></th>
          <th>8</th>
          <th><input type="text" class="form-control" placeholder="请输入姓名" name="name8"></th>
          <th><label>男</label> <input type="checkbox" name="male8" > <br>
                  <label>女</label> <input type="checkbox" name="female8"></th>
          <th><input type="number" class="form-control" placeholder="请输入年龄" name="age8"></th>
          <th><input type="text" class="form-control" placeholder="请输入电话号码" name="tel8"></th>
          <th><input type="email" class="form-control" placeholder="请输入邮箱地址" name="email8"></th>
          <th><input type="text" class="form-control" placeholder="请输入身份证号码" name="id8"></th>
        </tr>
        <tr>
          <th><input type="checkbox" name="choose" value="9"></th>
          <th>9</th>
          <th><input type="text" class="form-control" placeholder="请输入姓名" name="name9"></th>
          <th><label>男</label> <input type="checkbox" name="male9"><br>
                  <label>女</label> <input type="checkbox" name="female9"></th>
          <th><input type="number" class="form-control" placeholder="请输入年龄" name="age9"></th>
          <th><input type="text" class="form-control" placeholder="请输入电话号码" name="tel9"></th>
          <th><input type="email" class="form-control" placeholder="请输入邮箱地址" name="email9"></th>
          <th><input type="text" class="form-control" placeholder="请输入身份证号码" name="id9"></th>
        </tr>
        <tr>
          <th><input type="checkbox" name="choose" value="10"></th>
          <th>10</th>
          <th><input type="text" class="form-control" placeholder="请输入姓名" name="name10"></th>
          <th><label>男</label> <input type="checkbox" name="male10"><br>
                  <label>女</label> <input type="checkbox" name="female10"></th>
          <th><input type="number" class="form-control" placeholder="请输入年龄" name="age10"></th>
          <th><input type="text" class="form-control" placeholder="请输入电话号码" name="tel10"></th>
          <th><input type="email" class="form-control" placeholder="请输入邮箱地址" name="email10"></th>
          <th><input type="text" class="form-control" placeholder="请输入身份证号码" name="id10"></th>
        </tr>
      </tbody>
    </table>
  </div>

  <div class="row buttun">
      <input type="submit" name="submit" value="确认用户信息" class="btn btn-success btn-lg" role="button">
  </div>

 </form>
</div>

<hr class="feature-divider">

<!--底部-->
<footer>
        <p class="pull-right"><a href="admin.jsp">返回管理员界面</a></p>
</footer>


<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="bootstrapFileInput/js/fileinput.min.js"></script>
<script type="text/javascript" src="bootstrapFileInput/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="bootstrapFileInput/js/locale/zh.js"></script>
<c:if test="${not empty messageSuccess }">
	<script>
		<%
			String success = request.getSession().getAttribute("success").toString();
			String fail = request.getSession().getAttribute("fail").toString();
		%>
		var s = '<%=success%>';
		var f ='<%=fail%>';
		alert("提交用户信息成功！\n录入成功条数："+s+"    录入失败条数："+f);
	</script>
</c:if>
<c:if test="${not empty messageFailed }">
	<script>
		alert("提交用户信息失败！");
	</script>
</c:if>
</body>
</html>
