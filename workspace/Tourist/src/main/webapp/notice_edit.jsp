<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
// 파라미터로 가지고온 게시글 PrimaryKey를 저장
	String num = request.getParameter("num");
// DB 연결
	BoardDAO dao = new BoardDAO();
// 조회수 1증가
	dao.updateVisitCount(num);
// 게시글 데이터 조회
	BoardDTO dto = dao.selectView(num);
// DB 접속 종료
	dao.close();
	// DB에서 가지고온 데이터를 request에 저장
	request.setAttribute("dto", dto);
	// 화면을 출력하는 EditResult가 실행되도록 설정
	request.getRequestDispatcher("notice_editResult.jsp")
		.forward(request, response);
%>
