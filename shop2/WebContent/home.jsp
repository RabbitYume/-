<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Home</title>
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
    <link rel="stylesheet" href="css/style.blue.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.png?3">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
  </head>
  <body>
    <!-- navbar-->
    <header class="header">
      <nav class="navbar navbar-expand-lg px-4 py-2 bg-white shadow"><a href="#" class="sidebar-toggler text-gray-500 mr-4 mr-lg-5 lead"><i class="fas fa-align-left"></i></a><a href="home.jsp" class="navbar-brand font-weight-bold text-uppercase text-base">商品销售管理</a>
        <ul class="ml-auto d-flex align-items-center list-unstyled mb-0">
          <li>
          <span id="userInfo" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle">用户<%=session.getAttribute("pid")%></span>
          </li>
          <li class="nav-item dropdown ml-auto">
              <a href="${pageContext.request.contextPath}/LogoutServlet" class="dropdown-item">Logout</a>
          </li>
        </ul>
      </nav>
    </header>
    <div class="d-flex align-items-stretch">
      <div id="sidebar" class="sidebar py-3">
        <div class="text-gray-400 text-uppercase px-3 px-lg-4 py-4 font-weight-bold small headings-font-family">MAIN</div>
        <ul class="sidebar-menu list-unstyled">
              <li class="sidebar-list-item"><a href="home.jsp" class="sidebar-link text-muted active"><i class="o-home-1 mr-3 text-gray"></i><span>主页</span></a></li>
			  <li class="sidebar-list-item"><a href="javascript:void(0);" onclick="sale(<%=session.getAttribute("q")%>)" class="sidebar-link text-muted"><i class="o-imac-screen-1 mr-3 text-gray"></i><span>销售</span></a></li>
              <li class="sidebar-list-item"><a href="javascript:void(0);" onclick="salerecord(<%=session.getAttribute("q")%>)" class="sidebar-link text-muted"><i class="o-sales-up-1 mr-3 text-gray"></i><span>销售记录</span></a></li>
              <li class="sidebar-list-item"><a href="javascript:void(0);" onclick="goods(<%=session.getAttribute("q")%>)" class="sidebar-link text-muted"><i class="o-table-content-1 mr-3 text-gray"></i><span>商品管理</span></a></li>
			  <li class="sidebar-list-item"><a href="javascript:void(0);" onclick="kucun(<%=session.getAttribute("q")%>)" class="sidebar-link text-muted"><i class="o-database-1 mr-3 text-gray"></i><span>库存管理</span></a></li>
              <li class="sidebar-list-item"><a href="javascript:void(0);" onclick="supplier(<%=session.getAttribute("q")%>)" class="sidebar-link text-muted"><i class="o-survey-1 mr-3 text-gray"></i><span>供货商管理</span></a></li>
              <li class="sidebar-list-item"><a href="javascript:void(0);" onclick="quanxian(<%=session.getAttribute("q")%>)" class="sidebar-link text-muted"><i class="o-exit-1 mr-3 text-gray"></i><span>员工管理</span></a></li>
        </ul>
      </div>
      <div class="page-holder w-100 d-flex flex-wrap">
        <div class="container-fluid px-xl-5">
        <section class="py-5">
          </section>
          <section>
            <div class="row mb-4">
              <div class="col-lg-7 mb-4 mb-lg-0">
                <div class="card">
                  <div class="card-header">
                    <h2 class="h6 text-uppercase mb-0">Current server uptime</h2>
                  </div>
                  <div class="card-body">
                    <p class="text-gray">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                    <div class="chart-holder">
                      <canvas id="lineChart1" style="max-height: 14rem !important;" class="w-100"></canvas>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-lg-5 mb-4 mb-lg-0 pl-lg-0">
                <div class="card mb-3">
                  <div class="card-body">
                    <div class="row align-items-center flex-row">
                      <div class="col-lg-5">
                        <h2 class="mb-0 d-flex align-items-center"><span>86.4</span><span class="dot bg-green d-inline-block ml-3"></span></h2><span class="text-muted text-uppercase small">Work hours</span>
                        <hr><small class="text-muted">Lorem ipsum dolor sit</small>
                      </div>
                      <div class="col-lg-7">
                        <canvas id="pieChartHome1"></canvas>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="card">
                  <div class="card-body">
                    <div class="row align-items-center flex-row">
                      <div class="col-lg-5">
                        <h2 class="mb-0 d-flex align-items-center"><span>1.724</span><span class="dot bg-violet d-inline-block ml-3"></span></h2><span class="text-muted text-uppercase small">Server time</span>
                        <hr><small class="text-muted">Lorem ipsum dolor sit</small>
                      </div>
                      <div class="col-lg-7">
                        <canvas id="pieChartHome2"></canvas>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-lg-5 mb-4 mb-lg-0">
                <div class="card mb-3">
                  <div class="card-body">
                    <div class="row align-items-center mb-3 flex-row">
                      <div class="col-lg-5">
                        <h2 class="mb-0 d-flex align-items-center"><span>86%</span><span class="dot bg-violet d-inline-block ml-3"></span></h2><span class="text-muted text-uppercase small">Monthly Usage</span>
                        <hr><small class="text-muted">Lorem ipsum dolor sit</small>
                      </div>
                      <div class="col-lg-7">
                        <canvas id="pieChartHome3"></canvas>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="card">
                  <div class="card-body">
                    <div class="row align-items-center flex-row">
                      <div class="col-lg-5">
                        <h2 class="mb-0 d-flex align-items-center"><span>$126,41</span><span class="dot bg-green d-inline-block ml-3"></span></h2><span class="text-muted text-uppercase small">All Income</span>
                        <hr><small class="text-muted">Lorem ipsum dolor sit</small>
                      </div>
                      <div class="col-lg-7">
                        <canvas id="pieChartHome4"></canvas>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-lg-7">
                <div class="card">
                  <div class="card-header">
                    <h2 class="h6 text-uppercase mb-0">Total Overdue</h2>
                  </div>
                  <div class="card-body">
                    <p class="text-gray">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                    <div class="chart-holder">
                      <canvas id="lineChart2" style="max-height: 14rem !important;" class="w-100"></canvas>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
          <section class="py-5">
          </section>
        </div>
        <footer class="footer bg-white shadow align-self-end py-3 px-xl-5 w-100">
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-6 text-center text-md-left text-primary">
                <p class="mb-2 mb-md-0"></p>
              </div>
              <div class="col-md-6 text-center text-md-right text-gray-400">
                <p class="mb-0">Copyright &copy; 2019.Company name All rights reserved.More Templates </p>
              </div>
            </div>
          </div>
        </footer>
      </div>
    </div>
    <!-- JavaScript files-->
    <script src="js/jquery.min.js"></script>
    <script src="vendor/popper.js/umd/popper.min.js"> </script>
    <script src="js/bootstrap.min.js"></script>
    <script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="js/Chart.min.js"></script>
    <script src="js/js.cookie.min.js"></script>
    <script src="js/charts-home.js"></script>
    <script src="js/front.js"></script>
</body>
  <script>
   	function quanxian(val){ //员工
   		if(val == 1){
   			location.href="index.jsp";
   		}else {
   			alert("没有权限查看");
   		}
   	}
   	function sale(val){ //销售
   		if(val == 1){
   			location.href="zhuye.jsp";
   		}else if(val == 3){
   			location.href = "zhuye.jsp";
   		}else {
   			alert("没有权限查看");
   		}
   	}
   	function salerecord(val){ //销售记录
   		if(val == 1){
   			location.href="salesindex.jsp";
   		}else if(val == 3){
   			location.href = "salesindex.jsp";
   		}else {
   			alert("没有权限查看");
   		}
   	}
   	function goods(val){ //商品
   		if(val == 1){
   			location.href="goodsindex.jsp";
   		}else if(val == 2){
   			location.href = "goodsindex.jsp";
   		}else {
   			alert("没有权限查看");
   		}
   	}
   	function kucun(val){ //库存
   		if(val == 1){
   			location.href="stockindex.jsp";
   		}else if(val == 2){
   			location.href = "stockindex.jsp";
   		}else {
   			alert("没有权限查看");
   		}
   	}
   	function supplier(val){ //供货商
   		if(val == 1){
   			location.href="supplierindex.jsp";
   		}else if(val == 2){
   			location.href = "supplierindex.jsp";
   		}else {
   			alert("没有权限查看");
   		}
   	}
   </script>
</html>