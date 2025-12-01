<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
BoardDAO dao = new BoardDAO();
String searchWord = request.getParameter("searchWord");

// 현재 페이지 데이터
int pageNum = 1;
if(request.getParameter("pageNum")!=null &&
	!request.getParameter("pageNum").equals("")){
	pageNum = Integer.parseInt(request.getParameter("pageNum"));
}
// 한페이지 출력할 데이터의 개수
int pageSize = 10;
// 검색 및 페이징에 사용할 데이터를 맵에 저장
Map<String, Object> param = new HashMap<>();
param.put("searchWord", searchWord);
param.put("pageNum", pageNum);
param.put("pageSize",pageSize);
List<BoardDTO> boardLists = dao.selectListPage(param);
int totalPages = (int)Math.ceil((double)dao.selectCount(searchWord)/pageSize);
dao.close();


request.setAttribute("boardLists", boardLists);
request.setAttribute("totalPages", totalPages);
request.setAttribute("pageNum", pageNum);
request.setAttribute("searchWord", searchWord);

request.getRequestDispatcher("notice_listResult.jsp")
	.forward(request, response);
%>
