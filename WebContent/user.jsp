<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@  page import="org.account.web.viewmodel.User" %>
<%  User user = (User)session.getAttribute("user_info"); %>
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
      <a class="simple-text">Account Manager Pro</a> 
     </div> 
     <ul class="nav"> 
      <li class="nav-item "> <a class="nav-link" href="<%=session.getAttribute("user") %>"> <i class="nc-icon nc-circle-09"></i> <p> 用 户 档 案</p> </a> </li> 
      <li> <a class="nav-link" href="<%=session.getAttribute("table") %>"> <i class="nc-icon nc-notes"></i> <p> 账 号 列 表</p> </a> </li> 
      <li class="nav-item active active-pro"> <a class="nav-link active" href="upgrade.html"> <i class="nc-icon nc-alien-33"></i> <p>Upgrade to PRO</p> </a> </li> 
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
        <li class="dropdown nav-item"> <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown"> <i class="nc-icon nc-planet"></i> <span class="notification">1</span> <span class="d-lg-none">Notification</span> </a> 
         <ul class="dropdown-menu"> 
          <a class="dropdown-item" href="#">Another notification</a> 
         </ul> </li> 
        <li class="nav-item"> <a class="nav-link" href="#pablo"> <span class="no-icon">Account</span> </a> </li> 
        <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <span class="no-icon">相关操作</span> </a> 
         <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> 
          <a class="dropdown-item" href="<%=session.getAttribute("home_url") %>">切换账号</a> 
          <div class="divider"></div> 
          <a class="dropdown-item" href="<%=session.getAttribute("deplan_url") %>">退出账号</a> 
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
        <div class="card"> 
         <div class="card-header"> 
          <h4 class="card-title">档案信息</h4> 
          <p class="card-category">Here is a subtitle for this table</p> 
         </div> 
         <div class="card-body"> 
          <form action="<%=session.getAttribute("update_url") %>"> 
           <div class="row"> 
            <div class="col-md-4 pr-1"> 
             <div class="form-group"> 
              <label>所属公司</label> 
              <input type="text" class="form-control" disabled="" placeholder="Company" name="company" value="<%=user.getCompany() %>"/> 
             </div> 
            </div> 
            <div class="col-md-4 px-1"> 
             <div class="form-group"> 
              <label>登录账号</label> 
              <input type="text" class="form-control" placeholder="Account" name="account" value="<%=user.getAccount() %>"/> 
             </div> 
            </div> 
            <div class="col-md-4 pl-1"> 
             <div class="form-group"> 
              <label>工号</label> 
              <input  class="form-control" placeholder="number" name="number" value="<%=user.getNumber() %>"/> 
             </div> 
            </div> 
           </div> 
           <div class="row"> 
            <div class="col-md-4"> 
             <div class="form-group"> 
              <label>部门</label> 
              <input type="text" class="form-control" placeholder="Department" name="department" value="<%=user.getDepartment() %>"/> 
             </div> 
            </div> 
            <div class="col-md-4"> 
             <div class="form-group"> 
              <label>职位</label> 
              <input type="text" class="form-control" placeholder="Position" name="role" value="<%=user.getRole() %>"/> 
             </div> 
            </div> 
            <div class="col-md-4"> 
             <div class="form-group"> 
              <label>权限</label> 
              <input type="text" class="form-control" placeholder="Permission" name="permission" value="<%=user.getPermissioms() %>"/> 
             </div> 
            </div> 
           </div> 
           <div class="row"> 
            <div class="col-md-4"> 
             <div class="form-group"> 
              <label>登记日期</label> 
              <input type="text" class="form-control" placeholder="Entry Date" name="entryDate" value="<%=user.getEntryDate() %>"/> 
             </div> 
            </div> 
            <div class="col-md-4 pl-1"> 
             <div class="form-group"> 
              <label for="exampleInputEmail1">邮箱</label> 
              <input type="email" class="form-control" placeholder="Email" name="email" value="<%=user.getEmail() %>"/> 
             </div> 
            </div> 
           </div> 
           <div class="row"> 
            <div class="col-md-4 pr-1"> 
             <div class="form-group"> 
              <label>市</label> 
              <input type="text" class="form-control" placeholder="City" name="city" value="<%=user.getCity() %>"/> 
             </div> 
            </div> 
            <div class="col-md-4 px-1"> 
             <div class="form-group"> 
              <label>国家</label> 
              <input type="text" class="form-control" placeholder="Country" name="country"  value="<%=user.getCountry() %>"/> 
             </div> 
            </div> 
            <div class="col-md-4 pl-1"> 
             <div class="form-group"> 
              <label>邮政编码</label> 
              <input type="number" class="form-control" placeholder="ZIP Code" name="zipCode" value="<%=user.getZipCode() %>"/> 
             </div> 
            </div> 
           </div> 
           <div class="row"> 
            <div class="col-md-12"> 
             <div class="form-group"> 
              <label>相关信息</label> 
              <textarea rows="4" cols="80" class="form-control" placeholder="Here can be your description" value="Mike">Lamborghini Mercy, Your chick she so thirsty, I'm in that two seat Lambo.</textarea> 
             </div> 
            </div> 
           </div> 
           <button type="submit" class="btn btn-info btn-fill pull-right">更新信息</button> 
           <div class="clearfix"></div> 
          </form> 
         </div> 
        </div> 
       </div> 
       <div class="col-md-4"> 
        <div class="card card-user"> 
         <div class="card-image"> 
          <img src="https://ununsplash.imgix.net/photo-1431578500526-4d9613015464?fit=crop&amp;fm=jpg&amp;h=300&amp;q=75&amp;w=400" alt="..." /> 
         </div> 
         <div class="card-body"> 
          <div class="author"> 
           <a href="#"> <img class="avatar border-gray" src="../assets/img/faces/face-3.jpg" alt="..." /> <h5 class="title">Mike Andrew</h5> </a> 
           <p class="description"> michael24 </p> 
          </div> 
          <p class="description text-center"> &quot;Lamborghini Mercy <br /> Your chick she so thirsty <br /> I'm in that two seat Lambo&quot; </p> 
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
