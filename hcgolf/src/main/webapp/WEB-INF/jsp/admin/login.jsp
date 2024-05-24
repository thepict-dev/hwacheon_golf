<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="egovframework.breeze.common.RSA"%>
<%
	//RSA admin = new RSA();
	//admin.initRsa(request);
%>

	<!-- 
	<script src="/rsa/rsa.js"></script>
	<script src="/rsa/jsbn.js"></script>
	<script src="/rsa/prng4.js"></script>
	<script src="/rsa/rng.js"></script>
	-->
	<script>
		var msg = "${message}";
		if(msg != "" && msg != null){
			alert(msg);
		}
		
		function fn_login() {
			
			// rsa 암호화
			//var rsa = new RSAKey();
			//rsa.setPublic($('#RSAModulus').val(),$('#RSAExponent').val());
			
			if ($("#id").val() == "") {
				alert("아이디를 입력하세요.");
				$("#id").focus();
				return false;
			} else {
				//$("#adminId").val(rsa.encrypt($("#id").val()));
				$("#adminId").val($("#id").val());
			}
			
			if ($("#pw").val() == "") {
				alert("비밀번호를 입력하세요.");
				$("#pw").focus();
				return false;
			} else {
				//$("#adminPw").val(rsa.encrypt($("#pw").val()));
				$("#adminPw").val($("#pw").val());
			}
			document.loginForm.action = "/_admin/adminLogin.do";
			document.loginForm.submit();
			
		}
	</script>

	<div class="login-wrapper">
	    <div class="login-wrap">
        	<h1><img src="/_admin/img/admin_logo.png" style="width: 360px;" alt="로고"></h1>
	        <div class="login-box">
		        <form action="#" id="entryForm" name="entryForm" method="post">
	                <div class="login-inner">
	                    <div class="form id">
	                        <img src="/_admin/img/login_id.png" alt="아이디 아이콘">
		                    <input type="text" id="id" class="tit" placeholder="아이디" onkeypress="if(event.keyCode == 13){fn_login();}">
	                    </div>
	                    <div class="form pw">
	                        <img src="/_admin/img/login_pw.png" alt="비밀번호 아이콘">
		                    <input type="password" id="pw" class="tit" placeholder="비밀번호" onkeypress="if(event.keyCode == 13){fn_login();}">
	                    </div>
		                <button type="button" class="btn" onclick="javascript:fn_login();">로그인</button>
	                </div>
	            </form>
	        </div>
	        <p class="copyright">Copyright 2017 <strong><a class="colgreen" href="https://thepict.co.kr/" target="_blank" title="새창 열림">thepict</a></strong>. All rights reserved.</p>
	    </div>
	</div>
	
	<form action="#" id="loginForm" name="loginForm" method="post">
		<%-- 
		<input type="hidden" id="RSAModulus" value="${RSAModulus}"/>
		<input type="hidden" id="RSAExponent" value="${RSAExponent}"/>
		--%>
		<input type="hidden" name="adminId" id="adminId" value="">
		<input type="hidden" name="adminPw" id="adminPw" value="">
	</form>