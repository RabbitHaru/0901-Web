<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>WeatherKorea - 한국날씨</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/theme.css">
</head>
<body>
    <!-- 헤더 (네비게이션) -->
    <header th:replace="~{fragments/header :: header}"></header>
    
    <!-- 히어로 섹션 -->
    <section class="hero">
        <div class="container">
            <h1>한국에서 가장 정확한 날씨 정보</h1>
            <p>현재 서울 날씨: <span id="current-weather">☀️ 23°C 맑음</span></p>
            <div class="cta-buttons">
                <a href="/weather" class="btn btn-primary">지금 날씨 보기</a>
                <a href="/fashion" class="btn btn-secondary">옷차림 추천 받기</a>
            </div>
        </div>
    </section>
    
    <!-- 주요 기능 소개 -->
    <section class="features">
        <div class="container">
            <div class="feature-grid">
                <!-- 각 기능 카드 -->
            </div>
        </div>
    </section>
    
    <!-- 푸터 -->
    <footer th:replace="~{fragments/footer :: footer}"></footer>
    
    <script src="/js/main.js"></script>
</body>
</html>