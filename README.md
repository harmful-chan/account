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
![数据库设计](https://www.processon.com/embed/5ddc9984e4b0fcce5b59e5be)
  <br>角色（role）：分四个等级，roor、admin、一个角色对应多个或多个权限。
  <br>权限（permission）：权限码（code）由实际情况确定，一般安全等级越高，数值越高，一个权限对应一个或多个资源。作为角色与资源的连接桥梁
  <br>资源（node）：由公司实际情况确定，一般一个资源对应公司一个访问url。
  <br>员工（staff）：一个员工对应一个部门‘一个职位、一个登录账号。
  <br>部门（department）：一个部门对应多个员工、多个普通账号。

+ 加解密
![加解密](https://www.processon.com/embed/5df9f4b1e4b06c8b0bb37c52)
 <br>算法：Base64
 <br>如：当前时间为yyyy年MM月dd日hh点mm分ss秒fff毫秒
 <br>传输字符串 = Base64(password+yyyyMMddhhmmssfff)
 <br>账号有效性：通过密钥可以确定建立账号的时间，如果建立时间与当前时间比较超过30天，则账号不可用（仅针对登陆账号）。

+ 拦截器设计
![拦截器设计](https://www.processon.com/embed/5df99a66e4b0a9c790f20c5c)
  <br>主要功能
  <br>初始化数据库：只执行一次，检测目标数据库是否有业务数据，如果为空会自动添加数据。
  <br>服务初始化：初始化StaffInfoServer、ResourceServer、ActiveServer等服务，并通过接口函数IactionBase.setxxxServer(服务对象)把服务注入到相应的控制器中。
  <br>请求资源合法判断：获取请求url，判断资源是共有或私有资源，错误则返回home页面
  <br>请求资源权限判断：根据业务数据判断请求资源是否权限内。
  <br>活动员工管理：获取员工数据，并设置为当前登录员工，数据供Action使用。


+ 公司组织架构关系
![公司组织架构关系](https://www.processon.com/embed/5de8a0dde4b0df12b4b7532c)

+ 公司角色权限资源对照
![公司角色权限资源对照](https://www.processon.com/embed/5ddf829be4b0b2fab73a007a)

+  业务流程图
![业务流程图](https://www.processon.com/embed/5df64a4ee4b004cc9a30f4ff)
