use test;
--账号表
create table sys_account(
    "id" int primary key identity,
    "account"  varchar(16) not null,
    "password"  varchar(16) not null,
    "salf" char(8) not null,
    "explain"  text default null
);

--角色表
create table sys_role(
    "id" int primary key identity,
    "role"  varchar(max) not null,
    "explain"  text default null
);

--权限表
create table sys_permission(
    "id" int primary key identity,
    "permission"  int not null,
    "explain"  text default null
);

--url节点表
create table sys_node(
    "id" int primary key identity,
    "node"  varchar(max) not null,
    "private" bit not null
);


--员工表
create table cmp_staff(
    "id" int primary key identity,
    "number"    char(10)    not null,
    "firstName"    nvarchar(10)    not null,
    "lastName"    nvarchar(10)    not null,
    "city"    nvarchar(10)    not null,
    "province"    nvarchar(10)    not null,
    "country"    nvarchar(10)    not null,
    "zipCode"    varchar(10)    not null,
    "entryDate"    datetime    not null,
    "departmentId"    int    not null,
    "roleId"     int    not null
);

--部门表
create table cmp_department(
    "id" int primary key identity,
    "department"    nvarchar(10)    not null,
    "explain"    nvarchar(10)    default null,
);



--默认角色
insert into sys_role("role", "explain") values ('normal', '普通员工');
insert into sys_role("role", "explain") values ('governor', '部门主管');
insert into sys_role("role", "explain") values ('administrator', '后台管理员');
insert into sys_role("role", "explain") values ('root', '最高管理员');

--默认操作码
insert into sys_permission("permission", explain) values (10,  '允许查看普通账号、密码');
insert into sys_permission("permission", explain) values (20,  '允许插入普通账号');
insert into sys_permission("permission", explain) values (30,  '允许修改普通账号的密码');
insert into sys_permission("permission", explain) values (40,  '允许删除普通账号');
insert into sys_permission("permission", explain) values (50,  '允许查看管理账号、密码');
insert into sys_permission("permission", explain) values (60,  '允许插入管理账号');
insert into sys_permission("permission", explain) values (70,  '允许修改管理账号的密码');
insert into sys_permission("permission", explain) values (80,  '允许删除管理账号');
insert into sys_permission("permission", explain) values (90,  '允许查看全部账号、密码');
insert into sys_permission("permission", explain) values (100, '允许插入全部账号');
insert into sys_permission("permission", explain) values (110, '允许修改全部账号的密码');
insert into sys_permission("permission", explain) values (120, '允许删除全部账号');

--默认部门
insert into cmp_department("department", "explain") values ('market', '市场部');
insert into cmp_department("department", "explain") values ('production', '生产部');
insert into cmp_department("department", "explain") values ('development ', '研发部');
insert into cmp_department("department", "explain") values ('management', '管理部');
insert into cmp_department("department", "explain") values ('directors', '董事会');


--默认节点
insert into sys_node("private", "node") values ('false', 'http://localhost:8080/account/Home/login');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/Home/deplan');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/UserProfile/getInfo');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/UserProfile/alterdInfo');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/AccountTable/getInterior');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/AccountTable/addInterior');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/AccountTable/alterInterior');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/AccountTable/removeInterior');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/AccountTable/getSuper');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/AccountTable/addSuper');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/AccountTable/alterSuper');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/AccountTable/removeSuper');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/AccountTable/getRoot');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/AccountTable/addRoot');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/AccountTable/alterRoot');
insert into sys_node("private", "node") values ('true', 'http://localhost:8080/account/AccountTable/removeRoot');

--测试账号
insert into sys_account("account", "password", "salf", "explain") values ('A000000000', '000000', '20191129', '测试账号');
--测试用户：
insert into cmp_staff("number", "firstName", "lastName", "city", "province", "country", "zipCode", "entryDate", "departmentId", "roleId") 
values ('A000000000', 'chan', 'harmful', 'china', 'guangdong', 'guangzhou', '511400', '20191129', 3, '4');


--映射
create table sys_account_role(
    "id" int primary key identity,
    "accountId"  int not null,
    "roleId"  int not null,
);
create table sys_role_permission(
    "id" int primary key identity,
    "roleId"  int not null,
    "permissionId"  int not null,
);
create table sys_permission_node(
    "id" int primary key identity,
    "permissionId"  int not null,
    "nodeId"  int not null,
);
create table cmp_department_account(
    "id" int primary key identity,
    "permissionId"  int not null,
    "nodeId"  int not null,
);