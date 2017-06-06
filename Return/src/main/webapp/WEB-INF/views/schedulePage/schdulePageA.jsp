<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="./resources/js/schedule/schedule.js"/></script>
<script src="./resources/js/jquery-ui-1.10.4.custom.js" /></script>
<script src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=Ao5To6qq05xL8fmhSOTK&callback=initMap"></script>
<div id="header" class="clearfix bg-dark">
	<!-- TOP NAV -->
	<header id="topNav">
		<div class="full-container">

			<!-- Logo -->
			<a class="logo pull-left" href="main">
				<img src="http://placehold.it/200x80" alt="" />
			</a>
			
			<div class="navbar-collapse pull-right nav-main-collapse collapse">
				<nav class="nav-main">

					<ul id="topMain" class="nav nav-pills nav-main">
						<li><!-- Smart Cost -->
							<a href="#">Smart Cost</a>
						</li>
						<li><!-- 준비물 -->
							<a href="#">준비물</a>
						</li>
						<li><!-- 채팅 -->
							<a href="#">채팅</a>
						</li>
						<li><!-- 친구추가 -->
							<a href="#">친구추가</a>
						</li>
						<li><!-- 임시저장 -->
							<a href="#">임시저장</a>
						</li>
						<li class="turn-margin-top-28"><!-- 완료 -->
							<button class="btn btn-teal margin-left-10">  완료  </button>
						</li>

					</ul>
				</nav>
			</div>

		</div>
	</header>
	<!-- /Top Nav -->
</div>

<div class="wrapper">
	<div id="mainMenu" class="sidebar-vertical sidebar-dark turn-scroll-auto turn-a">
		<div class="sidebar-nav">
			<div class="navbar navbar-default turn-bottom-zero" role="navigation">
			
				<!--.nav-collapse -->
				<div class="navbar-collapse sidebar-navbar-collapse collapse" aria-expanded="false">

					<!-- MENU -->
					<ul class="nav navbar-nav turn-nav addMenu">
						<li class="turn-border-bottom">
							<div>
								<label class="text-center turn-font-set dateName">${scheduleDate}</label>
							</div>
							<div id="groupCode" style="display:none">${groupCode}</div>
						</li>
					</ul>
					<!-- /MENU -->
					
				</div>
				<!--/.nav-collapse -->

			</div>
		</div>

		<!-- Social Icons -->
		<div class="social-icons hidden-md hidden-sm hidden-xs text-center">
			&nbsp;
			<a href="#" class="social-icon social-icon-sm social-icon-light social-facebook" data-toggle="tooltip" data-placement="top" title="Facebook">
				<i class="icon-facebook"></i>
				<i class="icon-facebook"></i>
			</a>
			<a href="#" class="social-icon social-icon-sm social-icon-light social-twitter" data-toggle="tooltip" data-placement="top" title="Twitter">
				<i class="icon-twitter"></i>
				<i class="icon-twitter"></i>
			</a>
			<br/>
			<a href="#" class="social-icon social-icon-sm social-icon-light social-pinterest" data-toggle="tooltip" data-placement="top" title="Pinterest">
				<i class="icon-pinterest"></i>
				<i class="icon-pinterest"></i>
			</a>
			<a href="#" class="social-icon social-icon-sm social-icon-light social-linkedin" data-toggle="tooltip" data-placement="top" title="Linkedin">
				<i class="icon-linkedin"></i>
				<i class="icon-linkedin"></i>
			</a>
			<a href="#" class="social-icon social-icon-sm social-icon-light social-gplus" data-toggle="tooltip" data-placement="top" title="Google Plus">
				<i class="icon-gplus"></i>
				<i class="icon-gplus"></i>
			</a>
		</div>
		<!-- /Social Icons -->

		<!-- Paragraph -->
		<p class="text-center hidden-xs">
			darianekey@gmail.com <br />
			(+10) 123 4567 8910
		</p>
		<!-- /Paragraph -->

	</div>	
	
	
	<div id="mainMenu" class="sidebar-vertical sidebar-dark turn-scroll-auto turn-b">
		<div class="sidebar-nav">
			<div class="navbar navbar-default turn-bottom-zero" role="navigation">
			
				<!--.nav-collapse -->
				<div class="navbar-collapse sidebar-navbar-collapse collapse">

					<!-- MENU -->
					<ul class="nav navbar-nav turn-nav selectPlace" style="height:853px;">
						<li class="turn-border-bottom">
							<div>
								<label class="text-center turn-font-set dayChoose"></label>
							</div>
						</li>
					</ul>
					<!-- /MENU -->
					
				</div>
				<!--/.nav-collapse -->

			</div>
			
		</div>

	</div>	
	
	<div id="mainMenu" class="sidebar-vertical sidebar-dark turn-c">
		<div class="sidebar-nav turn-heightful">
			<div class="navbar navbar-default turn-heightful turn-bottom-zero" role="navigation">
				
				<div class="turn-local">${local}</div>
				
				<!--.nav-collapse -->
				<div class="navbar-collapse sidebar-navbar-collapse collapse turn-border">

						<!-- INLINE SEARCH -->
							<div class="inline-search clearfix turn-tb-16 margin-left-15 margin-right-15">
								<form action="" method="get" class="widget_search">
									<input type="search" placeholder="장소검색..." id="s" name="s" class="serch-input">
									<button type="submit">
										<i class="fa fa-search"></i>
									</button>
								</form>
							</div>
						<!-- /INLINE SEARCH -->
					
				</div>
				<!--/.nav-collapse -->
				<div>
				장소 이미지 카데고리 
				</div>
				
				<div class="placeList turn-scroll-auto" style="height:725px;"></div>

			</div>
		</div>

	</div>	
	
	
	
	<section id="slider" class="heightfull">
		<div id="mapArea" style="width:100%; height:853px;"></div>
	</section>
	
	 <script>
		 var map = null;
		 var marker = null;
		 var cityhall = null;
		
		 function initMap() {
		    cityhall = new naver.maps.LatLng(37.338337, 127.110111);
		
		    map = new naver.maps.Map('mapArea', {
		       center : cityhall,
		       zoom : 10
		    });
		
		    marker = new naver.maps.Marker({
		       map : map,
		       position : cityhall
		    });
		
		 };
		
	</script>
	
</div>
