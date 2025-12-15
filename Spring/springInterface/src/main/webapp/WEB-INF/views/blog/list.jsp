<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BookBlog - 책 리뷰</title>
    <link rel="stylesheet" href="/resources/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main-container">
    <div class="blog-header">
        <h1 class="blog-title">📚 BookBlog</h1>
        <p class="blog-subtitle">다양한 책들의 리뷰와 추천을 만나보세요</p>
    </div>

    <!-- 메시지 표시 -->
    <c:if test="${not empty param.msg}">
        <div class="alert success">
            <c:choose>
                <c:when test="${param.msg == 'write_success'}">✅ 리뷰가 성공적으로 작성되었습니다.</c:when>
                <c:when test="${param.msg == 'update_success'}">✏️ 리뷰가 성공적으로 수정되었습니다.</c:when>
                <c:when test="${param.msg == 'delete_success'}">🗑️ 리뷰가 성공적으로 삭제되었습니다.</c:when>
            </c:choose>
        </div>
    </c:if>

    <c:if test="${not empty param.error}">
        <div class="alert error">
            <c:choose>
                <c:when test="${param.error == 'not_found'}">❌ 게시글을 찾을 수 없습니다.</c:when>
                <c:when test="${param.error == 'invalid_id'}">⚠️ 잘못된 접근입니다.</c:when>
            </c:choose>
        </div>
    </c:if>

    <div class="blog-actions">
        <c:if test="${not empty sessionScope.username}">
            <a href="${pageContext.request.contextPath}/blog/write.do" class="hero-btn">
                <span class="btn-icon">📖</span> 새 리뷰 작성
            </a>
        </c:if>
    </div>

    <div class="blog-grid">
        <c:forEach var="post" items="${blogList}">
            <div class="blog-card" onclick="location.href='${pageContext.request.contextPath}/blog/view.do?id=${post.id}'">
                <div class="card-image-container">
                    <!-- 이미지 표시 -->
                </div>

                <div class="card-content">
                    <div class="card-book-info">
                        <h4 class="card-book-title">${post.bookTitle}</h4>
                        <p class="card-book-author">${post.bookAuthor} | ${post.bookPublisher}</p>
                    </div>

                    <h3 class="card-title">${post.title}</h3>
                    <p class="card-excerpt">${fn:substring(post.content.replaceAll('<.*?>', ''), 0, 100)}...</p>

                    <div class="card-meta">
                        <div class="author">
                            <span class="author-avatar">👤</span>
                            <span class="author-name">${post.writer}</span>  <!-- 닉네임 표시 -->
                        </div>
                        <div class="read-more">
                            리뷰 보기 →
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${empty blogList}">
        <div class="empty-state">
            <div class="empty-icon">📚</div>
            <h3>아직 작성된 리뷰가 없습니다</h3>
            <p>첫 번째 책 리뷰를 작성해보세요!</p>
            <c:if test="${not empty sessionScope.username}">
                <a href="${pageContext.request.contextPath}/blog/write.do" class="hero-btn">첫 리뷰 작성하기</a>
            </c:if>
        </div>
    </c:if>
</div>

</body>
</html>