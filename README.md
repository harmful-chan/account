## 概述
项目整体设计为一个公司内部账号管理系统，管理登录账号，内部账号，能够更好的突出权限管理的重要性。

## 目录
```
src/org/account
|-  orm
|   |-  bean    //数据库实体模型，hibenate映射文件
|   |-  impl    //数据库表操作实现（已启用）
|	  |-  services    //信息操作，加解密，活动管理，日志记录等服务
|		    |-  test   //单元测试
|-  util    //Hibernate操作静态工作类
|-  web
|   |-  action    //业务逻辑实现
|   |-  bean    //客户端服务器数据传输模型，struts2验证器配置文件
|   |-  interceptor    //业务过滤器	
|-  hibernate.cfg.xml    // hibernate配置文件
|-  struts.cml    //struts配置文件
WebContent
|-  assetts    //前端框架所需文件
|-  js    //加解密方法等js脚本
|-  home.jsp    //首页
|-  table.jsp    //权限账号显示页面
|-  user.jsp    //员工信息页面
|-  welcome.jsp    //欢迎页面
```

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

## 系统设计

+  业务流程图
![业务流程图](http://assets.processon.com/chart_image/5df64a4ee4b004cc9a30f504.png?_=1577103926668)

+ [数据库设计](https://github.com/harmful-chan/account/tree/1.0/src/org/account/orm/bean)
![数据库设计](http://assets.processon.com/chart_image/5ddc9984e4b0fcce5b59e5c1.png?_=1577104062968)
  <br>角色（role）：一个角色对应多个权限。分四个等级，roor、admin、governer、normal。
  <br>权限（permission）：一个权限对应一个或多个资源。权限码（code）由实际情况确定，一般安全等级越高，数值越高。
  <br>资源（node）：一个资源对应公司一个访问url。
  <br>员工（staff）：一个员工对应一个部门一个职位一个登录账号。
  <br>部门（department）：一个部门对应多个员工、多个普通账号。

+ [加解密](https://github.com/harmful-chan/account/blob/1.0/src/org/account/orm/services/SecretServer.java)
![加解密](http://assets.processon.com/chart_image/5df9f4b1e4b06c8b0bb37c55.png?_=1577103843644)
 <br>算法：Base64
 <br>如：当前时间为yyyy年MM月dd日hh点mm分ss秒fff毫秒
 <br>传输字符串 = Base64(password+yyyyMMddhhmmssfff)
 <br>账号有效性：通过密钥可以确定建立账号的时间，如果建立时间与当前时间比较超过30天，则账号不可用（仅针对登陆账号）。

+ [拦截器设计](https://github.com/harmful-chan/account/blob/1.0/src/org/account/web/interceptor/IdentityInterceptor.java)
![拦截器设计](http://assets.processon.com/chart_image/5df99a66e4b0a9c790f20c5f.png?_=1577104279687)
  <br>主要功能
  <br>初始化数据库：只执行一次，检测目标数据库是否有业务数据，如果为空会自动添加数据。
  <br>服务初始化：初始化StaffInfoServer、ResourceServer、ActiveServer等服务，并通过接口函数IactionBase.setxxxServer(服务对象)把服务注入到相应的控制器中。
  <br>请求资源合法判断：获取请求url，判断资源是共有或私有资源，错误则返回home页面
  <br>请求资源权限判断：根据业务数据判断请求资源是否权限内。
  <br>活动员工管理：获取员工数据，并设置为当前登录员工，数据供Action使用。

+ 公司部门角色权限关系
![公司部门角色权限关系](http://assets.processon.com/chart_image/5de8a0dde4b0df12b4b7532f.png?_=1577088556096)

+ 公司权限资源关系
![公司权限资源关系](http://assets.processon.com/chart_image/5ddf829be4b0b2fab73a007d.png?_=1577088579299)




## 部署
+ 前端：Light Bootstrap Dashboard
+ 后端：hibernate3.6.10，struts2.3.34，sqljdbc4
+ Web服务托管：Apache Tomcat 9 x64
+ 数据库：SQL Server2008 R2 x64
+ 服务器；阿里云Ubuntu16.04TSL x64
+ 演示地址：http://47.94.162.230/account/Home/home


