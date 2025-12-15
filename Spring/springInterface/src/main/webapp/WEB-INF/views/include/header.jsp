<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<header class="header_navbar">
    <div class="header-container">
        <div class="header-left">
            <a href="${pageContext.request.contextPath}/index.do" class="navbar-brand">
                📚 BookBlog
            </a>
        </div>

        <div class="header-center">
            <ul class="navbar-custom">
                <li><a href="${pageContext.request.contextPath}/index.do" class="nav-link">홈</a></li>
                <li><a href="${pageContext.request.contextPath}/blog/list.do" class="nav-link">리뷰목록</a></li>
                <li><a href="${pageContext.request.contextPath}/about.do" class="nav-link">소개</a></li>
            </ul>
        </div>

        <div class="header-right">
            <c:choose>
                <c:when test="${not empty sessionScope.username}">
                    <span class="welcome-text">안녕하세요, ${sessionScope.nickname}님!</span>
                    <a href="${pageContext.request.contextPath}/logout.do" class="btn btn-logout">로그아웃</a>
                    <a href="${pageContext.request.contextPath}/mypage.do" class="btn btn-login">마이페이지</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login.do" class="btn btn-login">로그인</a>
                    <a href="${pageContext.request.contextPath}/join.do" class="btn btn-join">회원가입</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>