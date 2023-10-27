DROP TABLE AUTH;
DROP TABLE MEMBER;
-- JPA가 테이블 생성 후 기본값넣기
INSERT INTO BOARD (TITLE, CONTENT, WRITER)
VALUES('오늘 저녁에 하늘마당에서 보실 분', '내가 치킨 쏜다', '명훈쌤');
INSERT INTO BOARD (TITLE, CONTENT, WRITER)
VALUES('안녕하세요! 손석구입니다!', '너~낙지된거야', '손썩구');
INSERT INTO BOARD (TITLE, CONTENT, WRITER)
VALUES('내가 자랑하고 싶으면 얼마든지 해', '겁나 부러워임', '장기하');
INSERT INTO BOARD (TITLE, CONTENT, WRITER)
VALUES('이번주 금요일 시간 어때요', '곱창먹을래요?','조준용');
INSERT INTO BOARD (TITLE, CONTENT, WRITER)
VALUES('나는요 Spring 좋은 거얼~', '아이쿠','아이유');


SELECT * FROM BOARD;
SELECT * FROM MEMBER;