$(document).ready(function(){
	
	var count = 0;
	
	// 일정등록버튼클릭했을때 
	$("#modals").click(function(){
		
		$(".turn-modal").css("display","none");
	});
	
	// 친구목록의 그룹창을 닫았을시
	$(".closeGroup").click(function(){
		
		$(".turn-modal").css("display","none");
		count = 0;
	});
	
	// 그룹추가 버튼을 클릭했을시
	$("#addGroup").click(function(){
		if(count == 0) {
			$(".turn-modal").css("display","block");
			count++;
		} else {
			$(".turn-modal").css("display","none");
			count = 0;
		}
	});
	
	// 모달버튼이 사라질때 이벤트 발생함수
	$('#myModal').on('hidden.bs.modal', function (e) {
		  count = 0;
	});
	
	// =========== 페이지 이동 함수 =============== // 
	
	$(".nextSchedule").click(function(){
		var nexton = confirm("다음으로 ");
		if(nexton) {
			$('#submitData').attr('method','post');
			$('#submitData').attr('action','scheduleSet');
			$('#submitData')[0].submit();
		}
	});
	
	
});