<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <title>录入多模态信息</title>
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

        .attention2{
          font-size: 120%;
          color:#2F4056;
        }

        .form-control{
          margin-bottom: 10px;
          margin-top: 10px

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
            <img src="images/standard20.jpeg" >
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
   <!--上传图片-->
  <div class="row imgUpload" id="imgUpload">    
      <label class="attention2" for="">请录入您的生物特征信息</label><br>

    <form action="${pageContext.request.contextPath }/doAddAuthenInfo" method="post" enctype="multipart/form-data">
      <!--上传指纹信息的图片-->
      <div class="col-md-6">
          <div>    
              <img class="img-circle" src="images/standard10.jpg" >
          </div>
          <label class="control-label">上传指纹信息1</label>
          <input type="file" class="file" id="input-1" name="finger1">
          <label class="control-label">上传指纹信息2</label>
          <input type="file" class="file" id="input-2" name="finger2">
          <label class="control-label">上传指纹信息3</label>
          <input type="file" class="file" id="input-3" name="finger3">
      </div>

      <!--上传人脸信息的图片-->
      <div class="col-md-6">
          <div>    
              <img class="img-circle" src="images/standard11.gif" >
          </div>
          <label class="control-label">上传人脸信息1</label>
          <input type="file" class="file" id="input-4" name="face1">
          <label class="control-label">上传人脸信息2</label>
          <input type="file" class="file" id="input-5" name="face2">
          <label class="control-label">上传人脸信息3</label>
          <input type="file" class="file" id="input-6" name="face3">
          <label class="control-label">上传人脸信息4</label>
          <input type="file" class="file" id="input-7" name="face4">
          <label class="control-label">上传人脸信息5</label>
          <input type="file" class="file" id="input-8" name="face5">
      </div>

      <input type="submit" value="确认" class="btn btn-info btn-lg" role="button" name=“confirmButton”>
    </form>  
  </div>
  
</div>



<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="bootstrapFileInput/js/fileinput.min.js"></script>
<script type="text/javascript" src="bootstrapFileInput/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="bootstrapFileInput/js/locale/zh.js"></script>
</body>
</html>