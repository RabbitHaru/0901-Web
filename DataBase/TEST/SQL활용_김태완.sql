--1. 아래의 값에 맞게 DEPT 테이블에 데이터를 추가하라.
-- - 부서번호 : 50 , 부서명 : DEVELOP , 지역 : BUSAN
INSERT INTO DEPT VALUES (50, 'DEVELOP','BUSAN');

--2. DEPT 테이블에 LOC 필드의 값을 DALLAS 를 SEOUL 로 변경하라.
UPDATE DEPT
SET LOC = 'SEOUL'
WHERE LOC = 'DALLAS';

-- 3. EMP 테이블에서 부서명이 RESEARCH 인 데이터를 삭제하라. (SUBQUERY)
DELETE FROM EMP
WHERE e.deptno = d.deptno
	AND SELECT *
		FROM dept
		WHERE dname = 'RESEARCH';

-- 4. EMP 테이블을 사용하여 사원명과 급여을 출력하라. 단, 급여는 0 – 1500 이면 급여의 10%,   
--1501 – 3000 이면 급여의 5%, 3000 초과이면 급여의 0%를 추가로 지정한다. (CASE문 사용)
SELECT ENAME, SAL,
	CASE
		WHEN SAL BETWEEN 0 AND 1500 THEN SAL* 1.1
		WHEN SAL BETWEEN 1501 AND 3000 THEN SAL * 1.05
		WHEN SAL>3000 THEN SAL*1
		END
FROM EMP;
	

-- 5. EMP 테이블에서 급여가 2000 초과인 사원들의 사원명, 부서명, 급여를 출력하라. 단, 급여를 기
--준으로 내림차순으로 표시되도록 하라.
SELECT e.ENAME , d.DNAME ,e.sal
FROM EMP e, DEPT d 
WHERE e.DEPTNO = d.DEPTNO
AND sal > 2000
ORDER BY e.SAL DESC;
	
-- 6. EMP 테이블을 이용하여 사원의 이름과 사수의 이름을 출력하라. 단, 사수가 없는 경우 null이 표
--시되도록 하라.
SELECT E.ENAME AS "사원이름", M.ENAME AS "사수이름"
FROM EMP E LEFT JOIN EMP M ON (E.MGR = M.EMPNO);

-- 7. EMP 테이블을 이용하여 각 부서별 부서 번호와 평균 급여, 사원 수를 출력하라. 단, 평균 급여
--에서 소수점은 모두 절삭하도록 한다.
SELECT e.DEPTNO , trunc(avg(sal)), count(e.DEPTNO)
FROM emp e
GROUP BY e.Deptno;

-- 8. EMP 테이블에서 전체 사원의 평균 급여보다 높은 급여를 받는 사원들의 사원 번호, 사원명, 부
--서명, 급여를 출력하여라. (SUBQUERY)
SELECT e.empno, e.ename, d.dname, e.SAL
FROM emp e, dept d
WHERE e.DEPTNO = d.DEPTNO
AND e.SAL > (SELECT AVG(SAL)
				FROM EMP);

-- 9. EMP 테이블에서 MARTIN과 같은 직책인 사원들의 이름과 직책, 부서명을 출력하
--라.(SUBQUERY)
SELECT e.ENAME,e.JOB, d.DNAME 
FROM EMP e, DEPT d 
WHERE e.DEPTNO = d.DEPTNO
AND job = (SELECT JOB
			FROM EMP
			WHERE ENAME = 'MARTIN');

-- 10. SALESMAN 직책의 최소급여보다 높은 급여를 받는 사원들을 부서 번호별로 합계를 출력하여
--라. (SUBQUERY)
SELECT e.DEPTNO, SUM(SAL)
FROM EMP e
WHERE SAL > (SELECT MIN(SAL)
				FROM EMP
				WHERE JOB = 'SALESMAN')
GROUP BY DEPTNO;