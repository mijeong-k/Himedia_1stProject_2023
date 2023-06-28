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

INSERT INTO SUSER values('1003', '아디찾기','idtest@naver.com', 'idtest1234@','010-1111-2222')

DELETE FROM SUSER 
WHERE USER_id = '1003'

DELETE FROM USERREQUEST 
WHERE USER_id = '1006'

DELETE FROM DOCUMENTWHERE
WHERE USER_id = '1006'

SELECT * FROM USERREQUEST

UPDATE SUSER
SET user_pwd = 'test5678@'
WHERE user_email = 'test@naver.com'

SELECT * FROM SUSER
WHERE USER_NAME = '아디찾기'
AND USER_CON = '010-1111-2222'

SELECT * FROM SUSER
WHERE USER_NAME = '테스트'
AND USER_EMAIL = 'test@naver.com'


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
	fixguide VARCHAR2(100),
	CONSTRAINT fk_userid FOREIGN key(USER_ID)
	REFERENCES SUSER(USER_ID),
	CONSTRAINT fk_mangid FOREIGN key(MANAGER_ID)
	REFERENCES MEMBER(MANAGER_ID)
)

INSERT INTO USERREQUEST values('1004', '회사명','대표자', '2000-01-01','100','','','')

CREATE TABLE MEMBER
(
	MANAGER_ID VARCHAR2(5) PRIMARY KEY,
	MANAGER_NAME VARCHAR2(50) NOT null,
	MANAGER_EMAIL VARCHAR2(50) NOT null,
	MANAGER_PWD VARCHAR2(5) NOT null
)

SELECT * FROM MEMBER

SELECT * FROM USERREQUEST

-- 담당자 설정여부 조회쿼리
SELECT m.manager_id,m.manager_name,m.manager_email, u.manager_id, 
CASE
	WHEN u.manager_id IS NULL THEN 'N'
	WHEN u.manager_id IS NOT NULL THEN 'Y'
	END ISMATCH
FROM USERREQUEST u, member m
WHERE m.manager_id = u.manager_id(+)



FROM USERREQUEST u, MEMBER m
WHERE m.MANAGER_ID = u.MANAGER_ID (+)


UPDATE USERREQUEST
SET manager_id = '10001', ismatch = 'Y' WHERE USER_ID = '1001'

UPDATE USERREQUEST
SET fixguide = '자료를 모두 제출해주세요' WHERE USER_ID = '1001'

SELECT * FROM USERREQUEST
--(SELECT *FROM USERREQUEST ORDER BY user_id DESC)
WHERE ROWNUM= 1

SELECT * 
FROM (SELECT ROWNUM AS rownumber, user_id, COMPANY, CEO, REGIDATE, STAFF, MANAGER_ID, ISMATCH, fixguide
      FROM USERREQUEST) E
WHERE E.rownumber = 2


SELECT * FROM USERREQUEST
WHERE user_id=1001

SELECT * FROM USERREQUEST


UPDATE USERREQUEST
SET company = '회사', ceo = '대표' , regidate = '2010-10-11', staff = '50'  WHERE USER_ID = '1001'

UPDATE USERREQUEST 
SET COMPANY = '회사명', ceo = '대표자', date = '2010-10-11', staff = '50' WHERE USER_ID = '1001'


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
WHERE USER_id = '1002'



CREATE TABLE DOCUMENTWHERE
(
	USER_ID VARCHAR2(10) PRIMARY KEY,
	filename1 VARCHAR2(300), filename2 VARCHAR2(300), filename3 VARCHAR2(300), filename4 VARCHAR2(300),
	filename5 VARCHAR2(300), filename6 VARCHAR2(300), filename7 VARCHAR2(300), filename8 VARCHAR2(300),
	CONSTRAINT fk_docwhere_userid FOREIGN key(USER_ID)
	REFERENCES SUSER(USER_ID)
)	

DROP TABLE DOCUMENTWHERE

SELECT * FROM DOCUMENTWHERE

SELECT FIlename1 FROM DOCUMENTWHERE
WHERE user_id = '1002' 


SELECT * FROM 
(
	SELECT us.USER_ID, s.USER_EMAIL, d.filename1, d.FILENAME2 , d.FILENAME3 , d.FILENAME4 , d.FILENAME5 , d.FILENAME6 , d.FILENAME7 , d.FILENAME8  
	FROM SUSER s , USERREQUEST us, DOCUMENTWHERE d
	WHERE s.USER_ID = us.USER_ID 
	AND us.USER_ID = d.USER_ID 
)
where USER_EMAIL='test@naver.com'
	


DELETE FROM DOCUMENTWHERE
WHERE USER_ID = '1002'


SELECT FILENAME2 FROM DOCUMENTWHERE
WHERE USER_ID = '1003'


UPDATE DOCUMENTWHERE
SET FILENAME3 = '테스트' WHERE USER_ID = '1001'

UPDATE USERREQUEST
SET manager_id = '10010' WHERE USER_ID = '1001'


INSERT INTO DOCUMENTWHERE values('1004','abc','cde','','','','','','')

INSERT INTO DOCUMENTWHERE values('1001','가나다','라마사바','','','','','','')

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


SELECT us.USER_ID, s.USER_NAME, s.USER_EMAIL, us.COMPANY, us.CEO, us.REGIDATE, us.STAFF, us.MANAGER_ID, us.ISMATCH, us.FIXGUIDE 
FROM SUSER s, USERREQUEST us
WHERE s.USER_ID = us.USER_ID 


SELECT * FROM 
(
	SELECT us.USER_ID, s.USER_NAME, s.USER_EMAIL, us.COMPANY, us.CEO, us.REGIDATE, us.STAFF, us.MANAGER_ID, us.ISMATCH, us.FIXGUIDE 
	FROM SUSER s, USERREQUEST us
	WHERE s.USER_ID = us.USER_ID 
)
where USER_EMAIL='test@naver.com'


SELECT * FROM 
(
SELECT us.USER_ID, s.USER_NAME, s.USER_EMAIL, us.COMPANY, us.CEO, us.REGIDATE, us.STAFF, 
us.MANAGER_ID, us.ISMATCH, us.FIXGUIDE, m.MANAGER_NAME , m.MANAGER_EMAIL 
FROM SUSER s, USERREQUEST us, MEMBER m 
WHERE s.USER_ID = us.USER_ID	
AND us.MANAGER_ID = m.MANAGER_ID
) 
where USER_EMAIL='test@naver.com'



SELECT * FROM 
(
	SELECT us.USER_ID, s.USER_NAME, s.USER_EMAIL, us.COMPANY, us.CEO, us.REGIDATE, us.STAFF, us.MANAGER_ID, us.ISMATCH, us.FIXGUIDE, m.MANAGER_NAME , m.MANAGER_EMAIL 
	FROM SUSER s, USERREQUEST us, MEMBER m 
	WHERE s.USER_ID = us.USER_ID 
	AND us.MANAGER_ID = m.MANAGER_ID 
)
where USER_EMAIL='gs@naver.com'

SELECT * FROM MEMBER

INSERT INTO USERREQUEST values('1002','철수회사','김철희','2000-01-01','10','10010','','')

UPDATE USERREQUEST
SET FIXGUIDE = '자료를 제출하세요.' WHERE USER_ID = '1004'

UPDATE USERREQUEST
SET FIXGUIDE = '' WHERE USER_ID = '1004'

UPDATE MEMBER
SET MANAGER_EMAIL = 'qna@namu.co.kr' WHERE MANAGER_ID = '10010'

UPDATE MEMBER
SET MANAGER_NAME = '담당자 배치중' WHERE MANAGER_ID = '10010'
