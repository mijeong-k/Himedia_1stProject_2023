SELECT * FROM SUSER

CREATE TABLE SUSER
(
	USER_ID VARCHAR2(10) PRIMARY KEY,
	USER_NAME VARCHAR2(20) NOT null,
	USER_EMAIL VARCHAR2(50) NOT null,
	USER_PWD VARCHAR2(15) NOT null,
	USER_CON VARCHAR2(15) NOT null
)

DROP TABLE SUSER

INSERT INTO SUSER values('1001', '김미정','mjcarrotte@naver.com', 'mj1234!@','010-2380-7140')

DELETE FROM SUSER 
WHERE USER_id = '1004'


SELECT USER_ID FROM SUSER
WHERE USER_EMAIL = 'kcs@hanmail.net'

DROP TABLE USERREQUEST

CREATE TABLE USERREQUEST
(
	USER_ID VARCHAR2(10) PRIMARY KEY,
	COMPANY VARCHAR2(50) NOT null,
	CEO VARCHAR2(50) NOT null,
	REGIDATE VARCHAR2(10) NOT null,
	STAFF VARCHAR2(5) NOT NULL,
	MANAGER_ID VARCHAR2(5),
	ISMATCH VARCHAR2(5),
	CONSTRAINT fk_userid FOREIGN key(USER_ID)
	REFERENCES SUSER(USER_ID),
	CONSTRAINT fk_mangid FOREIGN key(MANAGER_ID)
	REFERENCES MEMBER(MANAGER_ID)
)

CREATE TABLE MEMBER
(
	MANAGER_ID VARCHAR2(5) PRIMARY KEY,
	MANAGER_NAME VARCHAR2(50) NOT null,
	MANAGER_EMAIL VARCHAR2(50) NOT null,
	MANAGER_PWD VARCHAR2(5) NOT null
)

SELECT * FROM MEMBER

SELECT * FROM USERREQUEST

INSERT INTO MEMBER values('10001', '김영희','10001@namu.co.kr', '50001')

INSERT INTO MEMBER values('10002', '공유','10002@namu.co.kr', '50002')

INSERT INTO MEMBER values('10003', '김고은','10003@namu.co.kr', '50003')

INSERT INTO MEMBER values('10004', '이엘','10004@namu.co.kr', '50004')

INSERT INTO MEMBER values('10005', '육성재','10005@namu.co.kr', '50005')

INSERT INTO MEMBER values('10006', '유인나','10006@namu.co.kr', '50006')

INSERT INTO MEMBER values('10007', '정해인','10007@namu.co.kr', '50007')

INSERT INTO MEMBER values('10008', '김소현','10008@namu.co.kr', '50008')

INSERT INTO MEMBER values('10009', '조우진','10009@namu.co.kr', '50009')

INSERT INTO MEMBER values('10010', '테스트','10010@namu.co.kr', '55555')


DELETE FROM USERREQUEST 
WHERE USER_id = '1003'



CREATE TABLE DOCUMENTWHERE
(
	USER_ID VARCHAR2(10) PRIMARY KEY,
	filename1 VARCHAR2(100), filename2 VARCHAR2(100), filename3 VARCHAR2(100), filename4 VARCHAR2(100),
	filename5 VARCHAR2(100), filename6 VARCHAR2(100), filename7 VARCHAR2(100), filename8 VARCHAR2(100),
	CONSTRAINT fk_docwhere_userid FOREIGN key(USER_ID)
	REFERENCES SUSER(USER_ID)
)	

SELECT * FROM DOCUMENTWHERE


CREATE TABLE test
(
	USER_ID VARCHAR2(10) PRIMARY KEY,
	filename VARCHAR2(100), filename2 VARCHAR2(100),
	CONSTRAINT fk_test_userid FOREIGN key(USER_ID)
	REFERENCES SUSER(USER_ID)
)	

SELECT * FROM test

DELETE FROM test 
WHERE USER_id = '1001'

DROP TABLE DOCUMENTNAME

DELETE FROM DOCUMENTSNAME 
WHERE USER_id = '1001'