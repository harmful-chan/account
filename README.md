# account

## 概述
项目整体设计为一个公司内部账号管理系统，能够更好的突出权限管理的重要性。

## 设计要点
  + 能统一管理公司内部所有账号
  + 登录人员能修改个人信息
  + 对登录人员身份进行验证
  + 非公司内部人员不能随意访问网站
  + 要防止第三方捕获api访问数据

## 功能分析
+ 权限管理：对访问服务器http请求url进行过滤，非允许url统一拒绝访问。
  <br>使用struts2框架能有效达到输入数据验证，http请求过滤等功能。

+ 个人信息管理：提供个人信息查看和修改功能，不能进行添加或者删除。

+ 内部账号管理：根据不同登录员工身份，允许查看权限内的账号。

+ 防捕获：对http请求中重要数据进行加密。
  <br>表单输入密码添加内部密钥进行md5加密处理，在服务器端进行相同解密处理，验证数据。

+ 数据保存：需要使用高性能、高可用、性能稳定的数据库。
  <br>选用SQL Server 2008 R2

+ 数据持久化：用于服务器应用与数据库交互，性能和是否便于使用很大程度决定了系统的好坏。
  <br>选用Hibernate+jdbc实现

## 实现细节
+ 数据库设计
[点击查看UML](https://www.processon.com/view/link/5ddc9984e4b07f8de34221ac)
<br>账号表：账号，密码，密钥，可用部门，可用人员
<br>角色表：角色，可用权限
<br>权限表：权限码，说明，对应资源
<br>资源表：url，是否私有，对应权限

+ 资源权限关系
[点击查看UML](https://www.processon.com/view/link/5de27d32e4b00e6d90104f34)

+ 类结构
[点击查看UML](https://www.processon.com/view/link/5dddecf6e4b01291734556b8)

+ 过滤器设计
[点击查看UML](https://www.processon.com/view/link/5ddcae0be4b07f8de342777f)

+  业务流程图
[点击查看UML](https://www.processon.com/view/link/5dde373ae4b0e2c2988ff8c7)
