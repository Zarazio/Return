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
	
	var planDay = nal(0) ; // 계획 날짜 확인 용도
	var groupCode = $("#groupCode").text();
	var placeCode ; //place_code 받는 변수
	
	
	elements = $(".addMenu"); // ul 엘리먼트값불러온다.
	
	// 일정의 날들을 순차적으로 실행
	for(var i=0; i<=cnt; i++) {
		$("<li><a class='turn-center dayon' href='#' data-cnt="+(i+1)+" data-nal=" + nal(i) + 
				" date-day=" + day(i) + ">Day " + (i+1) + "</a></li>").appendTo(elements);
	}
	initDay(); 
	
	
	$(".addMenu").on("click",".dayon", function(){
		// var old = $(this).attr("data-day");
		
		planDay = $(this).attr("data-nal");
		$(".dayChoose").text($(this).text() + " - " + $(this).attr("data-nal") + " " + $(this).attr("date-day"));
		planDayPrint();
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
					elem += "<li class='place placeCode' data-code='" + data[i].place_code + "'>"
						 + "<img src='http://placehold.it/150x150'>";

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
	
	// 요일을 구하는 함수
	function day(i) {
		
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
	
	
	
	
	// draggable
    $(".placeList").on("mouseover", ".place", function(){
       // placeCode에 현재 클릭하고 있는 애를 선택
    	placeCode = $(this);
    	
       $(".place").draggable({
          helper :  "clone"
       });
       
       $(".selectPlace").droppable({drop : function(event, ui) {
    	   
    	   $(this).append($("<li></li>").addClass("planList").html(ui.draggable.html()));
          
    	   // place 장소 코드를 받아옴
    	   placeCode = placeCode.attr("data-code");
    	   console.log(placeCode);
    	
    	   alert("placeCode : " + placeCode);
          
    	   $(".planList").css("border-bottom","1px solid black");
          
    	   planListStore(placeCode) ;
       }});
       
       
    });
    
    // draggable 한거 db에 저장
    function planListStore(placeCode){
     
    	alert(placeCode + "플랜함수");
    	alert(planDay  + "플랜함수");
    	alert(groupCode  + "플랜함수");
       
    	$.ajax({
    		type : 'POST',
    		url : 'planList',
    		data : {
    			place : placeCode,
    			plan : planDay,
    			group : groupCode
    		},
    		success : function(data){
    			alert(data + "안했구나나아아아아");
    		}, 
    		error : function() {
    			alert("에러입니다.11111");
    		}
	   })
       
       console.log("ddd");
    
    }
       
    function planDayPrint(){
    	alert(group_Code) ;
        alert(planDay);
       
        $.ajax({
        	type : 'POST',
        	url : 'planDayList',
        	data : {
        		group : group_Code,
        		plan : planDay
        	},
        	success : function(data){
        		alert(data + "안했구나나아아아아");
        	}, 
        	error : function() {
        		alert("에러입니다.22222");
        	}
        })
    }
 

	
});