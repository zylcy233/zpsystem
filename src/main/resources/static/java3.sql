--app��Ϣ��
--drop table app
create table app(
  appid number(4) primary key,
  name varchar(10),
  description varchar(200),
  url varchar(100),
  appcategoryid varchar(50)
);
insert into app values(0001,'�ٶ�',
'ȫ�����ȵ������������桢���������������ݵػ�ȡ��Ϣ���ҵ�����. �ٶȳ���ǧ�ڵ�������ҳ���ݿ⣬����˲���ҵ���ص����������'
,'https://www.baidu.com',1);

--�����
--drop table appcategory
create table appcategory(
  appcategoryid number(4) primary key,
  name varchar(10)
);
insert into appcategory values(1,'�����');

--app�û���Ϣ��
--drop table appuser
create table appuser(
 userid number(4) primary key,
 passwd varchar(10) not null
);
insert into appuser values(1111,'user1');

--�û��ղر�
--drop table appcollection
create table appcollection(
 appid number(4),
 userid number(4),
 constraint appcol_appiduserid primary key(appid,userid)
);

--����Ա��½��
--drop table adm
create table adm(
  Aid number(8) primary key,
  Aname varchar(50) not null,
  Apasswd varchar(20) not null
);
insert into adm values(
  '30000001','zy','zy12345'
);
commit;