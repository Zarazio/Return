<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="./resources/js/schedule/schedule.js"/></script>
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
			<div class="navbar navbar-default" role="navigation">
			
				<!--.nav-collapse -->
				<div class="navbar-collapse sidebar-navbar-collapse collapse" aria-expanded="false">

					<!-- MENU -->
					<ul class="nav navbar-nav turn-nav addMenu">
						<li class="turn-border-bottom">
							<div>
								<label class="text-center turn-font-set dateName">${scheduleDate}</label>
							</div>
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
			<div class="navbar navbar-default" role="navigation">
			
				<!--.nav-collapse -->
				<div class="navbar-collapse sidebar-navbar-collapse collapse">

					<!-- MENU -->
					<ul class="nav navbar-nav turn-nav">
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
	
	<div id="mainMenu" class="sidebar-vertical sidebar-dark turn-scroll-auto turn-c">
		<div class="sidebar-nav">
			<div class="navbar navbar-default" role="navigation">
			
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

			</div>
		</div>

	</div>	
	
	
	
	<section id="slider" class="heightfull">
		<div id="google_map" style="width:100%; height:853px;"></div>
	</section>
	
	<script>
	var map;
	
	// var lat = document.getElementById("lat").innerHTML;
	// var lng = document.getElementById("lng").innerHTML;
	
	function initialize() {
	
	  var mapOptions = { //구글 맵 옵션 설정
	      zoom : 16, //기본 확대율
	      center : new google.maps.LatLng(36.767355, 128.075525), // 지도 중앙 위치
	      scrollwheel : true, //마우스 휠로 확대 축소 사용 여부
	      mapTypeControl : true //맵 타입 컨트롤 사용 여부
	  };
	
	  map = new google.maps.Map(document.getElementById('google_map'), mapOptions); //구글 맵을 사용할 타겟
	  
	  var image = './resources/img/placeholder.png'; //마커 이미지 설정
	
	  var marker = new google.maps.Marker({ //마커 설정
	      map : map,
	      position : map.getCenter(), //마커 위치
	      icon : image //마커 이미지
	  });
	
	  google.maps.event.addDomListener(window, "resize", function() { //리사이즈에 따른 마커 위치
	      var center = map.getCenter();
	      google.maps.event.trigger(map, "resize");
	      map.setCenter(center); 
	  });
	
	}
	</script>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAF8iTF3JtdLLhprWyASWE8APl6RM6BGBQ&callback=initialize"></script>
</div>
