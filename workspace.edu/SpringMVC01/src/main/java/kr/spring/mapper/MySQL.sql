
-- ENTITY의 DTO와 컬럼명이 똑같아야함

-- MYSQL									  : 오라클
-- AUTO_INCREMENT 						      : SEQUENCE
-- INDATE	DATETIME	DEFAULT NOW() 	      : SYSDATE
-- PRIMARY KEY(IDX)							  : ~ PRIMARY KEY

CREATE TABLE BOARD(
	IDX		INT				NOT NULL	AUTO_INCREMENT,
	TITLE	VARCHAR(100)	NOT NULL,
	CONTENT	VARCHAR(2000)	NOT NULL,
	WRITER	VARCHAR(30)		NOT NULL,
	INDATE	DATETIME		DEFAULT NOW(),
	COUNT	INT				DEFAULT 0,
	PRIMARY KEY(IDX)
);

INSERT INTO BOARD (TITLE,	CONTENT,	WRITER)
VALUES('[속보]명훈쌤 시네에서 미상의 여자와 걷는 모습 발견되어', '힝 뻥인데~', '준용쌤');
INSERT INTO BOARD (TITLE,	CONTENT,	WRITER)
VALUES('[속보]명훈쌤 시네에서 미상의 여자와 걷는 모습 발견되어1', '힝 뻥인데~1', '준용쌤1');
INSERT INTO BOARD (TITLE,	CONTENT,	WRITER)
VALUES('[속보]명훈쌤 시네에서 미상의 여자와 걷는 모습 발견되어2', '힝 뻥인데~2', '준용쌤2');
INSERT INTO BOARD (TITLE,	CONTENT,	WRITER)
VALUES('[속보]명훈쌤 시네에서 미상의 여자와 걷는 모습 발견되어3', '힝 뻥인데~3', '준용쌤3');
INSERT INTO BOARD (TITLE,	CONTENT,	WRITER)
VALUES('[속보]명훈쌤 시네에서 미상의 여자와 걷는 모습 발견되어4', '힝 뻥인데~4', '준용쌤4');

SELECT * FROM BOARD;
DELETE FROM BOARD WHERE IDX=1;
COMMIT;