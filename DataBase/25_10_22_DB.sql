--	계층형 쿼리
--		: 상하위 관계를 표시하여 보기 편하게 만들어주는 쿼리
--		LEVEL : 계층 간에 몇 단계인지를 알려주는 용어

--		계층형 쿼리를 사용하지 않고, EMP 테이블의 ENAME 들 보기
SELECT ENAME FROM EMP;

--		계층형 쿼리를 사용해서 EMP 테이블의 ENAME 들 보기
SELECT LPAD(ENAME, LEVEL * 4, '-') AS ENAME, LEVEL
FROM EMP
CONNECT BY PRIOR EMPNO = MGR
START WITH EMPNO = 7839;

SELECT EMPNO, ENAME, MGR
FROM EMP;
--	쿼리 해석
-- SELECT 절의 LPAD(ENAME, LEVEL * 4, '-')
--		> LEVEL * 4BYTE의 길이로 출력하되, 왼쪽 빈자리는 '-'으로 채워라
--	CONNECT BY PRIOR절
--		> 각 행들이 어떤 형식으로 레벨이 결정되는지 조건을 지정
--		> PRIOR 은 '상위의' 라는 뜻을 지니고 있다.
--	START WITH 절
--		> 데이터를 계층화 하는 데 시작이 되는 데이터를 지정

--	** CONNECT BY PRIOR 절 주의사항
--		PRIOR 의 위치가 중요하다.
SELECT LPAD(ENAME, LEVEL*5, '-') AS ENAME
FROM EMP e CONNECT BY PRIOR EMPNO = MGR
START WITH EMPNO = 7839;

SELECT LPAD(ENAME, LEVEL*5, '-') AS ENAME
FROM EMP
CONNECT BY EMPNO = PRIOR MGR
START WITH EMPNO = 7369;

SELECT EMPNO, ENAME, MGR
FROM EMP;

--	계층형 쿼리가 수행되는 순서
--		1. START WITH 절에 시작 데이터의 조건을 찾는다.
--		2. CONNECT BY 절에 연결 조건을 찾는다.
--		3. WHERE 절의 조건을 검색한다.

--	계층형 쿼리를 작성할 때의 주의사항
--		> CONNECT BY 절에는 SUB QUERY를 사용할 수 없다.
--		> 데이터가 많을 경우 시간이 오래 걸릴 수 있으므로 START WITH 절,
--		  CONNECT BY 절, WHERE 절의 컬럼에 적절히 인덱스가 설정되어 있어야 한다.
--		> 부분 범위 처리 기법은 아쉽게도 계층형 쿼리에서 사용할 수 없다.

--	예제로 보는 계층형 쿼리의 구조
SELECT EMPNO, ENAME, JOB, MGR, PRIOR ENAME AS MGR_NAME, LEVEL,
		LPAD(' ', (LEVEL-1)*4, ' ') || ENAME AS DEPTH_ENAME,
		SYS_CONNECT_BY_PATH(ENAME, '-') AS ENAME_LIST
FROM EMP
START WITH MGR IS NULL
CONNECT BY PRIOR EMPNO = MGR
ORDER SIBLINGS BY EMPNO;

--	START WITH 절 : MGR IS NULL
--		> 시작 데이터는 MGR을 NULL로 가지는 데이터로 시작
--	CONNECT BY 절 : PRIOR EMPNO = MGR
--		> 상위 단계의 EMPNO 는 다음 단계의 MGR이 된다.
--	ORDER SIBLINGS BY 절 : EMPNO
--		> 같은 단꼐에서의 정렬 방법을 EMPNO 를 기준으로 출력한다.
--	SYS_CONNECT_BY_PATH(ENAME, '-')
--		> 각 단계를 1단계부터 현재 단계까지 ENAME을 '-'을 구분자로하여 보여준다.

--	계층이 쌓이는 순서
--	1. START WITH MGR IS NULL 부터 데이터가 시작
--		>> 7839 KING 의 데이터부터 시작.
--	2. CONNECT BY PRIOR EMPNO = MGR 이기 때문에
--	   상위 단계의 7839를 다음 단계의 MGR로 가지는 데이터를 찾는다.
--		>> 7566 JONES | 7698 BLAKE | 7782 CLARK ( level 2 )
--		>> 상위 단계의 7566 을 다음 단계의 mgr로 가지는 데이터를 찾고
--		   상위 단계의 7698 을 다음 단계의 mgr로 가지는 데이터를 찾고
--		   상위 단계의 7782 을 다음 단계의 mgr로 가지는 데이터를 찾는다.(level 3)
--		위 방식으로 계속 정리하게 되면 p484 의 트리 형식으로 정리가 된다.

--	계층 구조에서 일부분만 계층화 하기
--		1. 계층 전체를 제외하기
SELECT EMPNO, JOB, MGR, LEVEL,
		LPAD(' ', (LEVEL-1)*4, ' ') || ENAME AS DEPTH_ENAME,
		SYS_CONNECT_BY_PATH(ENAME, '-') AS ENAME_LIST
FROM EMP e 
START WITH MGR IS NULL
CONNECT BY PRIOR EMPNO = MGR AND ENAME <> 'JONES'
ORDER SIBLINGS BY ENAME;
--		2. 계층 자체는 놔두고 하나의 노드만 제외하기
SELECT EMPNO, JOB, MGR, LEVEL,
		LPAD(' ', (LEVEL-1)*4, ' ') || ENAME AS DEPTH_ENAME,
		SYS_CONNECT_BY_PATH(ENAME, '-') AS ENAME_LIST
FROM EMP e 
WHERE ENAME <> 'JONES'
START WITH MGR IS NULL
CONNECT BY PRIOR EMPNO = MGR
ORDER SIBLINGS BY ENAME;
--		따라서, CONNECT BY 절에 조건을 달면 계층 구조 전개 조건이 되고
--			WHERE 절에 조건을 달면 계층 구조를 다 생성한 후에 조건에 맞는 값만 보여준다

--	계층형 쿼리에서 사용할 수 있는 함수
--		1. CONNECT_BY_ISLEAF () 함수
--			LEAF -> 나뭇잎 -> TREE 구조의 마지막 노드를 뜻한다.
--			0과 1의 값을 반환하며
--			0 - > 자식 ( 하위 단계 )이 있다.
--			1 - > 자식 ( 하위 단계 )이 없다.
--			예제 ) CONNECT_BY_ISLEAF() 사용하지 않고 전체 데이터 보기
SELECT LPAD(' ', (LEVEL-1)*4, ' ') || ENAME AS ENAME,
		SYS_CONNECT_BY_PATH(ENAME, '->') AS "ORDER(LOW -> HIGH)"
FROM EMP
START WITH EMPNO = 7369
CONNECT BY PRIOR MGR = EMPNO;
--			예제2 ) CONNECT_BY_ISLEAF()를 0으로 설정해서 마지막 값 없애기
SELECT LPAD(' ', (LEVEL-1)*4, ' ') || ENAME AS ENAME,
		SYS_CONNECT_BY_PATH(ENAME, '->') AS "ORDER(LOW -> HIGH)"
FROM EMP
WHERE CONNECT_BY_ISLEAF = 0
START WITH EMPNO = 7369
CONNECT BY PRIOR MGR = EMPNO;
--			예제2 ) CONNECT_BY_ISLEAF()를 1로 설정해서 마지막 단계만 보기
SELECT LPAD(' ', (LEVEL-1)*4, ' ') || ENAME AS ENAME,
		SYS_CONNECT_BY_PATH(ENAME, '->') AS "ORDER(LOW -> HIGH)"
FROM EMP
WHERE CONNECT_BY_ISLEAF = 1
START WITH EMPNO = 7369
CONNECT BY PRIOR MGR = EMPNO;

--		2. CONNECT_BY_ROOT() 함수
--			현재 단계에서 최상위 단계의 값을 찾아주는 함수
SELECT EMPNO, ENAME, CONNECT_BY_ROOT EMPNO AS ROOT_EMPNO,
		SYS_CONNECT_BY_PATH(ENAME, '<-') AS "ROOT <= LEAF"
FROM EMP
WHERE LEVEL > 1
AND EMPNO = 7369
CONNECT BY PRIOR EMPNO = MGR;
--			1단계일 때
SELECT EMPNO, ENAME, CONNECT_BY_ROOT EMPNO AS ROOT_EMPNO,
		SYS_CONNECT_BY_PATH(ENAME, '<-') AS "ROOT <= LEAF"
FROM EMP
WHERE LEVEL = 1
AND EMPNO = 7369
CONNECT BY PRIOR EMPNO = MGR;
--			2단계일 때
SELECT EMPNO, ENAME, CONNECT_BY_ROOT EMPNO AS ROOT_EMPNO,
		SYS_CONNECT_BY_PATH(ENAME, '<-') AS "ROOT <= LEAF"
FROM EMP
WHERE LEVEL = 2
AND EMPNO = 7369
CONNECT BY PRIOR EMPNO = MGR;
--			3단계일 때
SELECT EMPNO, ENAME, CONNECT_BY_ROOT EMPNO AS ROOT_EMPNO,
		SYS_CONNECT_BY_PATH(ENAME, '<-') AS "ROOT <= LEAF"
FROM EMP
WHERE LEVEL = 3
AND EMPNO = 7369
CONNECT BY PRIOR EMPNO = MGR;

--	계층형 쿼리 문제 1 ) EMP2 테이블과 DEPT2 테이블을 사용
--		사원명, 부서, 직급을 합쳐서 출력하되 부서와 직급별로 계층형 쿼리를 사용하여 출력
--		단, 직급이 없는 사람들은 직급을 'Team-Worker'로 출력.
--		LPAD(' ', (level-1)*4, ' ') || 이름 || 부서 || 직급 AS name_and_position
SELECT * FROM dept2;

SELECT LPAD(' ', (level-1)*4, ' ') || E.NAME  || '-' || 
		D.DNAME  || '-' || nvl(e."POSITION",'Team-Worker')  AS name_and_position
FROM EMP2 e , DEPT2 d 
WHERE e.DEPTNO = d.dcode
CONNECT BY PRIOR e.EMPNO = e.pempno
START WITH pempno IS NULL
ORDER SIBLINGS BY e.name;
--	계층형 쿼리 문제 2 ) EMP 2 테이블과 DEPT2 테이블 사용
--		EMP2 테이블에서 Kevin Bacon-Engineering division-Department head"
--		아래에 속한 부하직원만 계층 쿼리로 조회해서 출력하세요.
--		단, 직급이 없는 사람들은 직급을 'Team-Worker'로 출력.
SELECT LPAD(' ', (level-1)*4, ' ') || E.NAME  || '-' || 
		D.DNAME  || '-' || nvl(e."POSITION",'Team-Worker')  AS name_and_position
FROM EMP2 e , DEPT2 d 
WHERE e.DEPTNO = d.dcode
CONNECT BY PRIOR e.EMPNO = e.pempno
START WITH e.name = 'Kevin Bacon'
ORDER SIBLINGS BY e.name;
--		계층형 쿼리 문제 3) EMP2 테이블과 DEPT2 테이블 사용
--			Kevin Costner 사원의 상사들을 계층 쿼리로 출력하세요
SELECT LPAD(' ', (level-1)*4, ' ') || E.NAME  || '-' || 
		D.DNAME  || '-' || nvl(e."POSITION",'Team-Worker')  AS name_and_position
FROM EMP2 e , DEPT2 d 
WHERE e.DEPTNO = d.dcode
CONNECT BY PRIOR e.pempno = e.EMPNO
START WITH e.name = 'Kevin Costner'
ORDER SIBLINGS BY e.name;
--	계층형 쿼리 문제 4) EMP2 테이블 사용
--		사원의 이름과 그 상사의 이름이 함께 나오도록 계층형 쿼리를 작성하세요.
SELECT NAME, PRIOR NAME
FROM EMP2
CONNECT BY PRIOR empno = pempno
START WITH pempno IS NULL;
-- 계층형 쿼리 문제 5) 2번 문제의 쿼리에 계층 전체 리스트를 추가해서 출력하세요.
SELECT LPAD(' ', (level-1)*4, ' ') || E.NAME  || '-' || 
		D.DNAME  || '-' || nvl(e."POSITION",'Team-Worker')  AS name_and_position,
		SYS_CONNECT_BY_PATH(e.NAME, '-') AS path
FROM EMP2 e , DEPT2 d 
WHERE e.DEPTNO = d.dcode
CONNECT BY PRIOR e.EMPNO = e.pempno
START WITH e.name = 'Kevin Bacon'
ORDER SIBLINGS BY e.name;
-- 계층형 쿼리 문제 6) EMP2 테이블과 DEPT2 테이블을 사용
--		사원 번호와 사원이름-부서-직급, 부하직원의 수를 출력하라
--		스칼라 서브 쿼리를 이용해서 작성할 것임.
SELECT EMPNO, E1.NAME || '-' || D.DNAME || '-' || 
		NVL(E1.POSITION, 'Team-Worker')  AS name_and_position,
		( SELECT COUNT(*) FROM EMP2 E2
		CONNECT BY PRIOR E2.EMPNO = E2.PEMPNO
		START WITH E2.EMPNO = E1.EMPNO ) -1 AS COUNT
FROM EMP2 E1 , DEPT2 d 
WHERE E1.DEPTNO = D.DCODE
ORDER BY COUNT DESC;

--	사용자, 구너한, 롤
--	사용자 = 계정
--	사용자는 보통 SYSTEM(DBA) 계정에서 만들도록 되어있다.
--	계정을 생성하는 문법
--	(필수) CREATE USER 사용자이름 					> 유저명을 지정
--	(필수) IDENTIFIED BY 비밀번호					> 비밀번호를 지정
--	(선택) DEFAULT TABLESPACE	 테이블스페이스이름		> 데이터를 저장할 공간을 지정
--	(선택) TEMPORARY TABLESPACE 테이블스페이스그룹이름	> 임시 데이터를 저장할 임시 공간 지정
--	(선택) QUOTA 테이블스페이스크기 ON 테이블스페이스이름	> 테이블스페이스에 얼만큼 용량을 사용할 수 있는 지 지정
--	(선택) PROFILE 프로파일이름						> 사용자에게 리소스 제한, 비밀번호 정책 등을 정의한 규칙을 지정
--	(선택) PASSWORD EXPIRE						> 첫 로그인시 무조건 비밀번호를 변경하게 함
--	(선택) ACCOUNT [LOCK / UNLOCK ]				> 계정을 잠금 / 잠금 해제

SELECT * FROM USER_SYS_PRIVS;	-- 유저에 부여된 시스템 권한 조회
SELECT * FROM USER_ROLE_PRIVS;	-- 유저에게 부여된 롤 조회
SELECT * FROM USER_TAB_PRIVS;	-- 유저에게 부여된 객체 권한 조회




















