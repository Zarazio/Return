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
	var priority = 0; // priority 변수 
	var priority01 = 0;
	
	
	
	elements = $(".addMenu"); // ul 엘리먼트값불러온다.
	
	// 일정의 날들을 순차적으로 실행
	for(var i=0; i<=cnt; i++) {
		$("<li><a class='turn-center dayon' href='#' data-cnt="+(i+1)+" data-nal=" + nal(i) + 
				" date-day=" + day(i) + ">Day " + (i+1) + "</a></li>").appendTo(elements);
		var liAdd = $(".liAdd");
		$("<div></div>")
			.addClass("turn-scroll-auto")
			.addClass("selectPlace")
			.attr("data-nal", nal(i))
			.css("display","none")
			.css("border-top","1px solid rgba(255, 255, 255, 0.1)")
		.appendTo(liAdd);
		
	}
	initDay(); // 처음함수를 실행
	
	
	//day 클릭 할때, 
	$(".addMenu").on("click",".dayon", function(){
		// var old = $(this).attr("data-day");
		priority = 0 ;
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
	
	
	function initDay() { // 함수실행 기본값 날짜
		
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
				var elem = "<div class='mxpnone'>";
				for(var i=0; i<count; i++) {
					elem += "<div class='place placeCode' data-code='" + data[i].place_code + "'>"
						 + "<img src='http://placehold.it/150x150'></div>";

				}
				elem += "</div>"
				$(".placeList").append(elem);
			}
		})
		
		console.log($(".selectPlace"));
		
		/// - 맨 처음 schedulePageA 페이지 로드할 때, 기존의 리스트에 있던 day1 출력 - ///
		$(".selectPlace").each(function(){
	         
	         var selectThis = $(this) ;
	         var select = $(this).attr("data-nal");
	         
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
	                	  $("<div data-code="+data[i].place_code+" data-pri="+ data[i].travel_Priority +"></div>")
	                        .addClass("planList")
	                        .append("<img src='http://placehold.it/150x150'>")
	                        .append("<div class='planPlaceDelete' data-code='"+data[i].place_code+"'><a href='#'>삭제</a></div>") 
	                        .css("border-bottom","1px solid black")
	                     .appendTo(selectThis) ;
	                  }
	               }
	            
	            })
	         }

	    });
		/// ------------------------------------------------------ /// 		
		
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
	
	
	// 장소 리스트 선택할때, draggable 선택
    $(".placeList").on("mouseover", ".place", function(){
        
        placeCode = $(this).attr("data-code") ;
        
        $(".place").draggable({
              accept : ".place",
             helper :  "clone",
             cursor: "crosshair",
             connectToSortable : ".selectPlace"
            
        });
        
     });
    
    $(".selectPlace").sortable({
    
        over : function(event, ui){
  
         placeCode = ui.item.attr("data-code") ;
         
         if(ui.item.hasClass("place")){
  
       	  planPlaceCheck() ;
       	  

       	  priority01 = ui.item;
       	  
          ui.item.attr("data-pri", priority);
       	  ui.item.append("<div class='planPlaceDelete'><a href='#'>삭제</a></div>");
       	  ui.item.removeClass() ;
       	  ui.item.addClass("planList").css("border-bottom","1px solid black");
         }

      },
      receive : function(event ,ui){
         // selectPlace의 draggable를 했을 때, 이벤트 발생
         console.log("receive : ") ;
         
         
         planPriority() ;
         planRealTimePriority() ;
         priority01 = priority01.attr("data-pri");
         console.log("priority : " + priority  + " priority01 : " + priority01);
         if(priority != priority01)
         {
       	  priority = priority01 ;
         }
         planListStore($(this));
        
         
         
         
      },
      update : function(){
         // 위치가 바꼈을 때, 이벤트 발생
         console.log("update : ") ;
         
         planPriority();
         planRealTimePriority() ;
      },
      cursor: "crosshair"
     
   });
    
    
    // 변화된  data-pri 값을 나타내줌
    function planRealTimePriority(){
    	
    	var count = $(".selectPlace > div").parent().children().index($(this)) + 1 ;
    	var countCheck = 1 ;
    	
    	$(".selectPlace > div").each(function(){

            $(this).attr("data-pri",countCheck) ;
            
            countCheck++;
            
           
        })
    	
    	
    }
    
    //day1~.. 에 계획된 장소가 몇개인지
    function planPlaceCheck(){
    	
    	
    	$.ajax({
    		type : 'POST',
    		url : 'planPlaceCodCheck',
    		data : {
    			
    			plan : planDay,
    			group : groupCode 
    		},
    		async : false,
    		success : function(data){
    			
    			priority = data+1 ;
    			
    		}, 
    		error : function() {
    			alert("에러입니다.11111");
    		}
	   })
    	
    }
    
    // draggable 한거 db에 저장
    function planListStore(){
  	     
    	alert("typeof : " + typeof(priority));
        
    	$.ajax({
    		type : 'POST',
    		url : 'planList',
    		data : {
    			place : placeCode,
    			plan : planDay,
    			group : groupCode,
    			priority : priority 
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
    
    // 변경된 data-pri 를 데이터베이스에 넣어줌
    function planPriority(){
        var count=0;
        var place;
        var updatePriority ;
        var array = new Array();
        var array01 = new Array();
        $(".selectPlace > div").each(function(){
 
           count = $(this).parent().children().index($(this)) + 1 ;
      
           place = $(this).attr("data-code") ;
           updatePriority = $(this).attr("data-pri") ;
           
           var item = place;
           var items = updatePriority;
           
           array.push(item);
           array01.push(items) ;
     
       })
      
        
       $.ajax({
	              url : "planPlacePriority",
	              type : "POST",
	              traditional: true,
	              data : {
	                 
	                 array :  array ,
	                 array01 : array01 ,
	                 group : groupCode ,
	                 plan : planDay ,
	                 count : count
	                  
	              },
	              success: function(data){
	            	  console.log(data);
	                  console.log("priority : 확인")
	                
	              }
            })
    }
    
   
   
    //plan Day에 List 출력~
    function planDayPrint(){
        
        $(".selectPlace").each(function(){
                 
           var selectThis = $(this) ;
           var select = $(this).attr("data-nal");
           
           if(select == planDay){
              $(this).css("display", "block");
              
              $.ajax({
                 type : "POST" ,
                 url :  'planDayList',
                 data : {
                    group : groupCode,
                      plan : planDay
                 },
                 dataType : "json",
                 success : function(data){    
                	 
                    for(var i=0 ; i<data.length; i++){
                       $("<div data-code="+data[i].place_code+" data-pri="+ data[i].travel_Priority +"></div>")
                       .addClass("planList")
                       .addClass("priority")
                       .append("<img src='http://placehold.it/150x150'><span>"+i+"</span>")
                       .append("<div class='planPlaceDelete' data-code='"+data[i].place_code+"'><a href='#'>삭제</a></div>") 
                       .css("border-bottom","1px solid black").appendTo(selectThis) ;
                    }
                    
                 }
              
              })
           }
        
        });
              
    }
    
    //삭제 버튼 눌렀을 때, planList에서 삭제
    $(".selectPlace").on("click", ".planPlaceDelete" ,function(){
       //event.preventDefault(); // 이벤트를 막아준다. 404error 막아줌
       var planList = $(this) ;
       var place = $(this).parent().attr("data-code") ;
       
       
       
       console.log("place : " + $(this).parent());
       alert("ddddd");
       $.ajax({
          url : "planPlaceDelete" ,
          type : "POST" ,
          data : {
             place : place ,
             group : groupCode ,
             plan : planDay
          },
          success : function(){
             planList.parent().remove();
             planPriority();
             planRealTimePriority();
             
             
          },
          error : function(){
             alert("planPlaceDelete : error") ;
          }
          
       })
       
    });
    
});