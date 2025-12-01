CREATE USER test_orcl
IDENTIFIED BY 1234;
--	이름이 부적합하다 라는 에러가 뜬다.
--		> 오라클 12c 버전 이후에는 C##유저이름의 형식으로 만들어야한다.

--	C##을 붙이기 싫으면 하나의 코드를 실행하면 된다.
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

--	사용자 계정 생성
CREATE USER test_orcl
IDENTIFIED BY 1234;

--	CMD 창으로 test_orcl 에 접속. --> 권한이 없어서 로그인 불가
--		CMD 창에 로그인 할때 > SQLPLUS 사용자이름/비밀번호

--	데이터베이스 사용자에 대해서는 CREATE SESSION 이라는 권한이 있어야 로그인이 가능하다.

GRANT CONNECT, RESOURCE TO TEST_ORCL;

--	권한을 부여한 뒤 CMD 창에 로그인하면 로그인이 정상적으로 된다.

--	사용자 정보 조회
--		ALL_USERS 또는 DBA_USERS 또는 DBA_OBJECTS 데이터 사전을 이용하여 조회
--		ALL_USERS 또는 DBA_USERS 데이터사전은 USER 관련해서 정보를 제공
SELECT * FROM ALL_USERS
WHERE USERNAME = 'TEST_ORCL'; --USERNAME 작성시에는 대문자로 작성
SELECT * FROM DBA_USERS
WHERE USERNAME = 'TEST_ORCL';
--		DBA_OBJECTS 데이터사전은 USER가 가지고 있는 객체의 정보를 제공
SELECT * FROM DBA_OBJECTS
WHERE OWNER = 'SCOTT';

--	사용자 계정의 비밀번호를 변경하기 ( 사용자 정보 변경하기 )
--	사용자 계정을 변경 또는 수정할 때
--		> ALTER USER 사용자명
--		> 병경할 사용자 옵션들을 작성
ALTER USER TEST_ORCL
IDENTIFIED BY 1111;

--	변경 후에 CMD창에 변경 되었는지 확인
-- 	CMD 창에서 패스워들 입력하면 어무것도 안적히니, 정확하게 작성하고 로그인하면 된다.

--	CMD창에 로그인한 후에 테이블 생성
--CREATE TABLE TEST01(
--	NO NUMBER);

SELECT * FROM DBA_OBJECTS
WHERE OWNER = 'TEST_ORCL';

--	사용자 삭제
--		> 현재 접속중인 사용자는 삭제할 수 없다.	
--		> 사용자가 객체를 자ㅣ고 있으면 삭제할 수 없다.
DROP USER TEST_ORCL;

-- 		> 객체가 있을 경우 그 객체까지 다 같이 삭제하는 명령어
DROP USER TSET_ORCAL CASCADE;
-- CMD에서 Ctrl + c = 현재 명령문에서 빠져나옴

--  권한
--	계정 접속, 테이블 생성, 시퀀스 생성, 뷰 생성, 동의어 생성 등등의 많은 권한
--	권한의 종류 ( 시스템권한 / 객체 권한 )
--		1. 시스템 권한
--			CREATE SESSION  > 계정이 접속 가능하게 만드는 권한
--			CREATE TABLE	> 테이블,인덱스,제약조건을 생성, 수정, 삭제
--			CREATE VIEW		> 뷰 생성, 수정, 삭제
--			CREATE SEQUENCE	> 시퀀스 생성, 수정, 삭제
--			CREATE SYNONYM  > 시노님(동의어) 생성, 수정, 삭제
--			CREATE ROLE		> 롤 생성, 수정, 삭제

--			CREATE ANY TABLE
--			ALTER ANY TABLE
--			DROP ANY TABLE
--			CREATE ANY VIEW 등등 ANY 가 붙으면
--			본인의 계정 이외의 계정에도 만들 수 있는 권한을 준다.

--			시스템 권한을 부여하는 방법
--			GRANT [시스템권한] TO [사용자이름 / 롤이름 / PUBLIC ]
--			[WITH ADMIN OPTION]
--			> 시스템 권한
--				위에 적힌 권한의 이름을 작성한다.
--				여러개를 작성하려면 , 로 이어서 작성하면 된다.
--			> 사용자이름 / 롤이름 /  PUBLIC
--				권한을 부여할 대상 ( 사용자이름, 롤이름 )
--				여러 이름을 작성할 때에는 , 로 이어서 작성하면 된다.
--				PUBLIC - 데이터베이스에 등록된 모든 사용자에게 권한을 부여한다.
--			> WITH ADMAIN OPTION
--				부여받은 권한을 다른 사용자에게 부여할 수 있는 권한도 함께 준다.

--			테스트 계정 생성			
CREATE USER TEST01
IDENTIFIED BY 0000;
--			테스트 계정에 접속권한과 테이블 만드는 권한을 부여
GRANT CREATE SESSION, CREATE TABLE TO TEST01;
--			CMD 창에 TEST01로 접속해서
--			테이블을 하나 만들겠습니다.
--			SQLPLUS TEST01/0000		-> CMD창에서 계정 접속
--			CREATE TABLE TT01(
--			NO NUMBER
--			);						-> 테이블 생성
--			INSERT INTO TT01 VALUES (1); -> 데이터 삽입
--			에러발생 !  TABLESPACE 에 권한이 없다
GRANT UNLIMITED TABLESPACE TO TEST01;

--			TABLESPACE 에 대한 용량을 늘려주는 방법
ALTER USER TEST01
QUOTA 2M ON USERS;

--			GRANT로 권한을 주는 방식은 용량 상관없이 테이블 스페이스를 사용하는 방법,
--			ALTER USER를 통해서 테이블 스페이스의 사용할 크기를 지정하는 방법

--		2. 객체 권한
--			테이블, 인덱스, 뷰, 시퀀스, 동의어 등에 관한 권한
--			테이블 - INSERT, UPDATE, DELETE, INDEX, REFERENCES, SELECT
--			뷰 - INSERT, DELETE, REFERENCES, SELECT, UPDATE
--			시퀀스 - ALTER, SELECT
--			등등 많은 객체 권한이 있다.

--			객체 권한 부여하는 기본 구문
--			GRANT [객체권한 / ALL PRIVILEGES ] ON [유저명.객체이름]
--			TO [유저명/롤이름/PUBLIC] [sleectWITH ADMIN OPTION]
--				> 객체권한 / ALL PRIVILEGES
--					부여할 객체 권한의 이름을 작성
--					ALL PRIVILEGES -> 객체에 관련한 모든 권한을 뜻함
--					여러 권한을 줄 때는 , 로 이어서 작성하면 된다.
--				> 유저명.객체이름
--					권한을 오픈해줄 사용자의 객체를 작성한다.
--					예) SCOTT의 EMP 테이블에 관한 권한을 준다. ON SCOTT.EMP
--				> 유저명 / 롤이름 / PUBLIC
--					받을 사용자의 이름 / 롤의 이름
--					여러 이름을 작성할 때에는 , 로 이어서 작성하면 된다.
--					PUBLIC -> 모든 사용자를 뜻한다.
					
--			예제 ) SCOTT 에게 TEST01 의 TT01 테이블을 조회할 수 있는 권한을 부여
GRANT SELECT ON TEST01.TT01 TO SCOTT;

--	권한 취소
--	1. 시스템 권한 취소
--		revoke [시스템권한] from [권한을가진사용자/롤/public]
--		예제 ) TEST01 의 CREATE TABLE 권한을 취소
REVOKE CREATE TABLE FROM TEST01;
--	2. 객체 권한 취소
--	 	REVOKE [객체권한] ON [사용자명.객체명] FROM [권한을가진사용자/롤/public]
--		예재 ) SCOTT가 가지고 있는 TEST01 사용자의 TT01 테이블을 SELECT하는 권한을 취소
REVOKE SELECT ON TEST01.TT01 FROM SCOTT;

--	롤 ( ROLE )
--		> 여러가지 권한을 묶은 그룹
--		> 대표적으로 쓰이는 기본적인 롤 ( CONNECT, RESOURCE )
--		CONNECT 롤 - CREATE SESSION
--		RESOURCE 롤 - CREATE TABLE, CREATE TYPE, CREATE SEQUENCE 등등
--			보통은 CONNECT, RESOURCE로 뷰, 동의어는 따로 권한을 부여해야한다.

--		롤에 부여된 권한을 확인하는 방법
--			DBA_SYS_PRIVS 데이터사전을 이용한다.
--			예제 ) RESOURCE 롤에 부여된 권한을 확인하기
SELECT * FROM DBA_SYS_PRIVS
WHERE GRANTEE = 'RESOURCE';

--		사용자 롤을 만드는 과정
--			1. 롤을 생성 			        > CREATE ROLE [롤 이름]
--			2. 롤에 권한을 부여				> GRANT [권한] TO [롤이름]
--			3. 권한을 가진 롤을 사용자에게 부여	> GRANT [롤이름] TO [사용자]
--			4. 권한이 필요없으면 권한 취소		> REVOKE [롤이름] FROM [사용자]
--			5. 롤을 삭제					> DROP ROLE [롤이름]

--		예제 ) 롤을 생성하고 사용자에게 부여하고 부여한 롤을 취소하고 롤 삭제까지.
CREATE USER TEST02 IDENTIFIED BY 1111; --테스트 유저 생성

CREATE ROLE TEST_ROLE;
GRANT CONNECT, RESOURCE, CREATE VIEW, CREATE SYNONYM TO TEST_ROLE;
-- 생성한 롤에 담을 권한을 부여
GRANT TEST_ROLE TO TEST02; -- 생성한 테스트 유저에 롤을 부여

REVOKE TEST_ROLE FROM TEST02; --  테스트 유저에 부여한 롤을 취소

DROP ROLE TEST_ROLE; -- 롤 삭제

--	롤에 등록되 권한을 수정하고 싶다면 ?
--		REVOKE 권한 FROM 롤이름 --> 으로 취소하고
--		GRANT 권한 TO 롤이름	 --> 으로 다시 등록하면 된다.

--	사용자에게 부여된 권한(롤) 조회
SELECT * FROM USER_SYS_PRIVS;	-- 유저에 부여된 시스템 권한 조회
SELECT * FROM USER_ROLE_PRIVS;	-- 유저에게 부여된 롤 조회
SELECT * FROM USER_TAB_PRIVS;	-- 유저에게 부여된 객체 권한 조회

--	문제1) 시스템 계정으로 접속하여 PREV_HW 라는 계정을 생성하시오.
--			단, 비밀번호는 ORCL로 지정, 접속권한을 부여하고 PREV_HW 계정으로 접속 확인하기
CREATE USER PREV_HW IDENTIFIED BY ORCL;
GRANT CREATE SESSION TO PREV_HW;

--	문제2 ) SCOTT 계정으로 접속하여 위에서 생성한 PREV_HW 계정에
--			SCOTT 소유의 EMP, DEPT, SALGRADE 테이블에
--			SELECT 권한을 부여하는 SQL문을 작성
--			권한을 부여했다면 PREV_HW 계정으로 SCOTT의
--			EMP, DEPT, SALGRADE 테이블이 잘 조회되는지 확인하시오.
GRANT SELECT ON EMP TO PREV_HW;
GRANT SELECT ON DEPT TO PREV_HW;
GRANT SELECT ON SALGRADE TO PREV_HW;

			
--	문제3) SCOTT 계정으로 접속하여 PREV_HW 계정에 SALGRADE 테이블의 SELECT 권한을
--			취소하는 SQL문을 작성, 권한을 취소했다면, PREV_HW 계정으로
--			SALGRADE 테이블을 조회하지 못하는지 확인하시오
REVOKE SELECT ON SALGRADE FROM PREV_HW;




















