--app信息表
--drop table app
create table app(
  appid number(4) primary key,
  name varchar(10),
  description varchar(200),
  url varchar(100),
  appcategoryid varchar(50)
);
insert into app values(0001,'百度',
'全球领先的中文搜索引擎、致力于让网民更便捷地获取信息，找到所求。. 百度超过千亿的中文网页数据库，可以瞬间找到相关的搜索结果。'
,'https://www.baidu.com',1);

--分类表
--drop table appcategory
create table appcategory(
  appcategoryid number(4) primary key,
  name varchar(10)
);
insert into appcategory values(1,'浏览器');

--app用户信息表
--drop table appuser
create table appuser(
 userid number(4) primary key,
 passwd varchar(10) not null
);
insert into appuser values(1111,'user1');

--用户收藏表
--drop table appcollection
create table appcollection(
 appid number(4),
 userid number(4),
 constraint appcol_appiduserid primary key(appid,userid)
);

--管理员登陆表
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