<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@  page import="org.account.web.model.TableContext,java.util.*" %>
<%  String[] msg = (String[])session.getAttribute("msg"); %>
<%
	List<TableContext> tables = (List<TableContext>)session.getAttribute("table_info");
	String user_url = (String)session.getAttribute("user_url");
	String table_url = (String)session.getAttribute("table_url");
	String home_url = (String)session.getAttribute("home_url");
	String deplan_url = (String)session.getAttribute("deplan_url");
	
	
	String table_add = (String)session.getAttribute("table_add");
	String table_alter = (String)session.getAttribute("table_alter");
	String table_remove = (String)session.getAttribute("table_remove");
	
	
%>
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
</head>
<body>
  <div class="wrapper"> 
   <div class="sidebar" data-image="../assets/img/sidebar-5.jpg" data-color="<%= (msg==null) ? "purple" : msg[2] %>"> 
    <div class="sidebar-wrapper"> 
     <div class="logo"> 
      <a class="simple-text">Account Manager Pro</a> 
     </div> 
     <ul class="nav"> 
      <li class="nav-item"> <a class="nav-link" href="<%=user_url %>"> <i class="nc-icon nc-circle-09"></i> <p> 用 户 档 案</p> </a> </li> 
      <li> <a class="nav-link" href="<%=table_url %>"> <i class="nc-icon nc-notes"></i> <p> 账 号 列 表</p> </a> </li> 
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
        <li class="dropdown nav-item"> <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown"> <i class="nc-icon nc-planet"></i> <span class="notification">4</span> <span class="d-lg-none">Notification</span> </a> 
         <ul class="dropdown-menu"> 
          <a class="dropdown-item" href="#">Another notification</a> 
         </ul> </li> 
        <li class="nav-item"> <a class="nav-link" href="#pablo"> <span class="no-icon">Account</span> </a> </li> 
        <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <span class="no-icon">相关操作</span> </a> 
         <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> 
          <a class="dropdown-item" href="<%=home_url %>">切换账号</a> 
          <div class="divider"></div> 
          <a class="dropdown-item" href="<%=deplan_url %>">退出账号</a> 
         </div> </li> 
       </ul> 
      </div> 
     </div> 
    </nav> 
    <!-- End Navbar --> 
    <div class="content"> 
     <div class="container-fluid"> 
      <div class="row"> 
       <div class="col-md-12"> 
        <div class="card strpied-tabled-with-hover"> 
         <div class="card-header "> 
          <h4 class="card-title">Striped Table with Hover</h4> 
          <p class="card-category">Here is a subtitle for this table</p> 
         </div> 
		 <a id="table_add" style="display:none" href="#"><%=table_add %></a>
         <a id="table_alter" style="display:none" href="#"><%=table_alter %></a>
         <a id="table_remove" style="display:none" href="#"><%=table_remove %></a>
         <div class="card-body table-full-width table-responsive"> 
          <table id="table" class="table table-hover table-striped"> 
           <thead> 
            <tr>
             <th></th> 
             <th>ID</th> 
             <th>帐号</th> 
             <th >密码</th> 
             <th></th> 
             <th>持有者工号</th> 
			 <th>持有部门</th>
             <th>持有者职位</th> 

             <th>是否有效</th>
             <th>说明</th>
            </tr>
           </thead> 
           <tbody id="table-tbody"> 
            <%for(int i = 0; i < tables.size(); i++) { %>
            	<tr> 
             		<td>
              			<div class="form-check"> 
   			   				<label class="form-check-label"> 
   			    				<input class="form-check-input" type="checkbox" value=""  /> <span class="form-check-sign"></span> 
   			   				</label> 
  			  			</div> 
  			 		</td>
             		<td><%=i+1 %></td> 
             		<td><%=tables.get(i).getAccountNumber()%></td>
             		<td class="password" id="<%=i %>" ><%=tables.get(i).getDeeppwd() %></td>
					<td><i onmousedown="over(<%=i %>)" onmouseup="out(<%=i %>)" class="nc-icon nc-zoom-split"></i></td>
             		<td class="" ><%=tables.get(i).getOwnerNumber()%></td>
             		<td class="" ><%=tables.get(i).getOwnerDepartment() %></td>
             		<td class="" ><%=tables.get(i).getOwnerRole() %></td>

             		<td class="" ><%=tables.get(i).isValid() %></td>
             		<td class="text" ><%=tables.get(i).getExplain() %></td>   
            	</tr> 
            <%} %>   
           </tbody> 
          </table> 
          
         </div> 
        </div> 
        <button class="btn btn-primary btn-fill">添加记录</button> 
        <button class="btn btn-warning btn-fill">保存修改记录</button> 
        <button class="btn btn-danger btn-fill pull-right">删除记录</button> 
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
   <script src="../js/table.js" type="text/javascript"></script> 
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
