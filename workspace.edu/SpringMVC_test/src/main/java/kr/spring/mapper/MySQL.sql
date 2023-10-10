
-- ENTITY의 DTO와 컬럼명이 똑같아야함

-- MYSQL									  : 오라클
-- AUTO_INCREMENT 						      : SEQUENCE
-- INDATE	DATETIME	DEFAULT NOW() 	      : SYSDATE
-- PRIMARY KEY(IDX)							  : ~ PRIMARY KEY

CREATE TABLE BOOK(
	NUM		INT				NOT NULL	AUTO_INCREMENT,
	TITLE	VARCHAR(50)		NOT NULL,
	AUTHOR	VARCHAR(30)		NOT NULL,
	COMPANY	VARCHAR(50)		NOT NULL,
	ISBN	VARCHAR(30)		NOT NULL,
	COUNT	INT				NOT NULL	DEFAULT 0,
	PRIMARY KEY(NUM)
);

INSERT INTO BOOK (
	TITLE, AUTHOR, COMPANY, ISBN, COUNT
) VALUES (
	'해리포터와 아즈카반의 죄수', 'J.K 롤링', '문학수첩', '8983920726', 12);
INSERT INTO BOOK (
	TITLE, AUTHOR, COMPANY, ISBN, COUNT
) VALUES (
	'난중일기', '이순신', '서해문집', '8974832232', 8);
INSERT INTO BOOK (
	TITLE, AUTHOR, COMPANY, ISBN, COUNT
) VALUES (
	'수학 귀신', '한스 마그누스', '비룡소', '8949187310', 9);
INSERT INTO BOOK (
	TITLE, AUTHOR, COMPANY, ISBN, COUNT
) VALUES (
	'윌리를 찾아라!', '마틴 핸드포드', ' 예꿈', '8992882734', 20);
INSERT INTO BOOK (
	TITLE, AUTHOR, COMPANY, ISBN, COUNT
) VALUES (
	'오세암', '정채봉', '창비', '8936440195',  4);

SELECT * FROM BOOK;


