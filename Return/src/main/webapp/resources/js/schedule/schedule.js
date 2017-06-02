$(document).ready(function(){
	$("#header").remove();
	$("#topBar").remove();
	$("#myModal").remove();
	$("#footer").hide();	
	
	var date = $(".dateName").html(); // 날짜 객체를 가져온다. 2017-05-30 - 2017-06-22
	var left = date.substr(0,10).split('-'); // 2017 05 30
	var right = date.substr(13, 18).split('-'); //  - 2017 06 22
	var day1 = new Date(left[0], left[1] -1 , left[2]); // 2017 (6-1) 30
	var day2 = new Date(right[0], right[1] -1 , right[2]); // 2017 (7-1) 22
	var diff = day2 - day1; 
	var currDay = 24 * 60 * 60 * 1000; // 일수 (차) 구하는식 
	var cnt = parseInt(diff/currDay); // date (차) 결과값
	
	
	elements = $(".addMenu"); // ul 엘리먼트값불러온다.
		
	for(var i=0; i<=cnt; i++) {
		$("<li><a class='turn-center dayon' href='#' data-day="+(i+1)+" data-date=" + day(i) +">" + 
				"Day " + (i+1) + "</a></li>").appendTo(elements);
		initDay();
	}
	
	// 날짜와 요일을 순차적으로 구하는 함수 ///////////////////에러 에러 
	function day(i) {
		
		var date = day1.getDate() + i;
		var value = day1.setDate(date);
		
		return value;
	}
	
	function initDay() {
		$(".dayChoose").text($(".addMenu .dayon").eq(0).text());
	}
	
	
	$(".addMenu").on("click",".dayon", function(){
//		var old = $(this).attr("data-day");
		$(".dayChoose").text($(this).text());
	});
	
	
	
});