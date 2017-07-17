create database dd
use dd

create table student(
  cname varchar(50),
  sid int primary key auto_increment,
  sname varchar(50)
 
);
 
commit;

insert into student(cname,sname) value('网络1班','小明');
insert into student(cname,sname) value('网络1班','小李');

select * from student;

delete from student where sid=4;