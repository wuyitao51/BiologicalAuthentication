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
    <title>管理员界面</title>
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

        /*查找框*/
        .search{
            margin-top: 50px;
            margin-bottom: 50px;
        }

        .listtop{
            margin-top: 60px;
            margin-bottom: 0px;
            margin-right: 0px;
            margin-left: 0px;
            padding-top: 20px;
            padding-bottom: 0px;
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
            margin-top: 20px;
  
        }

        .list{
            margin-bottom: 40px;
        }

        .choose{
            margin-bottom: 40px;
             text-align:center;
        }

        .uInfo{
            margin-top: 40px;
            margin-bottom: 60px;
            text-align:center;
        }

        .uImg{
             margin-top: 20px;
        }

        .buttenAuthen{
            text-align:center;
        }
        
         #results { float:right; margin:20px; padding:20px; border:1px solid; background:#ccc; }
         
         <script src="JSvideo/jquery-3.3.1.js"></script>
    </style>
</head>


<body>
<!--顶部-->
<div id="ad-carousel" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="item active">
            <img src="images/standard16.jpeg" >
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
    <ul class="nav nav-tabs" role="tablist" id="feature-tab">
        <li class="active"><a href="#tab-admin" role="tab" data-toggle="tab">管理模式</a></li>
        <li><a href="#tab-authen" role="tab" data-toggle="tab">认证模式</a></li>
    </ul>

    <div class="tab-content">
        <!--管理模式-->
        <div class="tab-pane active" id="tab-admin">
           <form class="form-horizontal" method="post" action="${pageContext.request.contextPath }/searchUsers">
            <!--查找框-->
            <div class="row search">
                <!--框-->
                <div class="col-md-10">
                    <input type="text" class="form-control" placeholder="您想要查找的信息/关键字" name="search">
                </div>

                <!--按钮-->
                <div class="col-md-2">
                    <input type="submit" name="submit" value="搜索" class="btn btn-info " role="button">
                </div>
            </div>
           </form>

            <hr class="feature-divider">

            <!--用户列表栏-->
            <div class="row listtop">
                <div class="col-md-11">
                    <p class="listword">用户列表</p>
                </div>
                
                <div class="col-md-1">
                     <p><a class="btn btn-default " href="addUsers.jsp" role="button">添加</a></p>
                </div>
            </div>

            <!--用户列表-->
            <form class="form-horizontal" action="${pageContext.request.contextPath }/doUpdate" method="post" enctype="multipart/form-data">
              <div class="row list">
                <table class="table table-striped table-bordered table-hover" >
                  <thead>
                    <tr>
                      <th>选择</th>
                      <th>序号</th>
                      <th>姓名</th>
                      <th>性别</th>
                      <th>年龄</th>
                      <th>身份证号码</th>
                      <th>电话号码</th>
                      <th>权限</th>
                      <th>指纹信息是否录入</th>
                      <th>面部信息是否录入</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${users}" var="u">
                    <tr>
                      <th><input type="checkbox" name="choose" value="${u.order}"></th>
                      <th>${u.order}</th>
                      <th>
                        <p class="showname">${u.name}</p>
                        <input type="text" class="form-control" placeholder="${u.name}" name="name" style="display:none;">
                      </th>
                      <th>
                        <p class="sexshow">${u.sex}</p>
                        <p style="display:none;">
                          <label><input name="sex" type="radio" value="male" />男 </label>
                          <label><input name="sex" type="radio" value="female" />女 </label>
                        </p>
                      </th>
                      <th>
                        <p class="showage">${u.age}</p>
                        <input type="number" class="form-control" placeholder="${u.age}" name="age" style="display:none;">
                      </th>
                      <th>${u.id}</th>
                      <th>
                        <p class="showtel">${u.tel}</p>
                        <input type="text" class="form-control" placeholder="${u.tel}" name="tel" style="display:none;">
                      </th>
                      <th>${u.permi}</th>
                      <th>${u.isFingerExist}</th>
                      <th>${u.isFaceExist}</th>
                      <th>
                        <p><a class="btn btn-success setAuthenInfo" role="button">录入多模态信息</a></p>
                        <p class="weightchange" style="display:none;">
                          <a class="btn btn-info weight_change" role="button">对用户提/降权</a>
                        </p>
                       </th>
                    </tr>
                    </c:forEach>

                    <script>
                      var nameShow = document.getElementsByClassName("showname");
                      //var nameInput = document.getElementsByClassName("inputname");
                      for(var i=0;i<nameShow.length;i++){
                          //alert(nameInput[i]);
                          nameShow[i].ondblclick = function(){
                          this.style.display = "none";                          
                          this.nextElementSibling.style.display = "";
                          //alert(this.nextElementSibling.className);
                        }
                      }
                    </script>

                    <script type="text/javascript">
                      var sexShow = document.getElementsByClassName("sexshow");
                      for(var i=0;i<sexShow.length;i++){
                          sexShow[i].ondblclick = function(){
                          this.style.display = "none";                          
                          this.nextElementSibling.style.display = "";
                        }
                      }
                    </script>

                    <script type="text/javascript">
                      var ageShow = document.getElementsByClassName("showage");
                      for(var i=0;i<ageShow.length;i++){
                          ageShow[i].ondblclick = function(){
                          this.style.display = "none";                          
                          this.nextElementSibling.style.display = "";
                        }
                      }
                    </script>

                    <script type="text/javascript">
                      var telShow = document.getElementsByClassName("showtel");
                      for(var i=0;i<telShow.length;i++){
                          telShow[i].ondblclick = function(){
                          this.style.display = "none";                          
                          this.nextElementSibling.style.display = "";
                        }
                      }
                    </script>
                    
                    <script type="text/javascript">
                      var buttons = document.getElementsByClassName("setAuthenInfo");
                      for(var i=0;i<buttons.length;i++){
                          buttons[i].onclick = function(){
                          	var id = this.parentNode.parentNode.previousElementSibling.previousElementSibling.
    		                                      previousElementSibling.previousElementSibling.previousElementSibling.innerText;
    		                var fingerExist = this.parentNode.parentNode.previousElementSibling.previousElementSibling.innerText;
    		                var faceExist = this.parentNode.parentNode.previousElementSibling.innerText;
    		                //获取这个按钮节点父节点的父节点的往上五个兄弟节点中的文本内容
    		                //即这一行的用户的id
    		                
    		                //alert(id);
    		               //以原始方法请求servlet
    		                /*
    		                var xmlhttp;
							if (window.XMLHttpRequest){
    							xmlhttp=new XMLHttpRequest();//  IE7+, Firefox, Chrome, Opera, Safari 
							}
							else{
    							xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");// IE6, IE5
							}
							//这里的路径应当是绝对路径
							var url = "/doAuthenInfo?id="+encodeURIComponent(id);
							//alert(url);
							xmlhttp.open("GET",url,true);
							xmlhttp.onreadystatechange = function(){
								if(xmlhttp.readyState ==0){
									alert("请求未初始化");
								}
								if(xmlhttp.readyState ==1){
									alert("服务器连接已建立");
								}
								if(xmlhttp.readyState ==2){
									alert("请求已接收");
								}
								if(xmlhttp.readyState ==3){
									alert("请求处理中");
								}
								if(xmlhttp.readyState ==4){
									alert("请求已完成，且响应已就绪");
								}	
							}
							xmlhttp.send();
							*/
							
							$.ajax({
								type:"POST",
								url:"http://localhost:8080/BioAuthen/doAuthenInfo",
								//url:"doAuthenInfo",
								dataType:"text",
								data:{"id":id,
										  "finger":fingerExist,
										  "face":faceExist},
								success:function(result){
									//result是后台传来的数据
									//alert(result);
									if(result == "success"){
										if(fingerExist=="是"||faceExist=="是"){
											alert("该用户的验证信息或部分验证信息已经存在，再次录入将更新个人信息");
										}
										window.location.href="authenInfoInput.jsp";
									}
								}
							});
                        }
                      }
                    </script>

                    <script type="text/javascript">
                        var buttons_chg = document.getElementsByClassName("weight_change");
                        for (var i = 0; i < buttons_chg.length; i++) {
                          buttons_chg[i].onclick = function(){
                          	//获取这一行的部分用户信息
                          	var face = this.parentNode.parentNode.previousElementSibling.innerText;
                          	var finger = this.parentNode.parentNode.previousElementSibling.previousElementSibling.innerText;
                          	var permi = this.parentNode.parentNode.previousElementSibling.previousElementSibling.
    		                                      previousElementSibling.innerText;
                          	var uId = this.parentNode.parentNode.previousElementSibling.previousElementSibling.
    		                                      previousElementSibling.previousElementSibling.previousElementSibling.innerText;
    		               //alert(uId+","+permi+","+finger+","+face)
    		                $.ajax({
								type:"POST",
								url:"http://localhost:8080/BioAuthen/changeWeight",
								dataType:"text",
								data:{"id":uId,
										  "permission":permi,
										  "finger":finger,
										  "face":face},
								success:function(result){
									alert(result);
								}
							});
                          }
                        }
                      </script>
                    
                    <c:if test="${not empty messagepermi }">
                      <script type="text/javascript">
                        //alert("您是超级管理员，有对用户提/降权的权限");
                        var weightchangebuttons = document.getElementsByClassName("weightchange");
                        //alert(weightchangebuttons.length);
                        for (var i = 0; i < weightchangebuttons.length; i++) {
                          //alert(weightchangebuttons[i].className);
                          weightchangebuttons[i].style.display = "";
                        }
                      </script>
                    </c:if>

                  </tbody>
                </table>
              </div>

              <c:if test="${not empty msg}">
              	<script>
              		<%
              			String s= request.getAttribute("notify").toString();
              		%>
              		var s = "<%=s%>";
              		alert(s);
              	</script>
              </c:if>
              <!--底部选择按钮-->
              <div class="row choose">
                <div class="col-md-6">
                    <input type="submit"  name="updateSubmit" value="批量提交修改信息" class="btn btn-warning btn-lg" role="button">
                </div>
                <div class="col-md-6">
                    <p id="udelete"><a class="btn btn-danger btn-lg" role="button">批量删除用户信息</a></p>
                </div>
              </div>
            </form>
            <script>
            	var deletebutton = document.getElementById("udelete");
            	deletebutton.onclick=function(){
            		var checkBoxes = document.getElementsByName("choose");
            		var check_val = [];
            		for(var i=0;i<checkBoxes.length;i++){
            			if(checkBoxes[i].checked){
            				check_val.push(checkBoxes[i].value);
            			}
            		}
            		//alert(check_val);
            		var cfmMsg = "真的要删除您所勾选的"+check_val.length+"个用户吗";
            		var r_cfm = confirm(cfmMsg);
            		if (r_cfm){
  						//对用户删除操作
  						$.ajax({
								type:"POST",
								url:"http://localhost:8080/BioAuthen/deleteUsers",
								dataType:"text",
								//traditional:true,
								data:{"checked":check_val.toString()},
								success:function(result){
									alert(result);							
								}
							});
 					}
					else{
  						alert("您取消了删除操作");
  					}
            	}
            </script>


        </div>
    
    
        <!--认证模式-->
        <div class="tab-pane" id="tab-authen">
          <form class="form-horizontal" method="post" id="form_reco" action="${pageContext.request.contextPath }/recogByFinger" enctype="multipart/form-data">   
            <div class="row uploadImg">
                <!--上传指纹信息的图片-->
                <div class="col-md-6 uInfo">
                    <div class="uImg">    
                        <img class="img-circle" src="images/standard10.jpg" >
                    </div>
                    <h2>上传指纹信息</h2>
                    <input type="file" class="file" id="input-1" name="finger">
                </div>

                <!--上传人脸信息的图片-->
                <div class="col-md-6 uInfo" >
                    <div class="uImg">    
                        <img class="img-circle" src="images/standard11.gif" >
                    </div>
                    <h2>上传人脸信息</h2>
                    <!--
                    <input type="file" class="file" id="input-2" name="face">
                    -->
                    <div id="my_camera"></div><br>
            		<input id="snap" type=button value="创建快照" onClick="take_snapshot()" class="btn btn-info btn-sm" role="button">
            		<p>      </p>
					<input id="auto" type="button" value="每秒自动截取" onClick="autoSnap()" class="btn btn-info btn-sm" role="button"><br>
					<div id="results">您截取的照片将在这里显示...</div>
                </div>  
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
            <div class="row buttenAuthen">
                <!-- 
                <input type="submit" name="submit" value="开启认证"  class="btn btn-success btn-lg" role="button">
                -->
                <p><a class="btn btn-success btn-lg" role="button" id="recobyface">开启认证</a></p>
                <script>
    	 			var recobyface = document.getElementById("recobyface");
    	 			recobyface.onclick=function(){
     	 				var img = window.faceimg;
    	 				//alert(img);
    	 				//alert("...");
     	 				//ajax在传输过程中 加号会变成空格 base64里是有加号的 
    					//把+替换成编码 %2B是加号的编码 
     					var realimg = img.replace(/\+/g, "%2B");
      	 				$.ajax({
  							type:"POST",
  							url:"http://localhost:8080/BioAuthen/recogByFace",
  							dataType:"text",
  							data:{"img":img},
  							success:function(result){
 								if(result.toString()!="failed"){
 									var id = result.toString();
									//alert(id);
									//人脸验证通过
 									var form = document.getElementById("form_reco");
 									form.submit();
							
 								}else{
									//人脸验证不通过
 									alert("认证失败！您的识别信息没有匹配项");
 								}
  							}
  						});
					}
    	 	</script>
            </div>
          </form> 
        </div>
    
    </div>

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
    <c:if test="${not empty messagefail }">
      <script>
        alert("认证失败！您的识别信息没有匹配项");
      </script>
    </c:if>
    <c:if test="${not empty messagesuccess }">
      <script>
        <%
          String id = request.getAttribute("id").toString();
          String name = request.getAttribute("name").toString();
          String age = request.getAttribute("age").toString();
          String tel = request.getAttribute("tel").toString();
          String email = request.getAttribute("email").toString();
        %>
        var id = '<%=id%>';
        var name = '<%=name%>';
        var age = '<%=age%>';
        var tel = '<%=tel%>';
        var email = '<%=email%>';
        alert("认证成功！"+'\n'+"身份信息："+'\n'+"身份证号："+id+'\n'+"姓名："+name+'\n'+"年龄："+age+'\n'+"电话"+tel+'\n'+"邮箱"+email);
      </script>
    </c:if>
    
    <c:if test="${not empty addsuccess}">
    	<script>
    		alert("用户身份认证信息添加/更新成功");
    	</script>
    </c:if>
    
    <c:if test="${not empty addfail}">
    	<script>
    		alert("用户身份认证信息添加/更新失败或部分失败，请重新录入");
    	</script>
    </c:if>
</body>
</html>


