<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- Modal menu Controll--> 
<div id="myModal" class="modal fade" role="dialog">
  	<div class="modal-dialog">
	   
	    <!-- Modal content-->
	    <div class="modal-content">
	    	<form id="submitData" class="padding-10" onsubmit="return false;">
		      	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        	<h4 class="modal-title text-center">일정만들기</h4>
		    	</div>
			    <div class="modal-body text-center">
			        	<div class="datepicker text-center"> <!-- 캘린더를 띄위기위한 기본틀 날짜함수 -->
							<!-- range picker -->
							<p></p>
							<p>
							<label class="fancy-form fancy-form-select">
								<select name="local" class="form-control text-center">
									<option class="lt" value="">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
										최초 여행지역을 선택해주세요.
									</option>
									<option class="lt" value="서울">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										서울특별시
									</option>
									<option class="lt" value="인천">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										인천광역시
									</option>
									<option class="lt" value="대전">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										대전광역시
									</option>
									<option class="lt" value="대구">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										대구광역시
									</option>
									<option class="lt" value="울산">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										울산광역시
									</option>
									<option class="lt" value="부산">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										부산광역시
									</option>
									<option class="lt" value="광주">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										광주광역시
									</option>
									<option class="lt" value="제주">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										제주특별자치도
									</option>
									<option class="lt" value="세종">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										세종특별자치시
									</option>
									<option class="lt" value="강원도">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										강원도
									</option>
									<option class="lt" value="경기도">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										경기도
									</option>
									<option class="lt" value="충청북도">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										충청북도
									</option>
									<option class="lt" value="충청남도">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										충청남도
									</option>
									<option class="lt" value="경상북도">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										경상북도
									</option>
									<option class="lt" value="경상남도">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										경상남도
									</option>
									<option class="lt" value="전라북도">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										전라북도
									</option>
									<option class="lt" value="전라남도">
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										전라남도
									</option>
								</select>
								<i class="fancy-arrow"></i>
							</label>
							</p>
							<p>
								<input type="text" class="form-control text-center" name="travel_Title"
								placeholder="여행 제목을 작성해주세요" value="" autocomplete="off">
							</p>
							<p>
								<input type="text" class="form-control text-center rangepicker" name="scheduleDate"
								data-format="yyyy-mm-dd" placeholder="시작일자 - 종료일자 " value="" maxlength="0" autocomplete="off">
							</p>
							<p>
								<a id="addGroup" class="btn btn-primary btn-lg btn-block">그룹추가 +</a>
							</p>
						</div>
			    </div>
		      	<div class="modal-footer">
		      		<input type="submit" class="btn btn-primary margin-top-8 pull-center nextSchedule" value="다음"></input>
		        	<button type="button" class="btn btn-default margin-top-8 pull-center" data-dismiss="modal">취소</button>
		    	</div>
	    	</form>
	    </div>
	    <div class="modal-content turn-modal padding-10">
	    	<div class="modal-header">
	        	<a class="close closeGroup">&times;</a>
	        	<h4 class="modal-title text-center">친구목록</h4>
	    	</div>
	    	<div class="modal-body text-center">
	    	</div>
	    	<div class="modal-footer">
	    	</div>
	    </div>

  	</div>
</div> 
<!-- /Modal menu Controll-->

<!-- TopBar menu -->
<div id="topBar" class="fixmenu">
	<div class="container">
		<ul class="top-links list-inline pull-right"> <!-- .turn-hei-center -->
			<c:if test="${info == null}">
				<li class=""><a href="login">로그인 메뉴</a>
				<li class=""><a href="register">회원가입 메뉴</a>
			</c:if>
			<c:if test="${info == 'admin'}">
				<li class="text-welcome"><a href="#"><strong>${mem.user_id}</strong> 님.</a>
				<li class="hidden-xs"><a href="logout">로그아웃</a>
			</c:if>
			<c:if test="${info == 'user'}">
				<li class="text-welcome"><a href="#"><strong>${mem.user_id}</strong> 님.</a>
				<li class="hidden-xs"><a href="logout">로그아웃</a>
			</c:if>
		</ul>
	</div>
</div>
<!-- /TopBar menu -->

<div id="header" class="sticky clearfix">
	<div id="topNav">
		<div class="container">
			<ul class="pull-right nav nav-pills nav-second-main has-topBar">
				<li class="quick-cart">
					<a href="#">
						<span class="badge badge-aqua btn-xs badge-corner">2</span>
						<i class="fa fa-bars"></i>
					</a>
					<div class="quick-cart-box padding-10" style="display:none;"> <!-- none, block evnet -->
						<h4>My Information</h4>
						<!-- 반복문 이벤트적용 -->
						<div class="quick-cart-wrapper">
							<a href="#">
								<img src="http://placehold.it/45x45" width="45" height="45" alt="">
								<h6>
									<span>test  :  </span> good!!! a<br>안녕하세요~
								</h6>
							</a>
						</div>
						<div class="quick-cart-wrapper">
							<a href="#">
								<img src="http://placehold.it/45x45" width="45" height="45" alt="">
								<h6>
									<span>test  :  </span> 여행의시작
									<br>재밌는여행 
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
							<li><a href="#">Log</a> <!-- 페이지 이동 -->
							<li><a href="login">일정만들기</a>
							<li><a href="placeInfo">장소</a>
							<li><a href="#">Book</a>
							<li><a href="comuList">커뮤니티</a>
						</c:if>
						<c:if test="${info == 'admin'}">
							<li><a href="memberList">회원정보 관리</a>
							<li><a href="upload">장소 등록하기</a>
							<li><a href="uploadList">장소 리스트</a>
							<li><a href="#">기타 관리</a>
						</c:if>
						<c:if test="${info == 'user'}">
							<li><a href="#">Log</a></li>
							<li><a id="modals" data-toggle="modal" data-target="#myModal">일정만들기</a></li>
							<li><a href="placeInfo">장소</a></li>
							<li><a href="#">Book</a></li>
							<li><a href="comuList">커뮤니티</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

