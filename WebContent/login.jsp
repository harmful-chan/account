<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="../assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
    <!-- CSS Files -->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="../assets/css/light-bootstrap-dashboard.css?v=2.0.0 " rel="stylesheet" />
	<title>19331503261陈汉锋</title>
</head>
 <body>
  <div class="wrapper"> 
   <div class="sidebar" data-image="../assets/img/sidebar-5.jpg"> 
    <div class="sidebar-wrapper"> 
     <div class="logo"> 
      <a class="simple-text"> 账 号 管 理 系 统 </a> 
     </div> 
     <ul class="nav"> 
      <li class="nav-item "> <a class="nav-link"> <i class="nc-icon nc-circle-09"></i> <p> 用 户 档 案</p> </a> </li> 
      <li> <a class="nav-link"> <i class="nc-icon nc-notes"></i> <p> 账 号 列 表</p> </a> </li> 
      <li class="nav-item active active-pro"> <a class="nav-link active"> <i class="nc-icon nc-alien-33"></i> <p>Upgrade to PRO</p> </a> </li> 
     </ul> 
    </div> 
   </div> 
   <div class="main-panel"> 
    <!-- Navbar --> 
    <nav class="navbar navbar-expand-lg " color-on-scroll="500"> 
     <div class="container-fluid"> 
      <button href="" class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-bar burger-lines"></span> <span class="navbar-toggler-bar burger-lines"></span> <span class="navbar-toggler-bar burger-lines"></span> </button> 
      <div class="collapse navbar-collapse justify-content-end" id="navigation"> 
       <ul class="navbar-nav ml-auto"> 
        <li class="nav-item"> <a href="#" class="nav-link" data-toggle="dropdown"> <i class="nc-icon nc-palette"></i> <span class="d-lg-none">Dashboard</span> </a> </li> 
        <li class="dropdown nav-item"> <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown"> <i class="nc-icon nc-planet"></i> <span class="notification">5</span> <span class="d-lg-none">Notification</span> </a> 
         <ul class="dropdown-menu"> 
          <a class="dropdown-item" href="#">Notification 1</a> 
          <a class="dropdown-item" href="#">Notification 2</a> 
          <a class="dropdown-item" href="#">Notification 3</a> 
          <a class="dropdown-item" href="#">Notification 4</a> 
          <a class="dropdown-item" href="#">Another notification</a> 
         </ul> </li> 
        <li class="nav-item"> <a class="nav-link" href="#pablo"> <span class="no-icon">Account</span> </a> </li> 
        <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <span class="no-icon">相关操作</span> </a> 
         <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> 
          <a class="dropdown-item" href="#">切换账号</a> 
          <a class="dropdown-item" href="#">重新登录</a> 
          <div class="divider"></div> 
          <a class="dropdown-item" href="#">退出账号</a> 
         </div> </li> 
       </ul> 
      </div> 
     </div> 
    </nav> 
    <!-- End Navbar --> 
    <div class="content"> 
     <div class="container-fluid"> 
      <div class="row"> 
       <div class="col-md-8"> 
        <div class="card "> 
         <div class="card-header "> 
          <h4 class="card-title">Email Statistics</h4> 
          <p class="card-category">Last Campaign Performance</p> 
         </div> 
         <div class="card-body "> 
          <div class="row"> 
           <div class="col-md-4 "></div> 
           <div class="col-md-4 ">
            <img src="../images/lock.png" />
           </div> 
           <div class="col-md-4 "></div> 
          </div> 
          <br /> 
          <br /> 
          <div class="typography-line"> 
           <h3>Quote</h3> 
           <blockquote> 
            <p class="blockquote blockquote-primary"> &quot;I will be the leader of a company that ends up being worth billions of dollars, because I got the answers. I understand culture. I am the nucleus. I think that’s a responsibility that I have, to push possibilities, to show people, this is the level that things could be at.&quot; <br /> <br /> <small> - Noaa </small> </p> 
           </blockquote> 
          </div> 
         </div> 
        </div> 
       </div> 
       <div class="col-md-4"> 
        <div class="card "> 
         <div class="card-header "> 
          <h4 class="card-title">User Infomation</h4> 
          <p class="card-category">账号30天有效</p> 
         </div> 
         <div class="card-body "> 
          <form> 
           <div class="form-group"> 
            <label for="exampleInputEmail1">工号</label> 
            <input type="email" class="form-control" id="exampleInputEmail1" placeholder="number" /> 
           </div> 
           <div class="form-group"> 
            <div class="form-group"> 
             <label for="exampleInputEmail1">账号</label> 
             <input type="email" class="form-control" id="exampleInputEmail1" placeholder="account" /> 
            </div> 
            <div class="form-group"> 
             <label for="exampleInputPassword1">密码</label> 
             <input type="password" class="form-control" id="exampleInputPassword1" placeholder="password" /> 
            </div> 
            <div class="checkbox"> 
             <label> <input type="checkbox" /> 记住密码 </label> 
            </div> 
            <button type="submit" class="btn btn-default">登录</button>  
           </div>
          </form> 
          <div class="card-footer "> 
           <div class="legend"> 
            <i class="fa fa-circle text-info"></i> Open 
            <i class="fa fa-circle text-danger"></i> Click 
            <i class="fa fa-circle text-warning"></i> Click Second Time 
           </div> 
           <hr /> 
           <div class="stats"> 
            <i class="fa fa-history"></i> Login 3 hours ago 
           </div> 
          </div> 
         </div> 
        </div> 
       </div> 
      </div> 
     </div> 
     <footer class="footer"> 
      <div class="container-fluid"> 
       <nav> 
        <ul class="footer-menu"> 
         <li> <a href="#"> Home </a> </li> 
         <li> <a href="#"> Company </a> </li> 
         <li> <a href="#"> Portfolio </a> </li> 
         <li> <a href="#"> Blog </a> </li> 
        </ul> 
        <p class="copyright text-center"> &copy; <script>
                                   document.write(new Date().getFullYear())
                               </script> <a href="http://www.creative-tim.com">Creative Tim</a>, made with love for a better web </p> 
       </nav> 
      </div> 
     </footer> 
    </div> 
   </div> 
  </div>
 </body>

  <script src="../assets/js/core/jquery.3.2.1.min.js" type="text/javascript"></script> 
  <script src="../assets/js/core/popper.min.js" type="text/javascript"></script> 
  <script src="../assets/js/core/bootstrap.min.js" type="text/javascript"></script> 
  <!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ --> 
  <script src="../assets/js/plugins/bootstrap-switch.js"></script> 
  <!--  Chartist Plugin  --> 
  <script src="../assets/js/plugins/chartist.min.js"></script> 
  <!-- Control Center for Light Bootstrap Dashboard: scripts for the example pages etc --> 
  <script src="../assets/js/light-bootstrap-dashboard.js?v=2.0.0 " type="text/javascript"></script>

</html>