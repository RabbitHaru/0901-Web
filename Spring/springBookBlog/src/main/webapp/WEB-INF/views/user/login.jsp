<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%-- Flash Attribute는 param이 아니라 바로 접근 --%>
<c:if test="${not empty error}">
    <div class="alert error">
        <c:choose>
            <c:when test="${error == 'empty_fields'}">아이디와 비밀번호를 입력해주세요.</c:when>
            <c:when test="${error == 'invalid_credentials'}">아이디 또는 비밀번호가 잘못되었습니다.</c:when>
            <c:when test="${error == 'login_required'}">로그인이 필요한 서비스입니다.</c:when>
        </c:choose>
    </div>
</c:if>

<c:if test="${not empty msg}">
    <div class="alert success">
        <c:choose>
            <c:when test="${msg == 'join_success'}">회원가입이 완료되었습니다. 로그인해주세요.</c:when>
            <c:when test="${msg == 'logout_success'}">로그아웃되었습니다.</c:when>
            <c:when test="${msg == 'login_success'}">로그인되었습니다.</c:when>
        </c:choose>
    </div>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main-container">
    <div class="form-container">
        <div class="container">
            <h2>🔐 로그인</h2>
            
            <c:if test="${not empty param.error}">
                <div class="alert error">
                    <c:choose>
                        <c:when test="${param.error == 'empty_fields'}">아이디와 비밀번호를 입력해주세요.</c:when>
                        <c:when test="${param.error == 'invalid_credentials'}">아이디 또는 비밀번호가 잘못되었습니다.</c:when>
                        <c:when test="${param.error == 'login_required'}">로그인이 필요한 서비스입니다.</c:when>
                    </c:choose>
                </div>
            </c:if>
            
            <c:if test="${not empty param.msg}">
                <div class="alert success">
                    <c:choose>
                        <c:when test="${param.msg == 'join_success'}">회원가입이 완료되었습니다. 로그인해주세요.</c:when>
                        <c:when test="${param.msg == 'logout_success'}">로그아웃되었습니다.</c:when>
                    </c:choose>
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/login.do" method="post">
                <label for="username">아이디</label>
                <input type="text" name="username" id="username" required placeholder="아이디를 입력하세요">
                
                <label for="password">비밀번호</label>
                <input type="password" name="password" id="password" required placeholder="비밀번호를 입력하세요">
                
                <button type="submit" class="btn-submit" style="width: 100%; margin-top: 10px;">로그인</button>
            </form>
            
            <div style="text-align: center; margin-top: 25px; padding-top: 20px; border-top: 1px solid #eee;">
                <p style="color: #666; margin-bottom: 15px;">아직 계정이 없나요?</p>
                <a href="${pageContext.request.contextPath}/join.do" class="btn-cancel" style="display: inline-block;">회원가입</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>