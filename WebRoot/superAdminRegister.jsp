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
    <title>超级管理员注册界面</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="bootstrapFileInput/css/fileinput.min.css">

    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">

        /*顶部*/
        
        .carousel {
            height: 450px;
            margin-bottom: 60px;
        }

        .carousel .item {
            height: 450px;
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

        .regInfo{
          margin-bottom: 40px;
          margin-top:: 40px;
          text-align:center;
        }

        .form-group{
          margin-bottom: 40px;
          margin-top: 40px;
        }

        .attention1{
          font-size: 150%;
          color:#FFB800;
        }

        .attention2{
          font-size: 120%;
          color:#2F4056;
        }

        .form-control{
          margin-bottom: 10px;
          margin-top: 10px

        }

        .sexCheckbox{
          padding-top: 10px;
          padding-bottom: 10px;
          margin-bottom: 10px;
          margin-top: 10px;
          text-align:left;
        }

        .main{
          text-align:center;
        }

        .col-md-6{
          margin-bottom: 20px
        }

        .img-circle{
            margin-bottom: 40px;
            margin-top: 40px;
        }

        .btn-info {
           margin-top: 60px;
        }
    </style>
</head>


<body>
<!--顶部-->
<div id="ad-carousel" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="item active">
            <img src="images/standard17.jpeg" >
            <div class="container">
                <div class="carousel-caption">
                    <h1>生物识别云平台</h1>
                    <h1>BIOLOGICAL AUTHENTICATION SYSTEM</h1>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container main">
  
  <!--注册信息-->
  <form  class="form-horizontal" method="post" action="${pageContext.request.contextPath }/register" enctype="multipart/form-data">
    <div class="form-group" role="form">
      <div class="row regInfo">
        <label  class="attention1" for="">您是本平台的第一位使用者，您将成为超级管理员，拥有对用户提权、降权等操作权</label><br>
        <h1>   </h1><br>
        <label class="attention2" for="">请完善您的个人信息</label><br>
        <p>   </p>
        <input type="text" class="form-control" placeholder="请输入您的身份证号码" name="idNum" id="idNum"><br>
        <input type="password" class="form-control" placeholder="请输入您的密码" name="password" id="password"><br>
        <input type="password" class="form-control" placeholder="请输重复您的密码" name="comfirm" id="comfirm"><br>
        <input type="text" class="form-control" placeholder="请输入您的姓名" name="name" id="name"><br>
        <input type="email" class="form-control" placeholder="请输入您的邮箱" name="email" id="email"><br>
        <div class="sexCheckbox"> 
          <label>请选择性别：       </label>
          <label>男</label>
          <input type="checkbox" name="male">
          <label>女</label>
          <input type="checkbox" name="female">
        </div> 
        <input type="number" class="form-control" placeholder="请输入您的年龄" name="age" id="age"><br>
        <input type="text" class="form-control" placeholder="请输入您的电话号码" name="tel" id="tel"><br>
      </div>
      <div class="row imgUpload" id="imgUpload">   
        
      <label class="attention2" for="">请录入您的生物特征信息</label><br>

      <!--上传指纹信息的图片-->
      <div class="col-md-6">
        <div>    
          <img class="img-circle" src="images/standard10.jpg" >
        </div>
        <label class="control-label">请上传您的第一张指纹信息</label>
        <input type="file" class="file" id="input-1" name="finger1">
        <label class="control-label">请上传您的第二张指纹信息</label>
        <input type="file" class="file" id="input-2" name="finger2">
        <label class="control-label">请上传您的第三张指纹信息</label>
        <input type="file" class="file" id="input-3" name="finger3">
      </div>

      <!--上传人脸信息的图片-->
      <div class="col-md-6">
        <div>    
          <img class="img-circle" src="images/standard11.gif" >
        </div>
        <label class="control-label">请上传您的第一张人脸信息</label>
        <input type="file" class="file" id="input-4" name="face1">
        <label class="control-label">请上传您的第二张人脸信息</label>
        <input type="file" class="file" id="input-5" name="face2">
        <label class="control-label">请上传您的第三张人脸信息</label>
        <input type="file" class="file" id="input-6" name="face3">
        <label class="control-label">请上传您的第四张人脸信息</label>
        <input type="file" class="file" id="input-7" name="face4">
        <label class="control-label">请上传您的第五张人脸信息</label>
        <input type="file" class="file" id="input-8" name="face5">
      </div>
        <input type="submit" name="submit" value="注册" class="btn btn-info btn-lg" role="button">
      </div>
    </div> 
  </form> 
  
</div>

<hr class="feature-divider">

<!--底部-->
<footer>
        <p class="pull-right"><a href="#top">回到顶部</a></p>
</footer>


<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="bootstrapFileInput/js/fileinput.min.js"></script>
<script type="text/javascript" src="bootstrapFileInput/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="bootstrapFileInput/js/locale/zh.js"></script>
		<c:if test="${not empty message }">
			<script>
				alert("注册失败！请检查您的注册信息并重新注册");
			</script>
		</c:if>
</body>
</html>

