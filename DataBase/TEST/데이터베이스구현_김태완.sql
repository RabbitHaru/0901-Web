-- SYSTEM 계정으로 진행 (1~3)------------

ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- 1. 시스템(관리자)계정으로 test01 사용자를 추가하는 쿼리를 작성하라. ( 비밀번호 : 1234 )
CREATE USER test01
IDENTIFIED BY 1234;

-- 2. 시스템(관리자)계정으로 test01 사용자에게 접속, 리소스 사용, 세션 생성, 뷰 생성, 동의어 생성이 
--가능하도록 권한과 롤을 설정하는 쿼리를 작성하라.
GRANT CONNECT, RESOURCE, CREATE SESSION, CREATE VIEW, CREATE SYNONYM TO test01;

-- 3. 시스템(관리자)계정으로 test01 사용자의 비밀번호를 oracle 로 재설정하는 쿼리를 작성하라.
ALTER USER test01
IDENTIFIED BY oracle;

------------------다시 SCOTT로 진행-----
-- 4. test01 사용자 계정으로 아래의 조건에 맞는 테이블을 생성하라
CREATE TABLE test_dept(
	deptno varchar2(6),
	dname  varchar2(6)
);
-- 5. test01 사용자 계정으로 test_dept 테이블에 loc 라는 필드를 가변길이문자 10으로 지정하고 열을 
--추가하여라.
alter table test_dept
ADD (loc varchar2(10));
   
-- 6. test01 사용자 계정으로 test_dept 테이블에 dname 이라는 열의 길이를 6 에서 10 으로 변경하라.
ALTER TABLE test_dept
MODIFY (dname varchar2(10));

-- 7. test01 사용자 계정으로 test_dept 테이블의 loc 라는 열을 삭제하라.
ALTER TABLE test_dept
DROP COLUMN loc;

-- 8. test01 사용자 계정으로 test_dept 테이블의 deptno 열에 PRIMARY KEY 제약조건을 추가하라.
   -- 제약조건 명 : PK_TEST_DEPT
ALTER TABLE test_dept
ADD CONSTRAINT PK_TEST_DEPT PRIMARY KEY(deptno);

-- 9. test01 사용자 계정으로 test_dept 테이블의 dname 열에 not null 과 unique 제약조건을 설정하라.
ALTER TABLE test_dept
ADD CONSTRAINT UN_DNAME_TEST_DEPT UNIQUE(dname)
MODIFY (dname CONSTRAINT NN_DNAME_TEST_DEPT NOT NULL);

-- 10. test01 계정으로 아래의 조건에 맞춰 테이블을 생성하라
CREATE TABLE test_emp(
		empno NUMBER(4) PRIMARY KEY,
		name varchar2(10) NOT NULL,
		salary NUMBER(10) NOT NULL,
		position varchar2(10) check(POSITION IN ('사원','대리','과장','부장')),
		deptno varchar2(6)
);

-- 11. 현재 접속해 있는 계정이 소유하고 있는 테이블을 보여주는 쿼리를 작성하라
SELECT * FROM USER_TABLES;

-- 12. test01 계정으로 test_emp 테이블의 deptno 컬럼에 외래키 제약 조건으로 test_dept 테이블의 
--deptno 컬럼을 참조하도록 지정하라.
ALTER TABLE test_emp
ADD CONSTRAINT test_emp_deptno_fk FOREIGN KEY(deptno)
REFERENCES test_dept(deptno);

-- 13. test01 계정으로 아래의 조건에 맞는 시퀀스를 생성하라.
CREATE SEQUENCE SEQ_TEST_EMP
START WITH 100
INCREMENT BY 10
MAXVALUE 9990
MINVALUE 0
NOCYCLE;

-- 14. test01 계정으로 아래의 표와 맞게 테이블에 데이터를 삽입하여라. 단, empno 는 시퀀스를 이용
--하여 자동으로 증가하도록 입력한다.
INSERT INTO test_dept values(101,'영업부');
INSERT INTO test_dept values(102,'개발부');

INSERT INTO test_emp empseq(empno,name,salary,POSITION,deptno)
VALUES(SEQ_TEST_EMP.NEXTVAL,'홍길동',1000,'사원',101);
INSERT INTO test_emp empseq(empno,name,salary,POSITION,deptno)
VALUES(SEQ_TEST_EMP.NEXTVAL,'전우치',1500,'대리',102);
INSERT INTO test_emp empseq(empno,name,salary,POSITION,deptno)
VALUES(SEQ_TEST_EMP.NEXTVAL,'실리안',2000,'과장',101);
INSERT INTO test_emp empseq(empno,name,salary,POSITION,deptno)
VALUES(SEQ_TEST_EMP.NEXTVAL,'김또치',1500,'부장',102);

-- 15. test01 계정으로 test_emp 테이블의 name 컬럼을 기준으로 인덱스를 설정하라.
-- - 인덱스 이름 : IDX_NAME_TEST_EMP
CREATE INDEX IDX_NAME_TEST_EMP
ON test_emp(name);

-- 16. test01 계정으로 test_dept 와 test_emp 테이블을 이용하여 empno, name, dname, salary 4가지
--의 컬럼만 보여주는 뷰(VIEW)를 생성하라. 
-- - 뷰 이름 : VW_TEST_EMP
CREATE OR REPLACE VIEW VW_TEST_EMP
AS
	SELECT e.empno, e.name, d.dname, e.salary
	FROM test_dept d, test_emp e;

---------------SYSTEM 에서 진행(17~20)-----------
-- 17. 시스템(관리자) 게정에서 test02 사용자를 생성하여 test02 사용자에게 test01 사용자의 test_emp 
--테이블을 조회, 입력, 수정, 삭제하는 권한을 설정하여라. 단, 생성 시 비밀번호는 4321 로 지정한다.
CREATE USER test02
IDENTIFIED BY 4321;
GRANT SELECT ,INSERT,UPDATE,DELETE ON test01.test_emp TO test02;

-- 18. 시스템(관리자) 계정에서 접속, 리소스, 세션 생성, 뷰 생성, 동의어 생성의 권한과 롤을 가지는 
--롤(ROLE)을 생성하고, test02 계정에 생성한 롤(ROLE)을 부여하라.
-- - 롤 이름 : NORMAL_USER01
CREATE ROLE NORMAL_USER01;
GRANT connect,RESOURCE, CREATE SESSION, CREATE VIEW, CREATE SYNONYM TO NORMAL_USER01;
GRANT NORMAL_USER01 TO test02;

-- 19. 시스템(관리자) 계정에서 test02 에게 위 문제 17번에서 주어진 권한을 제거하라.
REVOKE SELECT ,INSERT,UPDATE,DELETE ON test01.test_emp from test02;

-- 20. 시스템(관리자) 계정에서 test02 계정을 삭제하라.
DROP USER test02;