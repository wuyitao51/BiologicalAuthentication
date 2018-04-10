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
    <title>管理员验证登录</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="bootstrapFileInput/css/fileinput.min.css">

    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">

        <!--顶部-->
        .carousel {
            height: 350px;
            margin-bottom: 60px;
        }

        .carousel .item {
            height: 350px;
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

        .input {
            padding-right: 15px;
            padding-left: 15px;
            padding-top: 100px;
            padding-bottom: 40px;
            text-align: center;
            font-size: 25px;
            text-decoration-color: #6A5ACD; 
        }


        .btn-info {
           margin-top: 60px;
        }

        .img-circle{
            margin-bottom: 40px;
            margin-top: 40px;
        }
        
        #results { float:right; margin:20px; padding:20px; border:1px solid; background:#ccc; }
    </style>
    
    <script src="JSvideo/jquery-3.3.1.js"></script>
</head>


<body>
<!--顶部-->
<div id="ad-carousel" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="item active">
            <img src="images/standard15.jpeg" >
            <div class="container">
                <div class="carousel-caption">
                    <h1>生物识别云平台</h1>
                    <h1>BIOLOGICAL AUTHENTICATION SYSTEM</h1>
                </div>
            </div>
        </div>
    </div>
</div>

<!--登录-->
<div class="container input">
    <!--账号输入-->
    <div class="row" id="accountInput">
       
        <form class="form-horizontal"  method="post"  action="${pageContext.request.contextPath }/login">
            <div class="form-group">
                <label for="name">使用您的管理员账号进行身份验证</label>
                <h1>           </h1>
                <input type="text" class="form-control" id="username" name="username" placeholder="请输入您的管理员账号">
                <h1>           </h1><br>
                <h1>           </h1>
                <input type="password" class="form-control" id="password" name="password" placeholder="请输入您的账号密码">
                <input type="submit" name="submit" value="登录"  class="btn btn-info btn-lg" role="button">
            </div>
        </form>
        
    </div>

    <hr class="feature-divider">

    <!--上传图片-->
    <form class="form-horizontal" id="form_bio" method="post" action="${pageContext.request.contextPath }/loginByFinger" enctype="multipart/form-data">
    <div class="row" id="imgUpload">   
        
        <h2><strong>使用生物识别进行身份验证</strong></h2><br>

        <!--上传指纹信息的图片-->
        <div class="col-md-6" >
            <div>    
                <img class="img-circle" src="images/standard10.jpg" >
            </div>
            <label class="control-label">上传指纹信息</label>
            <input type="file" class="file" id="input-1" name="finger">
        </div>
        <!--上传人脸信息的图片-->
        <div class="col-md-6">      
            <div>    
                <img class="img-circle" src="images/standard11.gif" >
            </div>
            <label class="control-label">上传人脸信息</label>
            <!--  input type="file" class="file" id="input-2" name="face">-->
            <div id="my_camera"></div>
            <input id="snap" type=button value="创建快照" onClick="take_snapshot()" class="btn btn-info btn-sm" role="button">
			<input id="auto" type="button" value="每秒自动截取" onClick="autoSnap()" class="btn btn-info btn-sm" role="button">
			<div id="results">您截取的照片将在这里显示...</div>
            
         </div> 
         <script type="text/javascript" src="JSvideo/webcam.min.js"></script>
         <script language="JavaScript">
			Webcam.set({
			width: 540,
			height: 340,
			image_format: 'jpeg',
			jpeg_quality: 90
			});
			Webcam.attach( '#my_camera' );
		</script>
		
		<script language="JavaScript">
		function take_snapshot() {
			// take snapshot and get image data
			Webcam.snap( function(data_uri) {
				// display results in page
				document.getElementById('results').innerHTML = 
					'<h2>您所截取的图像:</h2>' + 
					'<img src="'+data_uri+'"/>';
					//alert(data_uri);		//data_uri 就是图像数据的base64编码加上固定字符串前缀“data:image/jpeg;base64,” 可以从浏览器控制台看到！
											//可以直接将这个数据进行传输
					window.faceimg;
					window.faceimg = data_uri;
			} );							
			
			
		}
	</script>
	<script>
		function autoSnap(){
			var autoButton = document.getElementById("auto");
			autoButton.value = "停止自动截取";
			setInterval(function(){
				document.getElementById("snap").click();
			},1000)
			$(autoButton).attr("onclick","stopAutoSnap()");
		}
		
		function stopAutoSnap(){
			var autoButton = document.getElementById("auto");
			autoButton.value = "每秒自动截取";
			$(autoButton).attr("onclick","");
			$(autoButton).attr("onclick","autoSnap()");
		}
	</script>
		<!--  
        <input type="submit" name="submit" value="登录"  class="btn btn-info btn-lg" role="button">
    	-->
    	<!-- 
    	表单的提交与AJAX不能同时进行
    	解决办法：
    	提交的按钮从submit改成button，然后点击触发AJAX的交互
    	等到AJAX交互完毕，success函数里，在用JS触发form.submit()的事件
    	这样就能保证在提交表单之前，AJAX优先执行完毕，并且从用户的视觉上是同时进行的了
    	 -->
    	 <p><a class="btn btn-info btn-lg" role="button" id="loginbyface">登录</a></p>
    	 <script>
    	 	var loginbyface = document.getElementById("loginbyface");
    	 	loginbyface.onclick=function(){
    	 		var img = window.faceimg;
    	 		//alert(img);
    	 		//alert("...");
     	 		// ajax在传输过程中 加号会变成空格 base64里是有加号的 
    			// 把+替换成编码 %2B是加号的编码 
    			var realimg = img.replace(/\+/g, "%2B");
     	 		$.ajax({
 					type:"POST",
 					url:"http://localhost:8080/BioAuthen/loginByFace",
 					dataType:"text",
 					data:{"img":img},
 					success:function(result){
						if(result.toString()!="failed"){
							var id = result.toString();
							//alert(id);
							//人脸验证通过
							var form = document.getElementById("form_bio");
							form.submit();
							
						}else{
							//人脸验证不通过
							alert("验证失败！请重新验证");
						}
 					}
 					});
			}
    	 </script>
    </div>
    </form>

    <hr class="feature-divider">

    <!--底部-->
    <footer>
        <p class="pull-right"><a href="#top">回到顶部</a></p>
    </footer>

</div>



<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="bootstrapFileInput/js/fileinput.min.js"></script>
<script type="text/javascript" src="bootstrapFileInput/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="bootstrapFileInput/js/locale/zh.js"></script>

		<c:if test="${not empty message }">
			<script>
				alert("验证失败！请重新验证");
			</script>
		</c:if>
</body>
</html>
