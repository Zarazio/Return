$(document).ready(function(){
	
		// ******************************* memberList.jsp ******************************* //
		var pageForm = $("#pageForm"); 
	
		$(".member_info").on("click", function(){
			event.preventDefault();
			var user_id = $(this).attr("href");
			pageForm.attr("action","memberRead");
			pageForm.attr("method", "get");
			$("<input type='hidden' name='user_id' value='"+user_id+"'>").appendTo(pageForm);
			pageForm.submit();
		});
		
		// ******************************* memberRead.jsp ******************************* //
		var formObj = $("form[role='form']"); 
		
		$(".btn-warning").on("click", function(){
			formObj.attr("action", "./memberSet");
			formObj.append($("#page"));
			formObj.append($("#recordPage"));
			formObj.append($("#check"));
			formObj.submit();
		});
		
		$(".btn-danger").on("click", function(){
			var answer = confirm("정말로 삭제 하시겠습니까?");
			if(!answer) return false;
			formObj.attr("action", "./memberDel");
			formObj.append($("#check")); 
			formObj.submit(); // 
		});
		
		$(".btn-primary").on("click", function(){
			// self.location = "listPage?page=${criteria.page}&recordNumPerPage=${criteria.recordNumPerPage}";
			formObj.attr("action", "./memberList");
			formObj.attr("method", "get");
			formObj.append($("#page"));
			formObj.append($("#recordPage"));
			formObj.submit();
			// 작성자의 주소가 남아있음 
		});
		
		
		// ******************************* memberSet.jsp ******************************* //
		var num = /[0-9]/;
		var spText = /[\[\]{}()<>?|`~!@#$%^&*+=,.;:\"\\\'\\\s]/g;
		var en = /[A-Z]/;
		var ko = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힝]/;
		var mixtext = /[^a-z0-9]+|^([a-z]+|[0-9]+)$/; // 아이디검사 정규식
		var regEmail = /^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{2,5}$/; // 이메일 형식 정규식 test@test.com
		var regphone = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/; // 휴대폰 번호 정규식 010/1234/5678
		var texts;
		var state= true;
		var flags = new Array(5);
		for(var i = 0; i< flags.length; i++){ // 회원정보 전체확인
			flags[i] = 1;
		}
		
		// 주소창 정보 가져오기 (쿼리스트링)
		function getQuerystring(paramName) { 
			var tempUrl = window.location.search.substring(1); //url에서 처음부터 '?'까지 삭제 
			var tempArray = tempUrl.split('&'); // '&'을 기준으로 분리하기 
			
			for(var i = 0; tempArray.length; i++) { 
				var keyValuePair = tempArray[i].split('='); // '=' 을 기준으로 분리하기 
				
				if(keyValuePair[0] == paramName) { // keyValuePair[0] : 파라미터 명 
					// keyValuePair[1] : 파라미터 값 
					return keyValuePair[1]; 
				}
			}
		}
		
		// ================== 아이디 검사  ================== //
		$('#user_id').blur(function() {
			
			var uriQuery = getQuerystring("check"); // 주소 체크정보가져옴
			var check = $('#user_id').val();
			
			if(uriQuery == check) {
				$('#idconfirm').css('color','#9c27b0');
				$('#idconfirm').html("<b>사용가능한 아이디</b>");
				flags[0] = 1;
			} else {
				if (check.length > 4 && (!mixtext.test(check) || !num.test(check) && !en.test(check) && !spText.test(check))&& !check.match(" ")) {
					var query = { id : $("#user_id").val() };
				$.ajax({
					type : 'POST',
					url : 'confirm',
					data : query,
					success : function(data) { // @ResponseBody의 있는 데이터가 리턴된다. 
						if (data == 1) {
							// $('#idconfirm').css('display','block');
							$('#idconfirm').css('color','red');
							$('#idconfirm').html("<b>이미 존재하는 아이디입니다</b>");
							flags[0] = 0;
						} else {
							flags[0] = 1;
							// $('#idconfirm').css('display','block');
							$('#idconfirm').css('color','#9c27b0');
							$('#idconfirm').html("<b>사용가능한 아이디</b>");
						}
					}
				});
				} else {
					// $('#idconfirm').css('display','block');
					$('#idconfirm').css('color','red');
					$('#idconfirm').html("<b>5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.</b>");
					flags[0] = 0;
				}
			}
		});
		
		// ================== 패스워드 검사 ================== //
		$('#user_pass').blur(function() {
			var pass = $('#user_pass').val();
			var check = $('#user_id').val();
			
			if(!pass) {
				$('#passconfirm').css('color','red');
				$('#passconfirm').html("<b>비밀번호를 확인하여주십시오</b>");
				// $('#passconfirm').css('display','block');
				flags[1] = 0;
			} else if (pass.length < 4) {
				// $('#passcheck').css('display','block');
				$('#passconfirm').css('color','red');
				$('#passconfirm').html("<b>비밀번호가 4자리 이하입니다.</b>");
				// $('#passconfirm').css('display','block');
				flags[1] = 0;
			} else if (pass.match(" ")) {
				$('#passconfirm').css('color','red');
				$('#passconfirm').html("<b>비밀번호에 공백이 포함될수없습니다.</b>");
				flags[1] = 0;
			} else if(pass == check) {
				$('#passconfirm').css('color','red');
				$('#passconfirm').html("<b>비밀번호가 아이디와 같습니다.</b>");
				$('#user_pass').val("");
			} else {
				flags[1] = 1;
				$('#passconfirm').html("&nbsp;");
			}
		});
		
		// ================== 날짜 검사 (윤년검사) ================== // 
		
		// 년도 검사 
		$('#yyyy').blur(function() {
			birth();
			// $('#birth_check').css('display','block');
			$('#birth_check').html(texts);
		});
		
		// 날짜 검사
		$('#mm').change(function() {
			 birth();
			 // $('#birth_check').css('display','block');
			$('#birth_check').html(texts);
		});
		
		// 일검사  
		$('#dd').blur(function() {
			birth();
			// $('#birth_check').css('display','block');
			$('#birth_check').html(texts);
		});
		
		// ================== 현재 생일 계산 (미래) ================== // 
		function nowcheck(flag){
			var date = new Date();
			var yych = date.getFullYear();
			var mmch = date.getMonth()+1;
			var ddch = date.getDate();
				
			var ycheck = $('#yyyy').val();
			var mcheck = $('#mm').val();
			var dcheck = $('#dd').val();
			
			if(flag==true){
				// 미래에서 왔는지 체크
				if(ycheck >= yych){
					if(ycheck==yych){
						if(mcheck >= mmch){
							if(mcheck == mmch){
								if(dcheck>ddch){
									texts="<b>미래날짜</b>";
									flags[2] = 0;
								}
							} else{
								texts="<b>미래날짜</b>";
								flags[2] = 0;
							}
						}
					} else{
						texts="<b>미래날짜</b>";
						flags[2] = 0;
					}
				}
			}
		}
		
		// ================== 생일 윤년 체크 메소드 ================== // 
		function birth(){
			var ycheck = $('#yyyy').val();
			var mcheck = $('#mm').val();
			var dcheck = $('#dd').val();

			var flag=false;
			
			if(!ycheck || ycheck.match(" ")){
				texts = "<b>태어난 년도 4자리를 입력하세요</b>";
			} else{
				if(mcheck==0){
					texts =  "<b>태어난 월을 선택하세요</b>";
				} else{
					if(!dcheck){
						texts = "<b>태어난 일을 입력하세요</b>";
					} else{
						if(mcheck==2){
					        if(ycheck%4==0 && ycheck%100!=0 || ycheck%400==0){ 
					        	if(dcheck>0 && dcheck<30) {
									flags[2] = 0;
					        		texts = "&nbsp;";
					        		flag= 1;
					        	}
					        	else {
					        		texts = "<b>생년월일을 다시 확인해주세요</b>";
									flags[2] = 0;
					        	}
					        }else{
					        	if(dcheck>0 && dcheck<29){
									flags[2] = 1;
					        		texts =  "&nbsp;";
					        		flag= true;
					        	}
					        	else {
					        		texts = "<b>생년월일을 다시 확인해주세요</b>";
									flags[2] = 0; 
					        	}
					        }
					    } else if(mcheck==4 || mcheck ==6 ||mcheck==9 || mcheck==11){
					    	if(dcheck>0 && dcheck<31){
								flags[2] = 1;
					    		texts = "&nbsp;";
					    		flag= true;
					    	}
					    	else {
					    		texts = "<b>생년월일을 다시 확인해주세요</b>";
								flags[2] = 0;
					    	}
					    } else {
					    	if(dcheck>0 && dcheck<32) {
								flags[2] = 1;
					    		texts = "&nbsp;";
					    		flag= true;
					    	}
				        	else {
				        		texts = "<b>생년월일을 다시 확인해주세요</b>";
								flags[2] = 0;
				        	}
					    }
					}
				}
			}
			nowcheck(flag);
		}
		
		// ================== 휴대폰 번호 확인 ================== // 
		$('#user_phone').blur(function() {
			var phoneck = $('#user_phone').val();
			if (!regphone.test(phoneck)) {
				// $('#phone_check').css('display','block');
				$('#phone_check').css('color','red');
				$('#phone_check').html("<b>휴대폰 번호 형식을 올바르게 입력해주세요.</b>");
				flags[3] = 0;
			} else {
				// $('#phone_check').css('display','block');
				$('#phone_check').html("&nbsp;");
				flags[3] = 1;
			}
		});
		
		// ================== 이메일 확인 ================== // 
		$('#user_email').blur(function() {
			var emailck = $('#user_email').val();
			if (!regEmail.test(emailck)) {
				// $('#email_check').css('display','block');
				$('#email_check').css('color','red');
				$('#email_check').html("<b>email 형식을 올바르게 입력해주세요.</b>");
				flags[4] = 0;
			} else {
				$('#email_check').css('display','block');
				$('#email_check').html("&nbsp;");
				flags[4] = 1;
			}
		});
		
		// ================== 성별 확인 ================== //
		var gender = $('.user_gender').val();
		
		if(gender == 0){
			$('.male').css('background-color','#e5e5e5');
			$('.male').css('color','gray');
			$('.gender').append('<input type="hidden" id="m" name="user_gender" value="0">');
		}else{
			$('.female').css('background-color','#e5e5e5');
			$('.female').css('color','gray');
			$('.gender').append('<input type="hidden" id="f" name="user_gender" value="1">');
		}
		$(".male").click(function(){
			$('#m').remove();
			$('#f').remove();
			$('.female').css('background-color','white');
			$('.female').css('color','black');
			
			$('.male').css('background-color','#e5e5e5');
			$('.male').css('color','gray');
			$('.gender').append('<input type="hidden" id="m" name="user_gender" value="0">');
		});
		
		$(".female").click(function(){
			$('#m').remove();
			$('#f').remove();
			$('.male').css('background-color','white');
			$('.male').css('color','black');
			
			$('.female').css('background-color','#e5e5e5');
			$('.female').css('color','gray');
			$('.gender').append('<input type="hidden" id="f" name="user_gender" value="1">');
		});
			
		// 전송버튼
		$('#btnsub').on('click', function() {
			state = checking();
			if(state==true){
				if(confirm("정말로 수정하시겠습니까?")) {
					$('#textbr')[0].submit();
					alert("회원정보 수정이 완료되었습니다.");
				} else {
					return false;
				}
			} else {
				alert("회원정보를 다시 한번 확인해주십시오.");
			}
		});
		
		/// ================== 입력란 공백 체크 ================== // 
		function checking() {
			for(var j =0; j<flags.length;j++){
				if(flags[j]==0){
					return false;
				}
			}
			if (!$("#user_id").val() && $("#user_id").val().length < 4) { // 아이디를 입력하지 않으면 수행
				$("#user_id").focus();
				return false;
			}
			if ($("#user_id").val().length < 4) { // 아이디를 입력하지 않으면 수행
				$("#user_id").focus();
				return false;
			}
			if (!$("#user_pass").val()) { // 비밀번호를 입력하지 않으면 수행
				$("#user_pass").focus();
				return false;
			}  

			if (!$("#yyyy").val()) { 
				$("#yyyy").focus();
				return false;
			}

			if (!$("#dd").val()) { 
				$("#dd").focus();
				return false;
			}
			
			if (!$("#user_phone").val()) {
				$("#user_phone").focus();
				return false;
			}
			if(!regphone.test($("#user_phone").val())){
				$("#user_phone").focus();
				return false;
			}
			if (!$("#user_email").val()) {
				$("#user_email").focus();
				return false;
			}
			if(!regEmail.test($("#user_email").val())){
				$("#user_email").focus();
				return false;
			}
			return true;
		}
		
		var mmValue = $('.mmValue').val();
		if(mmValue <= "10"){
			mmValue = mmValue.substr(1);
		}
		
		$('.mm').val(mmValue);
	
});