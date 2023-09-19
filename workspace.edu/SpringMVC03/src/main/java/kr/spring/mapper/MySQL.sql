-- 회원 테이블
create table member(
	memidx		int				auto_increment,
	memid		varchar(20)		not null,
	mempassword	varchar(20)		not null,
	memname		varchar(20)		not null,
	memage		int,
	memgender	varchar(20),
	mememail	varchar(50),
	memprofile	varchar(50), -- 사진 경로 저장할 컬럼
	primary key(memidx)
);

select * from member;

insert into member(memid, mempassword, memname, memage, memgender, mememail, memprofile)
values('admin', '1234', '관리자', 20, '남자', 'admin@gmail.com', '');

select DISTINCT memid from member where memid = 'admin';

delete from member;
commit;

update member set mempassword='수정됨', memname='수정된이름', memage=100, memgender='여자', mememail='이메일@a.com'
where memid = 'admin';





