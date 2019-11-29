use test;
--�˺ű�
create table sys_account(
    "id" int primary key identity,
    "account"  varchar(16) not null,
    "password"  varchar(16) not null,
    "salf" char(8) not null,
    "explain"  text default null
);

--��ɫ��
create table sys_role(
    "id" int primary key identity,
    "role"  varchar(max) not null,
    "explain"  text default null
);

--Ȩ�ޱ�
create table sys_permission(
    "id" int primary key identity,
    "permission"  int not null,
    "explain"  text default null
);

--url�ڵ��
create table sys_node(
    "id" int primary key identity,
    "node"  varchar(max) not null,
    "private" bit not null
);


--Ա����
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

--���ű�
create table cmp_department(
    "id" int primary key identity,
    "department"    nvarchar(10)    not null,
    "explain"    nvarchar(10)    default null,
);



--Ĭ�Ͻ�ɫ
insert into sys_role("role", "explain") values ('normal', '��ͨԱ��');
insert into sys_role("role", "explain") values ('governor', '��������');
insert into sys_role("role", "explain") values ('administrator', '��̨����Ա');
insert into sys_role("role", "explain") values ('root', '��߹���Ա');

--Ĭ�ϲ�����
insert into sys_permission("permission", explain) values (10,  '����鿴��ͨ�˺š�����');
insert into sys_permission("permission", explain) values (20,  '���������ͨ�˺�');
insert into sys_permission("permission", explain) values (30,  '�����޸���ͨ�˺ŵ�����');
insert into sys_permission("permission", explain) values (40,  '����ɾ����ͨ�˺�');
insert into sys_permission("permission", explain) values (50,  '����鿴�����˺š�����');
insert into sys_permission("permission", explain) values (60,  '�����������˺�');
insert into sys_permission("permission", explain) values (70,  '�����޸Ĺ����˺ŵ�����');
insert into sys_permission("permission", explain) values (80,  '����ɾ�������˺�');
insert into sys_permission("permission", explain) values (90,  '����鿴ȫ���˺š�����');
insert into sys_permission("permission", explain) values (100, '�������ȫ���˺�');
insert into sys_permission("permission", explain) values (110, '�����޸�ȫ���˺ŵ�����');
insert into sys_permission("permission", explain) values (120, '����ɾ��ȫ���˺�');

--Ĭ�ϲ���
insert into cmp_department("department", "explain") values ('market', '�г���');
insert into cmp_department("department", "explain") values ('production', '������');
insert into cmp_department("department", "explain") values ('development ', '�з���');
insert into cmp_department("department", "explain") values ('management', '����');
insert into cmp_department("department", "explain") values ('directors', '���»�');


--Ĭ�Ͻڵ�
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

--�����˺�
insert into sys_account("account", "password", "salf", "explain") values ('A000000000', '000000', '20191129', '�����˺�');
--�����û���
insert into cmp_staff("number", "firstName", "lastName", "city", "province", "country", "zipCode", "entryDate", "departmentId", "roleId") 
values ('A000000000', 'chan', 'harmful', 'china', 'guangdong', 'guangzhou', '511400', '20191129', 3, '4');


--ӳ��
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