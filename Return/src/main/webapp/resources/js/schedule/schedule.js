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
	
	// 일정의 날들을 순차적으로 실행
	for(var i=0; i<=cnt; i++) {
		$("<li><a class='turn-center dayon' href='#' data-cnt="+(i+1)+" data-nal=" + nal(i) + 
				" date-day=" + day(i) + ">Day " + (i+1) + "</a></li>").appendTo(elements);
	}
	initDay(); 
	
	
	$(".addMenu").on("click",".dayon", function(){
		// var old = $(this).attr("data-day");
		
		$(".dayChoose").text($(this).text() + " - " + $(this).attr("data-nal") + " " + $(this).attr("date-day"));
	});
	
	
	function initDay() { // 기본값 날짜
		
		$(".dayChoose").text(
			$(".addMenu .dayon").eq(0).text() + " - " + 
			$(".addMenu .dayon").eq(0).attr("data-nal") + " " + 
			$(".addMenu .dayon").eq(0).attr("date-day")
		);
		
		var local = {
				localData : $(".turn-local").text()
		};
		
		// 장소리스트 출력
		$.ajax({
			type : 'GET',
			url : 'placeList',
			data : local,
			dataType: "json",
			success : function(data) { // @ResponseBody
				var count = data.length;
				var elem = "<ul>";
				for(var i=0; i<count; i++) {
					elem += "<li><img src='http://placehold.it/150x150'>";
				}
				elem += "</ul>"
				$(".placeList").append(elem);
			}
		})
	}
	
	// 날짜와 요일을 순차적으로 구하는 함수 //
	function nal(i) {
		
		var dat = new Date(day1);
		var day = dat.getDate() + i;
		dat.setDate(day);
		dat.setMonth(dat.getMonth() + 1);
		
		var nal = "";
		
		if(dat.getMonth() < 10 && dat.getDate() < 10) {
			
			nal = dat.getFullYear() + "-0" + dat.getMonth() + "-0" + dat.getDate();
			
		} else if(dat.getMonth() < 10) {
			
			nal = dat.getFullYear() + "-0" + dat.getMonth() + "-" + dat.getDate();
			
		} else if (dat.getDate() < 10) {
			
			nal = dat.getFullYear() + "-" + dat.getMonth() + "-0" + dat.getDate();
			
		}
		
		return nal;
		
	}
	
	function day(i) { // 요일을 구하는 함수
		
		var dat = new Date(day1);
		var day = dat.getDate() + i;
		dat.setDate(day);
		
		var weekday = new Array(7);
	    weekday[0]="(일)";
	    weekday[1]="(월)";
	    weekday[2]="(화)";
	    weekday[3]="(수)";
	    weekday[4]="(목)";
	    weekday[5]="(금)";
	    weekday[6]="(토)";

	    return weekday[dat.getDay()]; 
	}
	
});