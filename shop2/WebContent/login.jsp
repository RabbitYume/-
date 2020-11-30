<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>

<meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="https://ajax.aspnetcdn.com/ajax/bootstrap/4.2.1/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!-- Google fonts - Popppins for copy-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,800">
    <!-- orion icons-->
    <link rel="stylesheet" href="css/orionicons.css">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.png?3">
    
<title>登录</title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js">
</script>
<meta content="MSHTML 6.00.2600.0" name=GENERATOR>
<script>
// 判断是登录账号和密码是否为空
function check(){
	var userid = $("#userid").val();
    var password = $("#password").val();
    if(userid=="" || password==""){
    	$("#message").text("账号或密码不能为空！");
        return false;
    }else{
    	return true;
    }
}
</script>
</head>
<body>
<div class="page-holder d-flex align-items-center">
      <div class="container">
        <div class="row align-items-center py-5">
          <div class="col-5 col-lg-7 mx-auto mb-5 mb-lg-0">
            <div class="pr-lg-5"><img src="img/illustration.svg" alt="" class="img-fluid"></div>
          </div>
          <div class="col-lg-5 px-lg-4">
            <h1 class="text-base text-primary text-uppercase mb-4">商品销售管理系统</h1>
            <h2 class="mb-4">Welcome!</h2>
            <p class="text-muted">请登录</p>
            <form id="loginForm" action="${pageContext.request.contextPath }/LoginServlet" method="post" onsubmit="return check()" class="mt-4">
              <div class="form-group mb-4">
                <input id="userid" type="text" name="userid" placeholder="Userid" class="form-control border-0 shadow form-control-lg" />
              </div>
              <div class="form-group mb-4">
                <input id="password" type="password" name="password" placeholder="Password" class="form-control border-0 shadow form-control-lg text-violet"/>
              </div>
              <!--<div class="form-group mb-4">
                <div class="custom-control custom-checkbox">
                  <input id="customCheck1" type="checkbox" checked class="custom-control-input">
                  <label for="customCheck1" class="custom-control-label">Remember Me</label>
                </div>
              </div>-->
              <input type="submit" value="登录" class="btn btn-primary shadow px-5"/>
            </form>
          </div>
        </div>
        <p class="mt-5 mb-0 text-gray-400 text-center">Copyright &copy; 2019.Company name All rights reserved.More Templates & Your company</p>
        <!-- Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)                 -->
      </div>
    </div>
    <!-- JavaScript files-->
    <script src="js/jquery.min.js"></script>
    <script src="vendor/popper.js/umd/popper.min.js"> </script>
    <script src="js/bootstrap.min.js"></script>
    <script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="js/Chart.min.js"></script>
    <script src="js/js.cookie.min.js"></script>
    <script src="js/front.js"></script>
</body>
</html>
