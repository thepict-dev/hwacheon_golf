<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.breeze.admin.service.AdminVO"%>
<%
	// 관리자 로그인 세션
	AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
%>




		<%
			if(user != null){
		%>
			<div class="bottom-wrap on">
	        	<p class="copyright">Copyright 2017 <strong><a class="colgreen" href="https://thepict.co.kr/" target="_blank" title="새창 열림">thepict</a></strong>. All rights reserved.</p>
			</div>
		<%
			}
		%>
		</div>