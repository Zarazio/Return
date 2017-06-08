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
	
	var planDay = nal(0) ;// 계획 날짜 확인 용도
	var planDayCheck ; // 첫째날인지 확인하는 용도
	var groupCode = $("#groupCode").text(); //schdulePageA groupCode
	var placeCode; //place_code 받는 변수
	var placeCodeDB;
	
	elements = $(".addMenu"); // ul 엘리먼트값불러온다.
	
	// 일정의 날들을 순차적으로 실행
	for(var i=0; i<=cnt; i++) {
		$("<li><a class='turn-center dayon' href='#' data-cnt="+(i+1)+" data-nal=" + nal(i) + 
				" date-day=" + day(i) + ">Day " + (i+1) + "</a></li>").appendTo(elements);
		
		var liAdd = $(".liAdd");
		$("<ul></ul>").addClass("selectPlace").attr("data-nal", nal(i)).css("display","none").appendTo(liAdd);
		
	}
	initDay(); // 처음함수를 실행
	
	
	//day 클릭 할때, 
	$(".addMenu").on("click",".dayon", function(){
		// var old = $(this).attr("data-day");
	
		// day 클릭 할때마다, planDay에 현재 여행일정짜는 날짜 넣어줌
		planDay = $(this).attr("data-nal");
		
		$(".dayChoose").text($(this).text() + " - " + $(this).attr("data-nal") + " " + $(this).attr("date-day"));
		
		console.log($(this).attr("data-nal") ) ;
		
		// class selectPlace에 data-nal 값 모두 비교해서 
		$(".selectPlace").each(function(){
			$(this).css("display","none") ;
			
			var select = $(this).attr("data-nal");
			
			if(select == planDay){ // 
				$(this).css("display", "block") ;
				console.log($(this).children(".selectPlace").empty()) ;
				
				$(".selectPlace ").empty();
				planDayPrint();
			}

		});
		
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
				var elem = "<ul class='mxpnone'>";
				for(var i=0; i<count; i++) {
					elem += "<li class='place placeCode' data-code='" + data[i].place_code + "'>"
						 + "<img src='http://placehold.it/150x150'>";

				}
				elem += "</ul>"
				$(".placeList").append(elem);
			}
		})
		
		console.log($(".selectPlace"));
		
/////////////-맨 처음 schedulePageA 페이지 로드할 때, 기존의 리스트에 있던 day1 출력 -//////////////////////////////////////////////////////////////		
		$(".selectPlace").each(function(){
			
			var selectThis = $(this) ;
			var select = $(this).attr("data-nal") ;
			
			if(select == planDay){
				$(this).css("display", "block") ;
				
				$.ajax({
					type : "POST" ,
					url :  'planDayList',
					data : {
						group : groupCode,
		        		plan : planDay
					},
					dataType : "json",
					success : function(data){
						for(var i=0 ; i<data.length ; i++){
							$("<li data-code="+data[i].place_code+"></li>").addClass("planList").append("<img src='http://placehold.it/150x150'>").css("border-bottom","1px solid black").appendTo(selectThis) ;
						}
					}
				
				})
			}

		})
//////////////////////////////////////////////////////////////////////////////////////////		
		
		
		
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
    	  accept : ".place",
          helper :  "clone",
          connectToSortable : ".selectPlace"
       });
       
    });
	
	//sortable 장소리스트를 장소플랜쪽으로 이동가능  	
    
    $(".placeList").on("mouseover", ".place", function(){
    	$(".selectPlace").sortable({ over : function(event, ui){
    	
    		console.log(ui.item);
       		 
       		placeCode = ui.item.attr("data-code") ;
       		 
       		console.log("placeCodeDB : " + ui.item.attr("data-code"));
       		 
       		ui.item.removeClass() ;
       		ui.item.addClass("planList").css("border-bottom","1px solid black") ;
       		
       		
	   		$.ajax({
	   			url : "planPlaceCodCheck",
	   			type : "POST" ,
	   			data : {
	    			group : groupCode ,
	    			plan : planDay ,
	    			place : placeCode	
	    		},
	    		dataType :"json",
	    		asnyc : false ,
	    		success: function(data){
					console.log("countdddd : " + data);
					data = Number(data);
	    				if(data > 0){
	    					console.log("plac0Codd : " + data) ;
	    					//ui.item.attr("data-code", placeCode+"-"+ Number(data));
	    					//placeCode= ui.item.attr("data-code");       					
	    				} 	
	    				
	    		}
	   		 })
       		 
       	 }
       	 
       });
    	
    	
    	
    
    });
    
    function planListStore(){
  	     
    	alert(placeCode + "플랜함수");
//    	alert(planDay  + "플랜함수");
//    	alert(groupCode  + "플랜함수");
       
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
    
    
    function planPriority(){
    	var count ;
    	
    	$(".selectPlace li").each(function(){
    		console.log($(this).parent().children());
    		count = $(this).parent().children().index($(this)) + 1 ;
    		console.log("count : " + count) ;
    		
    		var array = new Array() ;
    		console.log($(this).attr("data-code"));
    		array = $(this).attr("data-code") ;
    		
    		
    		$.ajax({
    			url : "planPlacePriority",
    			type : "POST",
    			data : {
    				group_Code : groupCode ,
    				travel_Date : planDay ,
    				place_code : array
    			},
    			success: function(){
    				
    			}
    		})
    	})
    }
	       
	
    
    
 
    // draggable 한거 db에 저장
   
     
    //plan Day에 List 출력~
    function planDayPrint(){
    	
			$(".selectPlace").each(function(){
						
						var selectThis = $(this) ;
						var select = $(this).attr("data-nal") ;
						
						if(select == planDay){
							$(this).css("display", "block") ;
							
							$.ajax({
								type : "POST" ,
								url :  'planDayList',
								data : {
									group : groupCode,
					        		plan : planDay
								},
								dataType : "json",
								success : function(data){
									
									for(var i=0 ; i<data.length ; i++){
										
										$("<li data-code="+data[i].place_code+"></li>").addClass("planList").addClass("priority").append("<img src='http://placehold.it/150x150'><span>"+i+"</span>").css("border-bottom","1px solid black").appendTo(selectThis) ;
									}
								}
							
							})
						}
			
					})
					
			    }
 

	
});