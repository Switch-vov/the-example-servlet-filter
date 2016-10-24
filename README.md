# create database
```
create database test character set utf8;
use test;
```

# create table user
```
CREATE TABLE `user` (
  `uid` varchar(32) PRIMARY KEY,
  `username` VARCHAR(20),
  `password` VARCHAR(20) 
) ENGINE INNODB CHAR SET utf8;
```

# insert into user
```
insert into user(uid,username,password) values('u0001','Switch','123456');
insert into user(uid,username,password) values('u0002','Kity','123456');
insert into user(uid,username,password) values('u0003','Mone','123456');
```

select * from `user`;
