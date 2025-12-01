<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title> MYPAGE | 투어리스트인투어 </title>
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="css/common.css">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/common.js"></script>  
<script src="js/jquery.smooth-scroll.min.js"></script> 
<!--[if lte IE 9]>
    <script src="js/html5shiv.js"></script>
	<script src="js/placeholders.min.js"></script>
<![endif]-->
</head>

<body>
<ul class="skipnavi">
    <li><a href="#container">본문내용</a></li>
</ul>
<!-- wrap -->
<div id="wrap">

		<jsp:include page="header.jsp"></jsp:include>
	
	<div id="container">
		<!-- location_area -->
		<div class="location_area member">
			<div class="box_inner">
				<h2 class="tit_page">TOURIST <span class="in">in</span> TOUR</h2>
				<p class="location">MYPAGE <span class="path">/</span> 개인 정보 수정</p>
				<ul class="page_menu clear">
					<li><a href="#" class="on">개인 정보 수정</a></li>
				</ul>
			</div>
		</div>	
		<!-- //location_area -->

		<!-- bodytext_area -->
		<div class="bodytext_area box_inner">
			<!-- myinfo -->
			<dl class="myinfo">
				<dt>내 정보</dt>
				<dd>
					<!-- appForm -->
					<form action="#" class="regForm">
						<fieldset>
							<legend>내정보 입력 양식</legend>
							<ul class="reg_list">
								<li class="clear">
									<span class="tit_lbl">이름</span>
									<div class="reg_content">홍길동</div>
								</li>
								<li class="clear">
									<label class="tit_lbl">생년월일</label>
									<div class="reg_content">19910327</div>
								</li>
								<li class="clear">
									<span class="tit_lbl">성별</span>
									<div class="reg_content radio_area">
										<input type="radio" class="css-radio" id="mmm_lbl" name="gender" checked><label for="mmm_lbl">남</label>
										<input type="radio" class="css-radio" id="www_lbl" name="gender"><label for="www_lbl">여</label>
									</div>
								</li>
								<li class="clear">
									<span class="tit_lbl">주소</span>
									<div class="reg_content">서울시 종로구 인사동 111-35 오리엔트파빌리온 1024호</div>
								</li>
								<li class="clear">
									<span class="tit_lbl">핸드폰</span>
									<div class="reg_content">010-1234-1234</div>
								</li>
								<li class="clear">
									<span class="tit_lbl">SMS 수신동의</span>
									<div class="reg_content radio_area">
										<input type="radio" class="css-radio" id="smsyes_lbl" name="smsyesno" checked><label for="smsyes_lbl">허용</label>
										<input type="radio" class="css-radio" id="smsno_lbl" name="smsyesno"><label for="smsno_lbl">거부</label>
										<p class="info_line">(고객님의 편의를 위해 알림, 공지사항, 이벤트 등의 내용을 제공하고자 합니다.)</p>
									</div>
								</li>
								<li class="clear">
									<span class="tit_lbl">이메일</span>
									<div class="reg_content">hong2018@naver.com</div>
								</li>
								<li class="clear">
									<span class="tit_lbl">이메일 수신동의</span>
									<div class="reg_content radio_area">
										<input type="radio" class="css-radio" id="emailyes_lbl" name="emailyesno" checked><label for="emailyes_lbl">허용</label>
										<input type="radio" class="css-radio" id="emailno_lbl" name="emailyesno"><label for="emailno_lbl">거부</label>
										<p class="info_line">(고객님의 편의를 위해 알림, 공지사항, 이벤트 등의 내용을 제공하고자 합니다.)</p>
									</div>
								</li>
							</ul>
							<p class="btn_line"><a href="#" class="btn_baseColor">등록</a></p>	
						</fieldset>
					</form>
					<!-- //appForm -->
				</dd>
			</dl>
			<!-- //myinfo -->			
			
		</div>
		<!-- //bodytext_area -->

	</div>
	<!-- //container -->

	<jsp:include page="footer.jsp"></jsp:include>

</div>
<!-- //wrap -->
	<jsp:include page="quicklink.jsp"></jsp:include>


</body>
</html>
