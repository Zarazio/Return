<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="fixmenu turn-bg-dark">
	<div class="container">
		<ul class="top-links list-inline pull-right turn-hei-center">
			<c:if test="${info == null}">
				<li class=""><a href="login">로그인 메뉴</a>
				<li class=""><a href="register">회원가입 메뉴</a>
			</c:if>
			<c:if test="${info == 'admin'}">
				<li class="text-welcome hidden-xs"><strong>${mem.user_id}님</strong> 환영합니다.
				<li class="hidden-xs"><a href="logout">로그아웃</a>
			</c:if>
			<c:if test="${info == 'user'}">
				<li class="text-welcome hidden-xs"><strong>${mem.user_id}님</strong> 환영합니다.
				<li class="hidden-xs"><a href="logout">로그아웃</a>
			</c:if>
		</ul>
	</div>
</div>
<div id="header" class="sticky clearfix">
	<div id="topNav">
		<div class="container">
			<ul class="pull-right nav nav-pills nav-second-main has-topBar">
				<li class="quick-cart">
					<a href="#">
						<span class="badge badge-aqua btn-xs badge-corner">2</span>
						<i class="fa fa-bars"></i>
					</a>
					<div class="quick-cart-box padding-10" style="display:none;"> <!-- none, block 이벤트 적용 -->
						<h4>My Information</h4>
						<!-- 반복문 이벤트적용 -->
						<div class="quick-cart-wrapper">
							<a href="#">
								<img src="http://placehold.it/45x45" width="45" height="45" alt="">
								<h6>
									<span>test  :  </span> good!!! a<br>ddddddㅁㄴㅇㅁㄴㅇㅁㄴㅇ
								</h6>
							</a>
						</div>
						<div class="quick-cart-wrapper">
							<a href="#">
								<img src="http://placehold.it/45x45" width="45" height="45" alt="">
								<h6>
									<span>test  :  </span> good!!! a
									<br>ddddddㅁㄴㅇㅁㄴㅇㄴㅁㅇ
									<br>asdasdsadsadasdsadasd
								</h6>
							</a>
						</div>
					</div>
				</li>
			</ul>
			<a class="logo pull-left" href="main">
				<img src="http://placehold.it/200x80" />
			</a>
			<div class="navbar-collapse pull-right nav-main-collapse collapse in">
				<div class="nav-main">
					<ul id="topMain" class="nav nav-pills nav-main has-topBar">
						<c:if test="${info == null}">
							<li><a href="#">일정등록하기</a> <!-- 페이지 이동 -->
							<li><a href="#">장소정보</a>
							<li><a href="#">커뮤니케이션</a>
							<li><a href="#">Books-shop</a>
						</c:if>
						<c:if test="${info == 'admin'}">
							<li><a href="memberList">회원정보 관리</a>
							<li><a href="upload">장소 등록하기</a>
							<li><a href="uploadList">장소 리스트</a>
							<li><a href="#">기타 관리</a>
						</c:if>
						<c:if test="${info == 'user'}">
							<li><a href="#">일정등록하기</a>
							<li><a href="#">장소정보</a>
							<li><a href="#">커뮤니케이션</a>
							<li><a href="#">Books-shop</a>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

