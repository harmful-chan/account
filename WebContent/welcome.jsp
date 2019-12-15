<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@  page import="org.account.web.bean.HomeContext,java.util.*, org.account.orm.bean.*" %>
<%
	String login_url = (String)session.getAttribute("login_url"); 
	String user_url = (String)session.getAttribute("user_url");
	String table_url = (String)session.getAttribute("table_url");
	String home_url = (String)session.getAttribute("home_url");
	String deplan_url = (String)session.getAttribute("deplan_url");
	String[] msg = (String[])session.getAttribute("msg"); 
	List<Staff> test_hold_staffs = (List<Staff>)session.getAttribute("test_hold_staffs"); 
	List<Staff> test_not_hold_staffs = (List<Staff>)session.getAttribute("test_not_hold_staffs"); 
	
	HomeContext home = (HomeContext)session.getAttribute("login_info");
%>
<%   %>
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
    <script src="https://github.com/dankogai/js-base64/blob/master/base64.js"></script>
	<s:head/>
	<title>19331503261陈汉锋</title>
</head>
<body>
    <div class="wrapper">
        <div class="sidebar" data-image="../assets/img/sidebar-5.jpg" data-color="<%= (msg==null) ? "purple " : msg[2] %>">
            <div class="sidebar-wrapper">
                <div class="logo">
                    <a class="simple-text">Account Manager Pro</a>
                </div>
                <ul class="nav">
                    <li class="nav-item ">
                        <a class="nav-link" href="<%=user_url %>">
                            <i class="nc-icon nc-circle-09"></i>
                            <p>Information</p>
                        </a>
                    </li>
                    <li>
                        <a class="nav-link" href="<%=table_url %>">
                            <i class="nc-icon nc-notes"></i>
                            <p>Account List</p>
                        </a>
                    </li>
                    <li class="nav-item active active-pro">
                        <a class="nav-link active" href="upgrade.html">
                            <i class="nc-icon nc-alien-33"></i>
                            <p>Upgrade to PRO</p>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="main-panel">
            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg " color-on-scroll="500">
                <div class="container-fluid">
                    <button href="" class="navbar-toggler navbar-toggler-right" type="button"
                    data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false"
                    aria-label="Toggle navigation">
                        <span class="navbar-toggler-bar burger-lines">
                        </span>
                        <span class="navbar-toggler-bar burger-lines">
                        </span>
                        <span class="navbar-toggler-bar burger-lines">
                        </span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-end" id="navigation">
                        <ul class="nav navbar-nav mr-auto">
                            <li class="dropdown nav-item">
                                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown"> <i class="nc-icon nc-planet"></i>  <span class="notification"><%=test_hold_staffs.size() %></span>  <span class="d-lg-none">Notification</span> </a>
                                <ul class="dropdown-menu">
                                    <% for(int i=0 ; i < test_hold_staffs.size(); i++) { %>
                                        <a class="dropdown-item" href="#"><%="["+test_hold_staffs.get(i).getDepartment().getExplain()+ "-"+test_hold_staffs.get(i).getRole().getExplain()+ "]---[工号:"+test_hold_staffs.get(i).getNumber()+ " 登录账号:"+test_hold_staffs.get(i).getAccount().getNumber()+ " 登录密码:"+test_hold_staffs.get(i).getAccount().getPassword()+"]" %></a>
                                    <% } %>
                                </ul>
                            </li>
							                            <li class="dropdown nav-item">
                                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown"> 
									<i class="nc-icon nc-alien-33" ></i> 
									<span class="notification"><%=test_not_hold_staffs.size() %></span>  
									<span class="d-lg-none">Notification</span>
								</a>
                                <ul class="dropdown-menu">
                                    <% for(int i=0 ; i < test_not_hold_staffs.size(); i++) { %>
                                        <a class="dropdown-item" href="#"><%="["+test_not_hold_staffs.get(i).getDepartment().getExplain()+ "-"+test_not_hold_staffs.get(i).getRole().getExplain()+ "]---[工号:"+test_not_hold_staffs.get(i).getNumber()+"]" %></a>
                                    <% } %>
                                </ul>
                            </li>
                        </ul>
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <a href="#" class="nav-link" data-toggle="dropdown"> <i class="nc-icon nc-palette"></i>  <span class="d-lg-none">Dashboard</span> </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#pablo"> <span class="no-icon">Account</span> </a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <span class="no-icon">相关操作</span> 
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="<%=home_url %>">切换账号</a> 
									<div class="divider"></div> 
									<a class="dropdown-item" href="<%=deplan_url %>">退出账号</a> 
                                </div>
                            </li>
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
                                    <h4 class="card-title">
                                        Account Manager Pro
                                    </h4>
                                    <p class="card-category">
                                        Last Campaign Performance
                                    </p>
                                </div>
                                <div class="card-body ">
                                    <br />
                                    <div class="typography-line">
                                        <blockquote>
                                            <p class="blockquote blockquote-primary">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&quot;Account Manager Pro是一个面向企业的密码安全管理软件，用于全面管理服务器、网络设备、数据库以及各种应用程序的密码，以及各种系统账号、文档、数字证书等。帮助集中存储密码信息、安全共享密码、实施标准的密码策略、跟踪密码访问历史、控制用户非法使用，实现企业密码的安全管理和使用。.&quot;
                                                <br />
                                                <br />
                                                <small>
                                                    - Noaa
                                                </small>
                                            </p>
                                        </blockquote>
                                        <div class="alert alert-<%=msg[0] %>">
                                            <button type="button" aria-hidden="true" class="close" data-dismiss="alert">
                                                <i class="nc-icon nc-simple-remove">
                                                </i>
                                            </button>
                                            <span>
                                                <b>
                                                    <%=msg[0] %>
                                                        -
                                                </b>
                                                <%=msg[1] %>
                                            </span>
                                        </div>
                                        <s:fielderror />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card ">
                                <div class="card-header ">
                                    <h4 class="card-title">
                                        User Infomation
                                    </h4>
                                    <p class="card-category">
                                        账号30天有效
                                    </p>
                                </div>
                                <div class="card-body ">
                                    <form "> 
                                    <div class="form-group "> 
                                    <label for="exampleInputEmail1 ">工号</label> 
                                    <input type="test " class="form-control " value="<%=home.getOperator() %>
                                        " disabled=""/>
                                </div>
                                <div class="form-group">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">
                                            账号
                                        </label>
                                        <input type="test" class="form-control" value="<%=home.getAccountNumber() %>"
                                        disabled="" />
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">
                                            密码
                                        </label>
                                        <input type="password" class="form-control" id="form_password" value="<%=home.getDeeppwd() %>"
                                        disabled="" />
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" />
                                            记住密码
                                        </label>
                                    </div>
                                    <button class="btn btn-default btn-fill pull-right">
                                        已登录
                                    </button>
                                </div>
                                </form>
                                <div class="card-footer ">
                                    <div class="legend">
                                        <i class="fa fa-circle text-info">
                                        </i>
                                        Open
                                        <i class="fa fa-circle text-danger">
                                        </i>
                                        Click
                                        <i class="fa fa-circle text-warning">
                                        </i>
                                        Click Second Time
                                    </div>
                                    <hr />
                                    <div class="stats">
                                        <i class="fa fa-history">
                                        </i>
                                        Login 3 hours ago
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
						<p class="copyright text-center">&copy;
							<script>
								document.write(new Date().getFullYear())
							</script>
							<a href="http://www.creative-tim.com">Creative Tim</a>, made with love for a better web
						</p>
					</nav>
				</div>
            </footer>
        </div>
    </div>
    </div>
 </body>
  <script>
  </script>
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