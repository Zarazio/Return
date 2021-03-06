<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="<c:url value="./resources/js/member/member.js"/>" ></script>
<c:if test="${info == 'admin'}">
<div class="container">
	<form method="post" id="textbr" class="nomargin sky-form boxed" onsubmit="return false">
		<fieldset class="nomargin">
			<label class="input margin-bottom-10">아이디
				<input id="user_id" name="user_id" type="text" size="20"
				maxlength="40" value="${member.user_id}">
			</label>
			<div id="idconfirm" class="margin-bottom-10">&nbsp;</div>
			
			<label class="input margin-bottom-10">비밀번호
				<input id="user_pass" name="user_pass" type="password" size="20"
				maxlength="20" value="">
			</label>
			<div id="passconfirm" class="margin-bottom-10">&nbsp;</div>
			
			<div class="row margin-bottom-10">
				<div class="col-md-5">
					<label class="input">
						<input id="yyyy" name="yyyy" type="text" size="3"
						maxlength="4" value="${member.yyyy}">
					</label>
				</div>
				<div class="col-md-2">
					<label class="margin-bottom-6 margin-top-0 fancy-form fancy-form-select">
						<select id="mm" name="mm" class="form-control select mm">
							<option value="0">월</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>
						<i class="fancy-arrow"></i>
					</label>
				</div>
				<div class="col-md-5">
					<label class="input">
						<input id="dd" name="dd" type="text" size="2"
						maxlength="2" value="${member.dd}">
					</label>
				</div>
			</div>
			<div id="birth_check" class="margin-bottom-10">&nbsp;</div>
			
			<div class="btn-group margin-bottom-10 gender">
				<button class="btn btn-default male">남</button>
				<button class="btn btn-default female">여</button>
			</div>
			<div class="margin-bottom-10">&nbsp;</div>
			
			<label class="input margin-bottom-10">
				<input id="user_phone" name="user_phone" type="text" size="11"
		       	maxlength="11" value="${member.user_phone}">	
			</label>
		    <div id="phone_check" class="margin-bottom-10">&nbsp;</div>   
		    
		    <label class="input margin-bottom-10">
		    	 <input id="user_email" name="user_email" type="text" size="20" 
		       	maxlength="40" value="${member.user_email}">
		    </label>
		    <div id="email_check" class="margin-bottom-10">&nbsp;</div>
		    <div style="text-align:center">
		    	<input type="submit" value="수정하기" id="btnsub">
		    </div>
		</fieldset>
	</form>
	<input type="hidden" class="user_gender" name="user_gender" value="${member.user_gender}">
	<input type="hidden" class="mmValue" value="${member.mm}">
</div>
</c:if>
<c:if test="${info == null || info == 'user'}">
잘못된접근입니다.
</c:if>
